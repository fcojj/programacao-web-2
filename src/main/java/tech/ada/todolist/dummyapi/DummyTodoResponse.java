package tech.ada.todolist.dummyapi;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import tech.ada.todolist.todo.boundary.TodoItemResponse;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class DummyTodoResponse {

    private List<TodoItemResponse> todos; // -> quotes
    private Long total;
    private Long skip;
    private Long limit;
}
