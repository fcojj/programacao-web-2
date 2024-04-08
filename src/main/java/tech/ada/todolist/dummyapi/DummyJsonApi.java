package tech.ada.todolist.dummyapi;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import tech.ada.todolist.todo.boundary.TodoItemResponse;

//https://dummyjson.com/quotes -> GET
@FeignClient(name="tododummyjson", url="https://dummyjson.com")
public interface DummyJsonApi {

    @GetMapping("/todos")
    DummyTodoResponse pegarTodos(@RequestParam Long limit,
                                 @RequestParam Long skip);

    @GetMapping("/todos/{id}")
    TodoItemResponse pegarTodoPorId(@PathVariable Long id);

}
