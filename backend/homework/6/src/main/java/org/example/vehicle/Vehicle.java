package org.example.vehicle;

import org.example.speaker.Speaker;
import org.example.tyre.Tyre;

public class Vehicle {
    private Tyre tyre;
    private Speaker speaker;
    private double price;


    public Vehicle(Speaker speaker, Tyre tyre)
    {
        this.speaker = speaker;
        this.tyre = tyre;
        this.price = speaker.getSpeakerPrice() + tyre.getPrice() + 10000;
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

}
