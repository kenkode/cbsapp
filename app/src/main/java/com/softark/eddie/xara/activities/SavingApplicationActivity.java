package com.softark.eddie.xara.activities;

/**
 * Created by kenkode on 7/14/2017.
 */

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.softark.eddie.xara.requests.AccountRequest;
import com.softark.eddie.xara.requests.ApplyRequest;
import com.softark.eddie.xara.database.LoanMethods;
import com.softark.eddie.xara.adapters.GuarantorAdapter;
import com.softark.eddie.xara.R;
import com.softark.eddie.xara.model.Constant;
import com.softark.eddie.xara.requests.RequestUrl;
import com.softark.eddie.xara.requests.SavingsRequest;
import com.softark.eddie.xara.requests.saveRequest;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

public class SavingApplicationActivity extends AppCompatActivity {


    //private saveRequest savingRequest;
    private SavingsRequest savingRequest;
    private Button saveButton;
    private EditText savingAmount;
    private ProgressBar loadProgress;
    private Spinner modeSpinner,typeSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saving_application);

        getSupportActionBar().setTitle("Saving Transaction");

        savingRequest = new SavingsRequest(this, getSupportFragmentManager());

        loadProgress = (ProgressBar) findViewById(R.id.select_g_progress);
        savingAmount = (EditText) findViewById(R.id.saving_amount_input);

        //productSpinner = (Spinner) findViewById(R.id.saving_product_list);
        typeSpinner = (Spinner) findViewById(R.id.saving_type_list);
        modeSpinner = (Spinner) findViewById(R.id.payemnt_mode);

        String[] types=new String[]{"Deposit","Withdraw"};

        ArrayAdapter<String> typeArray= new ArrayAdapter<String>(SavingApplicationActivity.this,android.R.layout.simple_spinner_item, types);
        typeArray.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        typeSpinner.setAdapter(typeArray);

        String[] modes=new String[]{"Mpesa","Cash","Cheque","Bank"};

        ArrayAdapter<String> modeArray= new ArrayAdapter<String>(SavingApplicationActivity.this,android.R.layout.simple_spinner_item, modes);
        modeArray.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        modeSpinner.setAdapter(modeArray);

        loadProgress.setVisibility(View.INVISIBLE);

        //savingRequest.populateSpinners(productSpinner,loadProgress);

        saveButton = (Button) findViewById(R.id.saving_button);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // String strSavingProduct = productSpinner.getSelectedItem().toString().trim();
                String strSavingType = typeSpinner.getSelectedItem().toString().trim();
                String strPaymentMode = modeSpinner.getSelectedItem().toString().trim();
                String strSavingAmount = savingAmount.getText().toString().trim();
//                String strLoanPeriod = period.getText().toString().trim();
                int loanMetric = 0;
                int toIntSavingAmount = 0;

                if(strSavingType.isEmpty()) {
                    Toast.makeText(SavingApplicationActivity.this, "Specify saving product", Toast.LENGTH_SHORT).show();
                }else if(strSavingAmount.isEmpty()) {
                    Toast.makeText(SavingApplicationActivity.this, "Specify saving amount", Toast.LENGTH_SHORT).show();
                }else {
                        savingRequest.submitSaving(strSavingType,strPaymentMode, strSavingAmount, loadProgress, RequestUrl.SAVING_URL);

                    //startActivity(new Intent(SavingApplicationActivity.this, SummaryActivity.class));

                }
            }
        });

    }
}
