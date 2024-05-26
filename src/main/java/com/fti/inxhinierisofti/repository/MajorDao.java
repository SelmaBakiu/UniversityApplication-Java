package com.fti.inxhinierisofti.repository;

import com.fti.softwareing.model.Major;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MajorDao extends CrudRepository<Major,Long> {
    List<Major> findAll();
}
