package com.softark.eddie.xara;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

/**
 * Created by Eddie on 3/14/2017.
 */

public class BaseActivity extends AppCompatActivity {

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.accounts:
                startActivity(new Intent(BaseActivity.this, AccountsActivity.class));
                break;
            case R.id.applied_loans:
                startActivity(new Intent(BaseActivity.this, AppliedLoans.class));
                break;
            case R.id.logout:
                startActivity(new Intent(BaseActivity.this, LoginActivity.class));
                break;
            default:
               return super.onOptionsItemSelected(item);
        }
        return true;
    }
}
