package com.goodsoft.webapp.dao.roles;

import com.goodsoft.webapp.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RolesDao extends JpaRepository<Role, Integer> {
    Role findById(Integer id);
}

