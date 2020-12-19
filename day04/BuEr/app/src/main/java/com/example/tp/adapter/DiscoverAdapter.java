package com.example.tp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.tp.R;
import com.example.tp.bean.DiscoverBean;

import java.util.ArrayList;

public class DiscoverAdapter extends RecyclerView.Adapter<DiscoverAdapter.ViewHolder> {
    Context context;
    ArrayList<DiscoverBean.DataBean> data;

    public DiscoverAdapter(Context context, ArrayList<DiscoverBean.DataBean> data) {
        this.context = context;
        this.data = data;
    }

    @NonNull
    @Override
    public DiscoverAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.discover_item, parent, false);
        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull DiscoverAdapter.ViewHolder holder, int position) {
        ViewHolder viewHolder = holder;
        DiscoverBean.DataBean dataBean = data.get(position);
        viewHolder.dis_tv.setText(dataBean.getTitle());
        viewHolder.dis_tv1.setText(dataBean.getStartTime());
        Glide.with(context).load(dataBean.getCover()).into(viewHolder.dis_img);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView dis_img;
        TextView dis_tv;
        TextView dis_tv1;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            dis_img = itemView.findViewById(R.id.dic_img);
            dis_tv = itemView.findViewById(R.id.dic_tv);
            dis_tv1 = itemView.findViewById(R.id.dic_tv1);
        }
    }
}
