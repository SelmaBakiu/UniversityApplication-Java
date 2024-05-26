package com.fti.inxhinierisofti.service;

import com.fti.softwareing.model.Course;
import com.fti.softwareing.model.Student;
import com.fti.softwareing.service.repository.RoleDao;
import com.fti.softwareing.service.repository.StudentDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentService {
    @Autowired
    private StudentDAO studentDAO;

    @Autowired
    private RoleDao roleDao;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private FriendRequestService friendRequestService;


    public Student saveStudent(Student student, BindingResult result) {

        Student potentialStudent = findByEmail(student.getEmail());

        if (potentialStudent != null) {
            result.rejectValue("email", "emailTaken", "email is already in use.");
        }

        if (!student.getPassword().equals(student.getPasswordConfirmation())) {
            result.rejectValue("passwordConfirmation", "Matches", "The Confirm Password must match Password!");
        }

        if (student.getMinor() == null) {
            result.rejectValue("minor", "notFound", "The Minor must not be empty!");
        }

        if (student.getMajor() == null) {
            result.rejectValue("major", "notFound", "The Major must not be empty!");
        }

        if (result.hasErrors()) {
            return null;
        } else {

            student.setPassword(bCryptPasswordEncoder.encode(student.getPassword()));
            student.setRoles(roleDao.findByName("ROLE_USER"));
//            student.setPhoto(potentialStudent.getPhoto());
            studentDAO.save(student);

            return student;
        }
    }

    public Student update(Student student) {
        return studentDAO.save(student);
    }

    public Student findByEmail(String username) {
        return studentDAO.findByEmail(username);
    }

    public Student findStudent(Long id) {
        return studentDAO.findById(id).orElse(null);
    }

    public void addCourseToStudent(Course course, Student student) {
        student.getCourses().add(course);
        studentDAO.save(student);
    }

    public void leaveCourseToStudent(Course course, Student student) {
        student.getCourses().remove(course);
        studentDAO.save(student);
    }

    public List<Student> getFriendSuggestions(Student loggedInUser) {
        List<Student> students = studentDAO.getAllStudents(loggedInUser.getId());
        List<Student> firends = friendRequestService.getFriendsOrPendingRequests(loggedInUser);

        List<Student> suggestions = students.stream().filter(student -> {
            for (Student tmp : firends) {
                if (tmp.getId().equals(student.getId())) return false;
            }
            return true;
        }).collect(Collectors.toList());


        return suggestions;
    }

}

