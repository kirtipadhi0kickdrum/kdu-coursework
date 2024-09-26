package org.example;


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
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SpeakerService.class, TyreService.class, VehicleService.class);

        SpeakerService speakerService = context.getBean(SpeakerService.class);
        TyreService tyreService = context.getBean(TyreService.class);
        VehicleService vehicleService = context.getBean(VehicleService.class);


        Speaker speaker1 = speakerService.speaker1();
        Speaker speaker2 = speakerService.speaker2();

        Tyre tyre1 = tyreService.tyre1();
        Tyre tyre2 = tyreService.tyre2();


        vehicleService.addVehicle(speaker1, tyre1);
        vehicleService.addVehicle(speaker2, tyre2);

        List<Vehicle> vehicleList = vehicleService.getVehicleList();
        for(Vehicle v : vehicleList)
        {
            logger.info("Speaker - " + v.getSpeaker().getSpeakerBrand());
            logger.info("Tyre - " + v.getTyre().getBrand());
        }

        logger.info("The most expensive vehicle is - ");
        vehicleService.init();

    }
}