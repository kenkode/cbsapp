package com.softark.eddie.xara.requests;

import android.content.Context;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ProgressBar;
import android.widget.Spinner;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.softark.eddie.xara.model.SavingProduct;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Eddie on 3/29/2017.
 */

public class saveRequest {

    private Context context;

    public saveRequest(Context context) {
        this.context = context;
    }

    public void populateSpinners(final Spinner productSpinner, final ProgressBar loadProgress) {

        final ArrayList<SavingProduct> products = new ArrayList<>();
        final List<String> dispProducts = new ArrayList<>();

        JsonObjectRequest request = new JsonObjectRequest(RequestUrl.SV_URL, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        try {
                            JSONArray productsArr = response.getJSONArray("products");

                            for (int i = 0; i < productsArr.length(); i++) {
                                JSONObject product = productsArr.getJSONObject(i);
                                SavingProduct savingProduct = new SavingProduct();
                                savingProduct.setId(product.getInt("id"));
                                savingProduct.setName(product.getString("name"));
                                savingProduct.setCurrency(product.getString("currency"));

                                products.add(savingProduct);
                                dispProducts.add(product.getString("name"));
                                loadProgress.setVisibility(View.INVISIBLE);
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
