package finance_api.service;

import finance_api.exception.RecursoNaoEncontradoException;
import finance_api.model.Receita;
import finance_api.repository.ReceitaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReceitaService {

    private final ReceitaRepository repository;

    public Receita salvar(Receita receita){
        return repository.save(receita);
    }

    public List<Receita> listarTodos(){
        return repository.findAll();
    }

    public Receita buscarPorId(Long id){
        return repository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Receita não encontrada"));
    }

    public void excluir(Long id){
        buscarPorId(id);
        repository.deleteById(id);
    }

    public Receita atualizar(Long id, Receita receitaAtualizada){
        Receita receita = buscarPorId(id);

        receita.setDescricao(receitaAtualizada.getDescricao());
        receita.setValor(receitaAtualizada.getValor());
        receita.setCategoria(receitaAtualizada.getCategoria());
        receita.setDataRecebimento(receitaAtualizada.getDataRecebimento());

        return repository.save(receita);
    }
}
