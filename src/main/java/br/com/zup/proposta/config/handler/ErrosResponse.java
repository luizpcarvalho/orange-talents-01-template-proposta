package br.com.zup.proposta.config.handler;

public class ErrosResponse {

    private String campo;
    private String mensagem;

    public ErrosResponse(String campo, String mensagem) {
        this.campo = campo;
        this.mensagem = mensagem;
    }

    public String getCampo() {
        return campo;
    }

    public String getMensagem() {
        return mensagem;
    }
}
