package finance_api.dto;

public record AuthRequestDTO(
        String email,
        String senha
) {
}
