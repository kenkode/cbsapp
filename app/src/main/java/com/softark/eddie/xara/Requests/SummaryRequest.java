package com.softark.eddie.xara.Requests;

import android.content.Context;
import android.icu.math.BigDecimal;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.NumberFormat;
import java.util.Currency;
import java.util.Locale;

/**
 * Created by Eddie on 3/27/2017.
 */

public class SummaryRequest {

    private Context context;

    public SummaryRequest(Context context) {
        this.context = context;
    }

    public void setSummary(final View savings, final View loans, final ProgressBar savingProgress, final ProgressBar loanProgress) {

        final ProgressBar sProgress = savingProgress;
        final ProgressBar lProgress = loanProgress;
        final TextView save = (TextView) savings;
        final TextView loan = (TextView) loans;

        StringRequest sumRequest = new StringRequest(Request.Method.GET, RequestUrl.SUM_URL,
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

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(context, "Something's not right", Toast.LENGTH_LONG).show();
                        sProgress.setVisibility(View.INVISIBLE);
                        lProgress.setVisibility(View.INVISIBLE);
                        save.setText("?");
                        loan.setText("?");
                    }
                });

        XSingleton.getInstance(context).addToRequestQueue(sumRequest);


    }

}
