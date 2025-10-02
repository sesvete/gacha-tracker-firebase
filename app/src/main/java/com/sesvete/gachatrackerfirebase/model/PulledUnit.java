package com.sesvete.gachatrackerfirebase.model;

import android.util.Log;

import com.sesvete.gachatrackerfirebase.helper.DatabaseHelper;

public class PulledUnit {
    private int numOfPulls;
    private String unitName;
    private boolean fromBanner;
    private String date;

    public PulledUnit() {
        // Required empty constructor
    }

    public PulledUnit(int numOfPulls, String unitName, boolean fromBanner, String date) {
        this.numOfPulls = numOfPulls;
        this.unitName = unitName;
        this.fromBanner = fromBanner;
        this.date = date;
    }

    public int getNumOfPulls() {
        return numOfPulls;
    }

    public void setNumOfPulls(int numOfPulls) {
        this.numOfPulls = numOfPulls;
    }

    public String getUnitName() {
        return unitName;
    }

    public void setUnitName(String unitName) {
        this.unitName = unitName;
    }

    public boolean isFromBanner() {
        return fromBanner;
    }

    public void setFromBanner(boolean fromBanner) {
        this.fromBanner = fromBanner;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "PulledUnit{" +
                "numOfPulls=" + numOfPulls +
                ", unitName='" + unitName + '\'' +
                ", fromBanner=" + fromBanner +
                ", date='" + date + '\'' +
                '}';
    }

    // write to database
    public void writePulledUnitToDatabase(String uid, String game, String banner, long timerInputPullStart){
        DatabaseHelper databaseHelper = new DatabaseHelper();
        databaseHelper.savePulledUnit(uid, game, banner, getUnitName(), getNumOfPulls(), isFromBanner(), getDate(), new DatabaseHelper.OnSavePulledUnitCallback() {
            @Override
            public void onSavedPulledUnit(boolean success) {
                if (success){
                    long timerInputPullEnd = System.nanoTime();
                    long timerInputPullResult = (timerInputPullEnd - timerInputPullStart)/1000000;
                    Log.i("Timer write pull", Long.toString(timerInputPullResult) + " " + "ms");
                    Log.d("Pulled unit Writing", "Successfully wrote to Database");
                } else {
                    Log.d("Pulled unit Writing", "Failed writing to Database");
                }
            }
        });
    }
}
