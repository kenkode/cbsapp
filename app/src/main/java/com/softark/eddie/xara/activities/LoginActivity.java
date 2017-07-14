package com.softark.eddie.xara.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import com.softark.eddie.xara.requests.UserRequest;
import com.softark.eddie.xara.helpers.SessionManager;
import com.softark.eddie.xara.R;

public class LoginActivity extends AppCompatActivity {

    private Button loginButton;
    private static final String MYTAG = "MYTAG";
    private EditText username, password;
    private Switch keepSignedIn;
    SessionManager session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        session = new SessionManager(this);

        username = (EditText) findViewById(R.id.username_editext);
        password = (EditText) findViewById(R.id.password_editext);
        keepSignedIn = (Switch) findViewById(R.id.remember_me_switch);

        if(session.isRemembered()) {
            Intent intent = new Intent(LoginActivity.this, SummaryActivity.class);
            startActivity(intent);
            finish();
        }

        loginButton = (Button) findViewById(R.id.login_button);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String strUser = username.getText().toString().trim();
                String strPass = password.getText().toString().trim();
                if(strUser.isEmpty()) {
                    Toast.makeText(LoginActivity.this, "Please enter username", Toast.LENGTH_SHORT).show();
                }else if(strPass.isEmpty()) {
                    Toast.makeText(LoginActivity.this, "Please enter password", Toast.LENGTH_SHORT).show();
                }else {
                    UserRequest user = new UserRequest(LoginActivity.this);
                    user.authenticateUser(strUser, strPass, keepSignedIn.isChecked(), 1);
                }
            }
        });
    }

    public void actDec(boolean exec) {
        if(exec) {
//            session.setLoggedIn(true);
//            session.setKeepSignedIn(keepSignedIn.isChecked());
//            Intent intent = new Intent(LoginActivity.this, SummaryActivity.class);
//            startActivity(intent);
//            finish();
        }
    }

}
