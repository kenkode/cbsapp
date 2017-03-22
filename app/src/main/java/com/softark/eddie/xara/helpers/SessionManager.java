package com.softark.eddie.xara.helpers;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Eddie on 3/21/2017.
 */

public class SessionManager {
    private static final String APP_PREFS = "xara_login_prefs";
    private static final String LOGGED_IN_KEY = "isLoggedIn";
    private static final String KEEP_SIGNED_IN = "keep_signed_in";
    private static final String USER_KEY = "user_key";
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

    public void setLoggedInUser(String userKey) {
        prefsEditor.putString(USER_KEY, userKey);
        prefsEditor.apply();
    }

    public String getUserKey() {
        return mPreferences.getString(USER_KEY, "");
    }

    public void setKeepSignedIn(boolean keepSignedIn) {
        prefsEditor.putBoolean(KEEP_SIGNED_IN, keepSignedIn);
        prefsEditor.apply();
    }

    public boolean isLoggedIn() {
        return mPreferences.getBoolean(LOGGED_IN_KEY, false);
    }

    public boolean isRemembered() {
        return mPreferences.getBoolean(KEEP_SIGNED_IN, false);
    }

}
