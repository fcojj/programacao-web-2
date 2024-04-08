package tech.ada.todolist.dummyapi;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CitacaoItemDto {
    private String citacao;
    private String autor;
}
