package org.example.vehicle;

import org.example.speaker.Speaker;

import org.example.tyre.Tyre;


import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.logging.Logger;


@Service
public class VehicleService {
    private static final Logger logger = Logger.getLogger(VehicleService.class.getName());

    private final List<Vehicle> vehicleList = new ArrayList<>();


    public void addVehicle(Speaker speaker, Tyre tyre)
    {
        Vehicle vehicle = new Vehicle(speaker, tyre);

        vehicleList.add(vehicle);
    }


    public List<Vehicle> getVehicleList()
    {
        return vehicleList;
    }

    @PostConstruct
    public void init(){
        if (!vehicleList.isEmpty()) {
            Vehicle v = vehicleList.stream()
                    .max(Comparator.comparingDouble(Vehicle::getPrice))
                    .orElse(null);

            logger.info("Price - " + v.getPrice());
            logger.info("Speaker - " + v.getSpeaker().getSpeakerBrand());
            logger.info("Tyre - " + v.getTyre().getBrand());
        } else {
            logger.info("No vehicles found.");
        }
    }


}
