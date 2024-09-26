package com.example.springhandson3.controller;


import com.example.springhandson3.model.Vehicle;

import com.example.springhandson3.service.VehicleService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class VehicleController {

    @Autowired
    private VehicleService vehicleService;

    @PostMapping("/vehicles/add")
    public ResponseEntity<String> addVehicle(@RequestBody Vehicle vehicle)
    {
        vehicleService.addVehicle(vehicle);
        return ResponseEntity.ok("Vehicle has been added successfully");
    }

    @GetMapping("/vehicles/{id}")
    public Vehicle getVehicleById(@PathVariable long id)
    {
        return vehicleService.getVehicleById(id);

    }

    @PutMapping("/vehicles/update/id/{id}")
    public ResponseEntity<String> updateVehicle(@PathVariable long id, @RequestBody Vehicle vehicle)
    {
        vehicleService.updateVehicle(id, vehicle);
        return ResponseEntity.ok("Vehicle factory has been updated");
    }

    @DeleteMapping("/vehicles/delete/id/{id}")
    public ResponseEntity<String> deleteVehicle(@PathVariable long id)
    {
        vehicleService.deleteVehicle(id);
        return ResponseEntity.ok("The vehicle has been successfully deleted");
    }

    @DeleteMapping("/vehicles/delete/name/{name}")
    public ResponseEntity<String> deleteVehicle(@PathVariable String name)
    {
        vehicleService.deleteVehicleByName(name);
        return ResponseEntity.ok("Vehicle deleted successfully");
    }

    @GetMapping("/vehicles/expensive")
    public Vehicle getExpensiveVehicle()
    {
        return vehicleService.getExpensiveVehicle();
    }
}
