package ru.zonasb.backend.service.role;

import ru.zonasb.backend.dto.people.RoleDto;
import ru.zonasb.backend.model.people.Role;

import java.util.List;


public interface RoleService {
    Role getRoleById(long id);
    Role getRoleByTitle(String title);
    List<Role> getAllRoles();
    Role createNewRole(RoleDto roleDto);
    Role updateRole(long id, RoleDto roleDto);
    void deleteRole(long id);

}
