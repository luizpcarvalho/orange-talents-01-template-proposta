package br.com.zup.proposta.proposta;

import br.com.zup.proposta.config.validacao.CPFOuCNPJ;
import br.com.zup.proposta.proposta.endereco.NovoEnderecoRequest;
import br.com.zup.proposta.proposta.util.Encryptor;

import javax.crypto.SecretKey;
import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;

public class NovaPropostaRequest {

    @NotBlank
    @CPFOuCNPJ
    private String documento;
    @NotBlank
    @Email
    private String email;
    @NotBlank
    private String nome;
    @NotNull
    @Valid
    private NovoEnderecoRequest endereco;
    @NotNull
    @Positive
    private BigDecimal salario;

    public String getDocumento() {
        return documento;
    }

    public String getEmail() {
        return email;
    }

    public String getNome() {
        return nome;
    }

    public NovoEnderecoRequest getEndereco() {
        return endereco;
    }

    public BigDecimal getSalario() {
        return salario;
    }

    public Proposta toModel(Encryptor encryptor) {
        String documentoEcriptado = encryptor.encrypt(this.documento);
        return new Proposta(documentoEcriptado, this.email, this.nome, this.endereco, this.salario);
    }
}
