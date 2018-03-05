package com.goodsoft.webapp.entity;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.userdetails.User;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name="users")
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="users_id")
    @NotNull
    private Integer id = 0;

    @Column(name="login")
    @NotBlank(message = "{Person.login.notblank}")
    @Size(min = 5, message = "{Person.login.size}")
    private String login;

    @Column(name="password")
    @NotBlank(message = "{Person.password.notblank}")
    @Size(min = 5, message = "{Person.password.size}")
    private String password;

    @Column(name="email")
    @NotBlank(message = "{Person.email.notblank}")
    @Pattern(regexp = "^[\\w-\\+]+(\\.[\\w]+)*@[\\w-]+(\\.[\\w]+)*(\\.[a-z]{2,})$")
    private String email;

    @Column(name="name")
    @NotBlank(message = "{Person.name.notblank}")
    private String name;

    @Column(name="datedday")
    @NotNull(message = "{Person.datedday.notnull}")
    @Past(message = "{Person.datedday.past}")
    private Date datedday;

    @Column(name="age")
    @NotNull(message = "{Person.age.notnull}")
    @Min(value = 18, message = "{Person.age.sizemin}")
    @Max(value = 150, message = "{Person.age.sizemax}")
    private Integer age;

    @Column(name="salary")
    @NotNull(message = "{Person.salary.notnull}")
    @Min(value = 500, message = "{Person.salary.sizemin}")
    @Max(value = 6000, message = "{Person.salary.sizemax}")
    private Integer salary;

    @ManyToMany
    @Fetch(FetchMode.JOIN)
    @JoinTable(name = "user_to_roles",
            joinColumns = { @JoinColumn(name = "users_id", referencedColumnName = "users_id") },
            inverseJoinColumns = { @JoinColumn(name = "roles_id", referencedColumnName = "role_id") })
    @Valid
    private List<Role> roles;

    public Person() {
    }

    public Person(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public Person(Integer id, String login,
                  String password, String email,
                  String name, Date datedday,
                  Integer age, Integer salary, List<Role> roles) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.email = email;
        this.name = name;
        this.datedday = datedday;
        this.age = age;
        this.salary = salary;
        this.roles = roles;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Person)) return false;
        Person person = (Person) o;
        return getId() == person.getId() &&
                Objects.equals(getLogin(), person.getLogin());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getLogin());
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", name='" + name + '\'' +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDatedday() {
        return datedday;
    }

    public void setDatedday(Date datedday) {
        this.datedday = datedday;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Integer getSalary() {
        return salary;
    }

    public void setSalary(Integer salary) {
        this.salary = salary;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }
}

