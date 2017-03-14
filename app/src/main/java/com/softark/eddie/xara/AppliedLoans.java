package com.softark.eddie.xara;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.widget.ListView;

import com.softark.eddie.xara.Model.AppliedLoanListView;

import java.util.ArrayList;
import java.util.HashMap;

public class AppliedLoans extends AppCompatActivity {

    private ListView listView;
    private ArrayList<HashMap<String, String>> appliedLoans;
    AppliedLoanListView appliedLoanListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_applied_loans);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        listView = (ListView) findViewById(R.id.applied_loans_listview);
        appliedLoans = new ArrayList<>();
        HashMap<String, String> appliedLoan[] = new HashMap[8];

        for(int i = 0;i < appliedLoan.length;i++) {
            appliedLoan[i] = new HashMap<>();
            appliedLoan[i].put("date", "21");
            appliedLoan[i].put("month", "Sep");
            appliedLoan[i].put("borrower", "Oirere Jr");
            appliedLoan[i].put("type", "Mortgage Loan");
            appliedLoan[i].put("amount", "12,000");
            appliedLoan[i].put("deadline", "12-Feb-2017");
            appliedLoans.add(appliedLoan[i]);
        }

        appliedLoanListView = new AppliedLoanListView(getApplicationContext(), getSupportFragmentManager(), appliedLoans);
        listView.setAdapter(appliedLoanListView);
    }

}
