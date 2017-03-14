package com.softark.eddie.xara;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.softark.eddie.xara.Model.MemberListView;

import java.util.ArrayList;
import java.util.HashMap;

public class AccountsActivity extends AppCompatActivity {

    private ListView members_list;
    private ArrayList<HashMap<String, String>> userDetails = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accounts);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        members_list = (ListView) findViewById(R.id.memebers_list);

        HashMap<String, String> users[] = new HashMap[7];

        for(int i = 0;i < users.length;i++) {
            users[i] = new HashMap<>();
            users[i].put("username", "Eddie");
            users[i].put("rank", "Admin");
            userDetails.add(users[i]);
        }

        MemberListView memberListView = new MemberListView(getApplicationContext(), userDetails);
        members_list.setAdapter(memberListView);
    }
}
