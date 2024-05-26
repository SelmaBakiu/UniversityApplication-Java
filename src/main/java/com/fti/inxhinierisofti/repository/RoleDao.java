package com.fti.inxhinierisofti.repository;

import com.fti.softwareing.model.Role;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface RoleDao extends CrudRepository<Role, Long> {
    List<Role> findAll();
    List<Role> findByName(String role_admin);

}

