package com.example.springhandson3.repository;

import com.example.springhandson3.model.Vehicle;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.logging.Logger;

@Repository
public class VehicleRepository {
    private static final Logger logger = Logger.getLogger(VehicleRepository.class.getName());
    List<Vehicle> allVehicles = new ArrayList<>();

    public List<Vehicle> getAllVehicles()
    {
        return allVehicles;
    }

    public Vehicle getVehicleById(long id)
    {
        return allVehicles.stream()
                .filter(v -> v.getId() == id)
                .findFirst()
                .orElse(null);
    }

    public void addVehicle(Vehicle vehicle)
    {
        allVehicles.add(vehicle);
    }

    public void updateVehicle(long id, Vehicle vehicle)
    {
        for(Vehicle vehicleSearch : allVehicles)
        {
            if(vehicleSearch.getName().equals(vehicle.getName()))
            {
                vehicleSearch.setId(id);
                return;
            }
        }
    }

    public void deleteVehicle(long id)
    {
        for(Vehicle vehicle : allVehicles)
        {
            if(vehicle.getId() == id)
            {
                allVehicles.remove(vehicle);
                return;
            }
        }
    }

    public void deleteVehicleByName(String name)
    {
        for(Vehicle vehicle: allVehicles)
        {
            if(vehicle.getName().equals(name))
            {
                allVehicles.remove(vehicle);
                return;
            }
        }
    }

    public Vehicle getExpensiveVehicle()
    {
        if(!allVehicles.isEmpty())
        {
            return allVehicles.stream()
                    .max(Comparator.comparingLong(Vehicle::getPrice))
                    .orElse(null);
        }
        else {
            logger.info("No vehicle in list");
        }
        return null;
    }
}
