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
import com.example.tp.bean.AsscoBean;

import java.util.ArrayList;

public class AsscoAdapter extends RecyclerView.Adapter<AsscoAdapter.ViewHolder> {
    Context context;
    ArrayList<AsscoBean.DataBean.ListBean>data;

    public AsscoAdapter(Context context, ArrayList<AsscoBean.DataBean.ListBean> data) {
        this.context = context;
        this.data = data;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.assco_item, parent, false);
        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ViewHolder viewHolder = holder;
        AsscoBean.DataBean.ListBean countsBean = data.get(position);
        viewHolder.asstv.setText(countsBean.getFullName());
        viewHolder.asstv1.setText(countsBean.getNote());
        Glide.with(context).load(countsBean.getLogo()).into(viewHolder.page);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView asstv;
        TextView asstv1;
        ImageView page;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            asstv = itemView.findViewById(R.id.asstv);
            asstv1 = itemView.findViewById(R.id.asstv1);
            page = itemView.findViewById(R.id.page);
        }
    }
}
