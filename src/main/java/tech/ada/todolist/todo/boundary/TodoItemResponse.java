package tech.ada.todolist.todo.boundary;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class TodoItemResponse {

    private Long id;
    private String todo;
    private Boolean completed;
    private Long userId;

    /**
     * {
     * "id": 1,
     * "quote": "Your heart is the size of an ocean. Go find yourself in its hidden depths.",
     * "author": "Rumi"
     * }
     */
}
