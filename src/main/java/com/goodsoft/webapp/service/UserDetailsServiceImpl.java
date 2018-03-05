package com.goodsoft.webapp.service;

import com.goodsoft.webapp.dao.person.PersonDao;
import com.goodsoft.webapp.entity.Person;
import com.goodsoft.webapp.entity.Role;
import com.goodsoft.webapp.entity.SecurityUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private PersonDao personDao;

    @Override
    public SecurityUser loadUserByUsername(String login) throws UsernameNotFoundException {

        Person person = personDao.findByLogin(login);
        Collection<GrantedAuthority> authorities = person.getRoles()
                .stream()
                .map(role -> new SimpleGrantedAuthority(role.getRole()))
                .collect(Collectors.toList());
        SecurityUser securityUser = new SecurityUser(person.getId(), person.getLogin(), person.getPassword(), authorities);
        return securityUser;
    }
}
