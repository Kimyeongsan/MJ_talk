package com.example.mj_talk.ui.dashboard;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.mj_talk.R;

import java.util.ArrayList;

public class board_Adapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private ArrayList<board_info> doardinfoArrayList;

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView title;
        TextView writer;
        TextView people;
        TextView major;

        MyViewHolder(View view){
            super(view);
            title = view.findViewById(R.id.board_title);
            writer = view.findViewById(R.id.writer);
            people = view.findViewById(R.id.limit_people);
            major = view.findViewById(R.id.major);
        }
    }

    board_Adapter(ArrayList<board_info> doardinfoArrayList){
        this.doardinfoArrayList = doardinfoArrayList;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_board, parent, false);

        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        MyViewHolder myViewHolder = (MyViewHolder) holder;

        myViewHolder.title.setText(doardinfoArrayList.get(position).board_title);
        myViewHolder.major.setText(doardinfoArrayList.get(position).major);
        myViewHolder.people.setText(doardinfoArrayList.get(position).limit_people);
        myViewHolder.writer.setText(doardinfoArrayList.get(position).writer);
    }

    @Override
    public int getItemCount() {
        return doardinfoArrayList.size();

    }
}
