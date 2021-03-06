package com.softark.eddie.xara.requests;

import android.content.Context;
import android.view.View;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.softark.eddie.xara.adapters.RecentActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

/**
 * Created by Eddie on 3/27/2017.
 */

public class SummaryRequest {

    private Context context;

    public SummaryRequest(Context context) {
        this.context = context;
    }

    public void setSummary(final View actListView, final View savings, final View loans, final ProgressBar savingProgress, final ProgressBar loanProgress,final String memberno,final String type) {

        final ProgressBar sProgress = savingProgress;
        final ProgressBar lProgress = loanProgress;
        final TextView save = (TextView) savings;
        final ListView listView = (ListView) actListView;
        final TextView loan = (TextView) loans;

        StringRequest sumRequest = new StringRequest(Request.Method.POST, RequestUrl.SUM_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {
                            JSONObject summary = new JSONObject(response);
                            int s_savings = (int) summary.getDouble("savings");
                            int s_loans = (int) summary.getDouble("loans");

                            sProgress.setVisibility(View.INVISIBLE);
                            lProgress.setVisibility(View.INVISIBLE);
                            save.setText(String.valueOf(NumberFormat.getInstance(Locale.US).format(s_savings)));
                            loan.setText(String.valueOf(NumberFormat.getInstance(Locale.US).format(s_loans)));

                            JSONArray transactions = summary.getJSONArray("transactions");
                            ArrayList<HashMap<String, String>> activities = new ArrayList<>();

                            for(int i = 0;i < transactions.length();i++) {
                                JSONObject transaction = transactions.getJSONObject(i);
                                HashMap<String, String> newTrans = new HashMap<>();
                                newTrans.put("transaction_no", transaction.getString("trans_no"));
                                newTrans.put("memberno", transaction.getString("membership_no"));
                                newTrans.put("name", transaction.getString("name"));
                                newTrans.put("detail", transaction.getString("description"));
                                newTrans.put("date", transaction.getString("created_at"));
                                newTrans.put("amount", NumberFormat.getInstance(Locale.US).format(transaction.getDouble("amount")));
                                newTrans.put("type", transaction.getString("type"));
                                newTrans.put("user_type", type);
                                activities.add(newTrans);
                            }

                            RecentActivity recentActivity = new RecentActivity(context, activities);
                            listView.setAdapter(recentActivity);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();
                        //Toast.makeText(context, error.toString(), Toast.LENGTH_LONG).show();
                        Toast.makeText(context, "Something's not right", Toast.LENGTH_LONG).show();
                        sProgress.setVisibility(View.INVISIBLE);
                        lProgress.setVisibility(View.INVISIBLE);
                        save.setText("?");
                        loan.setText("?");
                    }
                })
        {
        @Override
        protected Map<String, String> getParams() throws AuthFailureError {
            Map<String, String> userDetails = new HashMap<String, String>();
            userDetails.put("memberno", memberno);
            userDetails.put("type", type);
            return userDetails;
        }
    };

        XSingleton.getInstance(context).addToRequestQueue(sumRequest);


    }

}
