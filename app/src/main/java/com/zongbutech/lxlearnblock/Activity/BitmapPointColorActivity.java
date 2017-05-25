package com.zongbutech.lxlearnblock.Activity;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.zongbutech.lxlearnblock.Base.BaseActivity;
import com.zongbutech.lxlearnblock.R;

import butterknife.Bind;
import butterknife.ButterKnife;

public class BitmapPointColorActivity extends BaseActivity {

    @Bind(R.id.mImageView)
    ImageView mImageView;
    @Bind(R.id.mTextView1)
    TextView mTextView1;
    @Bind(R.id.mTextView2)
    TextView mTextView2;


    String KnowledgeAll = "1:知识点\n" +
            "图片类型 \n" +
            "ALPHA_8\n" +
            "ARGB_4444\n" +
            "ARGB_8888\n" +
            "RGB_565\n";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bitmap_point_color);
        ButterKnife.bind(this);
        mTextView1.setText(KnowledgeAll);

        getImagePointInfo();
    }

    public void getImagePointInfo(View v) {
        getImagePointInfo();
    }

    private void getImagePointInfo() {
        Drawable drawable = mImageView.getDrawable();
        if (drawable instanceof BitmapDrawable) {
            BitmapDrawable bitDrawable = (BitmapDrawable) drawable;
            Bitmap mBitmap = bitDrawable.getBitmap();
            int color = mBitmap.getPixel(0, 0);
            String BitmapInfo = "图片Config:" + mBitmap.getConfig() + "\n" + "0,0点的像素值：" + color;
            if (mBitmap.getConfig() == Bitmap.Config.ARGB_8888) {
                BitmapInfo += "因为获取的数值是int型\n" +
                        "（32位 区间-2147483648到2147483648 \n" +
                        "（16进制-80000000到80000000)）\n" +
                        "所以取的值为负数（当为不透明的时候值为FF000000  4278190080显然超过了区间），\n" +
                        "我们取负数的补码，可以得到正常的数值\n" +
                        "补码公式 4294967295（FFFFFFFF） +获取的点像素点值(" + color + ")+1 =" + ((long) (4294967295.0 + color + 1) + "即(" + Long.toHexString((long) (4294967295.0 + color + 1)) + ")");
            } else if (mBitmap.getConfig() == Bitmap.Config.RGB_565) {
                BitmapInfo += "当图片类型为 Bitmap.Config.RGB_565时，默认透明度为255（FF），\n因为获取的数值是int型\n" +
                        "（32位 区间-2147483648到2147483648 \n" +
                        "（16进制-80000000到80000000)）\n" +
                        "所以取的值为负数（当为不透明的时候值为FF000000  4278190080显然超过了区间），\n" +
                        "我们取负数的补码，可以得到正常的数值\n" +
                        "补码公式1 4294967295（FFFFFFFF） +获取的点像素点值(" + color + ")+1 =" + ((long) (4294967295.0 + color + 1) + "即(" + Long.toHexString((long) (4294967295.0 + color + 1)) + ")")
                        + "\n补码公式2(默认为不透明) 16777215（FFFFFF） +获取的点像素点值(" + color + ")+1 =" + ((long) (16777215 + color + 1) + "即(" + Long.toHexString((long) (16777215 + color + 1)) + ")");

            } else if (mBitmap.getConfig() == Bitmap.Config.ARGB_4444) {
                BitmapInfo += "当图片类型为 Bitmap.Config.ARGB_4444时，每个色值站6位4*6 =24, 2^24 =16777216-1=16777215 ，\n因为获取的数值是int型\n" +
                        "我们取负数的补码，可以得到正常的数值\n" +
                        "补码公式1 16777215+获取的点像素点值(" + color + ")+1 =" + ((long) (16777215 + color + 1) + "即(" + Long.toHexString((long) (16777215 + color + 1)) + ")");
            } else {

            }


            int a = Color.alpha(color);
            int r = Color.red(color);
            int g = Color.green(color);
            int b = Color.blue(color);
            String A = "\na:" + a + "(" + Integer.toHexString(a) + ")\n";
            String R = "r:" + r + "(" + Integer.toHexString(r) + ")\n";
            String G = "g:" + g + "(" + Integer.toHexString(g) + ")\n";
            String B = "b:" + a + "(" + Integer.toHexString(b) + ")\n";
            mTextView2.setText(BitmapInfo + A + R + G + B);
        }
    }

    int lxConfig = 0;

    public void changeImageConfig(View v) {

        Drawable drawable = null;
        if (type == 0) {
            drawable = ct.getResources().getDrawable(R.drawable.lx_color);
        } else if (type == 1) {
            drawable = ct.getResources().getDrawable(R.drawable.white);
        } else if (type == 2) {
            drawable = ct.getResources().getDrawable(R.drawable.black);
        }
        if (drawable instanceof BitmapDrawable) {
            BitmapDrawable bitDrawable = (BitmapDrawable) drawable;
            Bitmap bit = bitDrawable.getBitmap();
            if (lxConfig > Bitmap.Config.values().length - 1) {
                lxConfig = 0;
            }
            Bitmap newBit = bit.copy(Bitmap.Config.values()[lxConfig], true);
            mImageView.setImageBitmap(newBit);
            getImagePointInfo();
            lxConfig++;
        }
    }

    int type = 0;

    public void ChangeImageWhite(View v) {
        Drawable drawable = ct.getResources().getDrawable(R.drawable.white);
        mImageView.setImageDrawable(drawable);
        type = 1;
        getImagePointInfo();
    }

    public void ChangeImageBlack(View v) {
        Drawable drawable = ct.getResources().getDrawable(R.drawable.black);
        mImageView.setImageDrawable(drawable);
        type = 2;
        getImagePointInfo();
    }

    public void ReStart(View v) {
        Drawable drawable = ct.getResources().getDrawable(R.drawable.lx_color);
        mImageView.setImageDrawable(drawable);
        type = 0;
        getImagePointInfo();
    }


}
