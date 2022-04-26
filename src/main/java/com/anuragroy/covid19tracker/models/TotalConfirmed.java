package com.anuragroy.covid19tracker.models;

public class TotalConfirmed {
    private int totalNewCases;
    private int totalReportedCases;
    private String lastDate;

    public int getTotalNewCases() {
        return totalNewCases;
    }

    public void setTotalNewCases(int totalNewCases) {
        this.totalNewCases = totalNewCases;
    }

    public int getTotalReportedCases() {
        return totalReportedCases;
    }

    public void setTotalReportedCases(int totalReportedCases) {
        this.totalReportedCases = totalReportedCases;
    }

    public void setLastDate(String lastDate) {
        this.lastDate = lastDate;
    }

    public String getLastDate() {
        return lastDate;
    }
}
