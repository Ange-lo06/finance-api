package finance_api.controller;

import finance_api.model.Despesa;
import finance_api.service.DespesaService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/despesas")
@RequiredArgsConstructor
public class DespesaController {

    private final DespesaService service;

    @GetMapping
    public List<Despesa> listar(){
        return service.listarTodos();
    }

    @GetMapping("/{id}")
    public Despesa buscarPorId(@PathVariable Long id){
        return service.buscarPorId(id);
    }

    @PostMapping
    public Despesa atualizar(@RequestBody Despesa despesa){
        return service.salvar(despesa);
    }


    @PutMapping("/{id}")
    public Despesa atualizar(@PathVariable Long id, @RequestBody Despesa despesa){
        return service.atualizar(id,despesa);
    }

    @DeleteMapping("/{id}")
    public void excluir(@PathVariable Long id){
        service.excluir(id);
    }
}
