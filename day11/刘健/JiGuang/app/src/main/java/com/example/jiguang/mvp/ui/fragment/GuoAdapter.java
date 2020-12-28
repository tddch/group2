package com.example.jiguang.mvp.ui.fragment;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.example.jiguang.R;
import com.example.jiguang.mvp.model.bean.BannerBean;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 *
 */
public class GuoAdapter extends RecyclerView.Adapter<GuoAdapter.ViewHolder> {
    private ArrayList<BannerBean> list;
    private Context context;

    public GuoAdapter(ArrayList<BannerBean> list, Context context) {
        this.list = list;
        this.context = context;
    }


    @NonNull
    @Override
    public GuoAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_home_guo, parent, false);
        return new ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.tvName.setText(list.get(position).getTitle());
        Glide.with(context).load(list.get(position).getImage()).apply(new RequestOptions().transform(new RoundedCorners(30))).into(holder.ivItem);
    }


    @Override
    public int getItemCount() {
        return list.size();
    }

    static
    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_item)
        TextView tvName;
        @BindView(R.id.iv_item)
        ImageView ivItem;
        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
