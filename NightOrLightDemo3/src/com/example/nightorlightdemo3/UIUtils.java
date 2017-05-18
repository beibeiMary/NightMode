package com.example.nightorlightdemo3;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Rect;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

/**
 * Created by Administrator on 2016/5/26.
 */
public class UIUtils {

	//��һ���̶��ϣ��л�ʱ���ֺ����������������ǻ���һ���������������ǵ�ǰ��activity����һ�����󣬷�ֹ���ֺ�������
    /**
     * ��ͼ
     *
     * @param activity
     * @return
     */
    public static Bitmap captureContent(Activity activity) {
        //View������Ҫ��ͼ��View
        View view = activity.getWindow().getDecorView();
        view.setDrawingCacheEnabled(true);
        view.buildDrawingCache();
        Bitmap b1 = view.getDrawingCache();
        // ��ȡ״̬���߶� /
        Rect frame = new Rect();
        activity.getWindow().getDecorView().getWindowVisibleDisplayFrame(frame);
        int statusBarHeight = frame.top;
        // ��ȡ��Ļ���͸�
        int width = activity.getWindowManager().getDefaultDisplay().getWidth();
        int height = activity.getWindowManager().getDefaultDisplay().getHeight();
        // ȥ��������
        Bitmap b = Bitmap.createBitmap(b1, 0, statusBarHeight, width, height - statusBarHeight);
        view.destroyDrawingCache();
        return b;
    }

    /**
     * ����
     *
     * @param activity
     * @return
     */

    public static Bitmap captureScreen(Activity activity) {
        activity.getWindow().getDecorView().setDrawingCacheEnabled(true);
        Bitmap bmp = activity.getWindow().getDecorView().getDrawingCache();
        return bmp;
    }

    public static void forceImmersiveWindow(Activity context) {
        WindowManager.LayoutParams params = context.getWindow().getAttributes();
        params.systemUiVisibility = View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_FULLSCREEN;
        context.getWindow().setAttributes(params);
    }

    /**
     * ����ʽ���壬����status bar�� navigation bar
     * 4.4���²����ص���������Ϊ����¼�������������������µ���¼���Ԥ��Ĳ�һ��
     *
     * @param context
     */
    public static void immersiveWindow(Activity context) {
        WindowManager.LayoutParams params = context.getWindow().getAttributes();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            params.systemUiVisibility = View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_FULLSCREEN;
        } else {
            params.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN;
        }
        context.getWindow().setAttributes(params);
    }


    public static void popWindow(Context applicationContext, int layoutResId) {
        WindowManager wm = (WindowManager) applicationContext.getSystemService(Context.WINDOW_SERVICE);
        WindowManager.LayoutParams para = new WindowManager.LayoutParams();
        para.height = ViewGroup.LayoutParams.MATCH_PARENT;
        para.width = ViewGroup.LayoutParams.MATCH_PARENT;
        para.format = 1;

        para.flags = WindowManager.LayoutParams.FLAG_FULLSCREEN | WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN;

        para.type = WindowManager.LayoutParams.TYPE_SYSTEM_ALERT;
        View mView = LayoutInflater.from(applicationContext).inflate(
                layoutResId, null);
        wm.addView(mView, para);
    }
}
