package org.example.question3;

import java.util.ArrayList;

import java.util.logging.Logger;
public class GenericMethod {

    private static final Logger logger = Logger.getLogger(GenericMethod.class.getName());
    public <T> void exchangePositions(ArrayList<T> arr,T firstElement,T secondElement)
    {
        if(!arr.contains(firstElement) || !arr.contains(secondElement))
        {
            logger.info("The element not found in the array");
        }
        else {
            T tempHolder;
            int firstPosition = arr.indexOf(firstElement);
            int secondPosition = arr.indexOf(secondElement);
            tempHolder = arr.get(firstPosition);
            arr.set(firstPosition, secondElement);
            arr.set(secondPosition, tempHolder);
        }
    }

}


