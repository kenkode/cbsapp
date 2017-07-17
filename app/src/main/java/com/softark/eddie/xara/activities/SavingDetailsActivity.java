package com.softark.eddie.xara.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.TextView;

import com.softark.eddie.xara.R;
import com.softark.eddie.xara.listeners.Listener;
import com.softark.eddie.xara.model.Constant;
import com.softark.eddie.xara.model.Loan;
import com.softark.eddie.xara.model.Saving;
import com.softark.eddie.xara.requests.LoanRequest;
import com.softark.eddie.xara.requests.SavingsRequest;

import java.text.NumberFormat;
import java.util.Locale;

/**
 * Created by kenkode on 7/14/2017.
 */

public class SavingDetailsActivity extends AppCompatActivity {

    SavingsRequest savingRequest;
    private TextView accno,amount,type,transno,mode,date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saving_details);

        savingRequest = new SavingsRequest(this, getSupportFragmentManager());

        Saving saving = getIntent().getExtras().getParcelable(Constant.SAVING);

        accno = (TextView) findViewById(R.id.saving_account_number);
        amount = (TextView) findViewById(R.id.saving_amount_value);
        type = (TextView) findViewById(R.id.saving_transaction_value);
        transno = (TextView) findViewById(R.id.saving_transaction_number);
        mode = (TextView) findViewById(R.id.saving_payment_mode);
        date = (TextView) findViewById(R.id.saving_date);


        assert saving != null;
        if(getSupportActionBar() != null)
            getSupportActionBar().setTitle(saving.getProduct());
//        lType.setText(loan.getLoanType());

        transno.setText("CL000000111");
        mode.setText("Mpesa");
        savingRequest.setSavingDetails(saving.getSavingId(),accno,type,amount,date);
    }

}