package com.burakkocak.eventdrivenms.service;

import com.burakkocak.eventdrivenms.entity.UserFollower;
import com.burakkocak.eventdrivenms.exception.FollowerAlreadyExistsException;
import com.burakkocak.eventdrivenms.repository.UserFollowerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserFollowerService {
    @Autowired
    UserFollowerRepository userFollowerRepository;

    public UserFollower create(UserFollower userFollower) throws FollowerAlreadyExistsException {
        Optional<UserFollower> userFollowerOptional = userFollowerRepository.findById(userFollower.getId());
        if (userFollowerOptional.isPresent())
            throw new FollowerAlreadyExistsException(userFollower.getId());
        return userFollowerRepository.save(userFollower);
    }
}
