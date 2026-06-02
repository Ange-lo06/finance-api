package finance_api.repository;

import finance_api.model.Despesa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.math.BigDecimal;

public interface DespesaRepository extends JpaRepository<Despesa, Long> {

    @Query(""" 
            Select Coalesce(sum(d.valor),0)
            from Despesa d
            """)
    BigDecimal somarDespesas();
}
