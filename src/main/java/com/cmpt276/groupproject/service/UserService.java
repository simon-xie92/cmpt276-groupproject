package com.cmpt276.groupproject.service;

import com.cmpt276.groupproject.models.User;
import com.cmpt276.groupproject.models.UserRepository;

import jakarta.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User getCurrentUser(HttpSession session) {
        return (User) session.getAttribute("session_user");
    }

    
