package com.example.nightorlightdemo3;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.ImageView;


public class CaptureActivity extends Activity {
    private static final String PARAM_MEM_CACHE_KEY = "PARAM_MEM_CACHE_KEY";
    private static final int MSG_CLOSE_ACTIVITY = 1;
    private String mCacheKey;

    private Handler closeHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == MSG_CLOSE_ACTIVITY) {
                finish();
                overridePendingTransition(0, android.R.anim.fade_out);
                // ���map�����д洢����Ļ��ͼ  
                MemCacheHelper.getInstance().remove(mCacheKey);
            }
        }
    };


    public static void start(Context context, String memCacheKey) {
        Intent starter = new Intent(context, CaptureActivity.class);
        starter.putExtra(PARAM_MEM_CACHE_KEY, memCacheKey);
        context.startActivity(starter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full_image);
        // ��ȡ�ղŵ���Ļ��ͼ  
        mCacheKey = getIntent().getStringExtra(PARAM_MEM_CACHE_KEY);
        Bitmap bitmap = MemCacheHelper.getInstance().get(mCacheKey);

        if (bitmap == null)
            return;
        // �ҿؼ������ñ���ͼ  
        final ImageView imageView = (ImageView) findViewById(R.id.iv_fullImage);
        imageView.setImageBitmap(bitmap);
        // ���´������е�Activity  
        new Handler().postDelayed(new Runnable() {
            public void run() {
                ActivityCollection.refreshAllActivity();
                closeHandler.sendEmptyMessageDelayed(MSG_CLOSE_ACTIVITY, 300);
            }
        }, 100);


    }

}
