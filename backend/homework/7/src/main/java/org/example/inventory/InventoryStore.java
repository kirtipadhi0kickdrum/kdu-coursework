package org.example.inventory;


import org.example.factory.FactoryServiceOne;
import org.example.factory.FactoryServiceTwo;
import org.example.vehicle.Vehicle;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.logging.Logger;


@Service
@Scope("prototype")
public class InventoryStore {
    private static final Logger logger = Logger.getLogger(InventoryStore.class.getName());
    private final List<Vehicle> customisedVehicleList = new ArrayList<>();

    public void factoryOneCustomised(Vehicle vehicle)
    {
        Vehicle customisedVehicle = new FactoryServiceOne().customizeVehicle(vehicle);
        customisedVehicleList.add(customisedVehicle);
    }

    public void factoryTwoCustomised(Vehicle vehicle)
    {
        Vehicle customisedVehicle = new FactoryServiceTwo().customizeVehicle(vehicle);
        customisedVehicleList.add(customisedVehicle);
    }

    public List<Vehicle> getCustomisedVehicleList()
    {
        return customisedVehicleList;
    }

    @PostConstruct
    public void init() {
        if (!customisedVehicleList.isEmpty()) {
            Vehicle v = customisedVehicleList.stream()
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

}
