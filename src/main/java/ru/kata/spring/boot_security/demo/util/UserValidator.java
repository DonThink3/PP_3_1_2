package ru.kata.spring.boot_security.demo.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.kata.spring.boot_security.demo.models.User;
import ru.kata.spring.boot_security.demo.servises.UserService;

import java.util.Optional;

@Component
public class UserValidator implements Validator {

    private final UserService userService;

    @Autowired
    public UserValidator(UserService userService) {
        this.userService = userService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return User.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        User user = (User) target;
        Optional<User> userCheckByUsername = userService.userByUsername(user.getUsername());
        Optional<User> userCheckByEmail = userService.userByEmail(user.getEmail());
        if (userCheckByUsername.isPresent()) {
            errors.rejectValue("username", "", "Данное имя пользователя уже занято");
        } else if (userCheckByEmail.isPresent()) {
            errors.rejectValue("email", "", "Данный email уже существует");
        }
    }
}
