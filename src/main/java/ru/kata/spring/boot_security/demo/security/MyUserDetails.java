package ru.kata.spring.boot_security.demo.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import ru.kata.spring.boot_security.demo.models.Role;
import ru.kata.spring.boot_security.demo.models.User;

import java.util.*;

public class MyUserDetails implements UserDetails {

    private final User user;

    @Autowired
    public MyUserDetails(User user) {
        this.user = user;
    }

    @Override
    @EntityGraph(value = "User.role", type = EntityGraph.EntityGraphType.LOAD)
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Set<GrantedAuthority> list = new HashSet<>();
        for (Role role : user.getRoleList()) {
            list.add(new SimpleGrantedAuthority(role.getAuthority()));
        }
        return list;
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public User getUser() {
        return this.user;
    }
}
