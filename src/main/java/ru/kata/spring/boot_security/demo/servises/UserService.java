package ru.kata.spring.boot_security.demo.servises;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.models.User;
import ru.kata.spring.boot_security.demo.reposetories.UsersReposetories;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class UserService {
    private final UsersReposetories usersReposetories;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UsersReposetories usersReposetories, PasswordEncoder passwordEncoder) {
        this.usersReposetories = usersReposetories;
        this.passwordEncoder = passwordEncoder;
    }
    public User showUser(int id) {
        return usersReposetories.findById(id).orElse(null);
    }
    @Transactional
    public void save(User user) {
        usersReposetories.save(user);
    }

    public List<User> findAllUsers() {
        return usersReposetories.findAll();
    }

    @Transactional
    public void update(int id, User updateUser) {
        updateUser.setId(id);
        updateUser.setPassword(passwordEncoder.encode(updateUser.getPassword()));
        usersReposetories.save(updateUser);
    }

    @Transactional
    public void delete(int id) {
        usersReposetories.deleteById(id);
    }

    public Optional<User> userByUsername(String username) {
        return usersReposetories.findByUsername(username);
    }

}
