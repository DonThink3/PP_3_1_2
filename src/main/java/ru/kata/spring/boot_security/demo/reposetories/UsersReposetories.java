package ru.kata.spring.boot_security.demo.reposetories;


import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.kata.spring.boot_security.demo.models.User;

import java.util.Optional;

@Repository
public interface UsersReposetories extends JpaRepository<User, Integer> {
    @EntityGraph(value = "User.role", type = EntityGraph.EntityGraphType.LOAD)
    Optional<User> findByUsername(String username);
}
