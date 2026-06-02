package finance_api.service;

import finance_api.dto.UsuarioResponseDTO;
import finance_api.exception.RecursoNaoEncontradoException;
import finance_api.model.Usuario;
import finance_api.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UsuarioService {
    private final UsuarioRepository repository;

    public Usuario salvar(Usuario usuario){
        return repository.save(usuario);
    }

    public List<Usuario> listarTodos(){
        return repository.findAll();
    }

    public Usuario buscarPorId(Long id){
        return repository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Usuario Não encontrado"));
    }

    public void excluir(Long id){
        buscarPorId(id);
        repository.deleteById(id);
    }

    public Usuario atualizar(Long id, Usuario usuarioAtualizado){
        Usuario usuario = buscarPorId(id);

        usuario.setEmail(usuarioAtualizado.getEmail());
        usuario.setNome(usuarioAtualizado.getNome());
        usuario.setSenha(usuarioAtualizado.getSenha());

        return repository.save(usuario);
    }

    private UsuarioResponseDTO converterParaDTO(Usuario usuario){
        return new UsuarioResponseDTO(
                usuario.getId(),
                usuario.getNome(),
                usuario.getEmail()
        );
    }
}
