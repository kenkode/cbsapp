package com.softark.eddie.xara.dialogs;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.softark.eddie.xara.R;
import com.softark.eddie.xara.model.Constant;
import com.softark.eddie.xara.model.Loan;
import com.softark.eddie.xara.requests.RequestUrl;
import com.softark.eddie.xara.requests.XSingleton;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Eddie on 3/14/2017.
 */

public class TopUpDialog extends DialogFragment {

    private AlertDialog.Builder dialog;
    private EditText top_up_amount;
    private Context context;
    private Loan loan;
    String amount;
    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        super.onCreateDialog(savedInstanceState);
        dialog = new AlertDialog.Builder(getActivity());
        context=this.getActivity();
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.top_up_dialog, null);
        loan = getArguments().getParcelable(Constant.LOAN);
        top_up_amount = (EditText) view.findViewById(R.id.top_up_amount_input);
        dialog.setView(view);
        dialog.setPositiveButton("Top Up", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                amount= top_up_amount.getText().toString().trim();
                StringRequest stringRequest = new StringRequest(Request.Method.POST, RequestUrl.TOPUP_URL, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if (response.contains("Top Up Success")) {
                            Toast.makeText(context, "Loan top up successful", Toast.LENGTH_SHORT).show();
                        }else if(response.contains("Top Up Failure")){
                            Toast.makeText(context, "Top up failed. This Loan has not been approved.", Toast.LENGTH_SHORT).show();
                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();
                        Toast.makeText(context, error.toString(), Toast.LENGTH_SHORT).show();
                    }
                }) {
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String, String> params = new HashMap<>();
                        params.put("loan_id", String.valueOf(loan.getLoanId()));
                        params.put("top_up_amount", amount);
                        return params;
                    }
                };

                XSingleton.getInstance(context).addToRequestQueue(stringRequest);
                dialog.dismiss();

            }
        }).
        setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        return dialog.create();
    }


}
