package com.anuragroy.covid19tracker.models;

public class TotalRecovered {
    private int totalNewRecoveries;
    private int totalReportedRecoveries;
    private String lastDate;

    public int getTotalNewRecoveries() {
        return totalNewRecoveries;
    }

    public void setTotalNewRecoveries(int totalNewRecoveries) {
        this.totalNewRecoveries = totalNewRecoveries;
    }

    public int getTotalReportedRecoveries() {
        return totalReportedRecoveries;
    }

    public void setTotalReportedRecoveries(int totalReportedRecoveries) {
        this.totalReportedRecoveries = totalReportedRecoveries;
    }

    public void setLastDate(String lastDate) {
        this.lastDate = lastDate;
    }

    public String getLastDate() {
        return lastDate;
    }
}
