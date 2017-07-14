package com.softark.eddie.xara.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.TextView;

import com.softark.eddie.xara.requests.LoanRequest;
import com.softark.eddie.xara.listeners.Listener;
import com.softark.eddie.xara.R;
import com.softark.eddie.xara.model.Constant;
import com.softark.eddie.xara.model.Loan;

import java.text.NumberFormat;
import java.util.Locale;

public class LoanDetailsActivity extends AppCompatActivity {

    private Button payButton;
    LoanRequest loanRequest;
    private TextView lType,iPaid,ttlInterest, lAmount, lInterest, lPeriod, lTtlPayment, lTopUp, pAMount, pPeriodElapsed, pRemainingAmount, pRemainingPeriod;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loan_details);

        loanRequest = new LoanRequest(this, getSupportFragmentManager());

        Loan loan = getIntent().getExtras().getParcelable(Constant.LOAN);

        ttlInterest = (TextView) findViewById(R.id.loan_x_interest_value);
        iPaid = (TextView) findViewById(R.id.interest_paid_value);
        lAmount = (TextView) findViewById(R.id.loan_amount_value);
        lInterest = (TextView) findViewById(R.id.loan_interest_value);
        lPeriod = (TextView) findViewById(R.id.loan_period_value);
        lTtlPayment = (TextView) findViewById(R.id.total_payment_value);
        lTopUp = (TextView) findViewById(R.id.top_up_amount);
        pAMount = (TextView) findViewById(R.id.amount_paid_value);
        pPeriodElapsed = (TextView) findViewById(R.id.period_elapsed_value);
        pRemainingAmount = (TextView) findViewById(R.id.remaining_amount_value);
        pRemainingPeriod = (TextView) findViewById(R.id.remaining_period_value);

        assert loan != null;
        if(getSupportActionBar() != null)
        getSupportActionBar().setTitle(loan.getLoanType());
//        lType.setText(loan.getLoanType());
        String months = String.valueOf(loan.getRepaymentPeriod()).concat(" ") + getString(R.string.disp_month_string);
        lAmount.setText(loan.getCurrency().concat(" ").concat(NumberFormat.getInstance(Locale.US).format(loan.getLoanAmount())));
        lInterest.setText(loan.getLoanInterest());
        lPeriod.setText(months);
        lTtlPayment.setText(loan.getCurrency().concat(" ").concat(NumberFormat.getInstance(Locale.US).format(loan.getTotalPayment())));
        lTopUp.setText(loan.getCurrency().concat(" ").concat(NumberFormat.getInstance(Locale.US).format(loan.getTopUp())));
        pPeriodElapsed.setText(String.valueOf(loan.getPeriodElapsed()));
        pRemainingPeriod.setText(String.valueOf(loan.getRemainingPeriod()));
        loanRequest.setLoanRepaymentDetails(loan.getLoanId(), ttlInterest, iPaid, pAMount, pRemainingAmount);

        payButton = (Button) findViewById(R.id.pay_now_button);
        payButton.setOnClickListener(new Listener(getApplicationContext(), getSupportFragmentManager()));
    }

}
