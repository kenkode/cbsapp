package com.softark.eddie.xara.Requests;

import android.content.Context;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.softark.eddie.xara.model.DisbursementOption;
import com.softark.eddie.xara.model.LoanProduct;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Eddie on 3/29/2017.
 */

public class ApplyRequest {

    private Context context;

    public ApplyRequest(Context context) {
        this.context = context;
    }

    public void populateSpinners(final Spinner productSpinner, final Spinner optionSpinner) {

        final ArrayList<LoanProduct> products = new ArrayList<>();
        final ArrayList<DisbursementOption> options = new ArrayList<>();
        final List<String> dispProducts = new ArrayList<>();
        final List<String> dispOptions = new ArrayList<>();

        JsonObjectRequest request = new JsonObjectRequest(RequestUrl.APP_URL, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        try {
                            JSONArray optionsArr = response.getJSONArray("disburseoptions");
                            JSONArray productsArr = response.getJSONArray("products");

                            for (int i = 0; i < productsArr.length(); i++) {
                                JSONObject product = productsArr.getJSONObject(i);
                                LoanProduct loanProduct = new LoanProduct();
                                loanProduct.setId(product.getInt("id"));
                                loanProduct.setName(product.getString("name"));
                                loanProduct.setAmortisation(product.getString("amortization"));
                                loanProduct.setApplicationForm(product.getString("application_form"));
                                loanProduct.setCurrency(product.getString("currency"));
                                loanProduct.setFormula(product.getString("formula"));
                                loanProduct.setInterestRate(product.getDouble("interest_rate"));
                                loanProduct.setLoanLimit(product.getDouble("auto_loan_limit"));
                                loanProduct.setPeriod(product.getInt("period"));

                                products.add(loanProduct);
                                dispProducts.add(product.getString("name"));
                            }

                            for (int i = 0; i < optionsArr.length(); i++) {
                                JSONObject product = optionsArr.getJSONObject(i);
                                DisbursementOption option = new DisbursementOption();
                                option.setId(product.getInt("id"));
                                option.setName(product.getString("name"));
                                option.setMax(product.getDouble("max"));
                                option.setMin(product.getDouble("min"));
                                option.setDesc(product.getString("description"));

                                options.add(option);
                                dispOptions.add(product.getString("name"));
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        productSpinner.setAdapter(new ArrayAdapter<String>(context, android.R.layout.simple_spinner_dropdown_item, dispProducts));
                        productSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                            @Override
                            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                            }

                            @Override
                            public void onNothingSelected(AdapterView<?> parent) {

                            }
                        });

                        optionSpinner.setAdapter(new ArrayAdapter<String>(context, android.R.layout.simple_spinner_dropdown_item, dispOptions));
                        optionSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                            @Override
                            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                            }

                            @Override
                            public void onNothingSelected(AdapterView<?> parent) {

                            }
                        });

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });

        XSingleton.getInstance(context).addToRequestQueue(request);
    }


}
