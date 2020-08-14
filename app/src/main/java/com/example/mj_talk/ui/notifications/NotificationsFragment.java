package com.example.mj_talk.ui.notifications;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.mj_talk.ui.Login.LoginActivity;
import com.example.mj_talk.R;
import com.google.firebase.auth.FirebaseAuth;

public class NotificationsFragment extends Fragment {

    private NotificationsViewModel notificationsViewModel;
    private Button btn_logout;
    private Button swbtn1;
    private Button swbtn2;
    private Button swbtn3;
    private Button swbtn4;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        notificationsViewModel =
                ViewModelProviders.of(this).get(NotificationsViewModel.class);
        View root = inflater.inflate(R.layout.fragment_notifications, container, false);

        final TextView textView = root.findViewById(R.id.text_home);
        notificationsViewModel.getText().observe(getViewLifecycleOwner(), new Observer<Integer>() {
            @Override
            public void onChanged(Integer s) {
                textView.setText(s);
            }
        });



        Button swbtn1 = root.findViewById(R.id.swbtn1);
        Button swbtn2 = root.findViewById(R.id.swbtn2);
        Button swbtn3 = root.findViewById(R.id.swbtn3);
        Button swbtn4 = root.findViewById(R.id.swbtn4);

     //  Vibrator vibrator = (Vibrator)getSystemService(Context.VIBRATOR_SERVICE);
    //이부분이 오류 떠서... 코멘트 처리했어요ㅜㅜ
        swbtn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
       //          Vibrator.vibrate(VibrationEffect.createOneShot(1000,50);
        }
        });
        swbtn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
        //        Vibrator.vibrate(VibrationEffect.createOneShot(1000,60);
            }
        });
        swbtn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
        //        Vibrator.vibrate(VibrationEffect.createOneShot(1000,70);
            }
        });
        swbtn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
        //        Vibrator.vibrate(VibrationEffect.createOneShot(1000,80);
            }
        });


        Button btn_logout = root.findViewById(R.id.button_logout);
        btn_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), LoginActivity.class);
                startActivity(intent);

                FirebaseAuth.getInstance().signOut();
            }

        });

        return root;
    }
}