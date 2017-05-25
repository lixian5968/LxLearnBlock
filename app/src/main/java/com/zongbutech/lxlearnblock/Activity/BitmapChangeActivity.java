package com.zongbutech.lxlearnblock.Activity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
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

public class  BitmapChangeActivity extends CameraActivity {

    @Bind(R.id.mImageView)
    ImageView mImageView;
    @Bind(R.id.mTextView1)
    TextView mTextView1;
    @Bind(R.id.mTextView2)
    TextView mTextView2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bitmap_change);
        ButterKnife.bind(this);
    }

    public void changeImageToBlackAndWhite1(View v){
        Drawable drawable = mImageView.getDrawable();
        if (drawable instanceof BitmapDrawable) {
            BitmapDrawable bitDrawable = (BitmapDrawable) drawable;
            Bitmap mSrcBitmap = bitDrawable.getBitmap();
            int width = mSrcBitmap.getWidth();
            int height = mSrcBitmap.getHeight();
            Bitmap newImage = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
            Canvas canvas = new Canvas(newImage);
            Paint paint = new Paint();
            paint.setAntiAlias(false);
            paint.setStyle(Paint.Style.STROKE);
            for (int x = 0; x < width; x++) {
                for (int y = 0; y < height; y++) {
                    int color = mSrcBitmap.getPixel(x, y);
                    int number = 16777215 + color + 1 > 8388607 ? 16777215 : 0;
                    paint.setColor(Color.rgb(Color.red(number), Color.green(number), Color.blue(number)));
                    canvas.drawCircle(x, y, 1, paint);
                }
            }
            mImageView.setImageBitmap(newImage);
        }
    }
    public void changeImageToBlackAndWhite2(View v){
        Drawable drawable = mImageView.getDrawable();
        if (drawable instanceof BitmapDrawable) {
            BitmapDrawable bitDrawable = (BitmapDrawable) drawable;
            Bitmap mSrcBitmap = bitDrawable.getBitmap();
            int width = mSrcBitmap.getWidth();
            int height = mSrcBitmap.getHeight();
            Bitmap newImage = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
            Canvas canvas = new Canvas(newImage);
            Paint paint = new Paint();
            paint.setAntiAlias(false);
            paint.setStyle(Paint.Style.STROKE);
            for (int x = 0; x < width; x++) {
                for (int y = 0; y < height; y++) {
                    int color = mSrcBitmap.getPixel(x, y);
                    int number = 16777215 + color + 1 > 8388607 ? 16777215 : 0;
                    paint.setColor(Color.rgb(Color.red(number), Color.green(number), Color.blue(number)));
                    canvas.drawCircle(x, y, 1, paint);
                }
            }
            mImageView.setImageBitmap(newImage);
        }
    }

    public void getImage(View v) {
        showImageDialog("", 1, true, false);
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
                mImageView.setImageBitmap(null);
                mImageView.setImageBitmap(bitmap);
            }
        }
    }
}
