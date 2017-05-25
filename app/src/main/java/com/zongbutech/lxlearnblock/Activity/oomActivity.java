package com.zongbutech.lxlearnblock.Activity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.zongbutech.lxlearnblock.Base.CameraActivity;
import com.zongbutech.lxlearnblock.R;

import java.io.File;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

import static android.text.format.Formatter.formatFileSize;

/**
 * 总结
 * 图片在内存中的占用减少
 * 1： 图片的Config改变
 * ALPHA_8代表8位Alpha位图    图片长度*图片宽度 （代表全透明度 图片会变全黑的）
 * ARGB_4444代表16位ARGB位图 图片长度*图片宽度 * 2
 * ARGB_8888代表32位ARGB位图 图片长度*图片宽度 * 4
 * RGB_565代表8位RGB位图    图片长度*图片宽度 * 2
 * <p>
 * ARGB_8888 质量最高（32位） ARGB_4444（16位）可能图片会不清楚
 * RGB_565 质量一般 但是能看的清楚（16位）
 * 如果想要内存少点的话 可以把图片转为RGB_565的
 * <p>
 * 2：图片放对位置
 * mdpi,hdpi,xhdpi,xxhdpi,xxxhdpi,drawable
 * drawable    1:1
 * mdpi        1:1
 * hdpi        1:1.5
 * xhdpi       1:2
 * xxhdpi      1:3
 * xxxhdpi     1:4
 * 根据手机的像素比值，手机会调用不同drawable包下面的图片，
 * 例如：我的手机是1：3那么，图片是直接从xxhdpi这里面选则的
 * 如果图片只有mdpi里面有，那么手机会把mdpi里面的图片扩大3倍（长*3 宽*3）显示
 * 如果图片只有hdpi里面有，那么手机会把hdpi里面的图片扩大1.5倍（长*1.5 宽*1.5）显示
 * 如果图片只有xxxhdpi里面有，那么手机会把xxxhdpi里面的图片扩大3/4倍（长*3/4 宽*3/4）显示
 * 会根据比值扩大
 * 所以做图的时候就要把比例算好
 */

public class oomActivity extends CameraActivity {

    @Bind(R.id.mImageView)
    ImageView mImageView;

    @Bind(R.id.mTextView1)
    TextView mTextView1;
    @Bind(R.id.mTextView2)
    TextView mTextView2;
    @Bind(R.id.mTextView3)
    TextView mTextView3;

    String Knowledge = "知识点：\n" +
            "1：mdpi,hdpi,xhdpi,xxhdpi,xxxhdpi 对应的像素密度:\n" +
            "mdpi    1:1, \n" +
            "hdpi    1:1.5,\n" +
            "xhdpi   1:2,\n" +
            "xxhdpi  1:3,\n" +
            "xxxhdpi 1:4\n" +
            "2：图片Config\n" +
            "ALPHA_8代表8位Alpha位图\t    图片长度*图片宽度\n" +
            "ARGB_4444代表16位ARGB位图 图片长度*图片宽度 * 2\n" +
            "ARGB_8888代表32位ARGB位图 图片长度*图片宽度 * 4\n" +
            "RGB_565代表8位RGB位图\t    图片长度*图片宽度 * 2\n";

    String KnowLedgeAll = "总结\n" +
            "图片在内存中的占用减少\n" +
            "1： 图片的Config改变\n" +
            "ALPHA_8代表8位Alpha位图    图片长度*图片宽度 （代表全透明度 图片会变全黑的）\n" +
            "ARGB_4444代表16位ARGB位图 图片长度*图片宽度 * 2\n" +
            "ARGB_8888代表32位ARGB位图 图片长度*图片宽度 * 4\n" +
            "RGB_565代表8位RGB位图    图片长度*图片宽度 * 2\n" +
            "\n" +
            "ARGB_8888 质量最高（32位） ARGB_4444（16位）可能图片会不清楚\n" +
            "RGB_565 质量一般 但是能看的清楚（16位）\n" +
            "如果想要内存少点的话 可以把图片转为RGB_565的\n" +
            "\n" +
            "2：图片放对位置\n" +
            "mdpi,hdpi,xhdpi,xxhdpi,xxxhdpi,drawable\n" +
            "drawable    1:1\n" +
            "mdpi        1:1\n" +
            "hdpi        1:1.5\n" +
            "xhdpi       1:2\n" +
            "xxhdpi      1:3\n" +
            "xxxhdpi     1:4\n" +
            "根据手机的像素比值，手机会调用不同drawable包下面的图片，\n" +
            "例如：我的手机是1：3那么，图片是直接从xxhdpi这里面选则的\n" +
            "如果图片只有mdpi里面有，那么手机会把mdpi里面的图片扩大3倍（长*3 宽*3）显示\n" +
            "如果图片只有hdpi里面有，那么手机会把hdpi里面的图片扩大1.5倍（长*1.5 宽*1.5）显示\n" +
            "如果图片只有xxxhdpi里面有，那么手机会把xxxhdpi里面的图片扩大3/4倍（长*3/4 宽*3/4）显示\n" +
            "会根据比值扩大\n" +
            "所以做图的时候就要把比例算好";


