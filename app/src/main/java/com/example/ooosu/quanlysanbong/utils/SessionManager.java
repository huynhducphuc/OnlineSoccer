package com.example.ooosu.quanlysanbong.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.ooosu.quanlysanbong.model.bean.User;
import com.example.ooosu.quanlysanbong.service.UserService;

/**
 * Created by Noname on 27/4/2016.
 */
public class SessionManager {

    private Context context;

    private SharedPreferences pref;
    private SharedPreferences.Editor editor;
    private final int PRIVATE_MODE = 0;

    // Sharedpref file name
    private static final String PREF_NAME = "OnlineSoccerXXX";
    private static final String IS_LOGIN = "IsLoggedIn";
    private static final String USERNAME = "Username";
    private static final String PASSWORD = "Password";
    private static final String USER_ID = "UserId";

    private static SessionManager instance;

    private SessionManager(Context context) {
        this.context = context;
        this.pref = this.context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        this.editor = this.pref.edit();
    }

    public static SessionManager getSessionManager(Context context) {
        if (instance == null) {
            instance = new SessionManager(context);
        }
        return instance;
    }

    public void storeUser(User user) {
        editor.putBoolean(IS_LOGIN, true);

        editor.putInt(USER_ID, user.getId());
        editor.putString(USERNAME, user.getUsername());
        editor.putString(PASSWORD, user.getPassword());

        editor.commit();
    }

    public boolean isLoggedIn() {
        return pref.getBoolean(IS_LOGIN, false);
    }

    public User getUser() {
        if (isLoggedIn()) {
            int userId = pref.getInt(USER_ID, 0);
            UserService userService = new UserService(context);
            return userService.getUser(userId);
        } else {
            return null;
        }
    }

    public void clear() {
        editor.clear();
        editor.commit();
    }

}
