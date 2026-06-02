package finance_api.dto;

import finance_api.model.CategoriaReceita;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;
import java.time.LocalDate;

public record ReceitaRequestDTO(

        @NotBlank(message = "Descrição é obrigatoria")
        String descricao,

        @Positive(message = "Valor deve ser maior que zero")
        BigDecimal valor,

        @NotNull(message = "Data é obrigatorio")
        LocalDate dataRecebimento,

        @NotNull(message = "Categoria é obrigatorio")
        CategoriaReceita categoria
) {
}
