package com.example.springhandson5.controller;

import com.example.springhandson5.dto.UserDTO;
import com.example.springhandson5.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService)
    {
        this.userService = userService;
    }


    @GetMapping("/users/{name}")
    @PreAuthorize("hasRole('BASIC') or hasRole('ADMIN')")
    public ResponseEntity<UserDTO> getUserByName(@PathVariable String name)
    {
        UserDTO userDTO = userService.getUserDataByName(name);
        return ResponseEntity.ok(userDTO);
    }

    @GetMapping("/users")
    @PreAuthorize("hasRole('BASIC') or hasRole('ADMIN')")
    public ResponseEntity<List<UserDTO>> getAllUser()
    {
        List<UserDTO> userDTOList = userService.getAllUserData();
        return ResponseEntity.ok(userDTOList);
    }

    @PostMapping("/users/add")
    @PreAuthorize("hasRole('ADMIN')")
    public void addUser(@PathVariable String username, @PathVariable String fullname, @PathVariable String password, @PathVariable String email, @PathVariable String role)
    {
        userService.addUserData(username, fullname, password, email, role);
    }
}
