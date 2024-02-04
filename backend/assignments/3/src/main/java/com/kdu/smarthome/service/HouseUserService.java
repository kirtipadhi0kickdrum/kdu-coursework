package com.kdu.smarthome.service;

import com.kdu.smarthome.entity.HouseEntity;
import com.kdu.smarthome.entity.UserEntity;
import com.kdu.smarthome.exception.HouseNotFoundException;
import com.kdu.smarthome.exception.UserNotFoundException;
import com.kdu.smarthome.repository.HouseRepository;
import com.kdu.smarthome.repository.UserRepository;
import com.kdu.smarthome.request.AddUserToHouseRequestDTO;
import com.kdu.smarthome.response.AddUserToHouseResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.nio.file.AccessDeniedException;

@Service
public class HouseUserService {

    private final HouseRepository houseRepository;
    private final UserRepository userRepository;

    @Autowired
    public HouseUserService(HouseRepository houseRepository, UserRepository userRepository)
    {
        this.houseRepository = houseRepository;
        this.userRepository = userRepository;
    }

    public AddUserToHouseResponseDTO addUserToHouse(Long houseId, AddUserToHouseRequestDTO request) throws AccessDeniedException {
        // Check if the user making the request is an ADMIN
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserEntity currentUser = userRepository.findByUsername(userDetails.getUsername())
                .orElseThrow(() -> new UserNotFoundException("Current user not found"));

        // Perform your authorization checks here
        if (!currentUser.getRoles().stream().anyMatch(role -> role.getName().equals("ADMIN"))) {
            throw new AccessDeniedException("Only ADMINs are allowed to add users to a house");
        }

        HouseEntity house = houseRepository.findById(houseId)
                .orElseThrow(() -> new HouseNotFoundException("House not found with the given id"));

        UserEntity user = userRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new UserNotFoundException("User was not found with the given ID"));

        house.getUsers().add(user);
        houseRepository.save(house);

        return new AddUserToHouseResponseDTO("User added to the house successfully", request.getUsername(), HttpStatus.OK);
    }
}
