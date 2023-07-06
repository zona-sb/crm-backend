package ru.zonasb.backend.service.role;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.zonasb.backend.dto.RoleDto;
import ru.zonasb.backend.model.people.Role;
import ru.zonasb.backend.repository.RoleRepository;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@Transactional
@AllArgsConstructor
public class RoleServiceImpl implements RoleService{
    private RoleRepository roleRepository;
    @Override
    public Role getRoleById(final long id) {
        return roleRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("role with that id is not found"));
    }

    @Override
    public Role getRoleByTitle(String title) {
        return roleRepository.findRoleByTitle(title)
                .orElseThrow(() -> new NoSuchElementException("role with that title is not found"));
    }

    @Override
    public List<Role> getAllRoles() {
        return roleRepository.findAll();
    }

    @Override
    public Role createNewRole(final RoleDto roleDto) {
        Role role = Role.builder()
                .title(roleDto.getTitle())
                .build();
        return roleRepository.save(role);
    }

    @Override
    public Role updateRole(final long id,final RoleDto roleDto) {
        Role role = getRoleById(id);
        role.setTitle(roleDto.getTitle());
        return roleRepository.save(role);
    }

    @Override
    public void deleteRole(final long id) {
        roleRepository.deleteById(id);
    }
}
