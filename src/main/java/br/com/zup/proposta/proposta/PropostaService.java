package br.com.zup.proposta.proposta;

import br.com.zup.proposta.feign.AnaliseCliente;
import br.com.zup.proposta.proposta.util.Encryptor;
import feign.FeignException;
import org.springframework.stereotype.Service;

@Service
public class PropostaService {

    private AnaliseCliente analiseCliente;

    public PropostaService(AnaliseCliente analiseCliente) {
        this.analiseCliente = analiseCliente;
    }

    public Proposta analisaCliente(Proposta proposta) {
        AnaliseCliente.ConsultaStatusRequest analiseRequest = new AnaliseCliente.ConsultaStatusRequest(proposta.getDocumento(),
                proposta.getNome(), proposta.getId());

        try {
            AnaliseCliente.ConsultaAnaliseResponse analiseResponse = analiseCliente.consulta(analiseRequest);
            proposta.atualizaStatus(analiseResponse.getResultadoSolicitacao());
        } catch (FeignException.UnprocessableEntity e) {
            proposta.atualizaStatus("COM_RESTRICAO");
        }

        return proposta;
    }

}
