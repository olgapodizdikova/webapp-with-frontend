package com.goodsoft.webapp.service;

import com.goodsoft.webapp.entity.Person;
import com.goodsoft.webapp.entity.Role;

import java.util.List;

public interface PersonService {

    public String validateUser(String login, String password);

    public Person getPerson(String login);

    public Person getPerson(Integer id);

    public List<Person> listAllUsers();

    public List<Role> listAllRoles();

    public Role getRole(Integer id);

    public boolean save(Person user);

    public boolean remove(Integer id);
}
