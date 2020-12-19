package com.example.day04_2.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.day04_2.R;
import com.example.day04_2.bean.JingBean;

import java.util.ArrayList;

public class JangAdapter extends RecyclerView.Adapter {
    private Context context;
    private ArrayList<JingBean.DataBean> list;

    public JangAdapter(Context context, ArrayList<JingBean.DataBean> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.activity_jiang, null);
        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ViewHolder viewHolder= (ViewHolder) holder;
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static
    class ViewHolder extends RecyclerView.ViewHolder {
        public View rootView;
        public ImageView blank1_image;
        public TextView tv_count1;
        public TextView tv_count2;
        public TextView tv_count3;
        public TextView tv_count4;

        public ViewHolder(View rootView) {
            super(rootView);
            this.rootView = rootView;
            this.blank1_image = (ImageView) rootView.findViewById(R.id.blank1_image);
            this.tv_count1 = (TextView) rootView.findViewById(R.id.tv_count1);
            this.tv_count2 = (TextView) rootView.findViewById(R.id.tv_count2);
            this.tv_count3 = (TextView) rootView.findViewById(R.id.tv_count3);
            this.tv_count4 = (TextView) rootView.findViewById(R.id.tv_count4);
        }

    }
}
