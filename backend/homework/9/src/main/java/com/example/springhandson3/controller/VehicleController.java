package com.example.springhandson3.controller;


import com.example.springhandson3.exception.customexception.HandleBadRequest;
import com.example.springhandson3.exception.customexception.HandleResourceNotFound;
import com.example.springhandson3.model.Vehicle;

import com.example.springhandson3.service.VehicleService;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
public class VehicleController {


    private final VehicleService vehicleService;
    @Autowired
    public VehicleController(VehicleService vehicleService){
        this.vehicleService=vehicleService;

    }


    @PostMapping("/vehicles/add")
    public ResponseEntity<String> addVehicle(@RequestBody Vehicle vehicle)
    {
        log.info("Vehicle addition to inventory starts...");
        vehicleService.addVehicle(vehicle);
        return ResponseEntity.ok("Vehicle has been added successfully");
    }



    @GetMapping("/vehicles/{id}")
    public ResponseEntity<Object> getVehicleById(@PathVariable long id) {
        try {

            Vehicle vehicle = vehicleService.getVehicleById(id);
            if(vehicle == null)
            {
                log.error("vehicle does not exist, throwing exception....");
                throw new HandleBadRequest("Vehicle does not exist");
            }
            else {
                log.info("Vehicle exist, giving the details..");
                return ResponseEntity.ok(vehicle);
            }
        }catch (HandleBadRequest e)
        {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }

    }

    @PutMapping("/vehicles/update/id/{id}")
    public ResponseEntity<String> updateVehicle(@PathVariable long id, @RequestBody Vehicle vehicle) throws HandleResourceNotFound
    {
        if(vehicleService.updateVehicle(id, vehicle)) {
            log.info("Vehicle details are getting updated");
            return ResponseEntity.ok("Vehicle has been updated");
        }
        throw new HandleResourceNotFound("ID is not available");

    }


    @DeleteMapping("/vehicles/delete/id/{id}")
    public ResponseEntity<String> deleteVehicle(@PathVariable long id) throws HandleResourceNotFound
    {
        if(vehicleService.deleteVehicle(id)){
            log.info("The entered id is deleting the vehicle....");
            return ResponseEntity.ok("The vehicle has been successfully deleted");}
        throw new HandleResourceNotFound("The vehicle ID requested is not available in the inventory");
    }

    @DeleteMapping("/vehicles/delete/name/{name}")
    public ResponseEntity<String> deleteVehicle(@PathVariable String name) throws HandleResourceNotFound
    {
        if(vehicleService.deleteVehicleByName(name)){
            log.info("The entered name is deleting the vehicle .... ");
            return ResponseEntity.ok("Vehicle deleted successfully");}
        throw new HandleResourceNotFound("The vehicle name requested is not available in the inventory");
    }

    @GetMapping("/vehicles/expensive")
    public Vehicle getExpensiveVehicle()
    {
        log.info("The details of the most expensive vehicle is getting displayed ... ");
        return vehicleService.getExpensiveVehicle();
    }



}
