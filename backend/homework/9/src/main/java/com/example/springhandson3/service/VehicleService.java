package com.example.springhandson3.service;

import com.example.springhandson3.model.Vehicle;
import com.example.springhandson3.repository.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VehicleService {


    private final VehicleRepository vehicleRepository;

    @Autowired
    public VehicleService(VehicleRepository vehicleRepository)
    {
        this.vehicleRepository = vehicleRepository;
    }

    public void addVehicle(Vehicle vehicle)
    {
        vehicleRepository.addVehicle(vehicle);
    }

    public List<Vehicle> getAllVehicles()
    {
        return vehicleRepository.getAllVehicles();
    }

    public Vehicle getVehicleById(long id){

        return vehicleRepository.getVehicleById(id);


    }

    public boolean updateVehicle(long id, Vehicle vehicle)
    {
        return vehicleRepository.updateVehicle(id, vehicle);
    }

    public boolean deleteVehicle(long id)
    {
        return vehicleRepository.deleteVehicle(id);
    }

    public boolean deleteVehicleByName(String name)
    {
        return vehicleRepository.deleteVehicleByName(name);
    }

    public Vehicle getExpensiveVehicle()
    {
        return vehicleRepository.getExpensiveVehicle();
    }
}
