package com.softark.eddie.xara.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.Button;

import com.softark.eddie.xara.listeners.Listener;
import com.softark.eddie.xara.R;

public class LoanDetailsActivity extends AppCompatActivity {

    private Button payButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loan_details);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayShowTitleEnabled(false);

        payButton = (Button) findViewById(R.id.pay_now_button);
        payButton.setOnClickListener(new Listener(getApplicationContext(), getSupportFragmentManager()));
    }

}
