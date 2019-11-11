package com.promobileapp.chiasenhac.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class PreferenceUtils {

    private static PreferenceUtils sInstance;
    private SharedPreferences mPref;

    private PreferenceUtils(final Context context) {
        mPref = PreferenceManager.getDefaultSharedPreferences(context);
    }

    public static PreferenceUtils getInstance(final Context context) {
        if (sInstance == null) {
            sInstance = new PreferenceUtils(context.getApplicationContext());
        }
        return sInstance;
    }

    public void putBoolean(String key, final boolean bool) {
        final SharedPreferences.Editor editor = mPref.edit();
        editor.putBoolean(key, bool);
        editor.apply();
    }

    public final boolean getBoolean(String key) {
        return mPref.getBoolean(key, false);
    }

    public void putString(String key, final String value) {
        final SharedPreferences.Editor editor = mPref.edit();
        editor.putString(key, value);
        editor.apply();
    }

    public final String getString(String key) {
        return mPref.getString(key, "");
    }

    public void putInt(String key, final int value) {
        final SharedPreferences.Editor editor = mPref.edit();
        editor.putInt(key, value);
        editor.apply();
    }

    public final int getInt(String key) {
        return mPref.getInt(key, 0);
    }

    public void putFloat(String key, final float value) {
        final SharedPreferences.Editor editor = mPref.edit();
        editor.putFloat(key, value);
        editor.apply();
    }

    public final float getFloat(String key) {
        return mPref.getFloat(key, 0);
    }


    public void putTrending(final String value) {
        final SharedPreferences.Editor editor = mPref.edit();
        editor.putString(AppConstants.TRENDING, value);
        editor.apply();
    }

    public final String getTrending() {
        return mPref.getString(AppConstants.TRENDING, "bp=4gIuCggvbS8wNHJsZhIiUExGZ3F1TG5MNTlhbVBud2pLbmNhZUp3MDYzZlU1M3Q0cA%3D%3D");
    }


    public void putPositionTrending(final int value) {
        final SharedPreferences.Editor editor = mPref.edit();
        editor.putInt(AppConstants.POSITION_TRENDING, value);
        editor.apply();
    }

    public final int getPositionTrending() {
        return mPref.getInt(AppConstants.POSITION_TRENDING, 36);
    }

    public void putNumberName(final int value) {
        final SharedPreferences.Editor editor = mPref.edit();
        editor.putInt(AppConstants.COUT_NAME, value);
        editor.apply();
    }

    public final int getNumberName() {
        return mPref.getInt(AppConstants.COUT_NAME, 0);
    }
}
