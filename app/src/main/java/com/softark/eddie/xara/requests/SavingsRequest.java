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
import com.android.volley.toolbox.StringRequest;
import com.softark.eddie.xara.decorators.RecyclerDecorator;
import com.softark.eddie.xara.R;
import com.softark.eddie.xara.adapters.AppliedLoanAdapter;
import com.softark.eddie.xara.adapters.SavingAdapter;
import com.softark.eddie.xara.helpers.SessionManager;
import com.softark.eddie.xara.model.Saving;
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

public class SavingsRequest {

    private Context context;
    private FragmentManager fragment;
    private RecyclerView.Adapter savingAdapter;
    private AppliedLoanAdapter appliedLoanAdapter;
    private SessionManager session;
    String userId = "";

    public SavingsRequest(Context context, FragmentManager fragment) {
        this.context = context;
        this.fragment = fragment;
        session = new SessionManager(context);
    }

    public void setSavings(View view, View progress, final String url) {
        final ArrayList<Saving> xSavings = new ArrayList<>();
        final RecyclerView recyclerView = (RecyclerView) view;
        final ProgressBar savingProgress = (ProgressBar) progress;
        Drawable recDrawable = ContextCompat.getDrawable(context, R.drawable.recycler_spacer);
        RecyclerDecorator decorator = new RecyclerDecorator(recDrawable);
        recyclerView.addItemDecoration(decorator);

        //Toast.makeText(context, session.getUser().get(SessionManager.USER_ID), Toast.LENGTH_LONG).show();

        StringRequest request = new StringRequest(Request.Method.POST, RequestUrl.SAVING_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONArray request= new JSONArray(response);


                            for (int i = 0; i < request.length(); i++) {
                            Saving mySaving = new Saving();
                            JSONObject saving = null;

                                saving = request.getJSONObject(i);
                                mySaving.setSavingId(saving.getString("id"));
                                mySaving.setProduct(saving.getString("product"));
                                mySaving.setSavingAmount(saving.getDouble("amount"));
                                mySaving.setCurrency(saving.getString("currency"));
                                Log.i("CUR", saving.getString("currency"));
                                mySaving.setTotalSavings(20000.00);
                                mySaving.setTransaction(saving.getString("type"));

                                Date savingDate = null;
                                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
                                try {
                                    savingDate = dateFormat.parse(saving.getString("date"));
                                } catch (ParseException e) {
                                    e.printStackTrace();
                                }
                                String day = (String) android.text.format.DateFormat.format("dd", savingDate);
                                String month = (String) android.text.format.DateFormat.format("MMM", savingDate);

                                mySaving.setSavingAppDay(day);
                                mySaving.setSavingAppMonth(month);

//                                Adding user
                                String userId = saving.getString("user_id");
                                String userName = saving.getString("name");
                                String groupId  = saving.getString("group_id");
                                String userPhone  = saving.getString("phone");
                                String userEmail  = saving.getString("email");

                                User user = new User(context, userId, userName, groupId, userEmail, userPhone);
                                mySaving.setUser(user);

                                xSavings.add(mySaving);


                        }

                        savingProgress.setVisibility(View.INVISIBLE);

                        switch (url) {
                            case RequestUrl.SAVING_URL:
                                savingAdapter = new SavingAdapter(context, fragment, xSavings);
                                break;
                            /*case RequestUrl.AL_URL:
                                savingAdapter = new AppliedLoanAdapter(context, fragment, xSavings);
                                break;*/
                        }

                        recyclerView.setAdapter(savingAdapter);
                    }catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        savingProgress.setVisibility(View.INVISIBLE);
                        Toast.makeText(context, "Something went wrong", Toast.LENGTH_LONG).show();
                        error.printStackTrace();
                    }
                })

        {
        @Override
        protected Map<String, String> getParams() throws AuthFailureError {
            Map<String, String> params = new HashMap<>();
            params.put("user_id", session.getUser().get(SessionManager.USER_ID));
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
                params.put("user_id", userId);
                return params;
            }
        };

        XSingleton.getInstance(context).addToRequestQueue(request);

        return iRDetails;
    }

}
