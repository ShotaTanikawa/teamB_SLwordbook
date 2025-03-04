package com.example.slwordbook.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.slwordbook.repository.UserRepository;

@Service
public class UserService {
    
    @Autowired
    private UserRepository userRepository;
}
