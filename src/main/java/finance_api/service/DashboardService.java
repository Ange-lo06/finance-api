package finance_api.service;

import finance_api.dto.DashboardResumoDTO;
import finance_api.model.Usuario;
import finance_api.repository.DespesaRepository;
import finance_api.repository.ReceitaRepository;
import finance_api.security.SecurityUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class DashboardService {

    private final ReceitaRepository receitaRepository;
    private final DespesaRepository despesaRepository;
    private final UsuarioService usuarioService;

    public DashboardResumoDTO obterResumo(){
        String email = SecurityUtils.getEmailUsuarioLogado();

        Usuario usuario = usuarioService.buscarPorEmail(email);

        BigDecimal receitas = receitaRepository.somarReceita(usuario);

        BigDecimal despesas = despesaRepository.somarDespesas(usuario);

        BigDecimal saldo = receitas.subtract(despesas);

        return new DashboardResumoDTO(
                receitas,
                despesas,
                saldo
        );
    }
}
