package com.goodsoft.webapp.web.controller;

import com.goodsoft.webapp.entity.Person;
import com.goodsoft.webapp.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class PersonController {
    @Autowired
    private PersonService personService;

    @GetMapping("/person")
    public List<Person> list() {
        return personService.listAllUsers();
    }

    @GetMapping("/person/{id}")
    public Person getPerson(@PathVariable Integer id) {
        return personService.getPerson(id);
    }

    @PostMapping("/person")
    public void createPerson(@RequestBody Person person) {
        person.setId(0);
        personService.save(person);
    }

    @PutMapping("/person/{id}")
    public void updatePerson(@RequestBody Person person, @PathVariable Integer id) {
        person.setId(id);
        personService.save(person);
    }

    @DeleteMapping("/person/{id}")
    public void deletePerson(@PathVariable Integer id) {
        personService.remove(id);
    }


}
