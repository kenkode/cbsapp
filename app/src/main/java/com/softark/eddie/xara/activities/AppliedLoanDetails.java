package com.softark.eddie.xara.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.softark.eddie.xara.requests.GuarantorRequest;
import com.softark.eddie.xara.R;
import com.softark.eddie.xara.model.Constant;
import com.softark.eddie.xara.model.Loan;

import java.util.ArrayList;
import java.util.HashMap;

public class AppliedLoanDetails extends AppCompatActivity {

    private ListView listView;
    private GuarantorRequest request;
    private Button disburseButton, rejectButton;
    private ArrayList<HashMap<String, String>> arrayList;
    private TextView userName, userEmail, userPhone, loanInterest, loanAmount, loanPeriod, loanAppDate, totalPayment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_applied_loan_details);

        userName = (TextView) findViewById(R.id.username);
        userEmail = (TextView) findViewById(R.id.email);
        userPhone = (TextView) findViewById(R.id.phone_number);
        loanInterest = (TextView) findViewById(R.id.applied_loan_details_loan_interest_input);
        loanAmount = (TextView) findViewById(R.id.applied_loan_details_loan_amount_input);
        loanPeriod = (TextView) findViewById(R.id.applied_loan_details_loan_period_input);
        loanAppDate = (TextView) findViewById(R.id.applied_loan_details_loan_app_date);
        totalPayment = (TextView) findViewById(R.id.applied_loan_details_loan_total_input);
        listView = (ListView) findViewById(R.id.applied_loan_details_guarantors_listview);
        disburseButton = (Button) findViewById(R.id.disburse_button);
        rejectButton = (Button) findViewById(R.id.reject_button);

        disburseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(AppliedLoanDetails.this, "OK", Toast.LENGTH_LONG).show();
            }
        });

        Loan loan = getIntent().getExtras().getParcelable(Constant.LOAN);

        assert loan != null;
        getSupportActionBar().setTitle(loan.getLoanType());
        userName.setText(loan.getUser().getUserName());
        userEmail.setText(loan.getUser().getUserEmail());
        userPhone.setText(loan.getUser().getUserPhone());
        loanInterest.setText(loan.getLoanInterest());
        loanAmount.setText(String.valueOf(loan.getLoanAmount()));
        loanPeriod.setText(loan.getRepaymentPeriod());
        loanAppDate.setText(loan.getLoanAppDay().concat("-").concat(loan.getLoanAppMonth()));
        totalPayment.setText(String.valueOf(loan.getTotalPayment()));

        request = new GuarantorRequest(this);
        request.setGuarantors(listView, disburseButton);

    }
}
