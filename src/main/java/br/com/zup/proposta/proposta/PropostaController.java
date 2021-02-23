package br.com.zup.proposta.proposta;

import br.com.zup.proposta.config.handler.ErrosResponse;
import br.com.zup.proposta.proposta.util.Encryptor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/propostas")
public class PropostaController {

    private PropostaRepository propostaRepository;
    private PropostaService propostaService;

    public PropostaController(PropostaRepository propostaRepository, PropostaService propostaService) {
        this.propostaRepository = propostaRepository;
        this.propostaService = propostaService;
    }

    @PostMapping
    @Transactional
    public ResponseEntity<?> criar(@RequestBody @Valid NovaPropostaRequest request, UriComponentsBuilder uriBuilder) {
        List<Proposta> list = propostaRepository.findAll();
        for (Proposta proposta : list) {
            if(proposta.getDocumento().equals(request.getDocumento())){
                return ResponseEntity.unprocessableEntity().body(new ErrosResponse("documento",
                        "O solicitante já realizou a proposta."));
            }
        }

        Proposta proposta = request.toModel();
        propostaRepository.save(proposta);

        proposta = propostaService.analisaCliente(proposta);
        propostaRepository.save(proposta);

        URI location = uriBuilder.path("/propostas/{id}").buildAndExpand(proposta.getId()).toUri();
        return ResponseEntity.created(location).body(proposta.toString());
    }

    @GetMapping("/{id}")
    public ResponseEntity<DetalhesPropostaResponse> detalhar(@PathVariable("id") Long id) {
        Optional<Proposta> proposta = propostaRepository.findById(id);
        if(proposta.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok().body(new DetalhesPropostaResponse(proposta.get()));
    }

}
