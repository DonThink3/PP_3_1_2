package ru.kata.spring.boot_security.demo.reposetories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.kata.spring.boot_security.demo.models.Role;

import java.util.Set;

@Repository
public interface RolesReposetories extends JpaRepository<Role, Integer> {
}
