package com.softark.eddie.xara.activities;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.softark.eddie.xara.database.LoanMethods;
import com.softark.eddie.xara.listeners.Listener;
import com.softark.eddie.xara.adapters.GuarantorListView;
import com.softark.eddie.xara.R;
import com.softark.eddie.xara.model.Loan;

import java.text.DateFormatSymbols;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;

public class LoanApplicationActivity extends AppCompatActivity {

    private ListView listView;
    private ArrayList<HashMap<String, String>> guarantors = new ArrayList<>();
    private GuarantorListView guarantorListView;
    private Button applyButton;
    private EditText loanType, loanAmount, period;
    private TextView totalAmount;
    private Double actualAmount;
    private String repay_period;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loan_application);
//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Appy For Loan");

        listView = (ListView) findViewById(R.id.guaranters_list);
        loanType = (EditText) findViewById(R.id.loan_type_input);
        loanAmount = (EditText) findViewById(R.id.loan_amount_input);
        period = (EditText) findViewById(R.id.loan_period_calendar);
        totalAmount = (TextView) findViewById(R.id.total_amount_payable);
        actualAmount = 0.0;
        totalAmount.setText("PAYMENT: " + actualAmount);
        period.setFocusable(false);
        period.setGravity(Gravity.CENTER);

        period.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar calendar = Calendar.getInstance();
                DatePickerDialog dialog = new DatePickerDialog(LoanApplicationActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        String loanReturnDate = dayOfMonth + "/" + month + "/" + year;
//                        new DateFormatSymbols().getMonths()[month - 1]
                        repay_period = year + "/" + month + "/" + dayOfMonth;
                        period.setText(loanReturnDate);
                    }
                }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
                dialog.show();
            }
        });

//        period.setOnFocusChangeListener(new View.OnFocusChangeListener() {
//            @Override
//            public void onFocusChange(View v, boolean hasFocus) {
//                if(hasFocus) {
//
//                }
//            }
//        });

        loanAmount.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String amount = loanAmount.getText().toString().trim();
                try {
                    actualAmount = Double.parseDouble(amount);
                    actualAmount += 500;
                    totalAmount.setText("PAYMENT: " + actualAmount);
                } catch (NumberFormatException e) {
                    totalAmount.setText("PAYMENT: " + 0);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        HashMap<String, String> hashMap[] = new HashMap[26];
        for (int i = 0; i < hashMap.length; i++) {
            hashMap[i] = new HashMap<>();
            hashMap[i].put("guarantor", "Eddie Branth");
            guarantors.add(hashMap[i]);
        }

        guarantorListView = new GuarantorListView(getApplicationContext(), guarantors);
        listView.setAdapter(guarantorListView);

        applyButton = (Button) findViewById(R.id.apply_button);
        applyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String strLoanType = loanType.getText().toString().trim();
                String strLoanAmount = loanAmount.getText().toString().trim();
//                String strLoanPeriod = period.getText().toString().trim();
                int loanMetric = 0;
                int toIntLoanAmount = 0;

                if(strLoanType.isEmpty()) {
                    Toast.makeText(LoanApplicationActivity.this, "Specify loan type", Toast.LENGTH_SHORT).show();
                }else if(strLoanAmount.isEmpty()) {
                    Toast.makeText(LoanApplicationActivity.this, "Specify loan amount", Toast.LENGTH_SHORT).show();
                }else if(repay_period.isEmpty()) {
                    Toast.makeText(LoanApplicationActivity.this, "Specify loan period", Toast.LENGTH_SHORT).show();
                }else {
                    try {
                        toIntLoanAmount = Integer.parseInt(strLoanAmount);
                    } catch (NumberFormatException e) {
                        Toast.makeText(LoanApplicationActivity.this, "Invalid amount type", Toast.LENGTH_SHORT).show();
                    }

                    LoanMethods loanMethods = new LoanMethods(LoanApplicationActivity.this);
                    loanMethods.applyLoan(
                            null,
                            strLoanType,
                            toIntLoanAmount,
                            repay_period,
                            actualAmount);

                    startActivity(new Intent(LoanApplicationActivity.this, SummaryActivity.class));

                }
            }
        });

    }
}