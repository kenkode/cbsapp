package com.softark.eddie.xara.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.widget.Toast;

import com.softark.eddie.xara.database.BaseModel;
import com.softark.eddie.xara.database.UserTable;
import com.softark.eddie.xara.helpers.SessionManager;

import java.util.HashMap;
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
    SessionManager session;

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

    public void createUser() {
        ContentValues values = new ContentValues(6);
        values.put(UserTable.USER_ID, userId);
        values.put(UserTable.USER_NAME, userName);
        values.put(UserTable.USER_EMAIL, userEmail);
        values.put(UserTable.USER_PHONE, userPhone);
        values.put(UserTable.USER_PICTURE, userPicture);
        values.put(UserTable.USER_PASS, userPassword);

        long count = DatabaseUtils.queryNumEntries(sqLiteDatabase, UserTable.USERS);

        if(count == 0) {
            sqLiteDatabase.insert(UserTable.USERS, null, values);
//            Toast.makeText(context, "User inserted", Toast.LENGTH_SHORT).show();
        }else {
//            Toast.makeText(context, "User already inserted", Toast.LENGTH_SHORT).show();
        }
    }

    public HashMap<String, String> getUserDetails() {
        HashMap<String, String> user = new HashMap<>();
        String userKey = session.getUserKey();
        String query = "select * from " + UserTable.USERS + " where " + UserTable.USER_NAME + " = ?";
        Cursor cursor = sqLiteDatabase.rawQuery(query, new String[]{userKey});
        if(cursor.getCount() > 0) {
            cursor.moveToFirst();
            user.put("username", userKey);
            user.put("email", cursor.getString(cursor.getColumnIndex(UserTable.USER_EMAIL)));
        }

        return user;
    }

    public boolean authenticateUser(String username, String password) {
        String query = "select * from " + UserTable.USERS + " where " + UserTable.USER_NAME + " = ? and " + UserTable.USER_PASS + "= ?";
        Cursor cursor = sqLiteDatabase.rawQuery(query, new String[]{username, password});
        if(cursor.getCount() > 0) {
            return true;
        }
        return false;
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
