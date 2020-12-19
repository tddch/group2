package com.example.xmu.adapter;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.request.RequestOptions;
import com.example.xmu.R;
import com.example.xmu.bean.TuBean;

import java.util.ArrayList;

public class TuAdapter extends RecyclerView.Adapter<TuAdapter.ViewHolder> {
    Context context;
    ArrayList<TuBean.DataBeanX.DataBean> data;

    public TuAdapter(Context context, ArrayList<TuBean.DataBeanX.DataBean> data) {
        this.context = context;
        this.data = data;
    }

    @NonNull
    @Override
    public TuAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.tu, parent, false);
        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull TuAdapter.ViewHolder holder, int position) {
        ViewHolder viewHolder = holder;
        TuBean.DataBeanX.DataBean dataBean = data.get(position);
        viewHolder.txt.setText(dataBean.getAuthor().getName());
        viewHolder.tv.setText(dataBean.getFeeds_text());
        viewHolder.tv1.setText(dataBean.getActivityText());

        Glide.with(context).load(dataBean.getAuthor().getAvatar()).apply(RequestOptions.bitmapTransform(new CircleCrop())).into(viewHolder.img1);
        Glide.with(context).load(dataBean.getCover()).into(viewHolder.img2);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txt;
        TextView tv;
        TextView tv1;
        ImageView img1;
        ImageView img2;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txt = itemView.findViewById(R.id.txt);
            tv = itemView.findViewById(R.id.tv);
            tv1 = itemView.findViewById(R.id.tv1);
            img1 = itemView.findViewById(R.id.img1);
            img2 = itemView.findViewById(R.id.img2);
        }
    }
}
