package com.example.shouye;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class GirlAdapter extends RecyclerView.Adapter<GirlAdapter.ViewHolder> {
    Context context;
    ArrayList<Bean.ResultsBean> data ;

    public GirlAdapter(Context context, ArrayList<Bean.ResultsBean> data) {
        this.context = context;
        this.data = data;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.girl_item, parent, false);
        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Bean.ResultsBean resultsBean = data.get(position);
        ViewHolder viewHolder = holder;
        Glide.with(context).load(resultsBean.getUrl()).into(viewHolder.img1);
        Glide.with(context).load(resultsBean.getUrl()).into(viewHolder.img2);
        Glide.with(context).load(resultsBean.getUrl()).into(viewHolder.img3);
        Glide.with(context).load(resultsBean.getUrl()).into(viewHolder.img4);
        Glide.with(context).load(resultsBean.getUrl()).into(viewHolder.img5);
        Glide.with(context).load(resultsBean.getUrl()).into(viewHolder.img6);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView img1;
        ImageView img2;
        ImageView img3;
        ImageView img4;
        ImageView img5;
        ImageView img6;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            img1 = itemView.findViewById(R.id.img1);
            img2 = itemView.findViewById(R.id.img2);
            img3 = itemView.findViewById(R.id.img3);
            img4 = itemView.findViewById(R.id.img4);
            img5 = itemView.findViewById(R.id.img5);
            img6 = itemView.findViewById(R.id.img6);
        }
    }
}
