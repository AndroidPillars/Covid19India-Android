package com.androidpillars.covid19.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Gowtham on 2020-04-05.
 */

public class CustomPreference {

    public Context context;
    public static String DEVICE_ID = "device_id";
    public static String VERSION_DATE = "version_date";
    public static String VERSION_DESC = "version_description";
    public static String VERSION_SIZE = "version_size";
    public static String VERSION_VERSION = "version_version";



    public CustomPreference(Context context) {
        // TODO Auto-generated constructor stub
        this.context = context;
    }

    public static void storeString(Context _context, String _key, String _val) {
        SharedPreferences preference = _context.getSharedPreferences(_key,
                Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preference.edit();
        editor.putString(_key, _val);
        editor.commit();

    }

    public static String getString(Context _context, String _key) {
        SharedPreferences preference = _context.getSharedPreferences(_key,
                Context.MODE_PRIVATE);
        String value = preference.getString(_key, "");
        return value;
    }


    public static void storeInt(Context _context, String _key, int _val) {
        SharedPreferences preference = _context.getSharedPreferences(_key,
                Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preference.edit();
        editor.putInt(_key, _val);
        editor.commit();

    }

    public static void deleteInt(Context _context, String _key, int _val) {
        SharedPreferences preference = _context.getSharedPreferences(_key,
                Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preference.edit();
        editor.putInt(_key, _val).clear();
        editor.commit();

    }

    public static Integer getInt(Context _context, String _key) {
        SharedPreferences preference = _context.getSharedPreferences(_key,
                Context.MODE_PRIVATE);
        int value = preference.getInt(_key, 0);
        return value;
    }

    public static Boolean storeBoolean(Context _context, String _key, Boolean _val) {
        SharedPreferences preference = _context.getSharedPreferences(_key,
                Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preference.edit();
        editor.putBoolean(_key, _val);
        editor.commit();

        return _val;
    }

    public static Boolean getBoolean(Context _context, String _key) {
        SharedPreferences preference = _context.getSharedPreferences(_key,
                Context.MODE_PRIVATE);
        Boolean value = preference.getBoolean(_key, false);
        return value;


    }
}
