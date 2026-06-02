package finance_api.repository;

import finance_api.model.Receita;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.math.BigDecimal;

public interface ReceitaRepository extends JpaRepository<Receita, Long> {

    @Query(""" 
            Select Coalesce(sum(r.valor),0) from receita r 
            """)
    BigDecimal somarReceita();
}
