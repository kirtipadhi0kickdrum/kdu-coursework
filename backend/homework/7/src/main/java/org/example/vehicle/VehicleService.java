package org.example.vehicle;

import org.example.factory.FactoryService;
import org.example.speaker.Speaker;

import org.example.tyre.Tyre;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
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


    public void addVehicle(Speaker speaker, Tyre tyre, String factory)
    {
        Vehicle vehicle = new Vehicle(speaker, tyre, factory);

        vehicleList.add(vehicle);
    }

    @Qualifier("factoryServiceOne")
    private final FactoryService factoryServiceOne;


    @Qualifier("factoryServiceTwo")
    private final FactoryService factoryServiceTwo;


    @Autowired
    public VehicleService(
            @Qualifier("factoryServiceOne") FactoryService factoryServiceOne,
            @Qualifier("factoryServiceTwo") FactoryService factoryServiceTwo
    ) {
        this.factoryServiceOne = factoryServiceOne;
        this.factoryServiceTwo = factoryServiceTwo;
    }

    public List<Vehicle> getVehicleList()
    {
        return vehicleList;
    }


    @PostConstruct
    public void init() {
        if (!vehicleList.isEmpty()) {
            Vehicle v = vehicleList.stream()
                    .max(Comparator.comparingDouble(Vehicle::getPrice))
                    .orElse(null);

            if (v != null) {
                logger.info("Price - " + v.getPrice());
                logger.info("Speaker - " + (v.getSpeaker() != null ? v.getSpeaker().getSpeakerBrand() : "Unknown Speaker"));
                logger.info("Tyre - " + (v.getTyre() != null ? v.getTyre().getBrand() : "Unknown Tyre"));
            } else {
                logger.info("No vehicles found.");
            }
        } else {
            logger.info("No vehicles found.");
        }
    }

    public void customizeVehicles()
    {
        for(Vehicle vehicle : vehicleList)
        {
            if("FactoryOne".equals(vehicle.getFactory()))
            {
                factoryServiceOne.customizeVehicle(vehicle);
            }
            else{
                factoryServiceTwo.customizeVehicle(vehicle);
            }
        }
    }

}
