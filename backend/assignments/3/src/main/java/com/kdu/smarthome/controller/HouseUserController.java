package com.kdu.smarthome.controller;

import com.kdu.smarthome.exception.HouseNotFoundException;
import com.kdu.smarthome.exception.UserNotFoundException;
import com.kdu.smarthome.request.AddUserToHouseRequestDTO;
import com.kdu.smarthome.response.AddUserToHouseResponseDTO;
import com.kdu.smarthome.service.HouseUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/house/{houseId}/add-user")
public class HouseUserController {

    private final HouseUserService houseUserService;

    @Autowired
    public HouseUserController(HouseUserService houseUserService) {
        this.houseUserService = houseUserService;
    }

    @PostMapping
    public ResponseEntity<AddUserToHouseResponseDTO> addUserToHouse(@PathVariable Long houseId, @RequestBody AddUserToHouseRequestDTO request) {
        try {
            AddUserToHouseResponseDTO response = houseUserService.addUserToHouse(houseId, request);
            return new ResponseEntity<>(response, response.getHttpStatus());
        } catch (HouseNotFoundException | UserNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new AddUserToHouseResponseDTO(ex.getMessage(), null, HttpStatus.NOT_FOUND));
        } catch (AccessDeniedException ex) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN)
                    .body(new AddUserToHouseResponseDTO("Unauthorized access to add user to house", null, HttpStatus.FORBIDDEN));
        } catch (java.nio.file.AccessDeniedException e) {
            throw new RuntimeException(e);
        }
    }
}
