package br.com.zup.proposta.proposta;

import org.hibernate.LockOptions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.stereotype.Repository;

import javax.persistence.LockModeType;
import javax.persistence.QueryHint;
import java.util.List;

@Repository
public interface PropostaRepository extends JpaRepository<Proposta, Long> {
    boolean existsByDocumento(String documento);

    @Lock(LockModeType.PESSIMISTIC_WRITE) // for update
    @QueryHints({
            @QueryHint(name = "javax.persistence.lock.timeout", value = (LockOptions.SKIP_LOCKED + "")) // ...skip locked
    })
    List<Proposta> findTop5ByStatusOrderByInstanteCriacaoAsc(StatusProposta status);
}
