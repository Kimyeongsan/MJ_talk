package com.example.mj_talk.ui.chat;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mj_talk.R;

import java.util.ArrayList;

public class chat_Adapter extends RecyclerView.Adapter<chat_Adapter.MyViewHolder> {
    private ArrayList<chat_info> chatinfoArrayList;
    private Chat_AdapterListener m_al;

    /*
    private static View.OnClickListener itemlistener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            ;
        }
    };*/

    // 클래스인데 이클래스는 아이템 1개.
    public static class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView Name;
        public ImageButton ib;

        MyViewHolder(View view) {
            super(view);
            Name = view.findViewById(R.id.profile_name);
            ib = view.findViewById(R.id.profile_button);
            //view.setOnClickListener(itemlistener);
        }
    }

    public chat_Adapter(){
        this.chatinfoArrayList = new ArrayList<>();
    }

    public chat_Adapter(ArrayList<chat_info> chatInfoArrayList){
        this.chatinfoArrayList = chatInfoArrayList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LinearLayout ll = (LinearLayout) LayoutInflater.from(parent.getContext()).inflate(R.layout.row_chat, parent, false);
        MyViewHolder mvh = new MyViewHolder(ll);

        return mvh;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.Name.setText(chatinfoArrayList.get(position).name);
        holder.ib.setBackgroundResource(chatinfoArrayList.get(position).drawresource);

        //holder.ib.set

    }

    public void sortlist(){
        //chatinfoArrayList.sort();
    }

    public void setInfo(String name, int res){
        chatinfoArrayList.add(new chat_info(name, res));
    }

    public void setInfo(chat_info ci){
        chatinfoArrayList.add(ci);
    }

    @Override
    public long getItemId(int position) {
        return super.getItemId(position);
        //return position;
    }

    public void delInfo(int position){
        chatinfoArrayList.remove(position);
    }

    @Override
    public int getItemCount() {
        return chatinfoArrayList.size();
    }

}
