package finance_api.service;


import finance_api.model.Usuario;
import finance_api.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UsuarioRepository repository;
    private final PasswordEncoder passwordEncoder;

    public Usuario autenticar(
            String email,
            String senha
    ){
        Usuario usuario = repository.findByEmail(email)
                .orElseThrow(()->
                        new RuntimeException("Usuario não encontrado"));

        if(!passwordEncoder.matches(senha, usuario.getSenha())){
            throw new RuntimeException("Senha invalida");
        }
        return usuario;
    }
}
