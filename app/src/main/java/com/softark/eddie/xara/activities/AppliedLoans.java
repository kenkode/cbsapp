package com.softark.eddie.xara.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.ListView;
import android.widget.ProgressBar;

import com.softark.eddie.xara.Requests.LoanRequest;
import com.softark.eddie.xara.Requests.RequestUrl;
import com.softark.eddie.xara.adapters.AppliedLoanAdapter;
import com.softark.eddie.xara.R;

import java.util.ArrayList;
import java.util.HashMap;

public class AppliedLoans extends AppCompatActivity {

    private RecyclerView recycler;
    private ArrayList<HashMap<String, String>> appliedLoans;
    private AppliedLoanAdapter adapter;
    private LoanRequest loan;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_applied_loans);
        progressBar = (ProgressBar) findViewById(R.id.applied_loan_progress);

        loan = new LoanRequest(this, getSupportFragmentManager());

        getSupportActionBar().setTitle("Applied Loans");

        recycler = (RecyclerView) findViewById(R.id.applied_loans_recycler_view);
        loan.setLoans(recycler, progressBar, RequestUrl.AL_URL);
    }

}
