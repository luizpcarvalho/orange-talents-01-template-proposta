package br.com.zup.proposta.proposta.bloqueio;

import br.com.zup.proposta.proposta.cartao.Cartao;

import javax.validation.constraints.NotBlank;

public class NovoBloqueioRequest {

    @NotBlank
    private String sistemaResponsavel;

    public String getSistemaResponsavel() {
        return sistemaResponsavel;
    }

    public Bloqueio toModel(String ipAddress, String userAgent, Cartao cartao) {
        return new Bloqueio(this.sistemaResponsavel, ipAddress, userAgent, cartao);
    }
}
