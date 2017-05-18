package com.example.nightorlightdemo3;

import android.content.Context;
import android.graphics.PixelFormat;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.view.WindowManager.LayoutParams;

public class BaseActivity extends AppCompatActivity{
	private BaseApplication mBaseApp = null;
	private View mNightView = null;
	private boolean mIsAddedView;
	private WindowManager mWindowManager = null;
	 protected boolean isSwipeToClose() {
	        return false;
	    }

	@Override
    protected void onCreate(Bundle savedInstanceState) {
        mBaseApp = (BaseApplication) getApplication();
        if (mBaseApp.isNightMode())
        {
        	setTheme(R.style.AppTheme_night);
//            setTheme(isSwipeToClose() ? R.style.AppTheme_night_transparent : R.style.AppTheme_night);
        }
        else{
        	setTheme(R.style.AppTheme_day);
//            setTheme(isSwipeToClose() ? R.style.AppTheme_day_transparent : R.style.AppTheme_day);
        }
        super.onCreate(savedInstanceState);
        mIsAddedView = false;
        if (mBaseApp.isNightMode()) {
            initNightView();
            mNightView.setBackgroundResource(R.color.night_mask);
        }
    }
    public BaseApplication getApp() {
        return mBaseApp;
    }
    @Override
    protected void onDestroy() {
        if (mIsAddedView) {
            mBaseApp = null;
            mWindowManager.removeViewImmediate(mNightView);
            mWindowManager = null;
            mNightView = null;
        }
        ActivityCollection.removeActivity(this);
        super.onDestroy();
    }
    public void ChangeToDay() {
        mBaseApp.setIsNightMode(false);
        mNightView.setBackgroundResource(android.R.color.transparent);
    }

    public void ChangeToNight() {
        mBaseApp.setIsNightMode(true);
        Log.d("zhsy", "setIsNightMode");
        initNightView();
    }

    protected void initNightView() {
    	if (mIsAddedView)
            return;
        LayoutParams mNightViewParam = new LayoutParams(
                LayoutParams.TYPE_APPLICATION,
                LayoutParams.FLAG_NOT_TOUCHABLE | LayoutParams.FLAG_NOT_FOCUSABLE,
                PixelFormat.TRANSPARENT);

        mWindowManager = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
        mNightView = new View(this);
        mWindowManager.addView(mNightView, mNightViewParam);
        mIsAddedView = true;
    }
}
