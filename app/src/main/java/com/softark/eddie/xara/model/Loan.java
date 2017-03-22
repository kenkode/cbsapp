package com.softark.eddie.xara.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.softark.eddie.xara.database.LoanTable;

import java.util.UUID;

/**
 * Created by Eddie on 3/22/2017.
 */

public class Loan extends BaseModel {
    Context context;
    private String loanId;
    private String loanType;
    private double loanAmount;
    private int period;
    private int periodMetric;
    private double totalPayment;
    BaseModel model;

    public Loan(Context context) {
        super(context);
    }

    public Loan(Context context, String loanId, String loanType, double loanAmount, int period, int periodMetric, double totalPayment) {
        super(context);
        if(loanId == null) {
            loanId = UUID.randomUUID().toString();
        }

        this.context = context;
        model = new BaseModel(context);
        this.loanId = loanId;
        this.loanType = loanType;
        this.loanAmount = loanAmount;
        this.period = period;
        this.periodMetric = periodMetric;
        this.totalPayment = totalPayment;
    }

    public void applyLoan() {
        ContentValues values = new ContentValues(6);
        values.put(LoanTable.LOAN_ID, loanId);
        values.put(LoanTable.LOAN_TYPE, loanType);
        values.put(LoanTable.LOAN_AMOUNT, loanAmount);
        values.put(LoanTable.LOAN_PERIOD, period);
        values.put(LoanTable.LOAN_METRIC, periodMetric);
        values.put(LoanTable.TOTAL_PAYMENT, totalPayment);

        sqLiteDatabase.insert(LoanTable.TABLE_NAME, null, values);
    }

    public int getCount() {
//        String query = "select * from " + LoanTable.TABLE_NAME;
        Cursor cursor = sqLiteDatabase.query(LoanTable.TABLE_NAME,LoanTable.ALL_COLUMNS, null, null, null, null, null);
        return cursor.getCount();
    }

}
