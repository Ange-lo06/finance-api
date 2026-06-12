package finance_api.service;

import finance_api.dto.ReceitaResponseDTO;
import finance_api.exception.RecursoNaoEncontradoException;
import finance_api.mapper.ReceitaMapper;
import finance_api.model.Receita;
import finance_api.model.Usuario;
import finance_api.repository.ReceitaRepository;
import finance_api.security.SecurityUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReceitaService {

    private final ReceitaRepository repository;
    private final UsuarioService usuarioService;

    public Receita salvar(Receita receita){
        return repository.save(receita);
    }

    /*public List<Receita> listarTodos(){
        return repository.findAll();
    }*/

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

    public Page<Receita> listarPaginado(Pageable pageable){
        return repository.findAll(pageable);
    }

    public Page<ReceitaResponseDTO> listarDoUsuarioLogado(
            int page,
            int size
    ){
        String email = SecurityUtils.getEmailUsuarioLogado();

        Usuario usuario = usuarioService.buscarPorEmail(email);

        Pageable pageable = PageRequest.of(page, size);

        return repository
                .findByUsuario(usuario,pageable)
                .map(ReceitaMapper::toDTO);
    }
}
