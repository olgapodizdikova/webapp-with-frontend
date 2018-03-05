package com.goodsoft.webapp.entity;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

public class SecurityUser extends User{

    private static final long serialVersionUID = 4171775680716658550L;

    private Integer id;

    public SecurityUser(Integer id, String username, String password, Collection<? extends GrantedAuthority> authorities){
        super(username, password, authorities);
        this.id = id;
    }

    public SecurityUser(Integer id, String username, String password, Collection<? extends GrantedAuthority> authorities, boolean blocked) {
        super(username, password, true, true, true, !blocked, authorities);
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id){
        this.id = id;
    }
}
