package com.softark.eddie.xara.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.softark.eddie.xara.R;
import com.softark.eddie.xara.helpers.SessionManager;

/**
 * Created by Eddie on 3/14/2017.
 */

public class BaseActivity extends AppCompatActivity {

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

}
