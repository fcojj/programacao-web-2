package tech.ada.todolist.dummyapi;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CitacoesDto {

    private List<CitacaoItemDto> citacoes;
}
