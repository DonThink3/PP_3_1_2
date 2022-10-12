package ru.kata.spring.boot_security.demo.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.kata.spring.boot_security.demo.models.Role;
import ru.kata.spring.boot_security.demo.models.User;
import ru.kata.spring.boot_security.demo.servises.AdminService;
import ru.kata.spring.boot_security.demo.servises.UserService;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Collections;

/**
 * password - admin
 */
@Component
public class AccountOfAdmin {

    private final UserService userService;
    private final AdminService adminService;

    @Autowired
    public AccountOfAdmin(UserService userService, AdminService adminService) {
        this.userService = userService;
        this.adminService = adminService;
    }

    @PostConstruct
    public void admin() {
        User admin = new User("admin", "admin","admin",
                "arbatros@email.com", 20, "$2a$12$3NnpSCW24QGSrDBlctXR/uMDiJZmqE87hQDFKY0odOU.A5/6G2Nd6");
        Role roleUser  = new Role("ROLE_USER");
        Role roleAdmin  = new Role("ROLE_ADMIN");
        admin.setRoleList(new ArrayList<>(Collections.singletonList(roleUser)));
        admin.getRoleList().add(roleAdmin);
        roleUser.setUser(admin);
        roleAdmin.setUser(admin);
        if (userService.userByUsername(admin.getUsername()).isEmpty()) {
            adminService.saveAdmin(admin);
        }
    }
}
