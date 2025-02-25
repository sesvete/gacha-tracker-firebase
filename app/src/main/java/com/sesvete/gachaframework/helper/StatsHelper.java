package com.sesvete.gachaframework.helper;

import android.content.res.Resources;

import com.sesvete.gachaframework.R;
import com.sesvete.gachaframework.model.Statistic;

import java.util.ArrayList;

public class StatsHelper {

    // to se bo pobralo iz podatkovne baze - za zdaj je to le provizorično
    int[] pullsFor5Star = {78, 54, 77, 80, 56, 43, 76, 80, 81, 56, 66, 45, 23, 86, 24, 77, 78, 76, 74, 2};
    boolean[] won5050 = {true, false, true, false, true, false, true, false, true, false, true, false, true, false, true, false, true, false, true, true};

    // imel bom ločen database helper, ki bo parsal podatke iz podatkovne baze in ustvaril ustrezne arraye

    public void statsCalculator(Resources resources, ArrayList<Statistic> statisticList){

        int intNumWonFiftyFifty = numWonFiftyFifty(won5050);
        int intNumLostFiftyFifty = numLostFiftyFifty(won5050);
        double doublePercentageFiftyFifty = percentageFiftyFifty(intNumWonFiftyFifty, intNumLostFiftyFifty);
        double doubleAvgNumPulls = avgNumPulls(pullsFor5Star);
        int intTotalNumPulls = totalNumPulls(pullsFor5Star);
        int currencyValue = 160;

        statisticList.clear();
        statisticList.add(new Statistic(resources.getString(R.string.percentage_fifty_fifty), doublePercentageFiftyFifty));
        statisticList.add(new Statistic(resources.getString(R.string.total_won_fifty_fifty), intNumWonFiftyFifty));
        statisticList.add(new Statistic(resources.getString(R.string.total_lost_fifty_fifty), intNumLostFiftyFifty));
        statisticList.add(new Statistic(resources.getString(R.string.avg_for_five_star), doubleAvgNumPulls));
        statisticList.add(new Statistic(resources.getString(R.string.total_num_pulls), intTotalNumPulls));
        statisticList.add(new Statistic(resources.getString(R.string.avg_currency_five_star), doubleAvgNumPulls * currencyValue));
        statisticList.add(new Statistic(resources.getString(R.string.avg_currency_five_star), intTotalNumPulls * currencyValue));
    }

    private static int numWonFiftyFifty(boolean[] won5050){
        int won = 0;
        for (boolean i: won5050
             ) {
            if (i){
                won++;
            }
        }
        return won;
    }

    private static int numLostFiftyFifty(boolean[] won5050){
        int lost = 0;
        for (boolean i: won5050
        ) {
            if (!i){
                lost++;
            }
        }
        return lost;
    }

    private static double percentageFiftyFifty(int numWon, int numLost){
        if (numWon + numLost == 0){
            return 0.0; // v primeru, da delimo z 0
        }
        return (double) (numWon)/(numWon + numLost);
    }


    private static double avgNumPulls(int[] pullsFor5Star){
        int total = 0;
        if (pullsFor5Star.length == 0){
            return 0.0;
        } else {
            for (int i: pullsFor5Star) {
                total = total + i;
            }
            return (double) total/pullsFor5Star.length;
        }
    }

    private static int totalNumPulls(int[] pullsFor5Star){
        int total = 0;
        for (int i: pullsFor5Star) {
            total = total + i;
        }
        return total;
    }
}
