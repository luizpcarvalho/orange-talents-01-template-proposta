package br.com.zup.proposta.proposta.viagem;

import br.com.zup.proposta.proposta.cartao.Cartao;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
public class Viagem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String destino;
    @Column(nullable = false)
    private LocalDate dataTermino;
    @Column(nullable = false)
    private LocalDateTime instanteAviso;
    @Column(nullable = false)
    private String ipCliente;
    @Column(nullable = false)
    private String userAgent;
    @ManyToOne
    private Cartao cartao;

    @Deprecated
    public Viagem() {
    }

    public Viagem(String destino, LocalDate dataTermino, String ipCliente, String userAgent, Cartao cartao) {
        this.destino = destino;
        this.dataTermino = dataTermino;
        this.instanteAviso = LocalDateTime.now();
        this.ipCliente = ipCliente;
        this.userAgent = userAgent;
        this.cartao = cartao;
    }

    public Long getId() {
        return id;
    }

    public String getDestino() {
        return destino;
    }

    public LocalDate getDataTermino() {
        return dataTermino;
    }

    public LocalDateTime getInstanteAviso() {
        return instanteAviso;
    }

    public String getIpCliente() {
        return ipCliente;
    }

    public String getUserAgent() {
        return userAgent;
    }

    @Override
    public String toString() {
        return "Viagem{" +
                "destino='" + destino + '\'' +
                ", dataTermino=" + dataTermino +
                ", instanteAviso=" + instanteAviso +
                ", ipCliente='" + ipCliente + '\'' +
                ", userAgent='" + userAgent + '\'' +
                '}';
    }
}
