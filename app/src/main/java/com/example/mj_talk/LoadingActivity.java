package com.example.mj_talk;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mj_talk.ui.Login.LoginActivity;

public class LoadingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading);

        getSupportActionBar().hide(); // 액션바를 감추는 추가적인 코드

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(LoadingActivity.this, LoginActivity.class));
                finish();
            }
        }, 2000); // 2초간 출력
    }
}