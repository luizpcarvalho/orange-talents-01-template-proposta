package br.com.zup.proposta.proposta.bloqueio;

import br.com.zup.proposta.proposta.cartao.Cartao;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Bloqueio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String sistemaResponsavel;
    @Column(nullable = false)
    private LocalDateTime instanteBloqueio;
    @Column(nullable = false)
    private String ipCliente;
    @Column(nullable = false)
    private String userAgent;
    @ManyToOne
    private Cartao cartao;

    @Deprecated
    public Bloqueio() {
    }

    public Bloqueio(String sistemaResponsavel, String ipCliente, String userAgent, Cartao cartao) {
        this.sistemaResponsavel = sistemaResponsavel;
        this.instanteBloqueio = LocalDateTime.now();
        this.ipCliente = ipCliente;
        this.userAgent = userAgent;
        this.cartao = cartao;
    }

    public Long getId() {
        return id;
    }

    public String getSistemaResponsavel() {
        return sistemaResponsavel;
    }

    public LocalDateTime getInstanteBloqueio() {
        return instanteBloqueio;
    }

    public String getIpCliente() {
        return ipCliente;
    }

    public String getUserAgent() {
        return userAgent;
    }

    @Override
    public String toString() {
        return "Bloqueio{" +
                "sistemaResponsavel='" + sistemaResponsavel + '\'' +
                ", instanteBloqueio=" + instanteBloqueio +
                ", ipCliente='" + ipCliente + '\'' +
                ", userAgent='" + userAgent + '\'' +
                '}';
    }
}
