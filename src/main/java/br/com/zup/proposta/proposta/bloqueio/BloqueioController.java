package br.com.zup.proposta.proposta.bloqueio;

import br.com.zup.proposta.feign.BloqueioCartao;
import br.com.zup.proposta.proposta.cartao.Cartao;
import br.com.zup.proposta.proposta.util.ClientHostResolver;
import feign.FeignException;
import org.apache.commons.lang3.ObjectUtils;
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
public class BloqueioController {

    @PersistenceContext
    private EntityManager entityManager;
    @Autowired
    private BloqueioCartao bloqueioCartao;
    @Autowired
    private ClientHostResolver clientHostResolver;

    @PostMapping("/bloqueio/{id}")
    @Transactional
    public ResponseEntity<?> bloquear(@RequestBody @Valid NovoBloqueioRequest bloqueioRequest,
                                      @PathVariable("id") Long idCartao,
                                      @RequestHeader("User-Agent") String userAgent,
                                      HttpServletRequest request) {
        Cartao cartao = entityManager.find(Cartao.class, idCartao);
        if(cartao == null) {
            return ResponseEntity.notFound().build();
        }
        try{
            BloqueioCartao.ResultadoBloqueioCartao response = bloqueioCartao.bloqueia(cartao.getNumero(),
                    bloqueioRequest);
            String ipAddress = clientHostResolver.resolve(request);
            Bloqueio bloqueio = bloqueioRequest.toModel(ipAddress, userAgent, cartao);
            cartao.associaBloqueio(bloqueio);
            entityManager.merge(cartao);

            return ResponseEntity.ok().body(response);
        } catch (FeignException.UnprocessableEntity e){
            return ResponseEntity.unprocessableEntity().build();
        }
    }

}
