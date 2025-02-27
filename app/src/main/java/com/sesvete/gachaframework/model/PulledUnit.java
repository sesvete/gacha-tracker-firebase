package com.sesvete.gachaframework.model;

// to se bo verjetno še spremenilo
// ni nujno, da bosta obe aplikaciji imeli enak model

public class PulledUnit {
    // tu bo še int userId ali String userName
    // tak bo definitivno za relational database
    // za firebase bom še videl
    private int numOfPulls;
    private String unitName;
    private boolean fromBanner;
    private String date;
    private String game;
    private String bannerType;

    public PulledUnit(int numOfPulls, String unitName, boolean fromBanner, String date, String game, String bannerType) {
        this.numOfPulls = numOfPulls;
        this.unitName = unitName;
        this.fromBanner = fromBanner;
        this.date = date;
        this.game = game;
        this.bannerType = bannerType;
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

    public String getBannerType() {
        return bannerType;
    }

    public void setBannerType(String bannerType) {
        this.bannerType = bannerType;
    }

    @Override
    public String toString() {
        return "PulledUnit{" +
                "numOfPulls=" + numOfPulls +
                ", unitName='" + unitName + '\'' +
                ", fromBanner=" + fromBanner +
                ", date='" + date + '\'' +
                ", game='" + game + '\'' +
                ", bannerType='" + bannerType + '\'' +
                '}';
    }
}
