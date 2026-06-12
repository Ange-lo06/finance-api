package finance_api.controller;

import finance_api.dto.ReceitaRequestDTO;
import finance_api.dto.ReceitaResponseDTO;
import finance_api.mapper.ReceitaMapper;
import finance_api.model.Receita;
import finance_api.model.Usuario;
import finance_api.service.ReceitaService;
import finance_api.service.UsuarioService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
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
@SecurityRequirement(name = "bearerAuth")
@RequiredArgsConstructor
public class ReceitaController {

    private final ReceitaService service;
    private final UsuarioService usuarioService;
    
    @GetMapping
    public Page<ReceitaResponseDTO> listar(
            @RequestParam(defaultValue = "0")
            int page,

            @RequestParam(defaultValue = "10")
            int size
    ){
        return service.listarDoUsuarioLogado(page, size);
    }

    @GetMapping("/{id}")
    public Receita buscarPorId(@PathVariable Long id){
        return service.buscarPorId(id);
    }

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

    @PutMapping("/{id}")
    public Receita atualizar(@PathVariable Long id, @RequestBody Receita receita){
        return service.atualizar(id, receita);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void excluir(@PathVariable Long id){
        service.excluir(id);
    }

    @GetMapping("/paginado")
    public Page<ReceitaResponseDTO> listarPaginado(@ParameterObject Pageable pageable){
        return service.listarPaginado(pageable).map(ReceitaMapper::toDTO);
    }

}



