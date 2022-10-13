package ru.kata.spring.boot_security.demo.servises;

import org.springframework.stereotype.Service;
import ru.kata.spring.boot_security.demo.models.Role;
import ru.kata.spring.boot_security.demo.reposetories.RolesReposetories;

@Service
public class RoleService {
    private final RolesReposetories rolesReposetories;

    public RoleService(RolesReposetories rolesReposetories) {
        this.rolesReposetories = rolesReposetories;
    }

    public void saveRole(Role role) {
        rolesReposetories.save(role);
    }
}
