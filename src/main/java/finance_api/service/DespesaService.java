package finance_api.service;

import finance_api.exception.RecursoNaoEncontradoException;
import finance_api.model.Despesa;
import finance_api.repository.DespesaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DespesaService {

    private final DespesaRepository repository;

    public Despesa salvar(Despesa despesa){
        return repository.save(despesa);
    }

    public List<Despesa> listarTodos(){
        return repository.findAll();
    }

    public Despesa buscarPorId(Long id){
        return repository.findById(id)
                .orElseThrow(()-> new RecursoNaoEncontradoException("Despesa não encontrada"));
    }

    public void excluir(Long id){
        buscarPorId(id);
        repository.deleteById(id);
    }

    public Despesa atualizar(Long id, Despesa despesaAtualizada){
        Despesa despesa = buscarPorId(id);

        despesa.setDescricao(despesaAtualizada.getDescricao());
        despesa.setValor(despesaAtualizada.getValor());
        despesa.setCategoria(despesaAtualizada.getCategoria());
        despesa.setDataPagamento(despesaAtualizada.getDataPagamento());

        return repository.save(despesa);
    }
}
