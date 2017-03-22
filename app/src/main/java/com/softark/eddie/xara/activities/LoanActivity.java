package com.softark.eddie.xara.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import com.softark.eddie.xara.adapters.LoanListView;
import com.softark.eddie.xara.R;
import com.softark.eddie.xara.model.User;

import java.util.ArrayList;
import java.util.HashMap;

public class LoanActivity extends AppCompatActivity {

    private LoanListView loanListView;
    private ListView loans;
    ArrayList<HashMap<String, String>> myLoans = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loan);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        Toast.makeText(this, "Database acquired", Toast.LENGTH_LONG).show();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoanActivity.this, LoanApplicationActivity.class));
            }
        });

        HashMap<String, String> hashMap[] = new HashMap[5];

        for(int i = 0;i < hashMap.length;i++) {
            hashMap[i] = new HashMap<>();
            hashMap[i].put("date", "21");
            hashMap[i].put("month", "Feb");
            hashMap[i].put("loan", "Mortgage Loan");
            hashMap[i].put("status", "Pending");
            hashMap[i].put("interest", "12.2%");
            myLoans.add(hashMap[i]);
        }

        loans = (ListView) findViewById(R.id.my_loans);

        loanListView = new LoanListView(getApplicationContext(),getSupportFragmentManager(), myLoans);
        loans.setAdapter(loanListView);
    }
}
