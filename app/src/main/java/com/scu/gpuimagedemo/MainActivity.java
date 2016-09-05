package com.scu.gpuimagedemo;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.ImageView;

import jp.co.cyberagent.android.gpuimage.GPUImage;
import jp.co.cyberagent.android.gpuimage.GPUImageGrayscaleFilter;

public class MainActivity extends Activity {
    private GPUImage gpuImage;
    //显示处理结果
    private ImageView resultIv;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        resultIv = (ImageView) findViewById(R.id.resultIv);

        Bitmap bitmap = null;
//        //获得Assets资源文件
//        AssetManager as = getAssets();
//        InputStream is = null;
//        try {
//            //注意名字要与图片名字一致
//            is = as.open("link.jpg");
//            bitmap = BitmapFactory.decodeStream(is);
//            is.close();
//        } catch (IOException e) {
//            Log.e("GPUImage", "Error");
//        }
        bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.test);

        // 使用GPUImage处理图像
        gpuImage = new GPUImage(this);
        gpuImage.setImage(bitmap);
        gpuImage.setFilter(new GPUImageGrayscaleFilter());
        bitmap = gpuImage.getBitmapWithFilterApplied();
        //显示处理后的图片
        resultIv.setImageBitmap(bitmap);
    }
}
