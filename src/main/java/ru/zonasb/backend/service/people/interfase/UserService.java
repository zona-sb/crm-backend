package ru.zonasb.backend.service.people.interfase;

import ru.zonasb.backend.dto.people.UserDto;
import ru.zonasb.backend.model.people.User;

import java.util.List;


public interface UserService {

    User getUserById(long id);
    List<User> getAllUsers();
    User createNewUser(UserDto userDto);

    User updateUser(long id, UserDto userDto);

    String getCurrentUserName();

    User getCurrentUser();
}
