package com.fingercash.app.fingercash;

import java.util.HashMap;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.util.Log;

public class SessionManager {
    // LogCat tag
    private static String TAG = SessionManager.class.getSimpleName();

    // Shared Preferences
    SharedPreferences pref;

    Editor editor;
    Context _context;

    // Shared pref mode
    int PRIVATE_MODE = 0;

    // Shared preferences file name
    private static final String PREF_NAME = "FingerCash";
    private static final String KEY_IS_LOGGEDIN = "isLoggedIn";
    public static final String KEY_NAME = "username";


    public SessionManager(Context context) {
        this._context = context;
        pref = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = pref.edit();
    }

    public void loginSession(String name){
        setLogin(true);

        editor.putString(KEY_NAME, name); //Get username from database

        editor.commit();
    }

    // Changing the user state (logged in or not)
    public void setLogin(boolean isLoggedIn) {

        editor.putBoolean(KEY_IS_LOGGEDIN, isLoggedIn);
        if(!this.isLoggedIn()) {
            editor.clear();
        }

        // commit changes
        editor.commit();

        Log.d(TAG, "User login session modified!");
    }

    //Get stored data
    public HashMap<String, String> getUserDetails(){
        HashMap<String, String> user = new HashMap<String, String>();

        user.put(KEY_NAME, pref.getString(KEY_NAME, null));


        return user;
    }

    //Quick check for login
    public boolean isLoggedIn(){
        return pref.getBoolean(KEY_IS_LOGGEDIN, false);
    }
}

