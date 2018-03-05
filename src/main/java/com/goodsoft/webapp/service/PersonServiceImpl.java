package com.goodsoft.webapp.service;

import com.goodsoft.webapp.dao.person.PersonDao;
import com.goodsoft.webapp.dao.roles.RolesDao;
import com.goodsoft.webapp.entity.Person;
import com.goodsoft.webapp.entity.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PersonServiceImpl implements PersonService {

    @Autowired
    private PersonDao personDao;

    @Autowired
    private RolesDao rolesDao;

    //получение пользователя по логину
    @Override
    public Person getPerson(String login) {
        return personDao.findByLogin(login);
    }

    @Override
    public Person getPerson(Integer id) {
        return personDao.findById(id);
    }

    //просмотр всех пользователей
    @Override
    public List<Person> listAllUsers() {
        return personDao.findAll();
    }

    //поиск роли по id
    @Override
    public Role getRole(Integer id) {
        return rolesDao.findById(id);
    }

    //просмотр всех ролей
    @Override
    public List<Role> listAllRoles() {
        return rolesDao.findAll();
    }

    //авторизация и определение роли
    public String validateUser(String login, String password) {
        String rolePerson = "error";
        Person p = personDao.findByLogin(login);
        try {
            if (p.getLogin() != null && p.getPassword().equals(password)) {
                rolePerson = "user";
                for (Role roles : p.getRoles()) {
                    if (roles.getRole().equals("ADMIN")) {
                        rolePerson = "admin";
                    }
                }
            }
        } catch (NullPointerException e) {

            return rolePerson;
        }
        return rolePerson;
    }

    //новый пользователь/редактирование
    @Override
    @Transactional
    public boolean save(Person user) {
        if (user.getId() == 0) {
            if (getPerson(user.getLogin()) == null) {
                personDao.save(user);
                return true;
            } else {
                return false;
            }
        } else {
            if (user.getId() != 0) {
                if (getPerson(user.getLogin()) == null || getPerson(user.getLogin()).getId() == user.getId()) {
                    personDao.save(user);
                    return true;
                } else {
                    return false;
                }
            }
        }
        return false;
    }

    //удаление пользователя
    @Override
    @Transactional
    public boolean remove(Integer id) {
        Person person = personDao.findById(id);
        personDao.delete(person);
        return true;
    }

}
