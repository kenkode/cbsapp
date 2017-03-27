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
    }

    public Loan() {
    }

    protected Loan(Parcel in) {
        this.context = in.readParcelable(Context.class.getClassLoader());
        this.loanId = in.readString();
        this.loanAppDay = in.readString();
        this.loanAppMonth = in.readString();
        this.loanStatus = in.readInt();
        this.loanInterest = in.readString();
        this.loanType = in.readString();
        this.loanAmount = in.readDouble();
        this.repaymentPeriod = in.readString();
        this.totalPayment = in.readDouble();
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
