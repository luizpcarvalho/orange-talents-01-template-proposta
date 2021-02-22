package br.com.zup.proposta.proposta.cartao;

import br.com.zup.proposta.biometria.Biometria;
import br.com.zup.proposta.proposta.Proposta;
import br.com.zup.proposta.proposta.bloqueio.Bloqueio;
import br.com.zup.proposta.proposta.carteira.Carteira;
import br.com.zup.proposta.proposta.viagem.Viagem;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
public class Cartao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String numero;
    @OneToOne(mappedBy = "cartao")
    private Proposta proposta;
    @Enumerated(EnumType.STRING)
    private StatusCartao status;
    @OneToMany(mappedBy = "cartao", cascade = CascadeType.PERSIST)
    private Set<Biometria> biometrias;
    @OneToMany(mappedBy = "cartao", cascade = CascadeType.MERGE)
    private List<Bloqueio> bloqueios;
    @OneToMany(mappedBy = "cartao", cascade = CascadeType.MERGE)
    private List<Viagem> viagens;
    @OneToMany(mappedBy = "cartao", cascade = CascadeType.MERGE)
    private List<Carteira> carteiras;

    @Deprecated
    public Cartao() {
    }

    public Cartao(String numero, Proposta proposta) {
        this.numero = numero;
        this.proposta = proposta;
        this.status = StatusCartao.NORMAL;
    }

    public Long getId() {
        return id;
    }

    public String getNumero() {
        return numero;
    }

    public StatusCartao getStatus() {
        return status;
    }

    public Proposta getProposta() {
        return proposta;
    }

    public Set<Biometria> getBiometrias() {
        return biometrias;
    }

    public List<Bloqueio> getBloqueios() {
        return bloqueios;
    }

    public List<Viagem> getViagens() {
        return viagens;
    }

    public List<Carteira> getCarteiras() {
        return carteiras;
    }

    public void associaBiometria(Biometria biometria) {
        this.biometrias.add(biometria);
    }

    public void associaBloqueio(Bloqueio bloqueio) {
        this.bloqueios.add(bloqueio);
        this.status = StatusCartao.BLOQUEADO;
    }

    public void associaAvisoViagem(Viagem viagem) {
        this.viagens.add(viagem);
    }

    public void associaCarteira(Carteira carteira) {
        this.carteiras.add(carteira);
    }

    @Override
    public String toString() {
        return "Cartao{" +
                "id=" + id +
                ", numero='" + numero + '\'' +
                ", status=" + status +
                ", biometrias=" + biometrias +
                ", bloqueios=" + bloqueios +
                ", viagens=" + viagens +
                '}';
    }
}
