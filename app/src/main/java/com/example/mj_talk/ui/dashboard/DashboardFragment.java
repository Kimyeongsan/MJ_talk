package com.example.mj_talk.ui.dashboard;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mj_talk.MainActivity;
import com.example.mj_talk.R;
import com.example.mj_talk.ui.home.HomeFragment;

import java.util.ArrayList;

public class DashboardFragment extends Fragment {

    private View root;
    RecyclerView mRecyclerView;
    board_Adapter myAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_dashboard, container, false);

        mRecyclerView = root.findViewById(R.id.boardlist);

        ArrayList<board_info> boardinfoArrayList = new ArrayList<>();
        boardinfoArrayList.add(new board_info("공모전", "우대전공", "인원제한", "글쓴이"));
        boardinfoArrayList.add(new board_info("공모전1", "우대전공1", "인원제한1", "글쓴이1"));
        boardinfoArrayList.add(new board_info("공모전2", "우대전공2", "인원제한2", "글쓴이2"));
        //여기서 값 수정

        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));

        myAdapter = new board_Adapter(boardinfoArrayList);
        mRecyclerView.setAdapter(myAdapter);


        return root;
    }
}

