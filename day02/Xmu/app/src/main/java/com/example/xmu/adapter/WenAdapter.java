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
import com.example.xmu.bean.WenBean;

import java.util.ArrayList;

public class WenAdapter extends RecyclerView.Adapter<WenAdapter.ViewHolder> {
    Context context;
    ArrayList<WenBean.DataBeanX.DataBean> data;

    public WenAdapter(Context context, ArrayList<WenBean.DataBeanX.DataBean> data) {
        this.context = context;
        this.data = data;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.wen_item, parent, false);
        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ViewHolder viewHolder = holder;
        WenBean.DataBeanX.DataBean dataBean = data.get(position);
        viewHolder.txt1.setText(dataBean.getAuthor().getName());
        Glide.with(context).load(dataBean.getAuthor().getAvatar()).apply(RequestOptions.bitmapTransform(new CircleCrop())).into(viewHolder.img11);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txt1;
        ImageView img11;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txt1 = itemView.findViewById(R.id.txt1);
            img11 = itemView.findViewById(R.id.img11);

        }
    }
}
