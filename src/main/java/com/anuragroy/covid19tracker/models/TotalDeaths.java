package com.anuragroy.covid19tracker.models;

public class TotalDeaths {
    private int totalNewDeaths;
    private int totalReportedDeaths;
    private String lastDate;

    public int getTotalNewDeaths() {
        return totalNewDeaths;
    }

    public void setTotalNewDeaths(int totalNewDeaths) {
        this.totalNewDeaths = totalNewDeaths;
    }

    public int getTotalReportedDeaths() {
        return totalReportedDeaths;
    }

    public void setTotalReportedDeaths(int totalReportedDeaths) {
        this.totalReportedDeaths = totalReportedDeaths;
    }

    public void setLastDate(String lastDate) {
        this.lastDate = lastDate;
    }

    public String getLastDate() {
        return lastDate;
    }
}
