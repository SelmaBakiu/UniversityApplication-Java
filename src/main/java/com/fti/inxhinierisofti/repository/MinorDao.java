package com.fti.inxhinierisofti.repository;

import com.fti.softwareing.model.Minor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MinorDao extends CrudRepository<Minor,Long> {
    List<Minor> findAll();
}
