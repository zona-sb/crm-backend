package ru.zonasb.backend.controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.zonasb.backend.dto.people.UserDto;
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

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public User createNewUser(@RequestBody @Valid UserDto userDto) {
        return userService.createNewUser(userDto);
    }
}
