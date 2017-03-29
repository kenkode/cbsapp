package com.softark.eddie.xara.helpers;

import android.content.Context;
import android.content.SharedPreferences;

import com.softark.eddie.xara.Requests.UserRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Eddie on 3/21/2017.
 */

public class SessionManager {
    public static final String APP_PREFS = "xara_login_prefs";
    public static final String LOGGED_IN_KEY = "isLoggedIn";
    public static final String KEEP_SIGNED_IN = "keep_signed_in";
    public static final String USER_ID = "user_id";
    public static final String GROUP_ID = "group_id";
    public static final String USER_NAME = "user_name";
    public static final String USER_PASS = "user_pass";
    public static final String USER_EMAIL = "user_email";
    public static final String USER_TYPE = "user_type";
    public static final String USER_PHONE = "user_phone";
    SharedPreferences mPreferences;
    SharedPreferences.Editor prefsEditor;
    Context context;

    public SessionManager(Context context) {
        this.context = context;
        mPreferences = context.getSharedPreferences(APP_PREFS, Context.MODE_PRIVATE);
        prefsEditor = mPreferences.edit();
    }

    public void setLoggedIn(boolean isLoggedIn) {
        prefsEditor.putBoolean(LOGGED_IN_KEY, isLoggedIn);
        prefsEditor.apply();
    }

    public void setLoggedInUser(String id, String groupId, String name, String pass, String email, String type, String phone) {
        prefsEditor.putString(USER_ID, id);
        prefsEditor.putString(USER_NAME, name);
        prefsEditor.putString(GROUP_ID, groupId);
        prefsEditor.putString(USER_EMAIL, email);
        prefsEditor.putString(USER_TYPE, type);
        prefsEditor.putString(USER_PHONE, phone);
        prefsEditor.putString(USER_PASS, pass);
        prefsEditor.apply();
    }

    public Map<String, String> getUser() {
        Map<String, String> user = new HashMap<String, String>();
        user.put(USER_ID, mPreferences.getString(USER_ID, ""));
        user.put(USER_NAME, mPreferences.getString(USER_NAME, ""));
        user.put(USER_EMAIL, mPreferences.getString(USER_EMAIL, ""));
        user.put(GROUP_ID, mPreferences.getString(GROUP_ID, ""));
        user.put(USER_TYPE, mPreferences.getString(USER_TYPE, ""));
        user.put(USER_PHONE, mPreferences.getString(USER_PHONE, ""));
        return user;
    }

    public String getUserKey() {
        return mPreferences.getString(USER_ID, "");
    }

    public String getUserType() {
        return mPreferences.getString(USER_TYPE, "");
    }

    public void setKeepSignedIn(boolean keepSignedIn) {
        prefsEditor.putBoolean(KEEP_SIGNED_IN, keepSignedIn);
        prefsEditor.apply();
    }

    public void syncDetails() {
        UserRequest userRequest = new UserRequest(context);
        userRequest.authenticateUser(mPreferences.getString(USER_NAME, ""),
                mPreferences.getString(USER_PASS, ""),
                mPreferences.getBoolean(LOGGED_IN_KEY, false), 0);
    }

    public boolean isLoggedIn() {
        syncDetails();
        return mPreferences.getBoolean(LOGGED_IN_KEY, false);
    }

    public boolean isRemembered() {
        syncDetails();
        return mPreferences.getBoolean(KEEP_SIGNED_IN, false);
    }

}
