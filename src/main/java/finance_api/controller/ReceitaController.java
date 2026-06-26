package finance_api.controller;

import finance_api.dto.ReceitaRequestDTO;
import finance_api.dto.ReceitaResponseDTO;
import finance_api.mapper.ReceitaMapper;
import finance_api.model.Receita;
import finance_api.model.Usuario;
import finance_api.service.ReceitaService;
import finance_api.service.UsuarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/receitas")
@SecurityRequirement(name = "Bearer Authentication")
@RequiredArgsConstructor
@Tag(
        name = "Receitas",
        description = "Operações relacionadas às receitas dos usúario"
)
public class ReceitaController {

    private final ReceitaService service;
    private final UsuarioService usuarioService;

    @Operation(summary = "Lista receitas do usúario logado.")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Busca concluida"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos"),
            @ApiResponse(responseCode = "401", description = "Não autenticado")
    })
    @GetMapping
    public Page<ReceitaResponseDTO> listar(
            @RequestParam(defaultValue = "0")
            int page,

            @RequestParam(defaultValue = "10")
            int size
    ){
        return service.listarDoUsuarioLogado(page, size);
    }

    @Operation(summary = "Lista receita por Id.")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Busca concluida"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos"),
            @ApiResponse(responseCode = "401", description = "Não autenticado")
    })
    @GetMapping("/{id}")
    public Receita buscarPorId(@PathVariable Long id){
        return service.buscarPorId(id);
    }

    @Operation(summary = "Salva novas receitas.")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Receita criada"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos"),
            @ApiResponse(responseCode = "401", description = "Não autenticado")
    })
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ReceitaResponseDTO salvar(@Valid @RequestBody ReceitaRequestDTO dto){

        Receita receita = ReceitaMapper.toEntity(dto);

        Usuario usuario = usuarioService.buscarPorId(dto.usuarioId());
        receita.setUsuario(usuario);

        return ReceitaMapper.toDTO(
                service.salvar(receita)
        );
    }

    @Operation(summary = "Atualiza receita por ID.")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Dados atualizados"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos"),
            @ApiResponse(responseCode = "401", description = "Não autenticado")
    })
    @PutMapping("/{id}")
    public Receita atualizar(@PathVariable Long id, @RequestBody Receita receita){
        return service.atualizar(id, receita);
    }

    @Operation(summary = "Exclui receita por ID.")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Exclusão concluida"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos"),
            @ApiResponse(responseCode = "401", description = "Não autenticado")
    })
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void excluir(@PathVariable Long id){
        service.excluir(id);
    }

    @Operation(summary = "Paginação")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Paginação criada"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos"),
            @ApiResponse(responseCode = "401", description = "Não autenticado")
    })
    @GetMapping("/paginado")
    public Page<ReceitaResponseDTO> listarPaginado(@ParameterObject Pageable pageable){
        return service.listarPaginado(pageable).map(ReceitaMapper::toDTO);
    }

}



