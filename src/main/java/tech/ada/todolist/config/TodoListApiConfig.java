package tech.ada.todolist.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import tech.ada.todolist.todo.boundary.PrioridadeEnum;
import tech.ada.todolist.todo.entity.Todo;
import tech.ada.todolist.user.entity.User;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class TodoListApiConfig {

    @Bean
    public ModelMapper modelMapper(){
        return new ModelMapper();
    }

    /*@Bean
    public Carro car(){
        return new Carro("verde");
    }*/

    @Bean
    public List<Todo> todoList(){

        List<Todo> todosList = new ArrayList<>();

        Todo todo = new Todo(1L, "Comprar miojo!", "Lista de supermecado", PrioridadeEnum.ALTA.name(), false);
        Todo todo2 = new Todo(2L, "Comprar saco de lixo!", "Lista de supermecado",PrioridadeEnum.BAIXO.name(), false);
        todosList.add(todo);
        todosList.add(todo2);

        return todosList;
    }

    @Bean
    public List<User> userList(){

        List<User> userList = new ArrayList<>();

        User user1 = new User(1L, "user1@ada.tech", "admin");
        User user2 = new User(2L, "user2@ada.tech", "normal");
        userList.add(user1);
        userList.add(user2);

        return userList;
    }
}
