package com.example.day_04.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.day_04.HomeActivity;
import com.example.day_04.R;
import com.example.day_04.modle.bean.RecyBean;
import com.example.day_04.view.fragment.LeftFragment;

import java.util.ArrayList;
import java.util.List;

public class LeftAdapter extends RecyclerView.Adapter {

    private Context context;
    private List<RecyBean.DataBean> list =new ArrayList<>();

    public void additem(List<RecyBean.DataBean> list) {
        this.list.addAll(list);
        notifyDataSetChanged();
    }

    public LeftAdapter(Context context) {
        this.context = context;
    }

    private static final int ITEM_TYPE= 0;
    private static final int RECY_TYPE= 1;

    @Override
    public int getItemViewType(int position) {
        if (position == 0){
            return  ITEM_TYPE;
        }else {
            return RECY_TYPE;
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == ITEM_TYPE){
            View root = LayoutInflater.from(context).inflate(R.layout.san_item, null, false);
            return new ItemViewHolder(root);
        }else {

            View root = LayoutInflater.from(context).inflate(R.layout.recy_item, null, false);
            return new RecyViewHolder(root);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        switch (getItemViewType(position)){
            case ITEM_TYPE:

                ItemViewHolder itemViewHolder = (ItemViewHolder) holder;

                itemViewHolder.img.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(context, HomeActivity.class);
                        context.startActivity(intent);
                    }
                });

                break;

            case RECY_TYPE:

                RecyBean.DataBean dataBean = list.get(position);
                RecyViewHolder recyViewHolder = (RecyViewHolder) holder;

                recyViewHolder.title.setText(dataBean.getTitle());
                recyViewHolder.text.setText(dataBean.getLocation());
                recyViewHolder.time.setText(dataBean.getStartTime());
                Glide.with(context).load(dataBean.getCover()).into(recyViewHolder.img);
                break;
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    private class ItemViewHolder extends RecyclerView.ViewHolder {
        ImageView img;
        public ItemViewHolder(View root) {
            super(root);

            img = itemView.findViewById(R.id.iv_pai);
        }
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
