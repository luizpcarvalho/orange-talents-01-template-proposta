package br.com.zup.proposta.novaproposta;

import br.com.zup.proposta.novoendereco.Endereco;
import br.com.zup.proposta.novoendereco.NovoEnderecoRequest;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "propostas")
public class Proposta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String documento;
    @Column(nullable = false)
    private String email;
    @Column(nullable = false)
    private String nome;
    @Embedded
    private Endereco endereco;
    @Column(nullable = false)
    private BigDecimal salario;
    @Enumerated(EnumType.STRING)
    private Status status;

    @Deprecated
    public Proposta() {
    }

    public Proposta(String documento, String email, String nome, NovoEnderecoRequest endereco, BigDecimal salario) {
        this.documento = documento;
        this.email = email;
        this.nome = nome;
        this.endereco = endereco.toModel();
        this.salario = salario;
    }

    public Long getId() {
        return id;
    }

    public String getDocumento() {
        return documento;
    }

    public String getNome() {
        return nome;
    }

    @Override
    public String toString() {
        return "Proposta{" +
                "id=" + id +
                ", documento='" + documento + '\'' +
                ", email='" + email + '\'' +
                ", nome='" + nome + '\'' +
                ", endereco=" + endereco +
                ", salario=" + salario +
                ", status=" + status +
                '}';
    }

    public void atualizaStatus(String solicitacao) {
        this.status = Status.resultadoPara(solicitacao);
    }
}
