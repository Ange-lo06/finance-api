package finance_api.mapper;

import finance_api.dto.ReceitaRequestDTO;
import finance_api.dto.ReceitaResponseDTO;
import finance_api.model.Receita;

public class ReceitaMapper {

    public static Receita toEntity(ReceitaRequestDTO dto){
        Receita receita = new Receita();

        receita.setDescricao(dto.descricao());
        receita.setValor(dto.valor());
        receita.setDataRecebimento(dto.dataRecebimento());
        receita.setCategoria(dto.categoria());

        return receita;
    }

    public static ReceitaResponseDTO toDTO(Receita receita){
        return new ReceitaResponseDTO(
                receita.getId(),
                receita.getDescricao(),
                receita.getValor(),
                receita.getDataRecebimento(),
                receita.getCategoria()
        );
    }
}
