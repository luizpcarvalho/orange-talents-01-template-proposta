package br.com.zup.proposta.proposta.cartao;

import br.com.zup.proposta.feign.AssociaCartao;
import br.com.zup.proposta.proposta.Proposta;
import br.com.zup.proposta.proposta.PropostaRepository;
import br.com.zup.proposta.proposta.StatusProposta;
import feign.FeignException;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.support.TransactionTemplate;

import java.util.List;

@Component
public class AssociaPropostasAosCartoesJob {

    private PropostaRepository repository;
    private AssociaCartao associaCartao;
    private TransactionTemplate transactionManager;

    public AssociaPropostasAosCartoesJob(PropostaRepository repository, AssociaCartao associaCartao,
                                         TransactionTemplate transactionManager) {
        this.repository = repository;
        this.associaCartao = associaCartao;
        this.transactionManager = transactionManager;
    }

    @SuppressWarnings("ConstantConditions")
    @Scheduled(fixedDelayString = "${periodicidade.associa-cartao}")
    public void associaCartao() {
        boolean pendente = true;
        while (pendente) {

            pendente = transactionManager.execute(transactionStatus -> {

                List<Proposta> list = repository.findTop5ByStatusOrderByInstanteCriacaoAsc(StatusProposta.ELEGIVEL);
                if (list.isEmpty()) {
                    return false;
                }

                list.forEach(proposta -> {
                    String numeroCartao = getCartaoIdResponse(proposta); // timeout=100
                    if(numeroCartao != null) {
                        Cartao cartao = new Cartao(numeroCartao, proposta);
                        proposta.cadastraCartao(cartao);

                        repository.save(proposta);
                    }
                });
                return true;

            });
        }
    }

    private String getCartaoIdResponse(Proposta proposta) {
        try {
            AssociaCartao.DadosCartaoResponse response = associaCartao.consulta(proposta.getId().toString());
            return response.getId();
        } catch (FeignException.InternalServerError e) {
            return null;
        }
    }

}
