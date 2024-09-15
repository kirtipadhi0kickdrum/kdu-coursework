package org.example.question3;

import java.util.ArrayList;


public class SwapMain {

    public static void main(String[] a)
    {
        ArrayList<Integer> arr = new ArrayList<>();
        arr.add(1);
        arr.add(2);
        arr.add(4);
        arr.add(10);

        GenericMethod swapFunction = new GenericMethod();
        swapFunction.exchangePositions(arr, 2, 4);

        ArrayList<String> arr2 = new ArrayList<>();
        arr2.add("Kirti");
        arr2.add("kuku");
        arr2.add("bobby");
        arr2.add("kunal");

        GenericMethod swapFunction2 = new GenericMethod();
        swapFunction2.exchangePositions(arr2, "Kirti", "kunal");


    }
}
