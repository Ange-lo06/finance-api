package finance_api.service;

import finance_api.exception.RecursoNaoEncontradoException;
import finance_api.model.Receita;
import finance_api.repository.ReceitaRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ReceitaServiceTest {

    @Mock
    private ReceitaRepository repository;

    @Mock
    private UsuarioService usuarioService;

    @InjectMocks
    private ReceitaService service;

    @Test
    void deveSalvarReceita() {

        Receita receita = Receita.builder()
                .descricao("Salário")
                .valor(new BigDecimal("5000"))
                .build();

        when(repository.save(any(Receita.class)))
                .thenReturn(receita);

        Receita resultado = service.salvar(receita);

        assertNotNull(resultado);
        assertEquals("Salário", resultado.getDescricao());
        assertEquals(new BigDecimal("5000"), resultado.getValor());

        verify(repository, times(1)).save(receita);
    }

    void deveBuscarReceitaPorID(){

        Receita receita = Receita.builder()
                .id(1L)
                .descricao("Salário")
                .build();

        when(repository.findById(1L))
                .thenReturn(Optional.of(receita));

        Receita resultado = service.buscarPorId(1L);

        assertEquals("Salário", resultado.getDescricao());
    }

    void deveLancarExcessaoQuandoReceitaNaoExiste(){

        when(repository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(RecursoNaoEncontradoException.class,()-> service.buscarPorId(1L));
    }

    void deveExcluirReceita(){

        Receita receita = Receita.builder()
                .id(1L)
                .build();

        when(repository.findById(1L)).thenReturn(Optional.of(receita));

        service.excluir(1L);

        verify(repository).deleteById(1L);
    }
}