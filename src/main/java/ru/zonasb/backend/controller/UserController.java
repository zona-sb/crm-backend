package ru.zonasb.backend.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.zonasb.backend.model.people.User;
import ru.zonasb.backend.service.user.UserService;

import java.util.List;


@AllArgsConstructor
@RestController
@RequestMapping("${base-url}" + UserController.USER_CONTROLLER_PATH)
public class UserController {
    public static final String USER_CONTROLLER_PATH = "/users";
    public static final String ID = "/{id}";

    private final UserService userService;

    @GetMapping(ID)
    public User getUserById(@PathVariable long id) {
        return userService.getUserById(id);
    }

    @GetMapping()
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

}
