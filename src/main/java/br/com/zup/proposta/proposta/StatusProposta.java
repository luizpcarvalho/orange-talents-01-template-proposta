package br.com.zup.proposta.proposta;

public enum StatusProposta {
    NAO_ELEGIVEL, ELEGIVEL, ELEGIVEL_COM_CARTAO;

    public static StatusProposta resultadoPara(String solicitacao) {
        if(solicitacao.equals("SEM_RESTRICAO")) {
            return ELEGIVEL;
        } else {
            return NAO_ELEGIVEL;
        }
    }

}
