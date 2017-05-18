package com.example.nightorlightdemo3;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract.Contacts.Data;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ListView;

public class MainActivity extends BaseActivity {
	private ArrayList<Map<String, String>> data = new ArrayList<>();
	private ListView listView;
	private MyAdapter adapter;
	private Button btn;
	 public static final String KEY_SCREEN_CAPTURE = "KEY_SCREEN_CAPTURE";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		listView = (ListView) findViewById(R.id.list);
		btn = (Button) findViewById(R.id.btn_toother);
		for (int i = 0; i < 10; i++) {
			Map<String, String> map = new HashMap<String, String>();
			map.put("title", "item"+i);
			map.put("describe", "describe"+i);
			data.add(map);
		}
		adapter = new MyAdapter(data, this);
		listView.setAdapter(adapter);
		btn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(MainActivity.this,ButtonClickActivity.class);
				startActivityForResult(intent, 23);
			}
		});
	}
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if (requestCode==23&&resultCode==10) {
			changeViewMode();
			recreate();
			  Log.d("zhsy", "resultCode=="+resultCode);
		}
	}
	public  void changeViewMode() {
//   	 MemCacheHelper.getInstance().put(KEY_SCREEN_CAPTURE, UIUtils.captureContent(this));
        boolean isNight = getApp().isNightMode();
        if (isNight){
        	ChangeToNight();
            Log.d("zhsy", "main activity change to night");
        }
        else{
            ChangeToDay();
            Log.d("zhsy", "main activity change to day");
        }
//        CaptureActivity.start(this, KEY_SCREEN_CAPTURE);
	}
}
