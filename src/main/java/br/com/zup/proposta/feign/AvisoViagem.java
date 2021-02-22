package br.com.zup.proposta.feign;

import br.com.zup.proposta.proposta.viagem.NovaViagemRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "avisoViagem", url = "http://${host.contas}")
public interface AvisoViagem {

    @PostMapping("/api/cartoes/{id}/avisos")
    ResultadoAvisoViagem avisa(@PathVariable("id") String idCartao, @RequestBody NovaViagemRequest request);

    class ResultadoAvisoViagem {

        private String resultado;

        public String getResultado() {
            return resultado;
        }
    }
}
