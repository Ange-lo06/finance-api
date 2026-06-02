package finance_api.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UsuarioRequestDTO (
        @NotBlank(message = "Nome é obrigatorio")
        String nome,

        @Email(message = "Email inváldo")
        @NotBlank(message = "Email é obrigatorio")
        String email,

        @Size(min = 6, message = "Senha deve ser de no minimo 6 caracteres")
        String senha

){}

