package br.com.zup.proposta.novaproposta;

import br.com.zup.proposta.config.handler.ErrosResponse;
import feign.FeignException;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("/propostas")
public class PropostaController {

    private PropostaRepository propostaRepository;
    private PropostaService propostaService;
    private Proposta propostaSalva;

    public PropostaController(PropostaRepository propostaRepository, PropostaService propostaService) {
        this.propostaRepository = propostaRepository;
        this.propostaService = propostaService;
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

        proposta = propostaService.analisaCliente(proposta);

        propostaRepository.save(proposta);

        this.propostaSalva = proposta;

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

    @Async
    @Scheduled(fixedDelayString = "${periodicidade.associa-cartao}")
    public void associaCartao() {
        if(propostaSalva != null) {
            propostaService.associaCartao(propostaSalva, propostaRepository);
            propostaSalva = null;
        }
    }

}
