package ru.zonasb.backend.service.user;

import ru.zonasb.backend.dto.UserDto;
import ru.zonasb.backend.model.User;

import java.util.List;


public interface UserService {

    User getUserById(long id);
    List<User> getAllUsers();
    User createNewUser(UserDto userDto);

    User updateUser(long id, UserDto userDto);

    String getCurrentUserName();

    User getCurrentUser();
}
