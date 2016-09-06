package com.scu.gpuimagedemo;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

import jp.co.cyberagent.android.gpuimage.GPUImage;
import jp.co.cyberagent.android.gpuimage.GPUImageGrayscaleFilter;

public class NetImageFilterActivity extends AppCompatActivity {
    private GPUImage gpuImage;
    //显示处理结果
    private ImageView resultIv;

    public static Bitmap getGPUImageFromURL(String url) {
        Bitmap bitmap = null;
        try {
            URL iconUrl = new URL(url);
            URLConnection conn = iconUrl.openConnection();
            HttpURLConnection http = (HttpURLConnection) conn;
            int length = http.getContentLength();
            conn.connect();
            // 获得图像的字符流
            InputStream is = conn.getInputStream();
            BufferedInputStream bis = new BufferedInputStream(is, length);
            bitmap = BitmapFactory.decodeStream(bis);
            bis.close();
            is.close();// 关闭流
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bitmap;
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        resultIv = (ImageView) findViewById(R.id.resultIv);

        //开启异步线程加载图片并处理
        MyAsynTask asynTask = new MyAsynTask();
        asynTask.execute();
    }

    class MyAsynTask extends AsyncTask<Integer, Integer, Bitmap> {

        @Override
        protected Bitmap doInBackground(Integer... params) {
            Bitmap bitmap = getGPUImageFromURL("http://userimage8.360doc.com/16/0716/10/642066_201607161041280818913355.jpg");
            return bitmap;
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            // 使用GPUImage处理图像
            ((ImageView) findViewById(R.id.original_img)).setImageBitmap(bitmap);
            gpuImage = new GPUImage(getApplicationContext());
            gpuImage.setImage(bitmap);
            gpuImage.setFilter(new GPUImageGrayscaleFilter());
            bitmap = gpuImage.getBitmapWithFilterApplied();
            //显示处理后的图片
            resultIv.setImageBitmap(bitmap);
        }
    }
}
