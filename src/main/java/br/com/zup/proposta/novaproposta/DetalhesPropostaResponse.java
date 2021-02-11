package br.com.zup.proposta.novaproposta;

import br.com.zup.proposta.novoendereco.Endereco;

public class DetalhesPropostaResponse {

    private Long id;
    private String documento;
    private String email;
    private String nome;
    private Endereco endereco;
    private Status status;
    private String cartao;

    public DetalhesPropostaResponse(Proposta proposta) {
        this.id = proposta.getId();
        this.documento = proposta.getDocumento();
        this.email = proposta.getEmail();
        this.nome = proposta.getNome();
        this.endereco = proposta.getEndereco();
        this.status = proposta.getStatus();
        this.cartao = proposta.getCartao();
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

    public Status getStatus() {
        return status;
    }

    public String getCartao() {
        return cartao;
    }
}
