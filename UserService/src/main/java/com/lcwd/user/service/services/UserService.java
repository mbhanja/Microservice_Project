package com.lcwd.user.service.services;

import com.lcwd.user.service.entities.User;

import java.util.List;

public interface UserService {

    //crete User
    User saveUser(User user);

    //get list of users
    List<User> getAllUsers();

    //get single user by id
    User getUserById(String userId);

    //TODO: delete

    //TODO: update
}
