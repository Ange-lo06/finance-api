package finance_api.controller;

import finance_api.dto.UsuarioRequestDTO;
import finance_api.dto.UsuarioResponseDTO;
import finance_api.mapper.UsuarioMapper;
import finance_api.model.Usuario;
import finance_api.service.UsuarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@SecurityRequirement(name = "Bearer Authentication")
@RestController
@RequestMapping("/usuarios")
@RequiredArgsConstructor
@Tag(
        name = "Usúarios",
        description = "Operações relacionadas ao usúarios."
)
public class UsuarioController {

    private final UsuarioService service;

    @Operation(summary = "Lista todos os Usúarios.")
    @GetMapping
    public List<Usuario> listar(){
        return service.listarTodos();
    }

    @Operation(summary = "Lista usúario por ID.")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Busca concluida"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos"),
            @ApiResponse(responseCode = "401", description = "Não autenticado")
    })
    @GetMapping("/{id}")
    public Usuario buscarPorId(@PathVariable Long id){
        return service.buscarPorId(id);
    }

    @Operation(summary = "Salva usúarios novos.")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Usúario criada"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos"),
            @ApiResponse(responseCode = "401", description = "Não autenticado")
    })
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UsuarioResponseDTO salvar(@RequestBody UsuarioRequestDTO dto){
        Usuario usuario = UsuarioMapper.toEntity(dto);
        return UsuarioMapper.toDTO(service.salvar(usuario));
    }

    @Operation(summary = "Atualiza informações de usúarios.")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Usúario atualizado"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos"),
            @ApiResponse(responseCode = "401", description = "Não autenticado")
    })
    @PutMapping("/{id}")
    public Usuario atualizar(@PathVariable Long id, @RequestBody Usuario usuario){
        return service.atualizar(id,usuario);
    }

    @Operation(summary = "Exclui usúarios.")
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
