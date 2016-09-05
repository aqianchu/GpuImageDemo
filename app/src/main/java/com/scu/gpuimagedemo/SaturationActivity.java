package com.scu.gpuimagedemo;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.SeekBar;

import jp.co.cyberagent.android.gpuimage.GPUImage;
import jp.co.cyberagent.android.gpuimage.GPUImageBrightnessFilter;
import jp.co.cyberagent.android.gpuimage.GPUImageSaturationFilter;

public class SaturationActivity extends Activity implements View.OnClickListener {


    private GPUImage gpuImage;
    //显示处理结果
    private ImageView resultIv;
    //进度条
    private SeekBar seekbar;
    private Bitmap filterBitmap;
    private boolean isSaturation = true;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saturation);
        resultIv = (ImageView) findViewById(R.id.resultIv);

        seekbar = (SeekBar) this.findViewById(R.id.seekbar);
        seekbar.setMax(10);
        seekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                //通过进度条的值更改饱和度
                filterBitmap = getGPUImageFromAssets(progress, isSaturation);
                if (filterBitmap == null) {
                    return;
                }
                resultIv.setImageBitmap(filterBitmap);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        //初始化图片
        resultIv.setImageBitmap(getGPUImageFromAssets(0, isSaturation));
        setListener();
    }

    private void setListener() {
        findViewById(R.id.saturation_item).setOnClickListener(this);
        findViewById(R.id.brightneww_bt).setOnClickListener(this);
    }

    //根据传进来的数值设置素材饱和度
    public Bitmap getGPUImageFromAssets(int progress, boolean isSaturation) {
        Bitmap bitmap = null;
        bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.test);
        gpuImage = new GPUImage(this);
        gpuImage.setImage(bitmap);
        if (isSaturation)
            gpuImage.setFilter(new GPUImageSaturationFilter(progress));
        else
            gpuImage.setFilter(new GPUImageBrightnessFilter(progress * 0.1f));
        bitmap = gpuImage.getBitmapWithFilterApplied();
        return bitmap;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.saturation_item:
                isSaturation = true;
                break;
            case R.id.brightneww_bt:
                isSaturation = false;
                break;
        }
    }
}
