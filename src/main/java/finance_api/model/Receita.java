package finance_api.model;

import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "receitas")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Receita {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String descricao;

    private BigDecimal valor;

    private LocalDate dataRecbimento;

    @Enumerated(EnumType.STRING)
    private CategoriaReceita categoria;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

}
