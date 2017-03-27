package com.softark.eddie.xara.Requests;

import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.softark.eddie.xara.adapters.LoanAdapter;
import com.softark.eddie.xara.model.Loan;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

/**
 * Created by Eddie on 3/27/2017.
 */

public class LoanRequest {

    private Context context;
    private FragmentManager fragment;
    private LoanAdapter loanAdapter;

    public LoanRequest(Context context, FragmentManager fragment) {
        this.context = context;
        this.fragment = fragment;
    }

    public void setLoans(View view) {
        final ArrayList<Loan> xLoans = new ArrayList<>();
        final RecyclerView listView = (RecyclerView) view;

        JsonArrayRequest request = new JsonArrayRequest(RequestUrl.LOAN_URL,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        JSONArray loans = response;

                        for (int i = 0;i < loans.length();i++) {
                            Loan myLoan = new Loan();
                            JSONObject loan = null;
                            try {
                                loan = loans.getJSONObject(i);
                                myLoan.setLoanId(loan.getString("loanproduct_id"));
                                myLoan.setLoanStatus(loan.getInt("is_approved"));
                                myLoan.setLoanInterest(loan.getString("interest_rate"));
                                myLoan.setLoanType(loan.getString("name"));
                                myLoan.setLoanAmount(loan.getDouble("amount_disbursed"));
                                myLoan.setRepaymentPeriod(loan.getString("repayment_duration"));
                                myLoan.setTotalPayment(20000.00);

                                Date loanDate = null;
                                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
                                try {
                                    loanDate = dateFormat.parse(loan.getString("application_date"));
                                } catch (ParseException e) {
                                    e.printStackTrace();
                                }
                                String day = (String) android.text.format.DateFormat.format("dd", loanDate);
                                String month = (String) android.text.format.DateFormat.format("MMM", loanDate);

                                myLoan.setLoanAppDay(day);
                                myLoan.setLoanAppMonth(month);

                                try {
                                    myLoan.setLoanStartDate(dateFormat.parse(loan.getString("repayment_start_date")));
                                } catch (ParseException e) {
                                    e.printStackTrace();
                                }

                                xLoans.add(myLoan);

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }

                        loanAdapter = new LoanAdapter(context, fragment, xLoans);
                        listView.setAdapter(loanAdapter);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(context, "Something went wrong", Toast.LENGTH_LONG).show();
                    }
                });

        XSingleton.getInstance(context).addToRequestQueue(request);

    }

}
