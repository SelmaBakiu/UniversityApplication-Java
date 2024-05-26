package com.fti.inxhinierisofti.service;

import com.fti.softwareing.model.Role;
import com.fti.softwareing.model.Student;
import com.fti.softwareing.service.repository.StudentDAO;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StudentDetailsServiceImplementation implements UserDetailsService {
    private StudentDAO studentDAO;

    public StudentDetailsServiceImplementation(StudentDAO studentDAO){
        this.studentDAO = studentDAO;
    }


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Student student = studentDAO.findByEmail(email);

        if(student == null) {
            throw new UsernameNotFoundException("Student not found");
        }

        return new org.springframework.security.core.userdetails.User(student.getEmail(), student.getPassword(), getAuthorities(student));
    }




    private List<GrantedAuthority> getAuthorities(Student student){
        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
        for(Role role : student.getRoles()) {
            GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(role.getName());
            authorities.add(grantedAuthority);
        }
        return authorities;
    }
}

