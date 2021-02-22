package br.com.zup.proposta.proposta.viagem;

import br.com.zup.proposta.proposta.cartao.Cartao;
import com.fasterxml.jackson.annotation.JsonFormat;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;

public class NovaViagemRequest {

    @NotBlank
    private String destino;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    @Future
    private LocalDate dataTermino;

    public String getDestino() {
        return destino;
    }

    public LocalDate getDataTermino() {
        return dataTermino;
    }

    public Viagem toModel(String ipCliente, String userAgent, Cartao cartao) {
        return new Viagem(this.destino, this.dataTermino, ipCliente, userAgent, cartao);
    }
}
