package com.softark.eddie.xara.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.softark.eddie.xara.adapters.LoanAdapter;
import com.softark.eddie.xara.listeners.Listener;
import com.softark.eddie.xara.R;
import com.softark.eddie.xara.model.Loan;

public class LoanDetailsActivity extends AppCompatActivity {

    private Button payButton;
    private TextView lType, lAmount, lInterest, lPeriod, lTtlPayment, lTopUp, pAMount, pPeriodElapsed, pRemainingAmount, pRemainingPeriod;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loan_details);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        Loan loan = getIntent().getExtras().getParcelable(LoanAdapter.LOAN);

        lType = (TextView) findViewById(R.id.loan_details_loan_name);
        lAmount = (TextView) findViewById(R.id.loan_amount_value);
        lInterest = (TextView) findViewById(R.id.loan_interest_value);
        lPeriod = (TextView) findViewById(R.id.loan_period_value);
        lTtlPayment = (TextView) findViewById(R.id.total_payment_value);
        lTopUp = (TextView) findViewById(R.id.top_up_amount);
        pAMount = (TextView) findViewById(R.id.amount_paid_value);
        pPeriodElapsed = (TextView) findViewById(R.id.period_elapsed_value);
        pRemainingAmount = (TextView) findViewById(R.id.remaining_amount_text);
        pRemainingPeriod = (TextView) findViewById(R.id.remaining_period_value);

        lType.setText(loan.getLoanType());
        lAmount.setText(String.valueOf(loan.getLoanAmount()));
        lInterest.setText(loan.getLoanInterest());
        lPeriod.setText(loan.getRepaymentPeriod());
        lTtlPayment.setText(String.valueOf(loan.getTotalPayment()));
//        lTopUp.setText(loan.getTopUpAmount());
//        pAMount.setText(loan.getPayedAmount());
//        pPeriodElapsed.setText(loan.getPeriodElapsed());
//        pRemainingAmount.setText(loan.getRemainingAmount());
//        pRemainingPeriod.setText(loan.getRemainingPeriod());

        payButton = (Button) findViewById(R.id.pay_now_button);
        payButton.setOnClickListener(new Listener(getApplicationContext(), getSupportFragmentManager()));
    }

}