    String[] BitmapFrom = new String[]{"mdpi", "hdpi", "xhdpi", "xxhdpi", "xxxhdpi", "drawable"};
    int BitmapFromType = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_oom);
        ButterKnife.bind(this);
        mTextView1.setText(Knowledge + "3:屏幕宽度：" + width + ",屏幕高度：" + height + ",像素密度:" + density);

        setBitmapFromAndType();
        getImageInfo();

        mTextView3.setText(KnowLedgeAll);
    }


    private int getConfig(Bitmap bit) {
        if (bit.getConfig() == Bitmap.Config.ALPHA_8) {
            return 1;
        }
        if (bit.getConfig() == Bitmap.Config.ARGB_4444) {
            return 2;
        }
        if (bit.getConfig() == Bitmap.Config.ARGB_8888) {
            return 4;
        }
        if (bit.getConfig() == Bitmap.Config.RGB_565) {
            return 2;
        }
        return 1;
    }


    public void getImageInfo(View v) {
        setDrawByFromType();
        getImageInfo();
    }

    public void changeImageFrom(View v) {
        BitmapFromType++;
        if (BitmapFromType > BitmapFrom.length - 1) {
            BitmapFromType = 0;
        }
        setDrawByFromType();
        getImageInfo();
    }


    int lxConfig = 0;

    public void changeImageConfig(View v) {
        setBitmapFromAndType();
        Drawable drawable = ct.getResources().getDrawable(R.drawable.bg);
        if (drawable instanceof BitmapDrawable) {
            BitmapDrawable bitDrawable = (BitmapDrawable) drawable;
            Bitmap bit = bitDrawable.getBitmap();
            if (lxConfig > Bitmap.Config.values().length - 1) {
                lxConfig = 0;
            }
            Bitmap newBit = bit.copy(Bitmap.Config.values()[lxConfig], true);
            mImageView.setImageBitmap(newBit);
            getImageInfo();
            lxConfig++;
        }
    }

    public void reStart(View v) {
        setBitmapFromAndType();
        Drawable drawable = ct.getResources().getDrawable(R.drawable.bg);
        mImageView.setImageDrawable(drawable);
        getImageInfo();
    }


    private void getImageInfo() {
        Drawable drawable = mImageView.getDrawable();
        if (drawable instanceof BitmapDrawable) {
            BitmapDrawable bitDrawable = (BitmapDrawable) drawable;
            Bitmap bit = bitDrawable.getBitmap();
            String mBtInfo = "图片宽度：" + bit.getWidth() + "，图片高度：" + bit.getHeight() + ",图片Config:" + bit.getConfig();
            String text1 = "\n计算方式1：图片长度*图片宽度*像素像素位数：" + bit.getWidth() + "*" + bit.getHeight() + "*" + getConfig(bit)
                    + "=" + (bit.getWidth() * bit.getHeight() * getConfig(bit));
            String text2 = "\n计算方式2： bit.getRowBytes()*height:" + bit.getRowBytes() + "*" + bit.getHeight()
                    + "=" + (bit.getRowBytes() * bit.getHeight());
            String text3 = "\n计算方式3： bit.getByteCount():" + bit.getByteCount();
            String text4 = "\n转换为MB：" + formatFileSize(ct, (bit.getWidth() * bit.getHeight() * getConfig(bit)));
            String text5 ="";
            if(BitmapFromType==-1){
                text5 = "\n图片来源:" +"SD卡";
            }else{
                text5 = "\n图片来源:" + BitmapFrom[BitmapFromType];
            }


            mTextView2.setText(mBtInfo + text1 + text2 + text3 + text4 + text5);
        }
    }

    private void setBitmapFromAndType() {
        if (density == 1.0) {
            BitmapFromType = 0;
        } else if (density == 1.5) {
            BitmapFromType = 1;
        } else if (density == 2.0) {
            BitmapFromType = 2;
        } else if (density == 3.0) {
            BitmapFromType = 3;
        } else if (density == 4.0) {
            BitmapFromType = 4;
        }
    }

    private void setDrawByFromType() {
        if(BitmapFromType<0){
            return;
        }
        Drawable drawable = null;
        if (BitmapFromType == 0) {
            drawable = ct.getResources().getDrawable(R.drawable.bg_mdpi);
        } else if (BitmapFromType == 1) {
            drawable = ct.getResources().getDrawable(R.drawable.bg_hdpi);
        } else if (BitmapFromType == 2) {
            drawable = ct.getResources().getDrawable(R.drawable.bg_xhdpi);
        } else if (BitmapFromType == 3) {
            drawable = ct.getResources().getDrawable(R.drawable.bg_xxhdpi);
        } else if (BitmapFromType == 4) {
            drawable = ct.getResources().getDrawable(R.drawable.bg_xxxhdpi);
        } else if (BitmapFromType == 5) {
            drawable = ct.getResources().getDrawable(R.drawable.bg_drawable);
        }
        if (drawable != null) {
            mImageView.setImageDrawable(drawable);
            getImageInfo();
        }
    }


    @Override
    public void setImages(List<String> images, String type) {
        if (images != null && images.size() > 0) {
            Bitmap bitmap = null;
            try {
                File file = new File(images.get(0));
                if (file.exists()) {
                    bitmap = BitmapFactory.decodeFile(images.get(0));
                }
            } catch (Exception e) {
                // TODO: handle exception
            }
            if(bitmap!=null){
                BitmapFromType=-1;
                mImageView.setImageBitmap(null);
                mImageView.setImageBitmap(bitmap);
                getImageInfo();
            }
        }
    }

    public void getImage(View v) {
        showImageDialog("", 1, true, false);
    }

}
