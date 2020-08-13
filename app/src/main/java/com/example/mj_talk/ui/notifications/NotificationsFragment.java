package com.example.mj_talk.ui.notifications;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.mj_talk.ui.Login.LoginActivity;
import com.example.mj_talk.R;
import com.google.firebase.auth.FirebaseAuth;

public class NotificationsFragment extends Fragment {

    private NotificationsViewModel notificationsViewModel;
    private Button btn_logout;

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


        Button btn_logout = root.findViewById(R.id.button_logout);
        btn_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), LoginActivity.class);
                startActivity(intent);

                FirebaseAuth.getInstance().signOut();
            }

        });

        // 이 프레그멘트 가희가 만든거 !?????

        return root;
    }
}