package finance_api.repository;

import finance_api.model.CategoriaReceita;
import finance_api.model.Receita;
import finance_api.model.Usuario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public interface ReceitaRepository extends JpaRepository<Receita, Long> {

    Page<Receita> findByUsuario(Usuario usuario, Pageable pageable);

    List<Receita> findByUsuarioAndCategoria(
            Usuario usuario,
            CategoriaReceita categoriaReceita,
            Pageable pageable
            );

    Page<Receita> findByUsuarioAndDataRecebimentoBetween(
            Usuario usuario,
            LocalDate inicio,
            LocalDate fim,
            Pageable pageable
    );

    @Query(""" 
            Select Coalesce(sum(r.valor),0)
             from Receita r
              WHERE r.usuario = :usuario 
            """)
    BigDecimal somarReceita(Usuario usuario);
}
