package com.softark.eddie.xara.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.softark.eddie.xara.requests.SummaryRequest;
import com.softark.eddie.xara.database.LoanMethods;
import com.softark.eddie.xara.helpers.SessionManager;
import com.softark.eddie.xara.listeners.Listener;
import com.softark.eddie.xara.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class SummaryActivity extends AppCompatActivity {

    private Button savingsButton;
    private Button loansButton;
    private TextView username, email, phone, position, savings, loans;
    private ListView recentActivities;
    private ProgressBar savingProgress, loanProgress;
    LoanMethods methods;
    private boolean backPressed;
    private SessionManager session;
    private SummaryRequest request;
    private ArrayList<HashMap<String, String>> activities;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing);
        session = new SessionManager(this);
        methods = new LoanMethods(this);
        request = new SummaryRequest(this);

        backPressed = false;

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null)
            actionBar.setTitle("Xara");

        savingsButton = (Button) findViewById(R.id.savings_button);
        loansButton = (Button) findViewById(R.id.loans_button);
        username = (TextView) findViewById(R.id.username);
        email = (TextView) findViewById(R.id.email);
        phone = (TextView) findViewById(R.id.phone_number);
        position = (TextView) findViewById(R.id.position);
        savings = (TextView) findViewById(R.id.total_savings);
        loans = (TextView) findViewById(R.id.total_loans);
        savingProgress = (ProgressBar) findViewById(R.id.saving_progress);
        loanProgress = (ProgressBar) findViewById(R.id.loan_progress);
        recentActivities = (ListView) findViewById(R.id.recent_activities_list);
        String userType = session.getUserType();
        String userName = session.getUserName();
        request.setSummary(recentActivities, savings, loans, savingProgress, loanProgress,userName,userType);

        if(session.isLoggedIn()) {
            Map<String, String> userDetails = session.getUser();
            username.setText(userDetails.get(SessionManager.USER_NAME));
            email.setText(userDetails.get(SessionManager.USER_EMAIL));
            phone.setText(userDetails.get(SessionManager.USER_PHONE));
            position.setText(userDetails.get(SessionManager.USER_TYPE));
        }else {
            finish();
        }

        loansButton.setOnClickListener(new Listener(getApplicationContext(), getSupportFragmentManager()));
        savingsButton.setOnClickListener(new Listener(getApplicationContext(), getSupportFragmentManager()));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        String userType = session.getUserType();
        if(userType.equals("admin")) {
            getMenuInflater().inflate(R.menu.main_menu, menu);
        }else {
            getMenuInflater().inflate(R.menu.menu_member, menu);
        }

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.accounts:
                startActivity(new Intent(SummaryActivity.this, AccountsActivity.class));
                break;
            case R.id.applied_loans:
                startActivity(new Intent(SummaryActivity.this, AppliedLoans.class));
                break;
            case R.id.logout:
                session.setLoggedIn(false);
                session.setKeepSignedIn(false);
                startActivity(new Intent(SummaryActivity.this, LoginActivity.class));
                break;
            default:
                return super.onOptionsItemSelected(item);
        }
        return true;
    }

    @Override
    public void onBackPressed() {
        if (backPressed) {
            moveTaskToBack(true);
        }
        Toast.makeText(getApplicationContext(), "Press back again to exit", Toast.LENGTH_SHORT).show();
        backPressed = true;
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (backPressed) {
                moveTaskToBack(true);
            }
            Toast.makeText(getApplicationContext(), "Press back again to exit", Toast.LENGTH_SHORT).show();
            backPressed = true;
        }
        return false;
    }

}
