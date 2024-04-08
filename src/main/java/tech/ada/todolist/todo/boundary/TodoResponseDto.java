package tech.ada.todolist.todo.boundary;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class TodoResponseDto {

    private Long id;
    private String descricao;
    private Boolean concluido;
}
