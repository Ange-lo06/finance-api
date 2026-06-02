package finance_api.model;

import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "despesas")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Despesa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String descricao;

    private BigDecimal valor;

    private LocalDate dataPagamento;

    @Enumerated(EnumType.STRING)
    private CategoriaDespesa categoria;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

}
