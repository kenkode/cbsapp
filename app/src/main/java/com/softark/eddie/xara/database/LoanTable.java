package com.softark.eddie.xara.database;

/**
 * Created by Eddie on 3/22/2017.
 */

public class LoanTable {
    private String loanId;
    private String loanType;
    private double loanAmount;
    private int period;
    private String periodMetric;

    public LoanTable(String loanId, String loanType, double loanAmount, int period, String periodMetric) {
        this.loanId = loanId;
        this.loanType = loanType;
        this.loanAmount = loanAmount;
        this.period = period;
        this.periodMetric = periodMetric;
    }

    public String getLoanId() {
        return loanId;
    }

    public void setLoanId(String loanId) {
        this.loanId = loanId;
    }

    public String getLoanType() {
        return loanType;
    }

    public void setLoanType(String loanType) {
        this.loanType = loanType;
    }

    public double getLoanAmount() {
        return loanAmount;
    }

    public void setLoanAmount(double loanAmount) {
        this.loanAmount = loanAmount;
    }

    public int getPeriod() {
        return period;
    }

    public void setPeriod(int period) {
        this.period = period;
    }

    public String getPeriodMetric() {
        return periodMetric;
    }

    public void setPeriodMetric(String periodMetric) {
        this.periodMetric = periodMetric;
    }
}
