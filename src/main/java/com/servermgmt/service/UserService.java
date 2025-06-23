package com.servermgmt.service;

import com.servermgmt.dto.UserRegistrationDTO;
import com.servermgmt.model.User;
import com.servermgmt.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
}