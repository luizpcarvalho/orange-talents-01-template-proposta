package br.com.zup.proposta.biometria;

import br.com.zup.proposta.proposta.cartao.Cartao;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
public class Biometria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String fingerprint;
    @Column(nullable = false)
    private LocalDateTime instanteCadastro;
    @ManyToOne
    private Cartao cartao;

    @Deprecated
    public Biometria() {
    }

    public Biometria(String fingerprint, Cartao cartao) {
        this.fingerprint = fingerprint;
        this.instanteCadastro = LocalDateTime.now();
        this.cartao = cartao;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Biometria biometria = (Biometria) o;
        return fingerprint.equals(biometria.fingerprint) && instanteCadastro.equals(biometria.instanteCadastro);
    }

    @Override
    public int hashCode() {
        return Objects.hash(fingerprint, instanteCadastro);
    }

    public Long getId() {
        return id;
    }

    public String getFingerprint() {
        return fingerprint;
    }

    public LocalDateTime getInstanteCadastro() {
        return instanteCadastro;
    }

    @Override
    public String toString() {
        return "Biometria{" +
                "id=" + id +
                ", fingerprint='" + fingerprint + '\'' +
                ", instanteCadastro=" + instanteCadastro +
                '}';
    }
}
