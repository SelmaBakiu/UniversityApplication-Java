package com.fti.inxhinierisofti.model;


import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "students")
@AllArgsConstructor
@NoArgsConstructor
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(min=3, message="First name must be greater than 3 characters")
    private String firstName;


    @Size(min=3, message="Last name must be greater than 3 characters")
    private String lastName;


    @Size(min=3, message="Password must be greater than 3 characters")
    private String password;

    @Transient
    private String passwordConfirmation;

    private String photo;

    @Email(message="Please enter a valid email!")
    private String email;

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

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "students_roles",
            joinColumns = @JoinColumn(name = "student_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private List<Role> roles;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name="likes",
            joinColumns = @JoinColumn(name = "student_id"),
            inverseJoinColumns = @JoinColumn(name = "comment_id")
    )
    private List<Comment> commentLiked;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="minor_id")
    private Minor minor;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="major_id")
    private Major major;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name="courses_students",
            joinColumns = @JoinColumn(name = "student_id"),
            inverseJoinColumns = @JoinColumn(name = "course_id")
    )
    private List<Course> courses;

    @OneToMany(mappedBy="commentator", fetch = FetchType.LAZY)
    private List<Comment> comments;

    @OneToMany(mappedBy="sender", fetch = FetchType.LAZY)
    private List<FriendRequest> following;

    @OneToMany(mappedBy="receiver", fetch = FetchType.LAZY)
    private List<FriendRequest> followers;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPasswordConfirmation() {
        return passwordConfirmation;
    }

    public void setPasswordConfirmation(String passwordConfirmation) {
        this.passwordConfirmation = passwordConfirmation;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public List<Comment> getCommentLiked() {
        return commentLiked;
    }

    public void setCommentLiked(List<Comment> commentLiked) {
        this.commentLiked = commentLiked;
    }

    public Minor getMinor() {
        return minor;
    }

    public void setMinor(Minor minor) {
        this.minor = minor;
    }

    public Major getMajor() {
        return major;
    }

    public void setMajor(Major major) {
        this.major = major;
    }

    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public List<FriendRequest> getFollowing() {
        return following;
    }

    public void setFollowing(List<FriendRequest> following) {
        this.following = following;
    }

    public List<FriendRequest> getFollowers() {
        return followers;
    }

    public void setFollowers(List<FriendRequest> followers) {
        this.followers = followers;
    }
}
