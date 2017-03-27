package com.softark.eddie.xara.activities;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.View;
import android.widget.ListView;

import com.softark.eddie.xara.Decorators.RecyclerDecorator;
import com.softark.eddie.xara.Requests.LoanRequest;
import com.softark.eddie.xara.adapters.LoanAdapter;
import com.softark.eddie.xara.R;
import com.softark.eddie.xara.database.LoanMethods;
import com.softark.eddie.xara.model.Loan;

import java.util.ArrayList;
import java.util.HashMap;

public class LoanActivity extends AppCompatActivity {

    private LoanAdapter loanAdapter;
    private RecyclerView loans;
    ArrayList<Loan> myLoans;
//    private TextView noLoansText;
    LoanRequest loan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loan);
        getSupportActionBar().setTitle("My Loans");

        loan = new LoanRequest(this, getSupportFragmentManager());

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoanActivity.this, LoanApplicationActivity.class));
            }
        });

        loans = (RecyclerView) findViewById(R.id.my_loans);
        Drawable recDrawable = ContextCompat.getDrawable(this, R.drawable.recycler_spacer);
        RecyclerDecorator decorator = new RecyclerDecorator(recDrawable);
        loans.addItemDecoration(decorator);

        loan.setLoans(loans);

//        loanAdapter = new LoanAdapter(getApplicationContext(),getSupportFragmentManager(), myLoans);
//        loans.setAdapter(loanAdapter);
    }
}
