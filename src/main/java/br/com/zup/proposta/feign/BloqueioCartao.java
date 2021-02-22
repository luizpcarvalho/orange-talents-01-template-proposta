package br.com.zup.proposta.feign;

import br.com.zup.proposta.proposta.bloqueio.NovoBloqueioRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "bloqueioCartao", url = "http://${host.contas}")
public interface BloqueioCartao {

    @PostMapping("/api/cartoes/{id}/bloqueios")
    ResultadoBloqueioCartao bloqueia(@PathVariable("id") String idCartao, @RequestBody NovoBloqueioRequest request);

    class ResultadoBloqueioCartao {

        private String resultado;

        public String getResultado() {
            return resultado;
        }
    }
}
