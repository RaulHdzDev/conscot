package com.example.conscot.Utilities;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import static com.example.conscot.Utilities.PreferencesUtility.LOGGED_IN_PREF;
import static com.example.conscot.Utilities.PreferencesUtility.EMAIL;
import static com.example.conscot.Utilities.PreferencesUtility.USER;

public class SaveSharedPreference {

    static SharedPreferences getPreferences(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context);
    }

    public static void setPreferences(Context context, boolean loggedIn, String usuario, String correo) {
        SharedPreferences.Editor editor = getPreferences(context).edit();
        editor.putBoolean(LOGGED_IN_PREF, loggedIn);
        editor.putString(USER, usuario);
        editor.putString(EMAIL, correo);
        editor.apply();
    }

    public static boolean getLoggedStatus(Context context) {
        return getPreferences(context).getBoolean(LOGGED_IN_PREF, false);
    }

    public static String getCurrentUser(Context context){
        return getPreferences(context).getString(USER, "");
    }

    public static String getCurrentEmail(Context context){
        return getPreferences(context).getString(EMAIL, "");
    }

}
