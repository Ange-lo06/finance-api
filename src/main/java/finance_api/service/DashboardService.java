package finance_api.service;

import finance_api.dto.DashboardResumoDTO;
import finance_api.repository.DespesaRepository;
import finance_api.repository.ReceitaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class DashboardService {

    private final ReceitaRepository receitaRepository;
    private final DespesaRepository despesaRepository;

    public DashboardResumoDTO obterResumo(){
        BigDecimal receitas = receitaRepository.somarReceita();

        BigDecimal despesas = despesaRepository.somarDespesas();

        BigDecimal saldo = receitas.subtract(despesas);

        return new DashboardResumoDTO(
                receitas,
                despesas,
                saldo
        );
    }
}
