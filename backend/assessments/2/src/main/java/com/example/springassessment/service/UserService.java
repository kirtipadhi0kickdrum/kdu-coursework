package com.example.springassessment.service;


import com.example.springassessment.entity.User;
import com.example.springassessment.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User createUser(User user)
    {

        // validation logic to be added kirti remember it
        return userRepository.save(user);
    }

    public Optional<User> getUserById(Long userId)
    {
        return userRepository.findById(userId);
    }

    public Optional<User> getUserByEmail(String email)
    {
        return userRepository.findByEmail(email);
    }
}
