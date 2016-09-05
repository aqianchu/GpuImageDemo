package com.scu.gpuimagedemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class StartActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        setListener();
    }

    private void setListener() {
        findViewById(R.id.main_bt).setOnClickListener(this);
        findViewById(R.id.saturation_bt).setOnClickListener(this);
        findViewById(R.id.netimg_bt).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.main_bt:
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                break;
            case R.id.saturation_bt:
                Intent intent1 = new Intent(this, SaturationActivity.class);
                startActivity(intent1);
                break;
            case R.id.netimg_bt:
                Intent intent2 = new Intent(this, NetImageFilterActivity.class);
                startActivity(intent2);
                break;
        }
    }
}
