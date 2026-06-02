package finance_api.dto;

import finance_api.model.CategoriaDespesa;

import java.math.BigDecimal;
import java.time.LocalDate;

public record DespesaResponseDTO (
        Long id,
        String descricao,
        BigDecimal valor,
        LocalDate dataRecebimento,
        CategoriaDespesa categoria
){
}
