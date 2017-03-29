package com.softark.eddie.xara.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.ProgressBar;

import com.softark.eddie.xara.R;
import com.softark.eddie.xara.Requests.AccountRequest;
import com.softark.eddie.xara.model.Constant;

import java.util.ArrayList;
import java.util.HashMap;

public class AccountsActivity extends AppCompatActivity {

    private ListView members_list;
    private AccountRequest request;
    ProgressBar progressBar;
    private ArrayList<HashMap<String, String>> userDetails = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accounts);
        if(getSupportActionBar() != null)
        getSupportActionBar().setTitle("Accounts");

        progressBar = (ProgressBar) findViewById(R.id.load_progress);

        request = new AccountRequest(this);
        members_list = (ListView) findViewById(R.id.members_list);

        request.setAccounts(members_list, progressBar, Constant.ACC);

    }
}
