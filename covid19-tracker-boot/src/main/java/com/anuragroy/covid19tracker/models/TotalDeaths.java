package com.anuragroy.covid19tracker.models;

public class TotalDeaths {
    private int totalNewDeaths;
    private int totalReportedDeaths;

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
}
