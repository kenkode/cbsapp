package com.softark.eddie.xara.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.preference.Preference;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.softark.eddie.xara.database.LoanMethods;
import com.softark.eddie.xara.helpers.SessionManager;
import com.softark.eddie.xara.listeners.Listener;
import com.softark.eddie.xara.R;
import com.softark.eddie.xara.model.User;

import org.w3c.dom.Text;

import java.util.HashMap;

public class SummaryActivity extends BaseActivity {

    private Button savingsButton;
    private Button loansButton;
    private TextView username, position, savings, loans;
    LoanMethods methods;
    private boolean backPressed;
    SessionManager session;
    User user;
//    private CardView loanCardView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing);
        session = new SessionManager(this);
        methods = new LoanMethods(this);
        user = new User(this);
        backPressed = false;

//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null)
//            actionBar.setDisplayShowTitleEnabled(false);
            actionBar.setTitle("Xara");

        savingsButton = (Button) findViewById(R.id.savings_button);
        loansButton = (Button) findViewById(R.id.loans_button);
        username = (TextView) findViewById(R.id.username);
        position = (TextView) findViewById(R.id.position);

        savings = (TextView) findViewById(R.id.total_savings);
        loans = (TextView) findViewById(R.id.total_loans);

        loans.setText(String.valueOf(methods.getTotalLoans()));

        HashMap<String, String> userDetails = user.getUserDetails();
        username.setText(userDetails.get("username"));
        position.setText(userDetails.get("email"));

        loansButton.setOnClickListener(new Listener(getApplicationContext(), getSupportFragmentManager()));

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
            finish();
            moveTaskToBack(true);
        }
        Toast.makeText(getApplicationContext(), "Press back again to exit", Toast.LENGTH_SHORT).show();
        backPressed = true;
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (backPressed) {
                finish();
                moveTaskToBack(true);
            }
            Toast.makeText(getApplicationContext(), "Press back again to exit", Toast.LENGTH_SHORT).show();
            backPressed = true;
        }
        return false;
    }
}
