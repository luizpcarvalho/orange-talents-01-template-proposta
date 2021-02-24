package br.com.zup.proposta.proposta;

import br.com.zup.proposta.proposta.cartao.Cartao;
import br.com.zup.proposta.proposta.endereco.Endereco;
import br.com.zup.proposta.proposta.endereco.NovoEnderecoRequest;
import br.com.zup.proposta.proposta.util.Encryptor;
import br.com.zup.proposta.proposta.util.EncryptorAttributeConverter;
import org.hibernate.annotations.ColumnTransformer;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "propostas")
public class Proposta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, unique = true)
    @Convert(converter = EncryptorAttributeConverter.class)
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
    private StatusProposta status;
    @Column(nullable = false)
    private LocalDateTime instanteCriacao;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(referencedColumnName = "id")
    private Cartao cartao;

    @Deprecated
    public Proposta() {
    }

    public Proposta(String documento, String email, String nome, NovoEnderecoRequest endereco, BigDecimal salario) {
        this.documento = documento;
        this.email = email;
        this.nome = nome;
        this.endereco = endereco.toModel();
        this.salario = salario;
        this.instanteCriacao = LocalDateTime.now();
    }

    public Long getId() {
        return id;
    }

    public String getDocumento() {
        return documento;
    }

    public String getEmail() { return email; }

    public String getNome() {
        return nome;
    }

    public Endereco getEndereco() { return endereco; }

    public BigDecimal getSalario() { return salario; }

    public StatusProposta getStatus() { return status; }

    public Cartao getCartao() { return cartao; }

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
        this.status = StatusProposta.resultadoPara(solicitacao);
    }

    public void cadastraCartao(Cartao cartao) {
        this.cartao = cartao;
        this.status = StatusProposta.ELEGIVEL_COM_CARTAO;
    }
}
