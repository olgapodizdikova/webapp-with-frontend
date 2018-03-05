package com.goodsoft.webapp.web.controller;

import com.goodsoft.webapp.config.JwtFilter;
import com.goodsoft.webapp.config.JwtUtils;
import com.goodsoft.webapp.entity.AuthToken;
import com.goodsoft.webapp.entity.Person;
import com.goodsoft.webapp.entity.SecurityUser;
import com.goodsoft.webapp.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.Collection;

@RestController
@RequestMapping("/api")
public class LoginController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private PersonService personService;

    @PostMapping("/login")
    public ResponseEntity loginController(@RequestBody LoginData loginData, HttpServletResponse response) {
        String path;
        try {
            final Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginData.getUsername(), loginData.getPassword()));
            if (authentication != null) {
                SecurityContextHolder.getContext().setAuthentication(authentication);
                final SecurityUser user = (SecurityUser) authentication.getPrincipal();
                final String token = jwtUtils.generateToken(user);
                Collection<GrantedAuthority> authorities = (Collection<GrantedAuthority>) SecurityContextHolder
                        .getContext()
                        .getAuthentication().getAuthorities();
                if (authorities.contains(new SimpleGrantedAuthority("ADMIN"))) {
                    path = "userList";
                } else {
                    if (authorities.contains(new SimpleGrantedAuthority("USER"))) {
                        path = "userWelcome";
                    } else {
                        path = "userWelcome";
                    }
                }
                response.setHeader(JwtFilter.TOKEN_HEADER, jwtUtils.generateToken(user));
                response.setStatus(HttpServletResponse.SC_OK);
                return ResponseEntity.ok(new AuthToken(token, path));
            }
        } catch (LockedException e) {
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            return null;
        } catch (Exception e) {
            //ignore
        }
        response.setStatus(HttpServletResponse.SC_NO_CONTENT);
        return null;
    }


    public static class LoginData {
        private String username;
        private String password;


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
    }

}
