package br.com.zup.proposta.novoendereco;

import br.com.zup.proposta.novaproposta.Proposta;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Endereco {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
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
    @OneToMany(mappedBy = "endereco", cascade = CascadeType.PERSIST)
    private Set<Proposta> propostas = new HashSet<>();

    @Deprecated
    public Endereco() {
    }

    public Endereco(String cep, String logradouro, Integer numero, String bairro,
                    String cidade, String estado, Proposta proposta) {
        this.cep = cep;
        this.logradouro = logradouro;
        this.numero = numero;
        this.bairro = bairro;
        this.cidade = cidade;
        this.estado = estado;
        this.propostas.add(proposta);
    }

    @Override
    public String toString() {
        return "Endereco{" +
                "id=" + id +
                ", cep='" + cep + '\'' +
                ", logradouro='" + logradouro + '\'' +
                ", numero=" + numero +
                ", bairro='" + bairro + '\'' +
                ", cidade='" + cidade + '\'' +
                ", estado='" + estado + '\'' +
                '}';
    }
}
