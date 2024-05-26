package com.fti.inxhinierisofti.repository;

import com.fti.softwareing.model.Course;
import com.fti.softwareing.model.Major;
import com.fti.softwareing.model.Minor;
import com.fti.softwareing.model.Student;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseDao extends CrudRepository<Course,Long> {


    List<Course> findAll();
    List<Course> findAllByStudentsNotContaining(Student student);
    List<Course> findByNameContainingIgnoreCaseAndStudentsNotContaining(String title,Student student);


//    @Query(value = "Select c.* from courses c join courses_students cs  on cs.course_id=c.id " +
//            "where cs.student_id!=?1 and (c.minor_id=?2 or c.major_id=?3)",nativeQuery = true)
//    List<Course> findNotAssignmentCourses(Long idStudent, Long minorId, Long MajorId);

    List<Course> findAllByStudentsContainingAndMajorContainingOrderByName(Student student, Major major);
    List<Course> findAllByStudentsContainingAndMinorContainingOrderByName(Student student, Minor minor);

    List<Course> findAllByStudentsContainingOrderByName(Student student);
}

