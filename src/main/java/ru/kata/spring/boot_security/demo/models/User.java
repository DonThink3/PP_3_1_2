package ru.kata.spring.boot_security.demo.models;

import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.List;
import org.hibernate.annotations.CascadeType;

@Entity
@Table(name="users")
public class User {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "firstName")
    @NotEmpty(message = "FirstName не должно быть пустым")
    @Size(min = 2, max = 30, message = "FirstName должно быть в пределах от 2 до 30 символов")
    private String firstName;

    @Column(name = "lastName")
    @NotEmpty(message = "LastName не должно быть пустым")
    @Size(min = 2, max = 30, message = "LastName должно быть в пределах от 2 до 30 символов")
    private String lastName;

    @Column(name = "username")
    @NotEmpty(message = "Username не должен быть пустым")
    private String username;

    @Column(name = "email")
    @Email(message = "Несоотвествие форме Email")
    @NotEmpty(message = "Email не должен быть пустым")
    private String email;

    @Column(name = "age")
    @Min(value = 0, message = "Age должен быть больше, чем 0")
    private int age;

    @Column(name = "password")
    @NotEmpty(message = "Password не должен быть пустым")
    private String password;

    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
    @Cascade(CascadeType.DELETE)
    private List<Role> roleList;

    public User() {}

    public User(int id, String firstName, String lastName, String username, String email, int age, String password) {
        this(firstName, lastName, username, email, age, password);
        this.id = id;

    }
    public User(String firstName, String lastName, String username, String email, int age, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.email = email;
        this.age = age;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public List<Role> getRoleList() {
        return roleList;
    }

    public void setRoleList(List<Role> roleList) {
        this.roleList = roleList;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", age=" + age +
                ", password='" + password + '\'' +
                ", roleList=" + roleList +
                '}';
    }
}

