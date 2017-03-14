package com.softark.eddie.xara;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.ListView;

import com.softark.eddie.xara.Model.AppliedLoanGuarantor;
import com.softark.eddie.xara.Model.GuarantorListView;

import java.util.ArrayList;
import java.util.HashMap;

public class AppliedLoanDetails extends AppCompatActivity {

    private ListView listView;
    private ArrayList<HashMap<String, String>> arrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_applied_loan_details);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        listView = (ListView) findViewById(R.id.applied_loan_details_guarantors_listview);

        arrayList = new ArrayList<>();
        HashMap<String, String> hashMap[] = new HashMap[8];

        for(int i = 0;i < hashMap.length;i++) {
            hashMap[i] = new HashMap<>();
            hashMap[i].put("name", "Oirere Jr");
            hashMap[i].put("state", "Rejected");
            arrayList.add(hashMap[i]);
        }

        AppliedLoanGuarantor details = new AppliedLoanGuarantor(getApplicationContext(), arrayList);
        listView.setAdapter(details);

    }
}
