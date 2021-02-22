package br.com.zup.proposta.proposta.endereco;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class Endereco {

    @Column(nullable = false)
    private String cep;
    @Column(nullable = false)
    private String logradouro;
    @Column(nullable = false)
    private Integer numero;
    @Column(nullable = false)
    private String bairro;
    @Column(nullable = false)
    private String cidade;
    @Column(nullable = false)
    private String estado;

    @Deprecated
    public Endereco() {
    }

    public Endereco(String cep, String logradouro, Integer numero, String bairro, String cidade, String estado) {
        this.cep = cep;
        this.logradouro = logradouro;
        this.numero = numero;
        this.bairro = bairro;
        this.cidade = cidade;
        this.estado = estado;
    }

    public String getCep() { return cep; }

    public String getLogradouro() { return logradouro; }

    public Integer getNumero() { return numero; }

    public String getBairro() { return bairro; }

    public String getCidade() { return cidade; }

    public String getEstado() { return estado; }

    @Override
    public String toString() {
        return "Endereco{" +
                ", cep='" + cep + '\'' +
                ", logradouro='" + logradouro + '\'' +
                ", numero=" + numero +
                ", bairro='" + bairro + '\'' +
                ", cidade='" + cidade + '\'' +
                ", estado='" + estado + '\'' +
                '}';
    }
}
