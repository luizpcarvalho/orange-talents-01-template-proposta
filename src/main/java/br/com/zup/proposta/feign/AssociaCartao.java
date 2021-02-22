package br.com.zup.proposta.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "associaCartao", url = "http://${host.contas}")
public interface AssociaCartao {

    @GetMapping("/api/cartoes?idProposta={idProposta}")
    DadosCartaoResponse consulta(@PathVariable("idProposta") String idProposta);

    class DadosCartaoResponse {

        private String id;

        public String getId() {
            return id;
        }
    }
}
