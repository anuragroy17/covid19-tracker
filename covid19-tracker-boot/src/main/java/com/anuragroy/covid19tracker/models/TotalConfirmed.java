package com.anuragroy.covid19tracker.models;

public class TotalConfirmed {
    private int totalNewCases;
    private int totalReportedCases;

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
}
