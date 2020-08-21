package com.example.mj_talk.ui.Login;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mj_talk.R;
import com.google.android.material.internal.NavigationMenu;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class LoginFind extends AppCompatActivity {
    private TextView TextInputEditText_name;
    private TextView TextInputEditText_phonenum_id;
    private TextView TextInputEditText_id;
    private TextView TextInputEditText_phonenum_password;
    private Button Button_find_id;
    private Button Button_find_pw;

    private DatabaseReference mDatabase;

    private DatabaseReference userRef;

    String name_id;
    String phonenum_id;
    String id_password;
    String phonenum_password;
    String ID = "";
    char target = '@';
    String pw_find = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        mDatabase = FirebaseDatabase.getInstance().getReference();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_forgot);
        Button_find_id = findViewById(R.id.Button_find_id);
        Button_find_pw = findViewById(R.id.Button_find_pw);
        TextInputEditText_name = findViewById(R.id.TextInputEditText_name);
        TextInputEditText_phonenum_id = findViewById(R.id.TextInputEditText_phonenum_id);
        TextInputEditText_id = findViewById(R.id.TextInputEditText_id);
        TextInputEditText_phonenum_password = findViewById(R.id.TextInputEditText_phonenum_password);

        getSupportActionBar().hide(); // 액션바를 감추는 추가적인 코드

        Button_find_id.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                userRef = mDatabase.child("account_list").getRef();//

                //값 주고 받는 활동
                Intent intent = new Intent(LoginFind.this, FindId.class);//넘어갈 곳 결정

                startActivity(intent);//다음 activity 진행
            }

        });


        Button_find_pw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userRef = mDatabase.child("account_list").getRef();

                id_password = TextInputEditText_id.getText().toString();
                phonenum_password = TextInputEditText_phonenum_password.getText().toString();

                int target_num = id_password.indexOf(target);
                ID = id_password.substring(0, target_num);


                userRef.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        for (DataSnapshot snapshot : dataSnapshot.getChildren()) {

                            AccountData account = dataSnapshot.child(ID).getValue(AccountData.class);
                            pw_find = account.getAccount_password();

                            Log.d("account_brought", pw_find);

                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        System.out.println("The read failed: " + databaseError.getCode());
                    }
                });


                //값 주고 받는 활동
                Intent intent = new Intent(LoginFind.this, FindPassword.class);//넘어갈 곳 결정

                startActivity(intent);//다음 activity 진행
            }

        });


    }

}
