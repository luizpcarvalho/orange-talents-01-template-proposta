package br.com.zup.proposta.novaproposta;

import br.com.zup.proposta.config.handler.ErrosResponse;
import br.com.zup.proposta.feign.AnaliseCliente;
import feign.FeignException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/propostas")
public class PropostaController {

    private PropostaRepository propostaRepository;
    private AnaliseCliente analiseCliente;

    public PropostaController(PropostaRepository propostaRepository, AnaliseCliente analiseCliente) {
        this.propostaRepository = propostaRepository;
        this.analiseCliente = analiseCliente;
    }

    @PostMapping
    @Transactional
    public ResponseEntity<?> criar(@RequestBody @Valid NovaPropostaRequest request, UriComponentsBuilder uriBuilder) {
        if(propostaRepository.existsByDocumento(request.getDocumento())){
            return ResponseEntity.unprocessableEntity().body(new ErrosResponse("documento",
                    "O solicitante já realizou a proposta."));
        }
        Proposta proposta = request.toModel();
        propostaRepository.save(proposta);

        try {
            AnaliseCliente.ConsultaStatusRequest analiseRequest = new AnaliseCliente.ConsultaStatusRequest(proposta.getDocumento(),
                    proposta.getNome(), proposta.getId());
            AnaliseCliente.ConsultaAnaliseResponse analiseResponse = analiseCliente.consulta(analiseRequest);

            proposta.atualizaStatus(analiseResponse.getResultadoSolicitacao());
        } catch (FeignException.UnprocessableEntity e) {
            proposta.atualizaStatus("COM_RESTRICAO");
        }

        propostaRepository.save(proposta);

        URI location = uriBuilder.path("/propostas/{id}").buildAndExpand(proposta.getId()).toUri();
        return ResponseEntity.created(location).body(proposta.toString());
    }

}
