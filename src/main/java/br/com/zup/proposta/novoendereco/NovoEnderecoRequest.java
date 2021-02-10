package br.com.zup.proposta.novoendereco;

import br.com.zup.proposta.novaproposta.Proposta;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class NovoEnderecoRequest {

    @NotBlank
    private String cep;
    @NotBlank
    private String logradouro;
    @NotNull
    private Integer numero;
    @NotBlank
    private String bairro;
    @NotBlank
    private String cidade;
    @NotBlank
    private String estado;

    public String getCep() {
        return cep;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public Integer getNumero() { return numero; }

    public String getBairro() {
        return bairro;
    }

    public String getCidade() {
        return cidade;
    }

    public String getEstado() {
        return estado;
    }

    public Endereco toModel(Proposta proposta) {
        return new Endereco(this.cep, this.logradouro, this.numero, this.bairro, this.cidade, this.estado, proposta);
    }
}
