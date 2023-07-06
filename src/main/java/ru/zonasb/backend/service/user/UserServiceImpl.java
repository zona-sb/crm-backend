package ru.zonasb.backend.service.user;

import lombok.AllArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.zonasb.backend.dto.UserDto;
import ru.zonasb.backend.model.User;
import ru.zonasb.backend.repository.UserRepository;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@Transactional
@AllArgsConstructor
public class UserServiceImpl implements UserService{
    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;


    @Override
    public User getUserById(final long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("user with that id is not found"));
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User createNewUser(final UserDto userDto) {
        final User user = new User();
        user.setEmail(userDto.getEmail());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        return userRepository.save(user);
    }

    @Override
    public User updateUser(final long id, final UserDto userDto) {
        final User userToUpdate = getUserById(id);
        userToUpdate.setEmail(userDto.getEmail());
        userToUpdate.setPassword(passwordEncoder.encode(userDto.getPassword()));
        return userRepository.save(userToUpdate);
    }

    @Override
    public String getCurrentUserName() {
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }

    @Override
    public User getCurrentUser() {
        return userRepository.findByEmail(getCurrentUserName())
                .orElseThrow(() -> new NoSuchElementException("user with that email is not found"));
    }
}
