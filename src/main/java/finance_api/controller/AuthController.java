package finance_api.controller;


import finance_api.dto.AuthRequestDTO;
import finance_api.dto.AuthResponseDTO;
import finance_api.model.Usuario;
import finance_api.repository.UsuarioRepository;
import finance_api.service.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {

    private final UsuarioRepository usuarioRepository;
    private final JwtService jwtService;
    private final BCryptPasswordEncoder passwordEncoder;

    @PostMapping("/login")
    public AuthResponseDTO login(
            @RequestBody AuthRequestDTO request
            ){
        Usuario usuario = usuarioRepository
                .findByEmail(request.email())
                .orElseThrow(()-> new RuntimeException("Usuario não encontrado"));


        if (!passwordEncoder.matches(
                request.senha(),
                usuario.getSenha()
        )){
            throw new RuntimeException("Senha inválida");
        }

        String token = jwtService.gerarToken(usuario.getEmail());

        return new AuthResponseDTO(token);
    }


}
