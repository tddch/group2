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
import com.example.xmu.bean.TuiBean;
import com.example.xmu.bean.WenBean;

import java.util.ArrayList;

public class TuiAdapter extends RecyclerView.Adapter<TuiAdapter.ViewHolder> {
    Context context;
    ArrayList<TuiBean.DataBeanX.DataBean> data;

    public TuiAdapter(Context context, ArrayList<TuiBean.DataBeanX.DataBean> data) {
        this.context = context;
        this.data = data;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.tui, parent, false);
        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ViewHolder viewHolder = holder;
        TuiBean.DataBeanX.DataBean dataBean = data.get(position);

        viewHolder.tvv.setText(dataBean.getTitle());
        viewHolder.tvv1.setText(dataBean.getIntro());
        Glide.with(context).load(dataBean.getIcon()).into(viewHolder.page);


    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvv;
        TextView tvv1;
        ImageView page;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvv = itemView.findViewById(R.id.tvv);
            tvv1 = itemView.findViewById(R.id.tvv1);
            page = itemView.findViewById(R.id.page);
        }
    }
}
