package com.zifung.gymclub.model;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.Window;

import com.zifung.gymclub.R;

public class SplashActivity extends Activity {

    private Handler mHandler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_splash);

        mHandler.postDelayed(runnable, 3000);

    }

    public void skip_btn_clicked(View view) {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
        //撤回自动跳转
        mHandler.removeCallbacks(runnable);
        finish();
    }

    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            Intent intent = new Intent(SplashActivity.this, LoginActivity.class);
            startActivity(intent);
            finish();
        }
    };

}
