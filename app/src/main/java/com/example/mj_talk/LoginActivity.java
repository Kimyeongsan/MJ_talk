package com.example.mj_talk;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.mj_talk.MainActivity;
import com.example.mj_talk.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class LoginActivity extends AppCompatActivity {

    private Button Button_login;
    private TextView Text_account;
    private TextView Text_forget;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        getSupportActionBar().hide(); // 액션바를 감추는 추가적인 코드

        Button_login = findViewById(R.id.Button_login);
        Text_account = findViewById(R.id.Text_account);
        Text_forget = findViewById(R.id.Text_forget);

        //id/pw 찾기
        Text_forget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(LoginActivity.this, LoginFind.class);//넘어갈 곳 결정


                startActivity(intent);//다음 activity 진행
            }

        });

        //회원가입
        Text_account.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(LoginActivity.this, LoginAccount.class);//넘어갈 곳 결정


                startActivity(intent);//다음 activity 진행
            }

        });

        //로그인 버튼

        Button_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //String id = TextInputEditText_id.getText().toString();//문자열 형태로 값을 가져옴
                //String password = TextInputEditText_password.getText().toString();

                //값 주고 받는 활동
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);//넘어갈 곳 결정
                //intent.putExtra("id", id);//id값을 "id"란 이름으로 넘겨줌
                //intent.putExtra("password", password);

                startActivity(intent);//다음 activity 진행
            }

        });


    }

}
