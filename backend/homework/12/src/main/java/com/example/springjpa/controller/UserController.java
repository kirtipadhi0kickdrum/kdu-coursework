package com.example.springjpa.controller;

import com.example.springjpa.entity.User;
import com.example.springjpa.service.UserService;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService)
    {
        this.userService = userService;
    }

    @GetMapping("/users/get/{tenantId}")
    public ResponseEntity<List<User>> getUserById(@PathVariable @JsonDeserialize UUID tenantId)
    {
        return ResponseEntity.ok(userService.getUserByTenant(tenantId));
    }

    @PostMapping("users/add")
    public ResponseEntity<String> addUser(@RequestBody User user)
    {
        boolean result = userService.addUser(user);
        if(result)
        {
            return ResponseEntity.ok("User was added successfully");
        }
        else {
            return ResponseEntity.ok("User was not added, try AGAIN!!");
        }
    }

    @PutMapping("users/updates/{username}/{userId}")
    public void updateUserByNameAndId(@PathVariable String username, @PathVariable @JsonDeserialize UUID userId)
    {
        userService.updateUser(username, userId);
    }
}
