package br.com.zup.proposta.novaproposta;

import br.com.zup.proposta.feign.AnaliseCliente;
import br.com.zup.proposta.feign.AssociaCartao;
import feign.FeignException;
import org.springframework.stereotype.Service;

@Service
public class PropostaService {

    private AnaliseCliente analiseCliente;
    private AssociaCartao associaCartao;

    public PropostaService(AnaliseCliente analiseCliente, AssociaCartao associaCartao) {
        this.analiseCliente = analiseCliente;
        this.associaCartao = associaCartao;
    }

    public Proposta analisaCliente(Proposta proposta) {
        try {
            AnaliseCliente.ConsultaStatusRequest analiseRequest = new AnaliseCliente.ConsultaStatusRequest(proposta.getDocumento(),
                    proposta.getNome(), proposta.getId());
            AnaliseCliente.ConsultaAnaliseResponse analiseResponse = analiseCliente.consulta(analiseRequest);

            proposta.atualizaStatus(analiseResponse.getResultadoSolicitacao());
        } catch (FeignException.UnprocessableEntity e) {
            proposta.atualizaStatus("COM_RESTRICAO");
        }

        return proposta;
    }

    public void associaCartao(Proposta proposta, PropostaRepository repository) {
        String numeroCartao = null;
        try {
            numeroCartao = associaCartao.consulta(String.valueOf(proposta.getId())).getId();
        } catch (FeignException.InternalServerError e) {
        }
        proposta.cadastraCartao(numeroCartao);
        repository.save(proposta);
    }

}
