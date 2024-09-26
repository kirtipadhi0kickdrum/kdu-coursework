package org.example.factory;


import org.example.vehicle.Vehicle;

public interface FactoryService {
    Vehicle customizeVehicle(Vehicle vehicle);
}
