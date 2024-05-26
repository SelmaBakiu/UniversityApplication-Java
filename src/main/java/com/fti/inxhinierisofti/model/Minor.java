package com.fti.inxhinierisofti.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "minors")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Minor {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Size(min=2, message="Minor name  must be greater than 1 character")
    private String name;

    @Column(updatable=false)
    @DateTimeFormat(pattern="MM/dd/yyyy hh")
    private Date createdAt;
    @DateTimeFormat(pattern="MM/dd/yyyy hh")
    private Date updatedAt;

    @PrePersist
    protected void onCreate(){
        this.createdAt = new Date();
    }
    @PreUpdate
    protected void onUpdate(){
        this.updatedAt = new Date();
    }


    @OneToMany(mappedBy="minor", fetch = FetchType.LAZY)
    private List<Student> students;

    @OneToMany(mappedBy="minor", fetch = FetchType.LAZY)
    private List<Course> courses;


}

