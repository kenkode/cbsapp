package com.softark.eddie.xara.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;

import com.softark.eddie.xara.requests.LoanRequest;
import com.softark.eddie.xara.requests.RequestUrl;
import com.softark.eddie.xara.adapters.LoanAdapter;
import com.softark.eddie.xara.R;
import com.softark.eddie.xara.model.Loan;

import java.util.ArrayList;

public class LoanActivity extends AppCompatActivity {

    private LoanAdapter loanAdapter;
    private RecyclerView loans;
    private ProgressBar loanProgress;
    private ArrayList<Loan> myLoans;
//    private TextView noLoansText;
    private LoanRequest loan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loan);
        getSupportActionBar().setTitle("My Loans");

        loanProgress = (ProgressBar) findViewById(R.id.load_loans_progress);

        loan = new LoanRequest(this, getSupportFragmentManager());

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoanActivity.this, LoanApplicationActivity.class));
            }
        });

        loans = (RecyclerView) findViewById(R.id.my_loans);

        loan.setLoans(loans, loanProgress, RequestUrl.LOAN_URL);
    }
}
