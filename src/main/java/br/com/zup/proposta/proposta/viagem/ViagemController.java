package br.com.zup.proposta.proposta.viagem;

import br.com.zup.proposta.feign.AvisoViagem;
import br.com.zup.proposta.proposta.cartao.Cartao;
import br.com.zup.proposta.proposta.util.ClientHostResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import javax.validation.Valid;

@RestController
@RequestMapping("/cartoes")
public class ViagemController {

    @PersistenceContext
    private EntityManager entityManager;
    @Autowired
    private ClientHostResolver clientHostResolver;
    @Autowired
    private AvisoViagem avisoViagem;

    @PostMapping("/viagens/{id}")
    @Transactional
    public ResponseEntity<?> cadastrar(@RequestBody @Valid NovaViagemRequest viagemRequest,
                                       @PathVariable("id") Long idCartao,
                                       @RequestHeader("User-Agent") String userAgent,
                                       HttpServletRequest request) {
        Cartao cartao = entityManager.find(Cartao.class, idCartao);
        if (cartao == null) {
            return ResponseEntity.notFound().build();
        }
        AvisoViagem.ResultadoAvisoViagem response = avisoViagem.avisa(cartao.getNumero(), viagemRequest);
        String ipAddress = clientHostResolver.resolve(request);
        Viagem viagem = viagemRequest.toModel(ipAddress, userAgent, cartao);
        cartao.associaAvisoViagem(viagem);
        entityManager.merge(cartao);

        return ResponseEntity.ok().body(response);

    }

}
