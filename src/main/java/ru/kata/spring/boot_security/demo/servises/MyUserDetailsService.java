package ru.kata.spring.boot_security.demo.servises;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.models.User;
import ru.kata.spring.boot_security.demo.reposetories.UsersReposetories;
import ru.kata.spring.boot_security.demo.security.MyUserDetails;

import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class MyUserDetailsService implements UserDetailsService {

    private final UsersReposetories usersReposetories;

    @Autowired
    public MyUserDetailsService(UsersReposetories usersReposetories) {
        this.usersReposetories = usersReposetories;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> userDetails = usersReposetories.findByUsername(username);
        if (userDetails.isEmpty()) {
            throw new UsernameNotFoundException("Пользователь не найден");
        } else {
            return new MyUserDetails(userDetails.get());
        }
    }
}
