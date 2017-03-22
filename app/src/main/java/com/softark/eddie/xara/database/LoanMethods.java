package com.softark.eddie.xara.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.softark.eddie.xara.adapters.LoanAdapter;
import com.softark.eddie.xara.model.Loan;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

/**
 * Created by Eddie on 3/22/2017.
 */

public class LoanMethods extends BaseModel {

    public LoanMethods(Context context) {
        super(context);
    }

    public void applyLoan(String loanId, String loanType, double loanAmount, String repayment_period, double totalPayment) {
        ContentValues values = new ContentValues(6);
        values.put(LoanTable.LOAN_ID, loanId);
        values.put(LoanTable.LOAN_TYPE, loanType);
        values.put(LoanTable.LOAN_AMOUNT, loanAmount);
        values.put(LoanTable.REPAYMENT_PERIOD, repayment_period);
        values.put(LoanTable.TOTAL_PAYMENT, totalPayment);

        sqLiteDatabase.insert(LoanTable.TABLE_NAME, null, values);
    }

    public ArrayList<Loan> getLoans() {
        ArrayList<Loan> myLoans = new ArrayList<>();
        Cursor cursor = sqLiteDatabase.query(LoanTable.TABLE_NAME,LoanTable.ALL_COLUMNS, null, null, null, null, null);

        while (cursor.moveToNext()) {
            Loan loan = new Loan();
            String strLoanDate = cursor.getString(cursor.getColumnIndex(LoanTable.CREATED_AT));
            Date loanDate = null;
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
            try {
                loanDate = dateFormat.parse(strLoanDate);
            } catch (ParseException e) {
                e.printStackTrace();
            }

            String day = (String) android.text.format.DateFormat.format("dd", loanDate);
            String month = (String) android.text.format.DateFormat.format("MMM", loanDate);

            loan.setLoanId(cursor.getString(cursor.getColumnIndex(LoanTable.LOAN_ID)));
            loan.setLoanAmount(Double.parseDouble(cursor.getString(cursor.getColumnIndex(LoanTable.LOAN_AMOUNT))));
            loan.setRepaymentPeriod(cursor.getString(cursor.getColumnIndex(LoanTable.REPAYMENT_PERIOD)));
            loan.setTotalPayment((Double.parseDouble(cursor.getString(cursor.getColumnIndex(LoanTable.TOTAL_PAYMENT)))));
            loan.setLoanAppDay(String.valueOf(day));
            loan.setLoanAppMonth(String.valueOf(month));
            loan.setLoanType(cursor.getString(cursor.getColumnIndex(LoanTable.LOAN_TYPE)));
            loan.setLoanStatus("Active");
            loan.setLoanInterest("2.2%");
            myLoans.add(loan);
        }

        return myLoans;
    }

    public void getTotalSavings() {

    }

    public double getTotalLoans() {
        String query = "select sum(" + LoanTable.LOAN_AMOUNT + ") from " + LoanTable.TABLE_NAME + " as totalSavings";
        Cursor cursor = sqLiteDatabase.rawQuery(query, new String[]{});
        if(cursor.getCount() > 0) {
            cursor.moveToNext();
            return cursor.getDouble(0);
        }
        return 0.0;
    }

}
