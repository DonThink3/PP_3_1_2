package ru.kata.spring.boot_security.demo.reposetories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.kata.spring.boot_security.demo.models.Role;

public interface RolesReposetories extends JpaRepository<Role, Integer> {
}
