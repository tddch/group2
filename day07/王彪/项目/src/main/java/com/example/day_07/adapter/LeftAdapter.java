package com.example.day_07.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.day_07.R;
import com.example.day_07.model.bean.RecyBean;

import java.util.ArrayList;
import java.util.List;

public class LeftAdapter extends RecyclerView.Adapter {

    private Context context;
    private List<RecyBean.DataBean> list = new ArrayList<>();

    public void additem(List<RecyBean.DataBean> list) {
        this.list.addAll(list);
        notifyDataSetChanged();
    }

    public LeftAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View root = LayoutInflater.from(context).inflate(R.layout.recy_item, null, false);
        return new RecyViewHolder(root);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        RecyBean.DataBean dataBean = list.get(position);
        RecyViewHolder recyViewHolder = (RecyViewHolder) holder;

        recyViewHolder.title.setText(dataBean.getTitle());
        recyViewHolder.text.setText(dataBean.getLocation());
        recyViewHolder.time.setText(dataBean.getStartTime());
        Glide.with(context).load(dataBean.getCover()).into(recyViewHolder.img);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    private class RecyViewHolder extends RecyclerView.ViewHolder {
        TextView text;
        TextView title;
        TextView time;
        ImageView img;

        public RecyViewHolder(View root) {
            super(root);
            img = itemView.findViewById(R.id.recy_img);
            title = itemView.findViewById(R.id.recy_title);
            text = itemView.findViewById(R.id.recy_text);
            time = itemView.findViewById(R.id.recy_time);
        }
    }
}
