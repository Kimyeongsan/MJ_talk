package com.example.mj_talk.ui.Login;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.mj_talk.MainActivity;
import com.example.mj_talk.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginAccount extends AppCompatActivity {
    private Button Button_complete;
    private FirebaseAuth mAuth;
    private LoginActivity activity_login;
    int succeed;

    private EditText TextInputEditText_account_id;
    private EditText TextInputEditText_account_password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        mAuth = FirebaseAuth.getInstance();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_account);

        Button_complete = findViewById(R.id.Button_complete);
        TextInputEditText_account_id = (EditText) findViewById(R.id.TextInputEditText_account_id);
        TextInputEditText_account_password = (EditText) findViewById(R.id.TextInputEditText_account_password);

        getSupportActionBar().hide(); // 액션바를 감추는 추가적인 코드

        Button_complete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!TextInputEditText_account_id.getText().toString().equals("") && !TextInputEditText_account_password.getText().toString().equals("")) {
                    createUser(TextInputEditText_account_id.getText().toString(), TextInputEditText_account_password.getText().toString());
                    if (succeed == 1) {
                        Intent intent = new Intent(LoginAccount.this, LoginActivity.class);//넘어갈 곳 결정
                        startActivity(intent);//다음 activity 진행
                    } else {

                    }
                } else {
                    //loginUser(TextInputEditText_id.getText().toString(), TextInputEditText_password.getText().toString());
                    Toast.makeText(LoginAccount.this, "이메일과 비밀번호를 입력하세요.", Toast.LENGTH_LONG).show();
                }

            }

        });
    }

    public void createUser(final String id, final String password) {
        mAuth.createUserWithEmailAndPassword(id, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        succeed = 0;
                        if (task.isSuccessful()) {
                            succeed = 1;
                            Toast.makeText(LoginAccount.this, "회원가입 성공!", Toast.LENGTH_LONG).show();
                        } else {
                            succeed = 2;
                            //id가 이미 존재할 경우 로그인이 되어짐
                            //activity_login.loginUser(id, password);
                            Toast.makeText(LoginAccount.this, "이미 존재하는 회원입니다.", Toast.LENGTH_SHORT).show();
                        }

                    }
                });
    }

}
