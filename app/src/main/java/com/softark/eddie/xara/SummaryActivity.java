package com.softark.eddie.xara;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.softark.eddie.xara.Listeners.Listener;

public class SummaryActivity extends BaseActivity {

    private Button savingsButton;
    private Button loansButton;
//    private CardView loanCardView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing);
        Configuration config = getResources().getConfiguration();

//        loanCardView = (CardView) findViewById(R.id.savings_summary);
//
//        if(config.orientation == Configuration.ORIENTATION_LANDSCAPE) {
//            loanCardView.setLayoutParams(new RelativeLayout.LayoutParams((int)TypedValue.applyDimension(
//                    TypedValue.COMPLEX_UNIT_SP, 360, getResources().getDisplayMetrics()), (int) TypedValue.applyDimension(
//                    TypedValue.COMPLEX_UNIT_SP, 180, getResources().getDisplayMetrics())));
//            loanCardView.offsetTopAndBottom(80);
//        }

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        savingsButton = (Button) findViewById(R.id.savings_button);
        loansButton = (Button) findViewById(R.id.loans_button);

        loansButton.setOnClickListener(new Listener(getApplicationContext(), getSupportFragmentManager()));

    }

}
