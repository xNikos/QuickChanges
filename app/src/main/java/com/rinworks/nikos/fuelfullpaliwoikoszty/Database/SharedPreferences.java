package com.rinworks.nikos.fuelfullpaliwoikoszty.Database;

import android.content.Context;

public class SharedPreferences {

    private static Context context;

    public SharedPreferences(Context context){
        this.context = context;
    }

    public final static String PREFS_NAME = "rinworks.com.fuelfull.preferences";

    public static void setInt( String key, int value) {
        android.content.SharedPreferences sharedPref = context.getSharedPreferences(PREFS_NAME,0);
        android.content.SharedPreferences.Editor editor = sharedPref.edit();
        editor.putInt(key, value);
        editor.apply();
    }

    public static int getInt(String key) {
        android.content.SharedPreferences prefs = context.getSharedPreferences(PREFS_NAME, 0);
        return prefs.getInt(key, 0);
    }

    public static void setStr(String key, String value) {
        android.content.SharedPreferences sharedPref = context.getSharedPreferences(PREFS_NAME,0);
        android.content.SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString(key, value);
        editor.apply();
    }

    public static String getStr(String key) {
        android.content.SharedPreferences prefs = context.getSharedPreferences(PREFS_NAME, 0);
        return prefs.getString(key,"Empty");
    }

    public static void setBool(String key, boolean value) {
        android.content.SharedPreferences sharedPref = context.getSharedPreferences(PREFS_NAME,0);
        android.content.SharedPreferences.Editor editor = sharedPref.edit();
        editor.putBoolean(key, value);
        editor.apply();
    }

    public static boolean getBool(String key) {
        android.content.SharedPreferences prefs = context.getSharedPreferences(PREFS_NAME, 0);
        return prefs.getBoolean(key,false);
    }
}
