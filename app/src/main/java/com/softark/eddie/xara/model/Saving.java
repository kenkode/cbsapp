package com.softark.eddie.xara.model;

import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Omwaks on 7/12/2017.
 */

public class Saving implements Parcelable {

    Context context;
    private String savingId;
    private double openingBalance;
    private String product;
    private String accountNumber;
    private String member;
    private String currency;
    private double savingAmount;
    private String savingAppDay;
    private String savingAppMonth;
    private String transaction;
    private double totalsavings;

    public String getTransaction() {
        return transaction;
    }

    public void setTransaction(String transaction) {
        this.transaction = transaction;
    }

    public String getSavingAppDay() {
        return savingAppDay;
    }

    public void setSavingAppDay(String savingAppDay) {
        this.savingAppDay = savingAppDay;
    }

    public String getSavingAppMonth() {
        return savingAppMonth;
    }

    public void setSavingAppMonth(String savingAppMonth) {
        this.savingAppMonth = savingAppMonth;
    }

    public double getTotalSavings() {
        return totalsavings;
    }

    public void setTotalSavings(double totalsavings) {
        this.totalsavings = totalsavings;
    }

    private User user;

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public String getSavingId() {
        return savingId;
    }

    public void setSavingId(String savingId) {
        this.savingId = savingId;
    }

    public double getOpeningBalance() {
        return openingBalance;
    }

    public void setOpeningBalance(double openingBalance) {
        this.openingBalance = openingBalance;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getMember() {
        return member;
    }

    public void setMember(String member) {
        this.member = member;
    }

    public double getSavingAmount() {
        return savingAmount;
    }

    public void setSavingAmount(double savingAmount) {
        this.savingAmount = savingAmount;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.savingId);
        dest.writeDouble(this.openingBalance);
        dest.writeString(this.product);
        dest.writeDouble(this.savingAmount);
        dest.writeDouble(this.totalsavings);
        dest.writeString(this.accountNumber);
        dest.writeString(this.member);
        dest.writeParcelable(this.user, flags);
        dest.writeString(this.currency);
        dest.writeString(this.savingAppDay);
        dest.writeString(this.savingAppMonth);
        dest.writeString(this.transaction);
    }

    public Saving() {
    }

    protected Saving(Parcel in) {
        this.savingId = in.readString();
        this.product = in.readString();
        this.member = in.readString();
        this.accountNumber = in.readString();
        this.openingBalance= in.readDouble();
        this.savingAmount = in.readDouble();
        this.totalsavings = in.readDouble();
        this.user = in.readParcelable(User.class.getClassLoader());
        this.currency = in.readString();
        this.savingAppDay = in.readString();
        this.savingAppMonth = in.readString();
        this.transaction = in.readString();
    }

    public static final Creator<Saving> CREATOR = new Creator<Saving>() {
        @Override
        public Saving createFromParcel(Parcel source) {
            return new Saving(source);
        }

        @Override
        public Saving[] newArray(int size) {
            return new Saving[size];
        }
    };


}
