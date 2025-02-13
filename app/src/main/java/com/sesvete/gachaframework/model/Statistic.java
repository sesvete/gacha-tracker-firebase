package com.sesvete.gachaframework.model;

public class Statistic {
    private String statisticDescription;
    private int statisticNumber;

    public Statistic(String statisticDescription, int statisticNumber) {
        this.statisticDescription = statisticDescription;
        this.statisticNumber = statisticNumber;
    }

    public String getStatisticDescription() {
        return statisticDescription;
    }

    public void setStatisticDescription(String statisticDescription) {
        this.statisticDescription = statisticDescription;
    }

    public int getStatisticNumber() {
        return statisticNumber;
    }

    public void setStatisticNumber(int statisticNumber) {
        this.statisticNumber = statisticNumber;
    }

    @Override
    public String toString() {
        return "Statistic{" +
                "statisticDescription='" + statisticDescription + '\'' +
                ", statisticNumber=" + statisticNumber +
                '}';
    }
}
