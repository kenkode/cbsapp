package com.softark.eddie.xara.Requests;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.softark.eddie.xara.activities.SummaryActivity;
import com.softark.eddie.xara.helpers.SessionManager;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;


/**
 * Created by Eddie on 3/27/2017.
 */

public class UserRequest {

    private Context context;
    private SessionManager session;

    public UserRequest(Context context) {
        this.context = context;
        session = new SessionManager(context);
    }

    public void authenticateUser(final String username, final String password, final boolean keepSignedIn, final int activityCall) {

        StringRequest stringRequest = new StringRequest(Request.Method.POST, RequestUrl.LOGIN_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject userObject = new JSONObject(response);
                            if((userObject.getInt("is_active") == 1 || userObject.getInt("is_active")== null) && userObject.getInt("confirmed") == 1) {
                                //JSONObject user = userObject.getJSONObject("user");
                                String userId = userObject.getString("id");
                                String userName = userObject.getString("username");
                                String userEmail = userObject.getString("email");
                                String groupId = "";
                                String userPhone = "";
                                String userType = userObject.getString("user_type");
                                session.setLoggedInUser(userId, groupId, userName, password, userEmail, userType, userPhone);
                                session.setLoggedIn(true);
                                session.setKeepSignedIn(keepSignedIn);
                                if(activityCall == 1) {
                                    allowUserAccess();
                                }
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();
                        Toast.makeText(context, "Connection Error....Please try again later!", Toast.LENGTH_LONG).show();
                    }
                })
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> userDetails = new HashMap<String, String>();
                userDetails.put("username", username);
                userDetails.put("password", password);
                return userDetails;
            }
        };

        XSingleton.getInstance(context).addToRequestQueue(stringRequest);
    }

    public void allowUserAccess() {
        Intent intent = new Intent(context, SummaryActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
        ((AppCompatActivity) context).finish();
    }

}
