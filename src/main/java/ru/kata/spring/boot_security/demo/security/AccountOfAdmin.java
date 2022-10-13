package ru.kata.spring.boot_security.demo.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.kata.spring.boot_security.demo.models.Role;
import ru.kata.spring.boot_security.demo.models.User;
import ru.kata.spring.boot_security.demo.servises.RoleService;
import ru.kata.spring.boot_security.demo.servises.UserService;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * password - admin
 */
@Component
public class AccountOfAdmin {

    private final UserService userService;
    private final RoleService roleService;

    @Autowired
    public AccountOfAdmin(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @PostConstruct
    public void admin() {
        User admin = new User("admin","admin",
                "admin@email.ru", 20, "$2a$12$3NnpSCW24QGSrDBlctXR/uMDiJZmqE87hQDFKY0odOU.A5/6G2Nd6");
        Role roleUser  = new Role("ROLE_USER");
        Role roleAdmin  = new Role("ROLE_ADMIN");
        admin.setRoleList(new ArrayList<>(List.of(roleUser, roleAdmin)));
        roleUser.setUserList(new ArrayList<>(Collections.singletonList(admin)));
        roleAdmin.setUserList(new ArrayList<>(Collections.singletonList(admin)));
        if (userService.userByUsername(admin.getUsername()).isEmpty()) {
            userService.save(admin);
            roleService.saveRole(roleUser);
            roleService.saveRole(roleAdmin);
        }
    }
}
