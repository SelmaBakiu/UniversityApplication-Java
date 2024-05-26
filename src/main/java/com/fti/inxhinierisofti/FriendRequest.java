package com.fti.softwareing.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "requests")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FriendRequest {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Column(updatable=false)
    @DateTimeFormat(pattern="MM/dd/yyyy hh")
    private Date createdAt;
    @DateTimeFormat(pattern="MM/dd/yyyy hh")
    private Date updatedAt;

    private Boolean answer;

    private Boolean isSeen;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="sender_id")
    private Student sender;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="receiver_id")
    private Student receiver;


    @PrePersist
    protected void onCreate(){
        this.createdAt = new Date();
    }
    @PreUpdate
    protected void onUpdate(){
        this.updatedAt = new Date();
    }


    public FriendRequest (Student sender,Student receiver){
        this.sender=sender;
        this.receiver=receiver;
        this.answer=null;
        this.isSeen=false;
    }
}
