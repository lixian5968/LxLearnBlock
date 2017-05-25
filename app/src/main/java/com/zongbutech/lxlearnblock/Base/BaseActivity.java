package com.zongbutech.lxlearnblock.Base;

import android.app.ActivityManager;
import android.content.ComponentName;
import android.content.Context;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Toast;

import java.util.List;

/**
 * Created by lixian on 2016/7/25.
 */
public class BaseActivity extends AppCompatActivity {
    public Context ct;
    public String Tag = "BaseActivity";
    public int width;     // 屏幕宽度（像素）
    public int height;
    public float density;
    public int densityDpi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ct = this;

        try {
            ActivityManager manager = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
            List<ActivityManager.RunningTaskInfo> runningTasks = manager.getRunningTasks(1);
            ActivityManager.RunningTaskInfo cinfo = runningTasks.get(0);
            ComponentName component = cinfo.topActivity;
            Tag = component.getShortClassName();
        } catch (Exception e) {
            e.printStackTrace();
        }


        DisplayMetrics metric = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metric);
        width = metric.widthPixels;
        height = metric.heightPixels;   // 屏幕高度（像素）
        density = metric.density;      // 屏幕密度（0.75 / 1.0 / 1.5）
        // 屏幕密度DPI（120 / 160 / 240）
        densityDpi = metric.densityDpi;


    }


    public void finish(View v) {
        finish();
    }

    public void mToast(String s) {
        try {
            Toast.makeText(BaseActivity.this, s, Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void mToast(Exception e) {
        try {
            Toast.makeText(BaseActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
        } catch (Exception mye) {
            mye.printStackTrace();
        }
    }


    /**
     * @param ct
     * @return true 表示网络联通
     */
    public boolean isOpenNetwork(Context ct) {
        ConnectivityManager connManager = (ConnectivityManager) ct.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connManager.getActiveNetworkInfo() != null) {
            return connManager.getActiveNetworkInfo().isAvailable();
        }
        return false;
    }


    public <T extends View> T $(int id) {
        return (T) findViewById(id);
    }
}



