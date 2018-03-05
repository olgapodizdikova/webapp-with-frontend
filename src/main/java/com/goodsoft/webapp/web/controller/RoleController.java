package com.goodsoft.webapp.web.controller;

import com.goodsoft.webapp.entity.Role;
import com.goodsoft.webapp.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class RoleController {
    @Autowired
    private PersonService personService;

    @GetMapping("/role")
    public List<Role> list(){
        System.out.println(personService.listAllRoles());
        return personService.listAllRoles();
    }
}
