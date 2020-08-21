package com.example.mj_talk.ui.Login;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mj_talk.R;

public class FindPassword extends AppCompatActivity {
    private Button Button_find_pw_login;
    private TextView TextView_find_password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_forgot_password);


        Button_find_pw_login = findViewById(R.id.Button_find_pw_login);
        TextView_find_password = findViewById(R.id.TextView_find_password);
        getSupportActionBar().hide(); // 액션바를 감추는 추가적인 코드


        Button_find_pw_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                //값 주고 받는 활동
                Intent intent = new Intent(FindPassword.this, LoginActivity.class);//넘어갈 곳 결정

                startActivity(intent);//다음 activity 진행
            }

        });


    }

}
