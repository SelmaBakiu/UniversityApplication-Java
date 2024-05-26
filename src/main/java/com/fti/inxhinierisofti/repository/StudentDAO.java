package com.fti.inxhinierisofti.repository;

import com.fti.softwareing.model.Student;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentDAO extends CrudRepository<Student,Long> {
    List<Student> findAll();

    Student findByEmail(String email);

    @Query(value = "SELECT * FROM students WHERE id!=?1",nativeQuery = true)
    List<Student> getAllStudents(Long id);

}

