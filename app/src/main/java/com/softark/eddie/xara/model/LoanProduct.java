package com.softark.eddie.xara.model;

/**
 * Created by Eddie on 3/29/2017.
 */

public class LoanProduct {

    private int id;
    private String name;
    private String formula;
    private double interestRate;
    private String amortisation;
    private String currency;
    private double loanLimit;
    private int period;
    private String applicationForm;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFormula() {
        return formula;
    }

    public void setFormula(String formula) {
        this.formula = formula;
    }

    public double getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(double interestRate) {
        this.interestRate = interestRate;
    }

    public String getAmortisation() {
        return amortisation;
    }

    public void setAmortisation(String amortisation) {
        this.amortisation = amortisation;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public double getLoanLimit() {
        return loanLimit;
    }

    public void setLoanLimit(double loanLimit) {
        this.loanLimit = loanLimit;
    }

    public int getPeriod() {
        return period;
    }

    public void setPeriod(int period) {
        this.period = period;
    }

    public String getApplicationForm() {
        return applicationForm;
    }

    public void setApplicationForm(String applicationForm) {
        this.applicationForm = applicationForm;
    }
}
