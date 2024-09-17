package org.example.vehicle;

import org.example.speaker.Speaker;
import org.example.tyre.Tyre;

public class Vehicle {
    private Tyre tyre;
    private Speaker speaker;
    private double price;
    private String factory;


    public Vehicle(Speaker speaker, Tyre tyre, String factory)
    {
        this.speaker = speaker;
        this.tyre = tyre;

        if(factory.equals("FactoryOne"))
        {
            double initialTyrePrice = tyre.getPrice();
            tyre.setPrice(initialTyrePrice * 1.10);
        }
        else {
            double initialTyrePrice = tyre.getPrice();
            tyre.setPrice(initialTyrePrice * 1.20);
        }
        this.price = speaker.getSpeakerPrice() + tyre.getPrice() + 10000;
        this.factory = factory;
    }
    public Tyre getTyre() {
        return tyre;
    }

    public void setTyre(Tyre tyre) {
        this.tyre = tyre;
    }

    public Speaker getSpeaker() {
        return speaker;
    }

    public void setSpeaker(Speaker speaker) {
        this.speaker = speaker;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getFactory() {
        return factory;
    }

    public void setFactory(String factory) {
        this.factory = factory;
    }
}
