package br.com.zup.proposta.proposta.cartao;

import br.com.zup.proposta.biometria.Biometria;
import br.com.zup.proposta.proposta.bloqueio.Bloqueio;
import br.com.zup.proposta.proposta.carteira.Carteira;
import br.com.zup.proposta.proposta.viagem.Viagem;

import java.util.List;
import java.util.Set;

public class CartaoResponse {

    private Long id;
    private String numero;
    private StatusCartao status;
    private Set<Biometria> biometrias;
    private List<Bloqueio> bloqueios;
    private List<Viagem> viagens;
    private List<Carteira> carteiras;

    public CartaoResponse(Cartao cartao) {
        this.id = cartao.getId();
        this.numero = cartao.getNumero();
        this.status = cartao.getStatus();
        this.biometrias = cartao.getBiometrias();
        this.bloqueios = cartao.getBloqueios();
        this.viagens = cartao.getViagens();
        this.carteiras = cartao.getCarteiras();
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
}
