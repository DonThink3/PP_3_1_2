package ru.kata.spring.boot_security.demo.servises;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.models.Role;
import ru.kata.spring.boot_security.demo.models.User;
import ru.kata.spring.boot_security.demo.reposetories.RolesReposetories;
import ru.kata.spring.boot_security.demo.reposetories.UsersReposetories;

import java.util.ArrayList;
import java.util.Collections;

@Service
public class RegistrationService {
    private final UsersReposetories usersReposetories;
    private final RolesReposetories rolesReposetories;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public RegistrationService(UsersReposetories usersReposetories, RolesReposetories rolesReposetories, PasswordEncoder passwordEncoder) {
        this.usersReposetories = usersReposetories;
        this.rolesReposetories = rolesReposetories;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public void register(User user) {
        Role role = new Role("ROLE_USER");
        user.setRoleList(new ArrayList<>(Collections.singletonList(role)));
        role.setUserList(new ArrayList<>(Collections.singletonList(user)));
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        usersReposetories.save(user);
        rolesReposetories.save(role);
    }
}
