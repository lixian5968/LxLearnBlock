package com.zongbutech.lxlearnblock;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.zongbutech.lxlearnblock.Activity.BitmapChangeActivity;
import com.zongbutech.lxlearnblock.Activity.BitmapPointColorActivity;
import com.zongbutech.lxlearnblock.Activity.UtilsAPIActivity;
import com.zongbutech.lxlearnblock.Activity.VectorAndPngActivity;
import com.zongbutech.lxlearnblock.Activity.oomActivity;
import com.zongbutech.lxlearnblock.Base.BaseActivity;

public class AllActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all);
    }

    Intent it;
    public void ToActivity(View v) {
        switch (v.getId()) {
            case R.id.bt1:
                it = new Intent(ct, oomActivity.class);
                break;
            case R.id.bt2:
                it = new Intent(ct, BitmapPointColorActivity.class);
                break;
            case R.id.bt3:
                it = new Intent(ct, BitmapChangeActivity.class);
                break;
            case R.id.bt4:
                it = new Intent(ct, VectorAndPngActivity.class);
                break;
            case R.id.bt5:
                it = new Intent(ct, UtilsAPIActivity.class);
                break;

        }
        startActivity(it);
    }


}
