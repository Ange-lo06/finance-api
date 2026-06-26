package finance_api.controller;

import finance_api.model.Despesa;
import finance_api.service.DespesaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@SecurityRequirement(name = "Bearer Authentication")
@RestController
@RequestMapping("/despesas")
@RequiredArgsConstructor
@Tag(
        name = "Despesas",
        description = "Operações relacionadas às despesas do usúario."
)
public class DespesaController {

    private final DespesaService service;

    @Operation(summary = "Lista todas as despesas.")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Busca Concluida"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos"),
            @ApiResponse(responseCode = "401", description = "Não autenticado")
    })
    @GetMapping
    public List<Despesa> listar(){
        return service.listarTodos();
    }

    @Operation(summary = "Lista as despesas por ID.")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Busca concluida"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos"),
            @ApiResponse(responseCode = "401", description = "Não autenticado")
    })
    @GetMapping("/{id}")
    public Despesa buscarPorId(@PathVariable Long id){
        return service.buscarPorId(id);
    }

    @Operation(summary = "Salva as despesas.")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Despesa criada"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos"),
            @ApiResponse(responseCode = "401", description = "Não autenticado")
    })
    @PostMapping
    public Despesa salvar(@RequestBody Despesa despesa){
        return service.salvar(despesa);
    }

    @Operation(summary = "Atualiza despesas por ID")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Dados Atualizados"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos"),
            @ApiResponse(responseCode = "401", description = "Não autenticado")
    })
    @PutMapping("/{id}")
    public Despesa atualizar(@PathVariable Long id, @RequestBody Despesa despesa){
        return service.atualizar(id,despesa);
    }

    @Operation(summary = "Exclui despesa.")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Exclusão concluida"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos"),
            @ApiResponse(responseCode = "401", description = "Não autenticado")
    })
    @DeleteMapping("/{id}")
    public void excluir(@PathVariable Long id){
        service.excluir(id);
    }
}
