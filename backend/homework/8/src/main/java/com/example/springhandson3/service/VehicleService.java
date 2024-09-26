package com.example.springhandson3.service;

import com.example.springhandson3.model.Vehicle;
import com.example.springhandson3.repository.VehicleRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VehicleService {

    @Autowired
    private VehicleRepository vehicleRepository;

    public void addVehicle(Vehicle vehicle)
    {
        vehicleRepository.addVehicle(vehicle);
    }

    public List<Vehicle> getAllVehicles()
    {
        return vehicleRepository.getAllVehicles();
    }

    public Vehicle getVehicleById(long id)
    {
        return vehicleRepository.getVehicleById(id);
    }

    public void updateVehicle(long id, Vehicle vehicle)
    {
        vehicleRepository.updateVehicle(id, vehicle);
    }

    public void deleteVehicle(long id)
    {
        vehicleRepository.deleteVehicle(id);
    }

    public void deleteVehicleByName(String name)
    {
        vehicleRepository.deleteVehicleByName(name);
    }

    public Vehicle getExpensiveVehicle()
    {
        return vehicleRepository.getExpensiveVehicle();
    }
}
