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
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.request.RequestOptions;
import com.example.tp.R;
import com.example.tp.bean.DengBean;

import java.util.ArrayList;

public class DengAdapter extends RecyclerView.Adapter<DengAdapter.ViewHolder> {
    Context context;
    ArrayList<DengBean.DataBean.SignTopBean.ListBean> data;

    public DengAdapter(Context context, ArrayList<DengBean.DataBean.SignTopBean.ListBean> data) {
        this.context = context;
        this.data = data;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.deng_item, parent, false);
        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ViewHolder viewHolder =holder;
        DengBean.DataBean.SignTopBean.ListBean listBean = data.get(position);
        viewHolder.hao.setText(listBean.getNickName());
        viewHolder.hao1.setText(listBean.getProvince());
        viewHolder.jing.setText(listBean.getExpScore()+"经验");
        Glide.with(context).load(listBean.getHeadUrl()).apply(RequestOptions.bitmapTransform(new CircleCrop())).into(viewHolder.page1);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView hao;
        TextView hao1;
        TextView jing;
        ImageView page1;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            hao = itemView.findViewById(R.id.hao);
            hao1 = itemView.findViewById(R.id.hao1);
            jing = itemView.findViewById(R.id.jing);
            page1 = itemView.findViewById(R.id.page1);
        }
    }
}
