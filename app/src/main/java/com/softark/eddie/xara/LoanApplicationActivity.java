package com.softark.eddie.xara;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.softark.eddie.xara.Dialogs.PaymentSummaryDialog;
import com.softark.eddie.xara.Listeners.Listener;
import com.softark.eddie.xara.Model.GuarantorListView;

import java.util.ArrayList;
import java.util.HashMap;

public class LoanApplicationActivity extends AppCompatActivity {

    private ListView listView;
    private ArrayList<HashMap<String, String>> guarantors = new ArrayList<>();
    private GuarantorListView guarantorListView;
    private Button applyButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loan_application);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayShowTitleEnabled(false);

        listView = (ListView) findViewById(R.id.guaranters_list);

        HashMap<String, String> hashMap[] = new HashMap[26];
        for(int i = 0;i < hashMap.length;i++) {
            hashMap[i] = new HashMap<>();
            hashMap[i].put("guarantor", "Oirere Jr");
            guarantors.add(hashMap[i]);
        }

        guarantorListView = new GuarantorListView(getApplicationContext(), guarantors);
        listView.setAdapter(guarantorListView);

        applyButton = (Button) findViewById(R.id.apply_button);
        applyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PaymentSummaryDialog dialog = new PaymentSummaryDialog();
                dialog.show(getSupportFragmentManager(), "PaymentConfirm");
            }
        });

    }
}