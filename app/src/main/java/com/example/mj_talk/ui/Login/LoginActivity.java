package com.example.mj_talk.ui.Login;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.mj_talk.MainActivity;
import com.example.mj_talk.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {

    private Button Button_login;
    private TextView Text_account;
    private TextView Text_forget;
    private FirebaseAuth mAuth;
    int succeed;


    private EditText TextInputEditText_id;
    private EditText TextInputEditText_password;

    private FirebaseAuth.AuthStateListener mAuthListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        getSupportActionBar().hide(); // 액션바를 감추는 추가적인 코드

        TextInputEditText_id = (EditText) findViewById(R.id.TextInputEditText_id);
        TextInputEditText_password = (EditText) findViewById(R.id.TextInputEditText_password);


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

                if (!TextInputEditText_id.getText().toString().equals("") && !TextInputEditText_password.getText().toString().equals("")) {
                    //loginUser(TextInputEditText_id.getText().toString(), TextInputEditText_password.getText().toString());
                    createUser(TextInputEditText_id.getText().toString(), TextInputEditText_password.getText().toString());

                    if (succeed == 1) {
                        //값 주고 받는 활동
                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);//넘어갈 곳 결정

                        startActivity(intent);//다음 activity 진행
                    } else {
                        loginUser(TextInputEditText_id.getText().toString(), TextInputEditText_password.getText().toString());
                    }


                } else {
                    Toast.makeText(LoginActivity.this, "이메일과 비밀번호를 입력하세요.", Toast.LENGTH_LONG).show();
                }

            }

        });

        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);//넘어갈 곳 결정
                    startActivity(intent);//다음 activity 진행
                    finish();
                } else {

                }
            }
        };
    }

    private void createUser(final String id, final String password) {
        mAuth.createUserWithEmailAndPassword(id, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(getApplicationContext(), "회원가입 성공", Toast.LENGTH_SHORT).show();
                        } else {
                            //id가 이미 존재할 경우 로그인이 되어짐
                            loginUser(id, password);
                        }

                    }
                });
    }


//    private void loginUser(String id, String password){
//        mAuth.signInWithEmailAndPassword(id, password)
//                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
//                    @Override
//                    public void onComplete(@NonNull Task<AuthResult> task) {
//                        if (task.isSuccessful()) {
//                            // Sign in success, update UI with the signed-in user's information
//                            //Log.d(TAG, "signInWithEmail:success");
//                            FirebaseUser user = mAuth.getCurrentUser();
//                            Toast.makeText(LoginActivity.this, "로그인 성공",
//                                    Toast.LENGTH_SHORT).show();
//                            //updateUI(user);
//                        } else {
//                            // If sign in fails, display a message to the user.
//                            //Log.w(TAG, "signInWithEmail:failure", task.getException());
//                            Toast.makeText(LoginActivity.this, "로그인 실패",
//                                    Toast.LENGTH_SHORT).show();
//                            //updateUI(null);
//                            // ...
//                        }
//
//                        // ...
//                    }
//                });
//    }


    public void loginUser(String id, String password) {
        mAuth.signInWithEmailAndPassword(id, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override

                    public void onComplete(@NonNull Task<AuthResult> task) {
                        succeed = 0;
                        if (task.isSuccessful()) {
                            // 로그인 성공
                            Toast.makeText(LoginActivity.this, "로그인 성공", Toast.LENGTH_SHORT).show();
                            mAuth.addAuthStateListener(mAuthListener);
                            succeed = 1;
                        } else {
                            // 로그인 실패
                            Toast.makeText(LoginActivity.this, "아이디나 비밀번호가 일치하지 않습니다.", Toast.LENGTH_SHORT).show();
                            succeed = 2;
                        }
                    }
                });
    }

    @Override
    protected void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }

    @Override
    protected void onStop() {
        super.onStop();
        mAuth.addAuthStateListener(mAuthListener);
    }


}
