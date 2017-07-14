package com.softark.eddie.xara.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;

import com.softark.eddie.xara.R;
import com.softark.eddie.xara.requests.SavingsRequest;
import com.softark.eddie.xara.requests.RequestUrl;
import com.softark.eddie.xara.model.Saving;

import java.util.ArrayList;

/**
 * Created by Omwaks on 7/11/2017.
 */

public class SavingsActivity extends AppCompatActivity{

    private com.softark.eddie.xara.adapters.SavingAdapter savingAdapter;
    private RecyclerView savings;
    private ProgressBar savingProgress;
    private ArrayList<Saving> mySavings;
    //    private TextView noLoansText;
    private SavingsRequest saving;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_savings);
        getSupportActionBar().setTitle("My Savings");

        savingProgress = (ProgressBar) findViewById(R.id.load_savings_progress);

        saving = new SavingsRequest(this, getSupportFragmentManager());

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SavingsActivity.this, LoanApplicationActivity.class));
            }
        });

        savings = (RecyclerView) findViewById(R.id.my_savings);

        saving.setSavings(savings, savingProgress, RequestUrl.SAVING_URL);
    }
}
