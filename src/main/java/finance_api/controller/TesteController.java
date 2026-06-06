package finance_api.controller;

import finance_api.service.CustomUserDetailsService;
import finance_api.service.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/teste")
@RequiredArgsConstructor
public class TesteController {

    private final JwtService jwtService;
    private final CustomUserDetailsService customUserDetailsService;

    @GetMapping
    public String gerarToken() {

        return jwtService.gerarToken(
                "angelo@email.com"
        );
    }

    @GetMapping("/teste-email")
    public String testeEmail() {

        String token = jwtService.gerarToken("angelo@email.com");

        return jwtService.extrairEmail(token);
    }

    @GetMapping("/teste-user")
    public String testeUser() {

        UserDetails usuario =
                customUserDetailsService
                        .loadUserByUsername(
                                "angelo@email.com"
                        );

        return usuario.getUsername();
    }

}