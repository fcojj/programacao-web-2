package tech.ada.todolist.dummyapi;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import tech.ada.todolist.todo.boundary.TodoItemResponse;

@FeignClient(name="citacoesdummyjson", url="https://dummyjson.com")
public interface CitacoesDummyJsonApi {


    @GetMapping("/quotes")
    CitacoesDummyJson pegarCitacoes(@RequestParam Long limit,
                                 @RequestParam Long skip);

}
