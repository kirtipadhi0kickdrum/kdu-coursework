package org.example.factory;

import org.example.vehicle.Vehicle;
import org.springframework.stereotype.Service;

@Service("factoryServiceTwo")
public class FactoryServiceTwo implements FactoryService{

    public Vehicle customizeVehicle(Vehicle vehicle)
    {
        double initialPrice = vehicle.getTyre().getPrice();
        vehicle.getTyre().setPrice(initialPrice * 1.20);
        return vehicle;
    }
}
