package com.goodsoft.webapp.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;


@Entity
@Table(name="roles")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="role_id")
    @NotNull
    private Integer id;

    @Column(name="role")
    @NotNull
    private String role;


    public Role(int id, String role) {
        this.id = id;
        this.role = role;
    }

    public Role() {
    }

    @Override
    public boolean equals(Object o) {
        if (o == null) {
            return false;
        }
        if (!(o instanceof Role)) {
            return false;
        }
        return this.id == ((Role) o).getId();
    }

    @Override
    public int hashCode() {
        return new Integer(id).hashCode();
    }

    @Override
    public String toString() {
        return id.toString();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
