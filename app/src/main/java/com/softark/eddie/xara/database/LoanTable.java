package com.softark.eddie.xara.database;

/**
 * Created by Eddie on 3/22/2017.
 */

public class LoanTable {

    public static final String TABLE_NAME = "loans";
    public static final String LOAN_ID = "loan_id";
    public static final String LOAN_TYPE = "loan_type";
    public static final String LOAN_AMOUNT = "loan_amount";
    public static final String TOTAL_PAYMENT = "total_payment";
    public static final String CREATED_AT = "created_at";
    public static final String REPAYMENT_PERIOD = "repayment_period";

    public static final String ALL_COLUMNS[] = {
            LOAN_ID, LOAN_TYPE, LOAN_AMOUNT, TOTAL_PAYMENT, CREATED_AT, REPAYMENT_PERIOD
    };

    public static final String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + "(" +
            LOAN_ID + " text," +
            LOAN_TYPE + " text," +
            LOAN_AMOUNT + " real," +
            CREATED_AT + " datetime default CURRENT_TIMESTAMP," +
            REPAYMENT_PERIOD + " text," +
            TOTAL_PAYMENT + " real," +
            " PRIMARY KEY(" + LOAN_ID + "))";

    public static final String DROP_TABLE = "DROP TABLE IF EXISTS " + TABLE_NAME;

}
