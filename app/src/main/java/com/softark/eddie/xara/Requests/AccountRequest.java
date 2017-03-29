package com.softark.eddie.xara.Requests;

import android.content.Context;
import android.view.View;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.softark.eddie.xara.adapters.AccountsAdapter;
import com.softark.eddie.xara.adapters.GuarantorAdapter;
import com.softark.eddie.xara.helpers.SessionManager;
import com.softark.eddie.xara.model.Constant;
import com.softark.eddie.xara.model.User;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Eddie on 3/29/2017.
 */

public class AccountRequest {

    private SessionManager session;
    private Context context;
    private BaseAdapter adapter;

    public AccountRequest(Context context) {
        this.context = context;
        session = new SessionManager(context);
    }

    public void setAccounts(final ListView listView, final ProgressBar progressBar, final String key) {

        final ArrayList<User> accounts = new ArrayList<>();
        StringRequest request = new StringRequest(Request.Method.POST, RequestUrl.ACC_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONArray accountsArr = new JSONArray(response);

                            for (int i = 0; i < accountsArr.length(); i++) {
                                JSONObject account = accountsArr.getJSONObject(i);
                                User user = new User();
                                user.setUserId(account.getString("id"));
                                user.setUserName(account.getString("name"));
                                user.setUserEmail(account.getString("email"));
                                user.setUserPhone(account.getString("phone"));
                                user.setUserType(account.getString("member_type"));

                                accounts.add(user);
                            }

                            switch (key) {
                                case Constant.ACC:
                                    adapter = new AccountsAdapter(context, accounts);
                                    break;
                                case Constant.APPLY_G:
                                    adapter = new GuarantorAdapter(context, accounts);
                                    break;
                            }

                            progressBar.setVisibility(View.INVISIBLE);

                            listView.setAdapter(adapter);

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
                params.put("group_id", session.getUser().get(SessionManager.GROUP_ID));
                params.put("member", session.getUser().get(SessionManager.USER_ID));
                return params;
            }
        };

        XSingleton.getInstance(context).addToRequestQueue(request);
    }

}
