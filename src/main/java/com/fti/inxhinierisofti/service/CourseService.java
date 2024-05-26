package com.fti.inxhinierisofti.service;

import com.fti.softwareing.model.Comment;
import com.fti.softwareing.model.Course;
import com.fti.softwareing.model.Student;
import com.fti.softwareing.service.repository.CommentDao;
import com.fti.softwareing.service.repository.CourseDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CourseService {

    @Autowired
    private CourseDao courseDao;
    @Autowired
    private CommentDao commentDao;

    public List<Course> getAllCourses(){
        return courseDao.findAll();
    }

    public List<Course> findNotAssignedCourses(Student student){
        List<Course> courses=courseDao.findAllByStudentsNotContaining(student);
        List<Course> courseList= new ArrayList<>();

        for (Course c :
                courses) {
            if(c.getMinor().getId()==student.getMinor().getId() || c.getMajor().getId()==student.getMajor().getId())
                courseList.add(c);
        }
        return courseList;
//        return courseDao.findNotAssignmentCourses(student.getId(),student.getMinor().getId(),student.getMajor().getId());
    }


    public Course findCourse(Long id){
        return courseDao.findById(id).orElse(null);
    }

    public List<Course> searchCourses(String title,Student student){
        return courseDao.findByNameContainingIgnoreCaseAndStudentsNotContaining(title,student);
    }
//    public  List<Course> allMinorCourses(Student student, Minor minor){
//        return courseDao.findAllByStudentsContainingAndMinorContainingOrderByName(student,minor);
//    }
//
//    public  List<Course> allMajorCourses(Student student, Major major){
//        return courseDao.findAllByStudentsContainingAndMajorContainingOrderByName(student,major);
//    }

    public List<Course> studentCourses(Student student){
        return  courseDao.findAllByStudentsContainingOrderByName(student);
    }



    public void addComment(Comment comment){
        commentDao.save(comment);
    }

    public Comment findOneComment(Long id){
        return commentDao.findById(id).orElse(null);
    }

    public void addLiker(Student student,Comment comment){
        List<Student> likers= comment.getLikers();
        likers.add(student);
        commentDao.save(comment);
    }

    public void removeLiker(Student student, Comment comment){
        List<Student> likers= comment.getLikers();
        likers.remove(student);
        commentDao.save(comment);
    }

}

