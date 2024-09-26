package com.example.springhandson5.repository;

import com.example.springhandson5.dto.UserDTO;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;


@Repository
public class UserRepository {

    private static final List<UserDTO> userList = new ArrayList<>();


    public UserDTO getUserDataByName(String name)
    {
        return  userList.stream()
                .filter(u -> u.getFullName().equals(name))
                .findFirst()
                .orElse(null);

    }

    public List<UserDTO> getAllUserData()
    {
        return userList;
    }

    public void addUserData(String username, String fullname, String password, String email, String role)
    {
        UserDTO userDTO = new UserDTO(username, fullname,password, email, role);
        userList.add(userDTO);
    }
}
