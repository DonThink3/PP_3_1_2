package ru.kata.spring.boot_security.demo.servises;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.models.Role;
import ru.kata.spring.boot_security.demo.models.User;
import ru.kata.spring.boot_security.demo.reposetories.UsersReposetories;

import java.util.*;

@Service
@Transactional(readOnly = true)
public class UserService {
    private final UsersReposetories usersReposetories;
    private final PasswordEncoder passwordEncoder;

    private final RoleService roleService;

    @Autowired
    public UserService(UsersReposetories usersReposetories, PasswordEncoder passwordEncoder, RoleService roleService) {
        this.usersReposetories = usersReposetories;
        this.passwordEncoder = passwordEncoder;
        this.roleService = roleService;
    }
    @Transactional
    public void save(User user, Set<Role> roles) {
        user.setRoleList(roles);
        roles.forEach(s -> s.setUserList(new ArrayList<>(Collections.singletonList(user))));
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        usersReposetories.save(user);
        roleService.saveAll(roles);
    }

    public List<User> findAllUsers() {
        return usersReposetories.findAll();
    }

    @Transactional
    public void update(int id, User updateUser, Set<Role> roles) {
        updateUser.setId(id);
        save(updateUser, roles);
    }

    @Transactional
    public void delete(int id) {
        usersReposetories.deleteById(id);
    }

    public Optional<User> userByUsername(String username) {
        return usersReposetories.findByUsername(username);
    }

}
