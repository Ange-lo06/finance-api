package finance_api.controller;

import finance_api.dto.UsuarioRequestDTO;
import finance_api.dto.UsuarioResponseDTO;
import finance_api.mapper.UsuarioMapper;
import finance_api.model.Usuario;
import finance_api.service.UsuarioService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
@RequiredArgsConstructor
public class UsuarioController {

    private final UsuarioService service;

    @GetMapping
    public List<Usuario> listar(){
        return service.listarTodos();
    }

    @GetMapping("/{id}")
    public Usuario buscarPorId(@PathVariable Long id){
        return service.buscarPorId(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UsuarioResponseDTO salvar(@RequestBody UsuarioRequestDTO dto){
        Usuario usuario = UsuarioMapper.toEntity(dto);
        return UsuarioMapper.toDTO(service.salvar(usuario));
    }

    @PutMapping("/{id}")
    public Usuario atualizar(@PathVariable Long id, @RequestBody Usuario usuario){
        return service.atualizar(id,usuario);
    }

    @DeleteMapping("/{id}")
    public void excluir(@PathVariable Long id){
        service.excluir(id);
    }
}
