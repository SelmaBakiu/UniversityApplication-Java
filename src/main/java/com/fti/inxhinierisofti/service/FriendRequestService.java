package com.fti.inxhinierisofti.service;

import com.fti.softwareing.model.FriendRequest;
import com.fti.softwareing.model.Student;
import com.fti.softwareing.service.repository.FriendRequestDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FriendRequestService {

    @Autowired
    private FriendRequestDao friendRequestDao;


    public FriendRequest getFriendRequest(Long id) {
        return friendRequestDao.findById(id).orElse(null);
    }

    public List<FriendRequest> getFriendRequests(Student student) {
        return friendRequestDao.findAllByReceiverIsAndAnswerIsNull(student);
    }

    public List<FriendRequest> getSenderAnswers(Student student) {
        return friendRequestDao.findAllBySenderIsAndAndAnswerIsNotNullAndIsSeenIsFalse(student);
    }

    public List<FriendRequest> getAcceptedRequests(Student studnet) {
        return friendRequestDao.findAllByReceiverIsAndAndAnswerIsTrue(studnet);
    }

    public List<FriendRequest> getAcceptedResponses(Student studnet) {
        return friendRequestDao.findAllBySenderIsAndAndAnswerIsTrue(studnet);
    }

    public List<Student> getFriendsOrPendingRequests(Student student) {
        List<FriendRequest> requests = friendRequestDao.findAllByReceiverIsAndAndAnswerIsTrue(student);
        List<FriendRequest> requests1 = friendRequestDao.findAllBySenderIsAndAndAnswerIsTrue(student);
        List<FriendRequest> requests2 = friendRequestDao.findAllBySenderIsAndAndAnswerIsNull(student);
        List<FriendRequest> requests3 = friendRequestDao.findAllByReceiverIsAndAndAnswerIsNull(student);
        List<Student> friends = new ArrayList<>();

        for (FriendRequest request : requests) {
            friends.add(request.getSender() );
        }
        for (FriendRequest request : requests1) {
            friends.add(request.getReceiver());
        }
        for (FriendRequest request : requests2) {
            friends.add(request.getReceiver());
        }
        for (FriendRequest request : requests3) {
            friends.add(request.getSender());
        }

        return friends;
    }
    public List<Student> getFriends(Student student) {
        List<FriendRequest> requests = friendRequestDao.findAllByReceiverIsAndAndAnswerIsTrue(student);
        List<FriendRequest> requests1 = friendRequestDao.findAllBySenderIsAndAndAnswerIsTrue(student);

        List<Student> friends = new ArrayList<>();

        for (FriendRequest request : requests) {
            friends.add(request.getSender());
        }
        for (FriendRequest request : requests1) {
            friends.add(request.getSender());
        }

        return friends;
    }

    public FriendRequest requestFriend(Student sender, Student receiver) {
        FriendRequest friendRequest = new FriendRequest(sender, receiver);
        return friendRequestDao.save(friendRequest);
    }

    public FriendRequest removeAnswer(FriendRequest friendRequest) {
        friendRequest.setIsSeen(true);
        return friendRequestDao.save(friendRequest);
    }

    public FriendRequest declineRequest(FriendRequest friendRequest) {
        friendRequest.setAnswer(false);
        return friendRequestDao.save(friendRequest);
    }

    public FriendRequest removeFriend(FriendRequest friendRequest) {
        friendRequest.setAnswer(false);
        return friendRequestDao.save(friendRequest);
    }

    public FriendRequest accepRequest(FriendRequest friendRequest) {
        friendRequest.setAnswer(true);
        return friendRequestDao.save(friendRequest);
    }

}

