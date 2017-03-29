package com.softark.eddie.xara.Requests;

import android.content.Context;
import android.widget.Button;
import android.widget.ListView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.softark.eddie.xara.adapters.AppliedLoanGuarantor;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Eddie on 3/29/2017.
 */

public class GuarantorRequest {

    private Context context;

    public GuarantorRequest(Context context) {
        this.context = context;
    }

    public void setGuarantors(final ListView listView, final Button disburse) {
        final ArrayList<HashMap<String, String>> guarantors = new ArrayList<>();

        final StringRequest request = new StringRequest(Request.Method.POST, RequestUrl.G_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {

                            JSONArray json = new JSONArray(response);

                            for (int i = 0; i < json.length(); i++) {

                                JSONObject object = json.getJSONObject(i);

                                HashMap<String, String> guarantor = new HashMap<>();
                                guarantor.put("name", object.getString("name"));
                                guarantor.put("state", object.getString("has_approved"));
                                guarantor.put("amount", object.getString("amount"));
                                guarantors.add(guarantor);

                                if(object.getString("has_approved").equals("0")) {
                                    disburse.setEnabled(false);
                                }
                            }

                            AppliedLoanGuarantor details = new AppliedLoanGuarantor(context, guarantors);
                            listView.setAdapter(details);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();
                    }
                })
        {

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("loanaccount_id", "61");
                params.put("member_id", "9");
                return params;
            }
        };

        XSingleton.getInstance(context).addToRequestQueue(request);
    }


}
