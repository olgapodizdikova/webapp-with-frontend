package com.goodsoft.webapp.dao.person;

import com.goodsoft.webapp.entity.Person;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonDao extends JpaRepository<Person, Integer> {
    Person findByLogin(String login);
    Person findById(Integer id);
}
