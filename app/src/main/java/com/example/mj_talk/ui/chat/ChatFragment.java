package com.example.mj_talk.ui.chat;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mj_talk.R;

import java.util.ArrayList;

public class ChatFragment extends Fragment {
    //private ChatViewModel chatViewModel;
    private View root;
    private RecyclerView rcv;
    private RecyclerView rcv2;
    private RecyclerView.LayoutManager layoutManager;
    private RecyclerView.LayoutManager layoutManager2;
    private chat_Adapter ca1;
    private chat_Adapter ca2;
    private ArrayList<chat_info> cc;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_chat, container, false);

        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        rcv = (RecyclerView)view.findViewById(R.id.chat_list);
        rcv.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getContext());
        rcv.setLayoutManager(layoutManager);

        //cc = new ArrayList<>();
        ca1 = new chat_Adapter();
        ca1.setInfo("재정아재정아", R.drawable.ic_symbol);
        ca1.setInfo("재정아재정아2", R.drawable.ic_symbol);
        ca1.setInfo("재정아재정아3", R.drawable.ic_home);
        rcv.setAdapter(ca1);


        rcv2 = (RecyclerView) view.findViewById(R.id.chat_list2);
        rcv2.setHasFixedSize(true);
        layoutManager2 = new LinearLayoutManager(getContext());
        rcv2.setLayoutManager(layoutManager2);



        ca2 = new chat_Adapter();
        ca2.setInfo("재정아dd재정아", R.drawable.ic_symbol);
        ca2.setInfo("재정아dd재정아2", R.drawable.ic_symbol);
        ca2.setInfo("재정아dd재정아3", R.drawable.ic_home);



        rcv2.setAdapter(ca2);

    }
}
