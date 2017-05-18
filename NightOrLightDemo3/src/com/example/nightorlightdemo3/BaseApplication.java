package com.example.nightorlightdemo3;

import android.app.ActivityManager;
import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;


/**
 * Created by Administrator on 2015/5/6.
 */
public class BaseApplication extends Application {
    private static final String PARAM_IS_NIGHT_MODE = "PARAM_IS_NIGHT_MODE";
    private SharedPreferences mSp;
    private boolean mIsNightMode;
    private int mScreenWidth;
    private int mScreenHeight;
    private int mScreenWidthDP;
    private static final String PARAM_SCREEN_WIDTH = "PARAM_SCREEN_WIDTH";
    private static final String PARAM_SCREEN_HEIGHT = "PARAM_SCREEN_HEIGHT";
    private static final String PARAM_SCREEN_WIDTH_DP = "PARAM_SCREEN_WIDTH_DP";
    @Override
    public void onCreate() {
        super.onCreate();
        mSp = PreferenceManager.getDefaultSharedPreferences(this);
        mIsNightMode = mSp.getBoolean(PARAM_IS_NIGHT_MODE, false);
        mScreenWidth = mSp.getInt(PARAM_SCREEN_WIDTH, 0);
        mScreenHeight = mSp.getInt(PARAM_SCREEN_HEIGHT, 0);
        mScreenWidthDP = mSp.getInt(PARAM_SCREEN_WIDTH_DP, 0);
    }
    public boolean isNightMode() {
    	Log.d("zhsy", "mIsNightMode=" +mIsNightMode);
        return mIsNightMode;
    }
    public void setIsNightMode(boolean isNightMode) {
        if (mIsNightMode == isNightMode)
            return;

        mIsNightMode = isNightMode;
        mSp.edit().putBoolean(PARAM_IS_NIGHT_MODE, mIsNightMode).apply();
    }
}
