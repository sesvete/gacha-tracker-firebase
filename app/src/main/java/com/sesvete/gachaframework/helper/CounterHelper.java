package com.sesvete.gachaframework.helper;


public class CounterHelper {
    public int counterPlusOne(String currentCount){
        int numCounter = Integer.parseInt(currentCount);
        return numCounter + 1;
    }

    public int counterPlusTen(String currentCount){
        int numCounter = Integer.parseInt(currentCount);
        return numCounter + 10;
    }
}
