package org.example.question2;

import java.util.*;

public class TicketReservation {


    private final List<Passenger> confirmedList = new ArrayList<>();
    private final Deque<Passenger> waitingList = new ArrayDeque<>();

    public boolean bookFlight(String firstName, String lastName, int age, String gender, String travelCLass, String confirmationNumber)
    {
        Passenger passenger = new Passenger(firstName, lastName, age, gender, travelCLass, confirmationNumber);
        if(confirmedList.size() <= 10)
        {
            confirmedList.add(passenger);
            return true;
        }
        else if(waitingList.size() <= 3){
            waitingList.add(passenger);
            return true;
        }
        else {
            return false;
        }

    }


    public boolean cancel(String confirmationNumber)
    {
        Iterator<Passenger> iteratorConfirmed = confirmedList.iterator();
        if(removePassenger(iteratorConfirmed, confirmationNumber))
        {
            if(!waitingList.isEmpty())
            {
                Passenger passenger = waitingList.poll();
                confirmedList.add(passenger);
                return true;
            }
        }
        else {
            Iterator<Passenger> iteratorWaiting = waitingList.iterator();
            if(removePassenger(iteratorWaiting, confirmationNumber))
            {
                return true;
            }
        }
        return false;

    }

    public boolean removePassenger (Iterator<Passenger> iterator, String confirmationNumber)
    {
        while(iterator.hasNext())
        {
            Passenger passenger = iterator.next();
            if(Objects.equals(passenger.getConfirmationNumber(), confirmationNumber))
            {
                iterator.remove();
                return true;
            }


        }
        return false;
    }
}
