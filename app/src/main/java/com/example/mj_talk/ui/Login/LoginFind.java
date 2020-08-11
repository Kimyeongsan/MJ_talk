package com.example.mj_talk.ui.Login;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mj_talk.R;

public class LoginFind extends AppCompatActivity {
    private Button Button_find_id;
    private Button Button_find_pw;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_forgot);
        Button_find_id = findViewById(R.id.Button_find_id);
        Button_find_pw = findViewById(R.id.Button_find_pw);
        getSupportActionBar().hide(); // 액션바를 감추는 추가적인 코드

        Button_find_id.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                //값 주고 받는 활동
                // Intent intent = new Intent(LoginFind.this, LoginActivity.class);//넘어갈 곳 결정

                //startActivity(intent);//다음 activity 진행
            }

        });

        Button_find_pw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                //값 주고 받는 활동
                //Intent intent = new Intent(LoginFind.this, LoginActivity.class);//넘어갈 곳 결정

                //startActivity(intent);//다음 activity 진행
            }

        });


    }

}
