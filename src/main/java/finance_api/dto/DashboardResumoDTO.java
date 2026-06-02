package finance_api.dto;

import java.math.BigDecimal;

public record DashboardResumoDTO (
        BigDecimal totalReceita,
        BigDecimal totalDespesas,
        BigDecimal saldo
){
}
