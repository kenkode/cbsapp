package com.softark.eddie.xara.requests;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.softark.eddie.xara.decorators.RecyclerDecorator;
import com.softark.eddie.xara.R;
import com.softark.eddie.xara.adapters.AppliedLoanAdapter;
import com.softark.eddie.xara.adapters.LoanAdapter;
import com.softark.eddie.xara.helpers.SessionManager;
import com.softark.eddie.xara.model.Loan;
import com.softark.eddie.xara.model.User;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

/**
 * Created by Eddie on 3/27/2017.
 */

public class LoanRequest {

    private Context context;
    private FragmentManager fragment;
    private RecyclerView.Adapter loanAdapter;
    private AppliedLoanAdapter appliedLoanAdapter;
    private SessionManager session;

    public LoanRequest(Context context, FragmentManager fragment) {
        this.context = context;
        this.fragment = fragment;
        session = new SessionManager(context);
    }

    public void setLoans(View view, View progress, final String url) {
        final ArrayList<Loan> xLoans = new ArrayList<>();
        final RecyclerView recyclerView = (RecyclerView) view;
        final ProgressBar loanProgress = (ProgressBar) progress;
        Drawable recDrawable = ContextCompat.getDrawable(context, R.drawable.recycler_spacer);
        RecyclerDecorator decorator = new RecyclerDecorator(recDrawable);
        recyclerView.addItemDecoration(decorator);

        StringRequest request = new StringRequest(Request.Method.POST, RequestUrl.LOAN_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONArray request = new JSONArray(response);
                            for (int i = 0; i < request.length(); i++) {
                                Loan myLoan = new Loan();
                                JSONObject loan = null;
                                loan = request.getJSONObject(i);
                                myLoan.setLoanId(loan.getString("id"));
                                myLoan.setLoanStatus(loan.getInt("is_approved"));
                                myLoan.setLoanInterest(loan.getString("interest_rate") );
                                myLoan.setLoanType(loan.getString("loan_name"));
                                if(loan.getString("amount_disbursed").equals("null")){
                                    myLoan.setLoanAmount(0.00);
                                }else {
                                    myLoan.setLoanAmount(loan.getDouble("amount_disbursed"));
                                }
                                myLoan.setRepaymentPeriod(loan.getString("repayment_duration"));
                                myLoan.setTopUp(loan.getDouble("top_up_amount"));
                                myLoan.setCurrency(loan.getString("currency"));
                                Log.i("CUR", loan.getString("currency"));
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

//                                Adding user
                                String userId = loan.getString("user_id");
                                String userName = loan.getString("user_name");
                                String groupId = loan.getString("group_id");
                                String userPhone = loan.getString("phone");
                                String userEmail = loan.getString("email");

                                User user = new User(context, userId, userName, groupId, userEmail, userPhone);
                                myLoan.setUser(user);

                                try {
                                    myLoan.setLoanStartDate(dateFormat.parse(loan.getString("repayment_start_date")));
                                } catch (ParseException e) {
                                    e.printStackTrace();
                                }

                                xLoans.add(myLoan);

                            }


                        loanProgress.setVisibility(View.INVISIBLE);

                        switch (url) {
                            case RequestUrl.LOAN_URL:
                                loanAdapter = new LoanAdapter(context, fragment, xLoans);
                                break;
                            case RequestUrl.AL_URL:
                                loanAdapter = new AppliedLoanAdapter(context, fragment, xLoans);
                                break;
                        }

                        recyclerView.setAdapter(loanAdapter);
                    }catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        loanProgress.setVisibility(View.INVISIBLE);
                        Toast.makeText(context, "Something went wrong", Toast.LENGTH_LONG).show();
                        error.printStackTrace();
                    }
                })
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("user_id", session.getUserKey());
                return params;
            }
        };

        XSingleton.getInstance(context).addToRequestQueue(request);

    }

    public HashMap<String, String> setLoanRepaymentDetails(final String loanId,View totalInterest, View interest, View amount, View remaining) {
        final HashMap<String, String> iRDetails = new HashMap<>();
        final TextView interestTv = (TextView) interest;
        final TextView amountTv = (TextView) amount;
        final TextView remainingTv = (TextView) remaining;
        final TextView ttlInterest = (TextView) totalInterest;

        StringRequest request = new StringRequest(Request.Method.POST, RequestUrl.LR_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject details = new JSONObject(response);
                            interestTv.setText(String.valueOf((NumberFormat.getInstance(Locale.US.US).format(details.getDouble("interest_paid")))));
                            amountTv.setText(String.valueOf((NumberFormat.getInstance(Locale.US.US).format(details.getDouble("amount_paid")))));
                            remainingTv.setText(String.valueOf((NumberFormat.getInstance(Locale.US.US).format(details.getDouble("balance")))));
                            ttlInterest.setText(String.valueOf((NumberFormat.getInstance(Locale.US.US).format(details.getDouble("total_interest")))));

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                })
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("loan_id", loanId);
                params.put("user_id", session.getUserKey());
                return params;
            }
        };

        XSingleton.getInstance(context).addToRequestQueue(request);

        return iRDetails;
    }

}
