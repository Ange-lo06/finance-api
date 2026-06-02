package finance_api.dto;

import finance_api.model.CategoriaReceita;

import java.math.BigDecimal;
import java.time.LocalDate;

public record ReceitaResponseDTO (
        Long id,
        String descricao,
        BigDecimal valor,
        LocalDate dataRecebimento,
        CategoriaReceita categoria

){
}
