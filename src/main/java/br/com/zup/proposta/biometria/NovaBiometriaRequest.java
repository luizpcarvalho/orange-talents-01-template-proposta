package br.com.zup.proposta.biometria;

import br.com.zup.proposta.proposta.cartao.Cartao;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotBlank;
import java.util.Base64;

public class NovaBiometriaRequest {

    @JsonProperty
    @NotBlank
    private String fingerprint;

    public String getFingerprint() {
        return fingerprint;
    }


    public Biometria toModel(Cartao cartao) {
        String fingerprintBase64 = Base64.getEncoder().encodeToString(this.fingerprint.getBytes());
        return new Biometria(fingerprintBase64, cartao);
    }
}
