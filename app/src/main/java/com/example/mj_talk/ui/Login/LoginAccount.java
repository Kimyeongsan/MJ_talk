package com.example.mj_talk.ui.Login;

import android.accounts.Account;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.mj_talk.MainActivity;
import com.example.mj_talk.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
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
    private LoginActivity activity_login;
    int succeed;

    private EditText TextInputEditText_name;
    private EditText TextInputEditText_phonenum;
    private EditText TextInputEditText_num;
    private EditText TextInputEditText_major;


    private EditText TextInputEditText_account_id;
    private EditText TextInputEditText_account_password;

    private DatabaseReference mDatabase;
    private List<AccountData> accountList;

    private DatabaseReference myRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
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

        accountList = new ArrayList<>();

        getSupportActionBar().hide(); // 액션바를 감추는 추가적인 코드

        Button_complete.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                String name = TextInputEditText_name.getText().toString();
                String phonenum = TextInputEditText_phonenum.getText().toString();
                String num = TextInputEditText_num.getText().toString();
                String major = TextInputEditText_major.getText().toString();
                String id = TextInputEditText_account_id.getText().toString();
                String password = TextInputEditText_account_password.getText().toString();


                if (!name.equals("") && !phonenum.equals("") && !num.equals("") && !major.equals("") && !id.equals("") && !password.equals("")) {

                    createUser(id, password);

                    AccountData account = new AccountData();
                    account.setAccount_id(id);
                    account.setAccount_password(password);
                    account.setName(name);
                    account.setPhonenum(phonenum);
                    account.setNum(num);
                    account.setMajor(major);

                    if (succeed == 1) {
                        myRef.push().setValue(account);
                        Intent intent = new Intent(LoginAccount.this, LoginActivity.class);//넘어갈 곳 결정
                        startActivity(intent);//다음 activity 진행
                    } else {

                    }

                } else {
                    //loginUser(TextInputEditText_id.getText().toString(), TextInputEditText_password.getText().toString());
                    //Toast.makeText(LoginAccount.this, "이메일과 비밀번호를 입력하세요.", Toast.LENGTH_LONG).show();
                    Toast.makeText(LoginAccount.this, "정보를 모두 입력해주세요.", Toast.LENGTH_LONG).show();
                }
            }
        });

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference();

//        myRef.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                // This method is called once with the initial value and again
//                // whenever data at this location is updated.
//                AccountData value = dataSnapshot.getValue(AccountData.class);
//                //Log.d(TAG, "Value is: " + value);
//            }
//
//            @Override
//            public void onCancelled(DatabaseError error) {
//                // Failed to read value
//                //Log.w(TAG, "Failed to read value.", error.toException());
//            }
//        });


        myRef.addChildEventListener(new ChildEventListener() {
            @Override
            //child가 추가될 경우 값이 아래 함수로 들어온다.
            //그 중 dataSnapshot snapshot에 들어가게 된다.
            //dataSanpshot = 채팅 데이터를 담고 있는 변수
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                //데이터 베이스에 가서 해당 유형의 정보를 가져오게 된다. 여기서는 class 형태
                AccountData chat = snapshot.getValue(AccountData.class);
                //리사이클러뷰의 어댑터에 가져온 채팅 데이터를 셋팅해야 함.
                //선언된 mAdapter가 그냥 adapter로 선언되어 있기 때문에
                //리스너 안에서는 타입을 알 수 없기 때문에 mAdapter.add가 나오지 않는다.
                //형변환을 진행해야 한다.
                //((ChatAdapter)mAdapter).addChat(chat);
                Log.d("ChatChat", snapshot.getValue().toString());
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

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


    public void addaccount(AccountData account) {
        accountList.add(account);
        //몇 번째에 데이터가 들어갔다는 것을 알려주는 것!!
        // notifyItemInserted(.size()-1);//데이터가 추가되면 그것에 따른 갱신용이다.
        //0,1,2 = 데이터의 크기가 3이다.
        //3번지에 넣어라는 없으므로 -1!
    }

}
