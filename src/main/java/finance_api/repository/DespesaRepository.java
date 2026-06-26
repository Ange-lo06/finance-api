package finance_api.repository;

import finance_api.model.Despesa;
import finance_api.model.Receita;
import finance_api.model.Usuario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.math.BigDecimal;
import java.util.List;

public interface DespesaRepository extends JpaRepository<Despesa, Long> {

    Page<Despesa> findByUsuario(Usuario usuario, Pageable pageable);

    @Query(""" 
            Select Coalesce(sum(d.valor),0)
            from Despesa d
            WHERE d.usuario = :usuario
            """)
    BigDecimal somarDespesas(Usuario usuario);
}
