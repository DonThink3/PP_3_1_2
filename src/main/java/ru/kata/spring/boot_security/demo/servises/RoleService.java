package ru.kata.spring.boot_security.demo.servises;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.models.Role;
import ru.kata.spring.boot_security.demo.reposetories.RolesReposetories;

import java.util.HashSet;
import java.util.Set;

@Service
@Transactional(readOnly = true)
public class RoleService {
    private final RolesReposetories rolesReposetories;

    public RoleService(RolesReposetories rolesReposetories) {
        this.rolesReposetories = rolesReposetories;
    }
    @Transactional
    public void saveAll(Set<Role> roles) {
        rolesReposetories.saveAll(roles);
    }
    public Set<Role> findAllRoles() {
        return new HashSet<>(rolesReposetories.findAll());
    }

}
