package com.sesvete.gachaframework.model;

public class PulledUnit {
    private int numOfPulls;
    private String unitName;
    private boolean fromBanner;
    private String date;
    private String game;

    public PulledUnit(int numOfPulls, String unitName, boolean fromBanner, String date, String game) {
        this.numOfPulls = numOfPulls;
        this.unitName = unitName;
        this.fromBanner = fromBanner;
        this.date = date;
        this.game = game;
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

    public String getGame() {
        return game;
    }

    public void setGame(String game) {
        this.game = game;
    }

    @Override
    public String toString() {
        return "PulledUnit{" +
                "numOfPulls=" + numOfPulls +
                ", unitName='" + unitName + '\'' +
                ", fromBanner=" + fromBanner +
                ", date='" + date + '\'' +
                ", game='" + game + '\'' +
                '}';
    }
}
