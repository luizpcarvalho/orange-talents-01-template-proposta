package br.com.zup.proposta.proposta.carteira;

import br.com.zup.proposta.proposta.cartao.Cartao;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

public class NovaCarteiraRequest {

    @NotBlank
    @Email
    private String email;
    @NotBlank
    private String carteira;

    public NovaCarteiraRequest(String email, @NotBlank String carteira) {
        this.email = email;
        this.carteira = carteira;
    }

    public String getEmail() {
        return email;
    }

    public String getCarteira() {
        return carteira;
    }

    public Carteira toModel(String idCarteira, Cartao cartao) {
        return new Carteira(this.carteira, this.email, idCarteira, cartao);
    }
}
