package com.example.hackathon_final;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ClaseAlmacenarArray extends RecyclerView.Adapter<MyViewHolder> {
    public String[] acv_data1;
    public String[] acv_data2;
    public String[] acv_data3;
    public String[] acv_data4;
    public String[] acv_data5;
    public Context acv_mContext;

    public ClaseAlmacenarArray(String[] data1, String[] data2, String[] data3, String[] data4, String[] data5, Context mContext){
        this.acv_data1 = data1;
        this.acv_data2 = data2;
        this.acv_data3 = data3;
        this.acv_data4 = data4;
        this.acv_data5 = data5;
        this.acv_mContext =mContext;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater acv_inflater = LayoutInflater.from(acv_mContext);
        View acv_view = acv_inflater.inflate(R.layout.list_items, parent, false);
        return new MyViewHolder(acv_view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.acv_textView1.setText(acv_data1[position]);
        holder.acv_textView2.setText(acv_data2[position]);
        holder.acv_textView3.setText(acv_data3[position]);
        holder.acv_textView4.setText(acv_data4[position]);
        holder.acv_textView5.setText(acv_data5[position]);
    }

    @Override
    public int getItemCount() {
        return acv_data1.length;
    }
}

class MyViewHolder extends RecyclerView.ViewHolder{
    TextView acv_textView1, acv_textView2, acv_textView3, acv_textView4, acv_textView5;

    public MyViewHolder(@NonNull View itemView) {
        super(itemView);
        acv_textView1 = itemView.findViewById(R.id.textview_recyclerview1);
        acv_textView2 = itemView.findViewById(R.id.textview_recyclerview2);
        acv_textView3 = itemView.findViewById(R.id.textview_recyclerview3);
        acv_textView4 = itemView.findViewById(R.id.textview_recyclerview4);
        acv_textView5 = itemView.findViewById(R.id.textview_recyclerview5);
    }
}
