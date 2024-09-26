package com.example.springhandson5.service;

import com.example.springhandson5.dto.UserDTO;
import com.example.springhandson5.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository)
    {
        this.userRepository = userRepository;
    }

    public UserDTO getUserDataByName(String name)
    {
        return userRepository.getUserDataByName(name);
    }

    public List<UserDTO> getAllUserData()
    {
        return userRepository.getAllUserData();
    }

    public void addUserData(String username, String fullname, String password, String email, String role)
    {
        userRepository.addUserData(username, fullname, password, email, role);
    }
}
