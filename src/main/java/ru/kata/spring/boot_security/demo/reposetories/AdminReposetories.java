package ru.kata.spring.boot_security.demo.reposetories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.kata.spring.boot_security.demo.models.User;

@Repository
public interface AdminReposetories extends JpaRepository<User, Integer> {

}
