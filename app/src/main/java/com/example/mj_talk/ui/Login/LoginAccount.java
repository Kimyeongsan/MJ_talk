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
    private List<AccountData> accountList;

    static ArrayList<String> arrayData = new ArrayList<String>();
    static ArrayList<String> arrayIndex = new ArrayList<String>();


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
        checkbox_teacher = (CheckBox) findViewById(R.id.checkbox_teacher);
        checkbox_student = (CheckBox) findViewById(R.id.checkbox_student);


        getSupportActionBar().hide(); // 액션바를 감추는 추가적인 코드

        checkbox_teacher.setChecked(false);
        checkbox_student.setChecked(false);

        getFirebaseDatabase();

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

                    createUser(id, password);
                    setAccount(count - 1);
                    postFirebaseDatabase(true);
                    getFirebaseDatabase();
                    setInsertMode();


                    if (succeed == 1) {
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


    }

    public void setInsertMode() {
        TextInputEditText_name.setText("");
        TextInputEditText_phonenum.setText("");
        TextInputEditText_num.setText("");
        TextInputEditText_major.setText("");
        TextInputEditText_account_id.setText("");
        TextInputEditText_account_password.setText("");
        checkbox_teacher.setChecked(false);
        checkbox_student.setChecked(false);
    }

    public void createUser(final String id, final String password) {
        mAuth.createUserWithEmailAndPassword(id, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        count++;
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

    //get position account
    public void setAccount(int position) {
        String[] tempData = arrayData.get(position).split("\\s+");
        Log.e("On Click", "Split Result = " + tempData);
        TextInputEditText_name.setText(tempData[0].trim());
        TextInputEditText_phonenum.setText(tempData[1].trim());
        TextInputEditText_num.setText(tempData[2].trim());
        TextInputEditText_major.setText(tempData[3].trim());
        TextInputEditText_account_id.setText(tempData[4].trim());
        TextInputEditText_account_password.setText(tempData[5].trim());
        if (tempData[6].trim().equals("교수")) {
            checkbox_teacher.setChecked(true);
            job = "teacher";
        } else {
            checkbox_student.setChecked(true);
            job = "student";
        }

    }

    public void postFirebaseDatabase(boolean add) {
        mDatabase = FirebaseDatabase.getInstance().getReference();
        Map<String, Object> childUpdates = new HashMap<>();
        Map<String, Object> postValues = null;
        if (add) {
            AccountData post = new AccountData(name, phonenum, num, major, id, password, job);
            postValues = post.toMap();
        }
        childUpdates.put("/id_list/" + id, postValues);
        mDatabase.updateChildren(childUpdates);
    }

    public void getFirebaseDatabase() {
        ValueEventListener postListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Log.e("getFirebaseDatabase", "key: " + dataSnapshot.getChildrenCount());
                arrayData.clear();
                arrayIndex.clear();
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    String key = postSnapshot.getKey();
                    AccountData get = postSnapshot.getValue(AccountData.class);
                    String[] info = {get.name, get.phonenum, get.num, get.major, get.account_id, get.account_password, get.job};
                    // String Result = info[0].toString() + info[1].toString()+ info[2].toString()+ info[3].toString()+ info[4].toString()+ info[5].toString()+ info[5].toString();
                    String Result = setTextLength(info[0], 10) + setTextLength(info[1], 10) + setTextLength(info[2], 10) + setTextLength(info[3], 10);
                    arrayData.add(Result);
                    arrayIndex.add(key);
                    //Log.d("getFirebaseDatabase", "key: " + key);
                    //Log.d("getFirebaseDatabase", "info: " + info[0] + info[1] + info[2] + info[3]);
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.w("getFirebaseDatabase", "loadPost:onCancelled", databaseError.toException());
            }
        };
        Query sortbyAge = FirebaseDatabase.getInstance().getReference().child("id_list").orderByChild(user);
        sortbyAge.addListenerForSingleValueEvent(postListener);
    }

    public String setTextLength(String text, int length) {
        if (text.length() < length) {
            int gap = length - text.length();
            for (int i = 0; i < gap; i++) {
                text = text + " ";
            }
        }
        return text;
    }

}
