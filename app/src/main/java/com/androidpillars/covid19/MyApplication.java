package com.androidpillars.covid19;

import android.app.Application;
import android.content.Context;

import androidx.multidex.MultiDex;

/**
 * Created by Gowtham.R on 2020-04-08.
 */
public class MyApplication extends Application {
    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }
}
