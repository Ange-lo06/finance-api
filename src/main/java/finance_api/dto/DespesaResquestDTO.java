package finance_api.dto;

import finance_api.model.CategoriaDespesa;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;
import java.time.LocalDate;

public record DespesaResquestDTO (

        @NotBlank(message = "Descrição é obrigatorio")
        String descricao,

        @Positive(message = "Valor deve ser maior que zero")
        BigDecimal valor,

        @NotNull(message = "Data é obrigatorio")
        LocalDate dataRecebimento,

        @NotNull(message = "Categorio é obrigatorio")
        CategoriaDespesa categoria

) {
}
