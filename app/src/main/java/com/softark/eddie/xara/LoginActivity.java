package com.softark.eddie.xara;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.softark.eddie.xara.Listeners.Listener;

public class LoginActivity extends AppCompatActivity {

    private Button loginButton;
    private static final String MYTAG = "MYTAG";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        loginButton = (Button) findViewById(R.id.login_button);

        loginButton.setOnClickListener(new Listener(getApplicationContext(), getSupportFragmentManager()));

    }
}
