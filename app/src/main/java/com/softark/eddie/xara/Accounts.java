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

public class Accounts extends AppCompatActivity {

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

        HashMap<String, String> user = new HashMap<>();
        user.put("username", "Edward Oirere");
        user.put("rank", "Admin");
        userDetails.add(user);

        HashMap<String, String> user1 = new HashMap<>();
        user1.put("username", "Branth");
        user1.put("rank", "Admin");
        userDetails.add(user1);

        HashMap<String, String> user2 = new HashMap<>();
        user2.put("username", "Eddie");
        user2.put("rank", "Admin");
        userDetails.add(user2);

        MemberListView memberListView = new MemberListView(getApplicationContext(), userDetails);
        members_list.setAdapter(memberListView);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }
}
