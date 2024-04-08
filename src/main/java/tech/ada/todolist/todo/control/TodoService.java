package tech.ada.todolist.todo.control;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import tech.ada.todolist.dummyapi.DummyJsonApi;
import tech.ada.todolist.dummyapi.DummyTodoResponse;
import tech.ada.todolist.todo.boundary.PrioridadeEnum;
import tech.ada.todolist.todo.entity.Todo;
import tech.ada.todolist.todo.boundary.TodoRequestDto;
import tech.ada.todolist.todo.boundary.TodoResponseDto;

import java.util.List;
import java.util.Optional;

@Service
public class TodoService {
    private final TodoRepository todoRepository;
    private final DummyJsonApi dummyJsonApi;

    public TodoService(TodoRepository todoRepository, DummyJsonApi dummyJsonApi) {
        this.todoRepository = todoRepository;
        this.dummyJsonApi = dummyJsonApi;
    }

    public TodoResponseDto guardaTodo(TodoRequestDto todoDto){
        Todo todo = new Todo();
        todo.setTitulo(todoDto.getTitulo());
        todo.setConcluido(todoDto.getConcluido());
        todo.setPrioridade(todoDto.getPrioridade().name());
        todo.setDescricao(todoDto.getDescricao());


        Todo foiSalvo = todoRepository.save(todo);

        TodoResponseDto responseDto = new TodoResponseDto(foiSalvo.getId(), foiSalvo.getDescricao(),foiSalvo.getConcluido());

        return responseDto;
    }


    public List<TodoResponseDto> pegarTodos(Long pagina, Long quantidadeTodoPorPagina, String ordenacao,String sortBy) {
        Pageable paginacao;
        if(ordenacao.equals("asc")){
            paginacao = PageRequest.of(pagina.intValue(), quantidadeTodoPorPagina.intValue(), Sort.by(sortBy).ascending());
        } else {
            paginacao = PageRequest.of(pagina.intValue(), quantidadeTodoPorPagina.intValue(), Sort.by(sortBy).descending());
        }


        Page<Todo> todos = todoRepository.findAll(paginacao);


        List<TodoResponseDto> todoResponseDtos = todos.stream()
                                                       .map(todo -> new TodoResponseDto(todo.getId(),todo.getDescricao(),todo.getConcluido()))
                                                       .toList();

        return todoResponseDtos;
    }

    public TodoResponseDto pegarTodoPorId(Long id) {
        Optional<Todo> todo = todoRepository.findById(id);

        if(todo.isPresent()){
            Todo todo1 = todo.get();
            TodoResponseDto todoResponseDto = new TodoResponseDto(todo1.getId(), todo1.getDescricao(), todo1.getConcluido());

            return todoResponseDto;
        }

        return null;
    }

    public List<TodoResponseDto> pegarTodoPorDescricao(String desc) {
        //opcao1: jpa query method
        //List<Todo> todos = todoRepository.findByDescricaoContaining(desc);

        //opcao2: Native query
        List<Todo> todos = todoRepository.pegaTodosPorDescricao(desc);

        List<TodoResponseDto> todoResponseDtos = todos.stream()
                                                        .map(todo -> new TodoResponseDto(todo.getId(), todo.getDescricao(), todo.getConcluido()))
                                                        .toList();

        return todoResponseDtos;
    }

    public void apagarTodoPorId(Long id) {
        todoRepository.deleteById(id);
    }

    public TodoResponseDto editarTodo(Long id, TodoRequestDto requestDto) {

        Optional<Todo> todoEncontrado = todoRepository.findById(id);

        if(todoEncontrado.isPresent()){
            Todo todo = todoEncontrado.get();

            todo.setTitulo(requestDto.getTitulo());
            todo.setDescricao(requestDto.getDescricao());
            todo.setConcluido(requestDto.getConcluido());
            todo.setPrioridade(requestDto.getPrioridade().name());

            Todo todoEditado = todoRepository.save(todo);

            return new TodoResponseDto(todoEditado.getId(), todoEditado.getDescricao(), todoEditado.getConcluido());
        }

        return null;
    }

    public void editarParcilamente(Long id, TodoRequestDto requestDto) {
        Optional<Todo> todoEncontrado = todoRepository.findById(id);

        if(todoEncontrado.isPresent()){
            Todo todo = todoEncontrado.get();

            if(requestDto.getTitulo() != null) {
                todo.setTitulo(requestDto.getTitulo());
            }

            if(requestDto.getDescricao() != null) {
                todo.setDescricao(requestDto.getDescricao());
            }

            if(requestDto.getConcluido() != null) {
                todo.setConcluido(requestDto.getConcluido());
            }

            if(requestDto.getPrioridade() != null) {
                todo.setPrioridade(requestDto.getPrioridade().name());
            }

            Todo todoEditado = todoRepository.save(todo);
        }
    }

    public void carregarTodosDoDummyJson() {
        DummyTodoResponse todos = dummyJsonApi.pegarTodos(30L, 0L);

        todos.getTodos().forEach(todoDummy -> {
            Todo todo = new Todo();
            todo.setTitulo("Titulo do todo dummy");
            todo.setPrioridade(PrioridadeEnum.ALTA.name());
            todo.setDescricao(todoDummy.getTodo());
            todo.setConcluido(todoDummy.getCompleted());

            todoRepository.save(todo);
        });
    }
}
