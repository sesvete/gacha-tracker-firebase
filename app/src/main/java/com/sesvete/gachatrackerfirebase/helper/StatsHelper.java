package com.sesvete.gachatrackerfirebase.helper;

// TODO: to še posodobi, ko boš enkrat pridobil podatke iz baze

import android.util.Log;
import java.util.List;

public class StatsHelper {

    public static int numWonFiftyFifty(boolean[] won5050){
        int won = 0;
        for (boolean i: won5050
             ) {
            if (i){
                won++;
            }
        }
        return won;
    }

    public static int numLostFiftyFifty(boolean[] won5050){
        int lost = 0;
        for (boolean i: won5050
        ) {
            if (!i){
                lost++;
            }
        }
        return lost;
    }

    public static double percentageFiftyFifty(int numWon, int numLost){
        if (numWon + numLost == 0){
            return 0.0; // v primeru, da delimo z 0
        }
        return (double) (numWon)/(numWon + numLost);
    }

    public static double avgNumPulls(List<Integer> numbers){
        if (numbers == null || numbers.isEmpty()) {
            return 0;
        }
        int sum = 0;
        for (int number : numbers) {
            sum += number;
        }
        Log.d("avhNumPulls", String.valueOf(sum));
        double result = ((double) sum /numbers.size());
        return Math.round(result * 100.0) / 100.0;
    }

    public static int totalNumPulls(List<Integer> numbers) {
        if (numbers == null || numbers.isEmpty()) {
            return 0;
        }
        int sum = 0;
        for (int number : numbers) {
            sum += number;
        }
        return sum;
    }
}
