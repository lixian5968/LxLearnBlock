package com.zongbutech.lxlearnblock.Activity;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Build;
import android.os.Bundle;
import android.util.ArrayMap;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.zongbutech.lxlearnblock.Base.BaseActivity;
import com.zongbutech.lxlearnblock.R;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;

public class UtilsAPIActivity extends BaseActivity {

    List<ResolveInfo> info;


    @Bind(R.id.mListView)
    ListView mListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_utils_api);
        ButterKnife.bind(this);


//        Intent intent = new Intent();
//        intent.setClassName("com.zongbutech.lxlearnblock", "com.zongbutech.lxlearnblock.Activity.BitmapChangeActivity");
//        boolean result = !(ct.getPackageManager().resolveActivity(intent, 0) == null ||
//                intent.resolveActivity(ct.getPackageManager()) == null ||
//                ct.getPackageManager().queryIntentActivities(intent, 0).size() == 0);


        String main = "";
        String AlLMian = "";
        Intent intent = new Intent(Intent.ACTION_MAIN, null);
        intent.addCategory(Intent.CATEGORY_LAUNCHER);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        PackageManager pm = ct.getPackageManager();
        info = pm.queryIntentActivities(intent, 0);
        for (ResolveInfo aInfo : info) {
            AlLMian += (aInfo.activityInfo.name + "\n");
            if (aInfo.activityInfo.packageName.equals("com.zongbutech.lxlearnblock")) {
                main = aInfo.activityInfo.name;
//                aInfo.activityInfo.packageName;
            }
        }
        main = "no " + "com.zongbutech.lxlearnblock";
        Log.e("Lx", AlLMian);
        mListView.setAdapter(new BaseAdapter() {
            @Override
            public int getCount() {
                return info.size();
            }

            @Override
            public Object getItem(int position) {
                return position;
            }

            @Override
            public long getItemId(int position) {
                return position;
            }

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                TextView tv = new TextView(ct);
                ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 150);
                tv.setLayoutParams(params);
                tv.setText(info.get(position).activityInfo.packageName + "\t" + info.get(position).activityInfo.name);
                return tv;
            }
        });

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                try {
                    Intent intent = new Intent();
                    intent.setClassName(info.get(position).activityInfo.packageName, info.get(position).activityInfo.name);
                    startActivity(intent);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

    }


    public void getTopActivity(View v){
        getTopActivity();
        Log.e("","");
    }

    private Activity getTopActivity(){
        try {
            Class activityThreadClass = Class.forName("android.app.ActivityThread");
            Object activityThread = activityThreadClass.getMethod("currentActivityThread").invoke(null);
            Field activitiesField = activityThreadClass.getDeclaredField("mActivities");
            activitiesField.setAccessible(true);
            Map activities = null;
            if (Build.VERSION.SDK_INT < Build.VERSION_CODES.KITKAT) {
                activities = (HashMap) activitiesField.get(activityThread);
            } else {
                activities = (ArrayMap) activitiesField.get(activityThread);
            }
            for (Object activityRecord : activities.values()) {
                Class activityRecordClass = activityRecord.getClass();
                Field pausedField = activityRecordClass.getDeclaredField("paused");
                pausedField.setAccessible(true);
                if (!pausedField.getBoolean(activityRecord)) {
                    Field activityField = activityRecordClass.getDeclaredField("activity");
                    activityField.setAccessible(true);
                    return (Activity) activityField.get(activityRecord);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
