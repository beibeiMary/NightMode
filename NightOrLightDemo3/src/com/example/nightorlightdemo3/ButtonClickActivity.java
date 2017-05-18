package com.example.nightorlightdemo3;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class ButtonClickActivity extends BaseActivity {
	private Button btn_night_mode;
	private BaseApplication baseApp;
    public static final String KEY_SCREEN_CAPTURE = "KEY_SCREEN_CAPTURE";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_button_click);
		btn_night_mode = (Button) findViewById(R.id.btn_night);
		btn_night_mode.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				changeViewMode();
				recreate();
			}
		});
	}
   public  void changeViewMode() {
//    	 MemCacheHelper.getInstance().put(KEY_SCREEN_CAPTURE, UIUtils.captureContent(this));
         boolean isNight = getApp().isNightMode();

         if (isNight){
             ChangeToDay();
             Log.d("zhsy", "change to day");
         }
         else{
             ChangeToNight();
             Log.d("zhsy", "change to night");
         }
//         CaptureActivity.start(this, KEY_SCREEN_CAPTURE);
	}
     @Override
    public void onBackPressed() {
    	 Log.d("zhsy", "onBackPressed()");
    	 setResult(10);
    	 finish();
    	super.onBackPressed();
    }
}
