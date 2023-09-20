package ru.zonasb.backend.service.people.interfase;

import ru.zonasb.backend.dto.people.ManagerDto;
import ru.zonasb.backend.model.people.Manager;

import java.util.List;

public interface ManagerService {
    Manager getManagerById(long id);
    Manager getManagerByUserId(long userId);

    List<Manager> getAllManagers();

    Manager createNewManager(ManagerDto managerDto);

    Manager updateManager(long id, ManagerDto managerDto);

    void deleteManager(long id);
    
}
