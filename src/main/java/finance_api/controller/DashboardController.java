package finance_api.controller;


import finance_api.dto.DashboardResumoDTO;
import finance_api.service.DashboardService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SecurityRequirement(name = "Bearer Authentication")
@RestController
@RequestMapping("/dashboard")
@RequiredArgsConstructor
@Tag(
        name = "Dashboard",
        description = "Operações relacionadas aos dashboard do usúario."
)
public class DashboardController {

    private final DashboardService service;

    @Operation(summary = "Lista o resumo das receitas e despesas.")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Dashboard criada"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos"),
            @ApiResponse(responseCode = "401", description = "Não autenticado")
    })
    @GetMapping("/resumo")
    public DashboardResumoDTO resumo(){
        return service.obterResumo();
    }
}
