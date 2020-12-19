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
import com.example.tp.bean.HotspotBean;


import java.util.List;

public class HotspotAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    Context context;
    List<HotspotBean.DataBean.ListBean>data;
    private static final int ITEM1=0;
    private static final int ITEM2=1;
    private static final int ITEM3=2;

    public HotspotAdapter(Context context, List<HotspotBean.DataBean.ListBean> data) {
        this.context = context;
        this.data = data;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType==ITEM1){
            View inflate = LayoutInflater.from(context).inflate(R.layout.hotspot_item1, parent, false);
            return new VH1(inflate);
        }else if (viewType==ITEM2){
            View inflate = LayoutInflater.from(context).inflate(R.layout.hotspot_item2, parent, false);
            return new VH2(inflate);
        }else {
            View inflate = LayoutInflater.from(context).inflate(R.layout.hotspot_item3, parent, false);
            return new VH3(inflate);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        HotspotBean.DataBean.ListBean bean = data.get(position);
        List<HotspotBean.DataBean.ListBean.FilePathListBean> list = bean.getFilePathList();
        int type = getItemViewType(position);
        if (type==ITEM1){
            VH1 vh1 = (VH1) holder;
            vh1.tv1.setText(bean.getTitle());
            vh1.tv2.setText(bean.getCreateTime());

            Glide.with(context).load(list.get(0).getFilePath()).into(vh1.img1);
            Glide.with(context).load(list.get(1).getFilePath()).into(vh1.img2);
            Glide.with(context).load(list.get(2).getFilePath()).into(vh1.img3);
        }else if (type==ITEM2){
            VH2 vh2 = (VH2) holder;
            vh2.item2_tv1.setText(bean.getTitle());
            vh2.item2_tv2.setText(bean.getCreateTime());
            Glide.with(context).load(list.get(0).getFilePath()).into(vh2.item2_img1);
        }else {
            VH3 vh3 = (VH3) holder;
            vh3.item3_tv1.setText(bean.getTitle());
            vh3.item3_tv2.setText(bean.getTitle());
        }
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (position%3==0){
            return ITEM1;
        }else if (position%3==1){
            return ITEM2;
        }else {
            return ITEM3;
        }
    }

    private class VH1 extends RecyclerView.ViewHolder {
        ImageView img1;
        ImageView img2;
        ImageView img3;
        TextView tv1;
        TextView tv2;
        public VH1(View inflate) {
            super(inflate);
            img1 = inflate.findViewById(R.id.img1);
            img2 = inflate.findViewById(R.id.img2);
            img3 = inflate.findViewById(R.id.img3);
            tv1 = inflate.findViewById(R.id.tv1);
            tv2 = inflate.findViewById(R.id.tv2);
        }
    }

    private class VH2 extends RecyclerView.ViewHolder {
        ImageView item2_img1;
        TextView item2_tv1;
        TextView item2_tv2;
        public VH2(View inflate) {
            super(inflate);
            item2_img1 = inflate.findViewById(R.id.item2_img1);
            item2_tv1 = inflate.findViewById(R.id.item2_tv1);
            item2_tv2 = inflate.findViewById(R.id.item2_tv2);
        }
    }

    private class VH3 extends RecyclerView.ViewHolder {
        TextView item3_tv1;
        TextView item3_tv2;
        public VH3(View inflate) {
            super(inflate);
            item3_tv1 = inflate.findViewById(R.id.item3_tv1);
            item3_tv2 = inflate.findViewById(R.id.item3_tv2);
        }
    }
}
