package com.fti.inxhinierisofti.repository;

import com.fti.softwareing.model.FriendRequest;
import com.fti.softwareing.model.Student;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FriendRequestDao extends CrudRepository<FriendRequest, Long> {

    List<FriendRequest> findAll();

    List<FriendRequest> findAllByReceiverIsAndAnswerIsNull(Student receiver);

    List<FriendRequest> findAllBySenderIsAndAndAnswerIsNotNullAndIsSeenIsFalse(Student sender);


    List<FriendRequest> findAllBySenderIsAndAndAnswerIsTrue(Student sender);

    List<FriendRequest> findAllByReceiverIsAndAndAnswerIsTrue(Student receiver);

    List<FriendRequest> findAllBySenderIsAndAndAnswerIsNull(Student sender);

    List<FriendRequest> findAllByReceiverIsAndAndAnswerIsNull(Student receiver);




}
