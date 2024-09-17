package org.example.factory;

import org.example.vehicle.Vehicle;
import org.springframework.stereotype.Service;

@Service("factoryServiceOne")
public class FactoryServiceOne implements FactoryService{
    @Override
    public Vehicle customizeVehicle(Vehicle vehicle)
    {
        double initialPrice = vehicle.getTyre().getPrice();
        vehicle.getTyre().setPrice(initialPrice * 1.10);
        return vehicle;
    }
}
