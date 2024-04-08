package tech.ada.todolist.user.boundary;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import tech.ada.todolist.user.entity.User;

import java.util.List;

@RestController//api User
public class UserController {

    private List<User> userList;

    public UserController(List<User> userList){
        this.userList = userList;
    }

    @GetMapping("/users")
    public List<User> getUsers(){

        return userList;
    }
}
