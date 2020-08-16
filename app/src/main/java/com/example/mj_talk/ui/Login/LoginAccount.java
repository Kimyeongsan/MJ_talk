package com.example.mj_talk.ui.Login;

import android.accounts.Account;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.mj_talk.MainActivity;
import com.example.mj_talk.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LoginAccount extends AppCompatActivity {
    private Button Button_complete;
    private FirebaseAuth mAuth;
    private FirebaseAnalytics mFirebaseAnalytics;
    private LoginActivity activity_login;
    int succeed;

    String name;
    String phonenum;
    String num;
    String major;
    String id;
    String password;
    String job = "";
    String user = "account_id";

    int count = 0;


    private EditText TextInputEditText_name;
    private EditText TextInputEditText_phonenum;
    private EditText TextInputEditText_num;
    private EditText TextInputEditText_major;

    private CheckBox checkbox_teacher;
    private CheckBox checkbox_student;


    private EditText TextInputEditText_account_id;
    private EditText TextInputEditText_account_password;

    private DatabaseReference mDatabase;


    DatabaseReference myref = FirebaseDatabase.getInstance().getReference();
    DatabaseReference ref = myref.child("text");

    Map<String, Object> map = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        mDatabase = FirebaseDatabase.getInstance().getReference();
        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);
        mAuth = FirebaseAuth.getInstance();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_account);

        Button_complete = findViewById(R.id.Button_complete);
        TextInputEditText_account_id = (EditText) findViewById(R.id.TextInputEditText_account_id);
        TextInputEditText_account_password = (EditText) findViewById(R.id.TextInputEditText_account_password);
        TextInputEditText_name = (EditText) findViewById(R.id.TextInputEditText_name);
        TextInputEditText_phonenum = (EditText) findViewById(R.id.TextInputEditText_phonenum);
        TextInputEditText_num = (EditText) findViewById(R.id.TextInputEditText_num);
        TextInputEditText_major = (EditText) findViewById(R.id.TextInputEditText_major);
        checkbox_teacher = (CheckBox) findViewById(R.id.checkbox_teacher);
        checkbox_student = (CheckBox) findViewById(R.id.checkbox_student);


        getSupportActionBar().hide(); // 액션바를 감추는 추가적인 코드

        checkbox_teacher.setChecked(false);
        checkbox_student.setChecked(false);

        // getFirebaseDatabase();

        Button_complete.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                name = TextInputEditText_name.getText().toString();
                phonenum = TextInputEditText_phonenum.getText().toString();
                num = TextInputEditText_num.getText().toString();
                major = TextInputEditText_major.getText().toString();
                id = TextInputEditText_account_id.getText().toString();
                password = TextInputEditText_account_password.getText().toString();

                if (!name.equals("") && !phonenum.equals("") && !num.equals("") && !major.equals("") && !id.equals("") && !password.equals("")) {

                    setAccount(count);
                    map.put("count", count);
                    map.put("name", name);
                    map.put("phonenum", phonenum);
                    map.put("num", num);
                    map.put("major", major);
                    map.put("id", id);
                    map.put("password", password);
                    map.put("job", job);
                    myref.push().setValue(map);
                    createUser(id, password);


                    if (succeed == 1) {
                        Intent intent = new Intent(LoginAccount.this, LoginActivity.class);//넘어갈 곳 결정
                        startActivity(intent);//다음 activity 진행
                    } else {

                    }

                } else {

                    Toast.makeText(LoginAccount.this, "정보를 모두 입력해주세요.", Toast.LENGTH_LONG).show();
                }
            }
        });


        myref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    AccountData account = snapshot.getValue(AccountData.class);

                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                System.out.println("The read failed: " + databaseError.getCode());
            }
        });


    }


    public void createUser(final String id, final String password) {
        mAuth.createUserWithEmailAndPassword(id, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        ++count;
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

    public void setAccount(int position) {

        if (checkbox_teacher.isChecked()) {
            job = "teacher";
            Log.d("jobteacher", job);
        } else {
            job = "student";
            Log.d("jobstudent", job);
        }

    }


}
