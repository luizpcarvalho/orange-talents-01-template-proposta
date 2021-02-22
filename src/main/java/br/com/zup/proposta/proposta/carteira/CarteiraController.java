package br.com.zup.proposta.proposta.carteira;

import br.com.zup.proposta.feign.IncluiCarteira;
import br.com.zup.proposta.proposta.cartao.Cartao;
import feign.FeignException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/cartoes")
public class CarteiraController {

    @PersistenceContext
    private EntityManager entityManager;
    @Autowired
    private IncluiCarteira incluiCarteira;

    @PostMapping("/carteiras/{id}")
    @Transactional
    public ResponseEntity<?> incluir(@RequestBody @Valid NovaCarteiraRequest request,
                                     @PathVariable("id") Long idCartao,
                                     UriComponentsBuilder uriBuilder) {
        Cartao cartao = entityManager.find(Cartao.class, idCartao);
        if(cartao == null) {
            return ResponseEntity.notFound().build();
        }
        try {
            IncluiCarteira.ResultadoInclusaoCarteira response = incluiCarteira
                    .incui(request, cartao.getNumero());
            Carteira carteira = request.toModel(response.getId(), cartao);
            cartao.associaCarteira(carteira);
            entityManager.merge(cartao);

            URI uri = uriBuilder.path("/cartoes/carteiras/{id}").buildAndExpand(carteira.getId()).toUri();
            return ResponseEntity.created(uri).body(response);
        } catch (FeignException.UnprocessableEntity e) {
            return ResponseEntity.unprocessableEntity().build();
        }
    }

}
