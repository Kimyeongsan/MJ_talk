package com.example.mj_talk.ui.Login;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mj_talk.R;

public class LoginAccount extends AppCompatActivity {
    private Button Button_complete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_account);
        Button_complete = findViewById(R.id.Button_complete);
        getSupportActionBar().hide(); // 액션바를 감추는 추가적인 코드

        Button_complete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                //값 주고 받는 활동
                Intent intent = new Intent(LoginAccount.this, LoginActivity.class);//넘어갈 곳 결정

                startActivity(intent);//다음 activity 진행
            }

        });

    }

}
