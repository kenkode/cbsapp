package com.softark.eddie.xara.model;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.os.Parcel;
import android.os.Parcelable;
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

public class User implements Parcelable {

    private String userId;
    private String userName;
    private String groupId;
    private String userEmail;
    private String userPhone;
    private String userPassword;
    private boolean authenticate;
    SessionManager session;

    public User(Context context, String userId, String userName, String groupId, String userEmail, String userPhone) {
        this.userId = userId;
        this.userName = userName;
        this.groupId = groupId;
        this.userEmail = userEmail;
        this.userPhone = userPhone;
    }

    public User(Context context) {
        this.session = new SessionManager(context);
    }

    public User() {

    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public boolean isAuthenticate() {
        return authenticate;
    }

    public void setAuthenticate(boolean authenticate) {
        this.authenticate = authenticate;
    }

    public static final String ALL_COLUMNS[] = { UserTable.USER_EMAIL, UserTable.USER_ID, UserTable.USER_NAME, UserTable.USER_PASS,
            UserTable.USER_PHONE, UserTable.USER_PICTURE };

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
                ", userPassword='" + userPassword + '\'' +
                '}';
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.userId);
        dest.writeString(this.userName);
        dest.writeString(this.groupId);
        dest.writeString(this.userEmail);
        dest.writeString(this.userPhone);
    }

    protected User(Parcel in) {
        this.userId = in.readString();
        this.userName = in.readString();
        this.groupId = in.readString();
        this.userEmail = in.readString();
        this.userPhone = in.readString();
    }

    public static final Parcelable.Creator<User> CREATOR = new Parcelable.Creator<User>() {
        @Override
        public User createFromParcel(Parcel source) {
            return new User(source);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };
}
