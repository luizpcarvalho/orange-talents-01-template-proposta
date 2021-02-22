package br.com.zup.proposta.biometria;

import br.com.zup.proposta.proposta.cartao.Cartao;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/biometrias")
public class BiometriaController {

    @PersistenceContext
    private EntityManager entityManager;

    @PostMapping("/{idCartao}")
    @Transactional
    public ResponseEntity<?> cadastrar(@RequestBody @Valid NovaBiometriaRequest request, @PathVariable Long idCartao,
                                    UriComponentsBuilder uriBuilder) {

        Cartao cartao = entityManager.find(Cartao.class, idCartao);
        if(cartao != null) {
            Biometria biometria = request.toModel(cartao);
            cartao.associaBiometria(biometria);
            entityManager.persist(cartao);

            URI location = uriBuilder.path("/biometrias/{id}").buildAndExpand(biometria.getId()).toUri();
            return ResponseEntity.ok().body(biometria.toString());
        } else {
            return ResponseEntity.notFound().build();
        }

    }

}
