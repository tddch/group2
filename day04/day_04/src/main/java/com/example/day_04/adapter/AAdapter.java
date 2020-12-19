package com.example.day_04.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.day_04.R;
import com.example.day_04.modle.bean.JingYanBean;

import java.util.ArrayList;
import java.util.List;

public class AAdapter extends RecyclerView.Adapter<AAdapter.ViewHolder> {
    private Context context;
    private List<JingYanBean.DataBean.ExpTopBean.ListBean> list = new ArrayList<>();

    public void additem(List<JingYanBean.DataBean.ExpTopBean.ListBean> list) {
        this.list.addAll(list);
        notifyDataSetChanged();
    }

    public AAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View root = LayoutInflater.from(context).inflate(R.layout.a_item, null, false);
        return new ViewHolder(root);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        JingYanBean.DataBean.ExpTopBean.ListBean listBean = list.get(position);

        holder.title.setText(listBean.getNickName());
//        holder.num.setText(listBean.getNoNum());

        RequestOptions options = new RequestOptions();
        options.circleCrop();
        Glide.with(context).load(listBean.getHeadUrl()).apply(options).into(holder.img);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView img;
        TextView title;
        TextView num;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.a_img);
            title = itemView.findViewById(R.id.a_title);
            num = itemView.findViewById(R.id.a_num);

        }
    }
}
