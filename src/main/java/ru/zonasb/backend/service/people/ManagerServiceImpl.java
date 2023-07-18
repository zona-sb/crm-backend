package ru.zonasb.backend.service.people;


import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.zonasb.backend.dto.people.ManagerDto;
import ru.zonasb.backend.model.people.Manager;
import ru.zonasb.backend.repository.ManagerRepository;
import ru.zonasb.backend.service.people.interfase.ManagerService;
import ru.zonasb.backend.service.people.interfase.PersonService;
import ru.zonasb.backend.service.people.interfase.UserService;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@Transactional
@AllArgsConstructor
public class ManagerServiceImpl implements ManagerService {
    private final ManagerRepository managerRepository;
    private final UserService userService;
    private final PersonService personService;
    @Override
    public Manager getManagerById(final long id) {
        return managerRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("manager with that id is not found"));
    }

    @Override
    public List<Manager> getAllManagers() {
        return managerRepository.findAll();
    }

    @Override
    public Manager createNewManager(final ManagerDto managerDto) {
        Manager manager = Manager.builder()
                .user(userService.getUserById(managerDto.getUserId()))
                .person(personService.getPersonById(managerDto.getPersonId()))
                .build();
        return managerRepository.save(manager);
    }

    @Override
    public Manager updateManager(final long id, final ManagerDto managerDto) {
        return null;
    }

    @Override
    public void deleteManager(final long id) {
        managerRepository.deleteById(id);
    }
}
