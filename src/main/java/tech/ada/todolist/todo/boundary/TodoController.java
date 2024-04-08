package tech.ada.todolist.todo.boundary;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;
import tech.ada.todolist.dummyapi.DummyJsonApi;
import tech.ada.todolist.dummyapi.DummyTodoResponse;
import tech.ada.todolist.todo.control.TodoService;
import tech.ada.todolist.todo.entity.Todo;

import java.util.List;

@RestController//api Todo
@Slf4j
public class TodoController {

    private final List<Todo> todoList;
    private final ModelMapper modelMapper;

    //private final Carro car;
    private final DummyJsonApi dummyJsonApi;

    private static Long CONTADOR = 2L;

    private final TodoService todoService;

    public TodoController(List<Todo> todoList,
                          ModelMapper modelMapper,
                          DummyJsonApi dummyJsonApi, TodoService todoService){
        this.todoList = todoList;
        this.modelMapper = modelMapper;
        this.dummyJsonApi = dummyJsonApi;
        this.todoService = todoService;
    }

    @GetMapping("/todos")
    public List<TodoResponseDto> getTodos(@RequestParam Long pagina,
                                          @RequestParam Long quantidadeTodoPorPagina,
                                          @RequestParam String ordenacao,
                                          @RequestParam String sortBy){
        List<TodoResponseDto> todosResponseDto = todoService.pegarTodos(pagina, quantidadeTodoPorPagina, ordenacao, sortBy);

        return todosResponseDto;
    }

    @GetMapping("/todos/{id}")
    public TodoResponseDto getTodoPorId(@PathVariable Long id){
        TodoResponseDto todoEncontrado = todoService.pegarTodoPorId(id);

        return todoEncontrado;
    }

    @GetMapping("/todos/descricao")
    public List<TodoResponseDto> getTodoPorDescricao(@RequestParam String desc){
        List<TodoResponseDto> todoResponseDtos = todoService.pegarTodoPorDescricao(desc);

        return todoResponseDtos;
    }

    @PostMapping("/todos")
    @ResponseStatus(HttpStatus.CREATED)
    public TodoResponseDto criarTodo(@Valid @RequestBody TodoRequestDto todoRequestDto){
        TodoResponseDto todoDto = todoService.guardaTodo(todoRequestDto);

        return todoDto;
    }

    @DeleteMapping("/todos/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTodo(@PathVariable @Valid @NotNull Long id){
        todoService.apagarTodoPorId(id);
    }

    @PutMapping("/todos/{id}")
    @ResponseStatus(HttpStatus.OK)
    public TodoResponseDto editarTodo(@PathVariable Long id, @Valid @RequestBody TodoRequestDto requestDto){

        TodoResponseDto todoResponseDto = todoService.editarTodo(id, requestDto);

        return todoResponseDto;
    }

    @PatchMapping("/todos/{id}")
    public void editarParcialmenteTodo(@PathVariable Long id, @RequestBody TodoRequestDto requestDto){

        todoService.editarParcilamente(id, requestDto);

    }

    @PostMapping("/todos/carregar")
    @ResponseStatus(HttpStatus.CREATED)
    public void criarTodos(){
        todoService.carregarTodosDoDummyJson();
    }

    @GetMapping("/dummyapi/todos")
    public List<TodoResponseDto> getTodosExternalApi(@RequestParam Long max, @RequestParam Long pulaItens){
        DummyTodoResponse todos = dummyJsonApi.pegarTodos(max, pulaItens);

        List<TodoResponseDto> todosDto = todos.getTodos().stream()
                                                        .map(todoDummyJson -> new TodoResponseDto(todoDummyJson.getId(),todoDummyJson.getTodo(),todoDummyJson.getCompleted()))
                                                        .toList();

        return todosDto;
    }

    @GetMapping("/dummyapi/todos/{id}")
    public TodoResponseDto  getTodosExternalApiPorId(@PathVariable Long id){
        TodoItemResponse todo = dummyJsonApi.pegarTodoPorId(id);

        TodoResponseDto todoDto = new TodoResponseDto();

        todoDto.setId(todo.getId());
        todoDto.setDescricao(todo.getTodo());
        todoDto.setConcluido(todo.getCompleted());


        return todoDto;
    }
}
