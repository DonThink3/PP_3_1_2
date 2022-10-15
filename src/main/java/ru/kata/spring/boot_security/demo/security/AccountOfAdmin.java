package ru.kata.spring.boot_security.demo.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.kata.spring.boot_security.demo.models.Role;
import ru.kata.spring.boot_security.demo.models.User;
import ru.kata.spring.boot_security.demo.servises.RoleService;
import ru.kata.spring.boot_security.demo.servises.UserService;

import javax.annotation.PostConstruct;
import java.util.*;

/**
 * password - admin
 */
@Component
public class AccountOfAdmin {

    private final UserService userService;

    @Autowired
    public AccountOfAdmin(UserService userService) {
        this.userService = userService;
    }

    @PostConstruct
    public void admin() {
        User admin = new User("admin","admin",
                "admin@email.ru", 20, "admin");
        Role roleUser  = new Role("ROLE_USER");
        Role roleAdmin  = new Role("ROLE_ADMIN");
        if (userService.userByUsername(admin.getUsername()).isEmpty()) {
            userService.save(admin, new HashSet<>(Set.of(roleUser, roleAdmin)));
        }
    }
}
