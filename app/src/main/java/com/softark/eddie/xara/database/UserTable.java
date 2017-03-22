package com.softark.eddie.xara.database;

/**
 * Created by Eddie on 3/21/2017.
 */

public class UserTable {

    public static final String USERS = "users";
    public static final String USER_NAME = "username";
    public static final String USER_ID = "user_id";
    public static final String USER_PHONE = "user_phone";
    public static final String USER_PASS = "password";
    public static final String USER_EMAIL = "email";
    public static final String USER_PICTURE = "picture";

    public static final String CREATE_QUERY = "CREATE TABLE " + USERS + "("
            +   USER_ID + " text,"
            +   USER_NAME + " text,"
            +   USER_EMAIL + " text,"
            +   USER_PHONE + " text,"
            +   USER_PASS + " text,"
            +   USER_PICTURE + " text,"
            +   " PRIMARY KEY(" + USER_ID + ")"
            +   ")";

    public static final String DROP_QUERY = "DROP TABLE IF EXISTS " + USERS;

}
