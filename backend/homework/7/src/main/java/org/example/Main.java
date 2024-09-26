package org.example;

import org.example.factory.FactoryServiceOne;
import org.example.factory.FactoryServiceTwo;
import org.example.inventory.InventoryStore;
import org.example.speaker.Speaker;
import org.example.speaker.SpeakerService;
import org.example.tyre.Tyre;
import org.example.tyre.TyreService;
import org.example.vehicle.Vehicle;
import org.example.vehicle.VehicleService;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;


import java.util.List;
import java.util.logging.Logger;

public class Main {
    private static final Logger logger = Logger.getLogger(Main.class.getName());
    public static void main(String[] args)
    {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SpeakerService.class, TyreService.class, VehicleService.class, InventoryStore.class, FactoryServiceOne.class, FactoryServiceTwo.class);

        SpeakerService speakerService = context.getBean(SpeakerService.class);
        TyreService tyreService = context.getBean(TyreService.class);

        VehicleService vehicleService = context.getBean(VehicleService.class);


        Speaker speaker1 = speakerService.speaker1();
        Speaker speaker2 = speakerService.speaker2();

        Tyre tyre1 = tyreService.tyre1();
        Tyre tyre2 = tyreService.tyre2();

/*
I am assuming that the first vehicle is made in factory 1 and the second vehicle is made in the factory 2
 */
        vehicleService.addVehicle(speaker1, tyre1, "FactoryOne");
        vehicleService.addVehicle(speaker2, tyre2, "FactoryTwo");

        InventoryStore inventoryStore = context.getBean(InventoryStore.class);
        Vehicle vehicleOne = vehicleService.getVehicleList().get(0);
        Vehicle vehicleTwo = vehicleService.getVehicleList().get(1);

        inventoryStore.factoryOneCustomised(vehicleOne);
        inventoryStore.factoryTwoCustomised(vehicleTwo);

        List<Vehicle> customisedVehicleListFromInventory  = inventoryStore.getCustomisedVehicleList();

        logger.info("The list of the customised vehicle are ");

        logger.info(" --------------------------------------------");


        for (Vehicle v : customisedVehicleListFromInventory)
        {
            logger.info("The customised vehicle price from factory is - " + v.getPrice());
        }

        inventoryStore.init();

        
    }
}