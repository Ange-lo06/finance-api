package finance_api.controller;

import finance_api.dto.ReceitaRequestDTO;
import finance_api.dto.ReceitaResponseDTO;
import finance_api.mapper.ReceitaMapper;
import finance_api.model.Receita;
import finance_api.service.ReceitaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/receitas")
@RequiredArgsConstructor
public class ReceitaController {

    private final ReceitaService service;
    
    @GetMapping
    public List<Receita> listar(){
        return service.listarTodos();
    }

    @GetMapping("/{id}")
    public Receita buscarPorId(@PathVariable Long id){
        return service.buscarPorId(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ReceitaResponseDTO salvar(@Valid @RequestBody ReceitaRequestDTO dto){

        Receita receita = ReceitaMapper.toEntity(dto);

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
}
