package br.com.zup.proposta.proposta;

import br.com.zup.proposta.proposta.cartao.CartaoResponse;
import br.com.zup.proposta.proposta.endereco.Endereco;

public class DetalhesPropostaResponse {

    private Long id;
    private String documento;
    private String email;
    private String nome;
    private Endereco endereco;
    private StatusProposta statusProposta;
    private CartaoResponse cartao;

    public DetalhesPropostaResponse(Proposta proposta) {
        this.id = proposta.getId();
        this.documento = proposta.getDocumento();
        this.email = proposta.getEmail();
        this.nome = proposta.getNome();
        this.endereco = proposta.getEndereco();
        this.statusProposta = proposta.getStatus();
        this.cartao = new CartaoResponse(proposta.getCartao());
    }

    public Long getId() {
        return id;
    }

    public String getDocumento() {
        return documento;
    }

    public String getEmail() {
        return email;
    }

    public String getNome() {
        return nome;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public StatusProposta getStatus() {
        return statusProposta;
    }

    public CartaoResponse getCartao() {
        return cartao;
    }
}
