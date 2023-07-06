package ru.zonasb.backend.service.registration;

import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.zonasb.backend.dto.fromFront.RegistrationDto;
import ru.zonasb.backend.model.people.Manager;
import ru.zonasb.backend.model.people.Person;
import ru.zonasb.backend.model.people.Role;
import ru.zonasb.backend.model.people.User;
import ru.zonasb.backend.repository.ManagerRepository;
import ru.zonasb.backend.repository.PersonRepository;
import ru.zonasb.backend.repository.UserRepository;
import ru.zonasb.backend.service.role.RoleService;

@Service
@Transactional
@AllArgsConstructor
public class RegistrationServiceImpl implements RegistrationService{
    private RoleService roleService;
    private final UserRepository userRepository;
    private final PersonRepository personRepository;
    private final ManagerRepository managerRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public RegistrationDto registrationNewManager(final RegistrationDto registrationDto) {
        Role role = roleService.getRoleByTitle("Менеджер");


        User user = User.builder()
                .email(registrationDto.getEmail())
                .password(passwordEncoder.encode(registrationDto.getPassword()))
                .role(role)
                .build();
        user = userRepository.save(user);

        Person person = Person.builder()
                .name(registrationDto.getName())
                .phone(registrationDto.getPhone())
                .email(registrationDto.getEmail())
                .build();
        person = personRepository.save(person);

        Manager manager = Manager.builder()
                .person(person)
                .user(user)
                .build();
        managerRepository.save(manager);

        return registrationDto;
    }
}
