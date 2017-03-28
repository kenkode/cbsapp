package com.softark.eddie.xara.model;

import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable;

import com.softark.eddie.xara.database.BaseModel;

import java.util.Date;

/**
 * Created by Eddie on 3/22/2017.
 */

public class Loan implements Parcelable {
    Context context;
    private String loanId;
    private String loanAppDay;
    private String loanAppMonth;
    private int loanStatus;
    private String loanInterest;
    private String loanType;
    private double loanAmount;
    private String repaymentPeriod;
    private double totalPayment;
    private double amountPaid, interestPaid, remainingAmount;
    private int periodElapsed, remainingPeriod;

    public double getAmountPaid() {
        return amountPaid;
    }

    public void setAmountPaid(double amountPaid) {
        this.amountPaid = amountPaid;
    }

    public double getInterestPaid() {
        return interestPaid;
    }

    public void setInterestPaid(double interestPaid) {
        this.interestPaid = interestPaid;
    }

    public double getRemainingAmount() {
        return remainingAmount;
    }

    public void setRemainingAmount(double remainingAmount) {
        this.remainingAmount = remainingAmount;
    }

    public int getPeriodElapsed() {
        return periodElapsed;
    }

    public void setPeriodElapsed(int periodElapsed) {
        this.periodElapsed = periodElapsed;
    }

    public int getRemainingPeriod() {
        return remainingPeriod;
    }

    public void setRemainingPeriod(int remainingPeriod) {
        this.remainingPeriod = remainingPeriod;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    private String currency;

    public double getTopUp() {
        return topUp;
    }

    public void setTopUp(double topUp) {
        this.topUp = topUp;
    }

    private double topUp;

    public Date getLoanStartDate() {
        return loanStartDate;
    }

    public void setLoanStartDate(Date loanStartDate) {
        this.loanStartDate = loanStartDate;
    }

    private Date loanStartDate;

    public String getLoanId() {
        return loanId;
    }

    public void setLoanId(String loanId) {
        this.loanId = loanId;
    }

    public String getLoanAppDay() {
        return loanAppDay;
    }

    public void setLoanAppDay(String loanAppDay) {
        this.loanAppDay = loanAppDay;
    }

    public String getLoanAppMonth() {
        return loanAppMonth;
    }

    public void setLoanAppMonth(String loanAppMonth) {
        this.loanAppMonth = loanAppMonth;
    }

    public int getLoanStatus() {
        return loanStatus;
    }

    public void setLoanStatus(int loanStatus) {
        this.loanStatus = loanStatus;
    }

    public String getLoanInterest() {
        return loanInterest;
    }

    public void setLoanInterest(String loanInterest) {
        this.loanInterest = loanInterest;
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

    public String getRepaymentPeriod() {
        return repaymentPeriod;
    }

    public void setRepaymentPeriod(String repaymentPeriod) {
        this.repaymentPeriod = repaymentPeriod;
    }

    public double getTotalPayment() {
        return totalPayment;
    }

    public void setTotalPayment(double totalPayment) {
        this.totalPayment = totalPayment;
    }

    @Override
    public String toString() {
        return "Loan{" +
                "loanId='" + loanId + '\'' +
                ", loanAppDay='" + loanAppDay + '\'' +
                ", loanAppMonth='" + loanAppMonth + '\'' +
                ", loanStatus='" + loanStatus + '\'' +
                ", loanInterest='" + loanInterest + '\'' +
                ", loanType='" + loanType + '\'' +
                ", loanAmount=" + loanAmount +
                ", repaymentPeriod='" + repaymentPeriod + '\'' +
                ", totalPayment=" + totalPayment +
                '}';
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.loanId);
        dest.writeString(this.loanAppDay);
        dest.writeString(this.loanAppMonth);
        dest.writeInt(this.loanStatus);
        dest.writeString(this.loanInterest);
        dest.writeString(this.loanType);
        dest.writeDouble(this.loanAmount);
        dest.writeString(this.repaymentPeriod);
        dest.writeDouble(this.totalPayment);
        dest.writeDouble(this.amountPaid);
        dest.writeDouble(this.interestPaid);
        dest.writeDouble(this.remainingAmount);
        dest.writeInt(this.periodElapsed);
        dest.writeInt(this.remainingPeriod);
        dest.writeString(this.currency);
        dest.writeDouble(this.topUp);
        dest.writeLong(this.loanStartDate != null ? this.loanStartDate.getTime() : -1);
    }

    public Loan() {
    }

    protected Loan(Parcel in) {
        this.loanId = in.readString();
        this.loanAppDay = in.readString();
        this.loanAppMonth = in.readString();
        this.loanStatus = in.readInt();
        this.loanInterest = in.readString();
        this.loanType = in.readString();
        this.loanAmount = in.readDouble();
        this.repaymentPeriod = in.readString();
        this.totalPayment = in.readDouble();
        this.amountPaid = in.readDouble();
        this.interestPaid = in.readDouble();
        this.remainingAmount = in.readDouble();
        this.periodElapsed = in.readInt();
        this.remainingPeriod = in.readInt();
        this.currency = in.readString();
        this.topUp = in.readDouble();
        long tmpLoanStartDate = in.readLong();
        this.loanStartDate = tmpLoanStartDate == -1 ? null : new Date(tmpLoanStartDate);
    }

    public static final Creator<Loan> CREATOR = new Creator<Loan>() {
        @Override
        public Loan createFromParcel(Parcel source) {
            return new Loan(source);
        }

        @Override
        public Loan[] newArray(int size) {
            return new Loan[size];
        }
    };
}
