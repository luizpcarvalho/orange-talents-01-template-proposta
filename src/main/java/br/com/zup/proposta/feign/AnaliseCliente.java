package br.com.zup.proposta.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "analiseCliente", url = "http://${host.analise}")
public interface AnaliseCliente {

    @PostMapping("/api/solicitacao")
    ConsultaAnaliseResponse consulta(@RequestBody ConsultaStatusRequest consultaStatusRequest);

    class ConsultaStatusRequest {

        private String documento;
        private String nome;
        private Long idProposta;

        public ConsultaStatusRequest(String documento, String nome, Long id) {
            this.documento = documento;
            this.nome = nome;
            this.idProposta = id;
        }

        public String getDocumento() {
            return documento;
        }

        public String getNome() {
            return nome;
        }

        public Long getIdProposta() {
            return idProposta;
        }
    }

    class ConsultaAnaliseResponse {

        private String resultadoSolicitacao;
        private String documento;
        private String nome;
        private Long idProposta;

        public String getResultadoSolicitacao() {
            return resultadoSolicitacao;
        }

        public String getDocumento() {
            return documento;
        }

        public String getNome() {
            return nome;
        }

        public Long getIdProposta() {
            return idProposta;
        }
    }
}
