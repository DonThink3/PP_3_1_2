package ru.kata.spring.boot_security.demo.servises;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.models.User;
import ru.kata.spring.boot_security.demo.reposetories.AdminReposetories;
import ru.kata.spring.boot_security.demo.reposetories.RolesReposetories;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class AdminService {
    private final AdminReposetories adminReposetories;
    private final RolesReposetories rolesReposetories;

    @Autowired
    public AdminService(AdminReposetories adminReposetories, RolesReposetories rolesReposetories) {
        this.adminReposetories = adminReposetories;
        this.rolesReposetories = rolesReposetories;
    }

    public List<User> findAllUsers() {
        return adminReposetories.findAll();
    }

    @Transactional
    public void saveAdmin(User user) {
        adminReposetories.save(user);
        rolesReposetories.saveAll(user.getRoleList());
    }
}
