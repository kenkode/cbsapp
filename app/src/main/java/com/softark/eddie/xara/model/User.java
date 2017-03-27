package com.softark.eddie.xara.model;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.softark.eddie.xara.Requests.XSingleton;
import com.softark.eddie.xara.activities.LoginActivity;
import com.softark.eddie.xara.activities.SummaryActivity;
import com.softark.eddie.xara.database.BaseModel;
import com.softark.eddie.xara.database.UserTable;
import com.softark.eddie.xara.helpers.SessionManager;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * Created by Eddie on 3/21/2017.
 */

public class User extends BaseModel {

    private String userId;
    private String userName;
    private String userEmail;
    private String userPhone;
    private String userPicture;
    private String userPassword;
    private boolean authenticate;
    SessionManager session;

    public boolean isAuthenticate() {
        return authenticate;
    }

    public void setAuthenticate(boolean authenticate) {
        this.authenticate = authenticate;
    }

    public static final String ALL_COLUMNS[] = { UserTable.USER_EMAIL, UserTable.USER_ID, UserTable.USER_NAME, UserTable.USER_PASS,
            UserTable.USER_PHONE, UserTable.USER_PICTURE };

    public User(Context context) {
        super(context);
        this.session = new SessionManager(context);
    }

    public User(Context context, String userId, String userName, String userEmail, String userPhone, String userPicture, String userPassword) {
        super(context);

        if(userId == null) {
            userId = UUID.randomUUID().toString();
        }

        this.session = new SessionManager(context);
        this.userId = userId;
        this.userName = userName;
        this.userEmail = userEmail;
        this.userPhone = userPhone;
        this.userPicture = userPicture;
        this.userPassword = userPassword;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public String getUserPicture() {
        return userPicture;
    }

    public void setUserPicture(String userPicture) {
        this.userPicture = userPicture;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId='" + userId + '\'' +
                ", userName='" + userName + '\'' +
                ", userEmail='" + userEmail + '\'' +
                ", userPhone='" + userPhone + '\'' +
                ", userPicture='" + userPicture + '\'' +
                ", userPassword='" + userPassword + '\'' +
                '}';
    }
}
