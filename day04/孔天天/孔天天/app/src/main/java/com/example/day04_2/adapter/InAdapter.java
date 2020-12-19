package com.example.day04_2.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.day04_2.R;
import com.example.day04_2.bean.ReWenBean;

import java.util.ArrayList;

public class InAdapter extends RecyclerView.Adapter {
    private Context context;
    private ArrayList<ReWenBean.DataBean> list;

    public InAdapter(Context context, ArrayList<ReWenBean.DataBean> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.activity_rewen, null);
        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ViewHolder viewHolder= (ViewHolder) holder;
        viewHolder.tv_title.setText(list.get(position).getTitle());
        Glide.with(context).load(list.get(position).getCover()).into(viewHolder.home_image);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static
    class ViewHolder extends RecyclerView.ViewHolder {
        public View rootView;
        public ImageView home_image;
        public TextView tv_title;

        public ViewHolder(View rootView) {
            super(rootView);
            this.rootView = rootView;
            this.home_image = (ImageView) rootView.findViewById(R.id.home_image);
            this.tv_title = (TextView) rootView.findViewById(R.id.tv_title);
        }

    }
}
