package finance_api.dto;

import finance_api.model.CategoriaReceita;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;
import java.time.LocalDate;

public record ReceitaRequestDTO(

        @Schema(example = "Salário")
        @NotBlank(message = "Descrição é obrigatoria")
        String descricao,

        @Schema(example = "5000.00")
        @Positive(message = "Valor deve ser maior que zero")
        BigDecimal valor,

        @Schema(example = "2026-06-07")
        @NotNull(message = "Data é obrigatorio")
        LocalDate dataRecebimento,

        @Schema(example = "SALARIO")
        @NotNull(message = "Categoria é obrigatorio")
        CategoriaReceita categoria,

        @NotNull(message = "Usuario é obrigatorio")
        Long usuarioId
) {
}
