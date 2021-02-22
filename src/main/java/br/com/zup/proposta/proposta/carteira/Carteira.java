package br.com.zup.proposta.proposta.carteira;

import br.com.zup.proposta.proposta.cartao.Cartao;

import javax.persistence.*;

@Entity
public class Carteira {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String nome;
    @Column(nullable = false)
    private String email;
    @Column(nullable = false)
    private String idCarteira;
    @ManyToOne
    private Cartao cartao;

    @Deprecated
    public Carteira() {
    }

    public Carteira(String nome, String email, String idCarteira, Cartao cartao) {
        this.nome = nome;
        this.email = email;
        this.idCarteira = idCarteira;
        this.cartao = cartao;
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    public String getIdCarteira() {
        return idCarteira;
    }

    @Override
    public String toString() {
        return "Carteira{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", email='" + email + '\'' +
                ", idCarteira='" + idCarteira + '\'' +
                '}';
    }
}
