package br.com.zup.proposta.novaproposta;

public enum Status {
    NAO_ELEGIVEL, ELEGIVEL;

    public static Status resultadoPara(String solicitacao) {
        if(solicitacao.equals("SEM_RESTRICAO")) {
            return ELEGIVEL;
        } else {
            return NAO_ELEGIVEL;
        }
    }

}
