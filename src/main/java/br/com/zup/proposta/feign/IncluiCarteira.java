package br.com.zup.proposta.feign;

import br.com.zup.proposta.proposta.carteira.NovaCarteiraRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;

@FeignClient(name = "incluiCarteira", url = "http://${host.contas}")
public interface IncluiCarteira {

    @PostMapping("/api/cartoes/{id}/carteiras")
    ResultadoInclusaoCarteira incui(@RequestBody @Valid NovaCarteiraRequest request, @PathVariable("id") String idCartao);

    class ResultadoInclusaoCarteira {

        private String resultado;
        private String id;

        public String getResultado() {
            return resultado;
        }

        public String getId() {
            return id;
        }
    }
}
