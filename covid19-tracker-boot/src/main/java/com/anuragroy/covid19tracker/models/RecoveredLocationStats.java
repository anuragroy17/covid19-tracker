package com.anuragroy.covid19tracker.models;

public class RecoveredLocationStats {
    private String state;
    private String country;
    private int latestTotalRecovered;
    private int diffFromPrevDay;

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public int getLatestTotalRecovered() {
        return latestTotalRecovered;
    }

    public void setLatestTotalRecovered(int latestTotalRecovered) {
        this.latestTotalRecovered = latestTotalRecovered;
    }

    public int getDiffFromPrevDay() {
        return diffFromPrevDay;
    }

    public void setDiffFromPrevDay(int diffFromPrevDay) {
        this.diffFromPrevDay = diffFromPrevDay;
    }

    @Override
    public String toString() {
        return "RecoveredLocationStats{" +
                "state='" + state + '\'' +
                ", country='" + country + '\'' +
                ", latestTotalRecovered=" + latestTotalRecovered +
                ", diffFromPrevDay=" + diffFromPrevDay +
                '}';
    }
}
