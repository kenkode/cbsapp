package com.softark.eddie.xara;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.softark.eddie.xara.Model.LoanListView;

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

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoanActivity.this, LoanApplicationActivity.class));
            }
        });

//        Loan list implementation

//        et("date").toString());
//        monthText.setText(loan.get("month").toString());
//        loanType.setText(loan.get("loan").toString());
//        loanStatus.setText(loan.get("status").toString());
//        loanInterest.setText(loan.get("interest").t

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
        loans.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                startActivity(new Intent(LoanActivity.this, LoanDetailsActivity.class));
            }
        });

        loanListView = new LoanListView(getApplicationContext(), myLoans);
        loans.setAdapter(loanListView);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }
}