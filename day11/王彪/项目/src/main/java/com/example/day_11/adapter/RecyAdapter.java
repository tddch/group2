package com.example.day_11.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.day_11.R;
import com.example.day_11.bean.RecyBean;
import com.youth.banner.Banner;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.List;

public class RecyAdapter extends RecyclerView.Adapter {

    private Context context;
    private List<RecyBean.ResultsBean> list = new ArrayList<>();
    private List<RecyBean.ResultsBean> banneList = new ArrayList<>();

    public void addbanner(List<RecyBean.ResultsBean> banneList) {
        if (banneList == null){
            return;
        }
        this.banneList.addAll(banneList);
        notifyDataSetChanged();
    }

    public void additem(List<RecyBean.ResultsBean> list) {
        if (list == null){
            return;
        }
        this.list.addAll(list);
        notifyDataSetChanged();
    }

    public RecyAdapter(Context context) {
        this.context = context;
    }

    private static final int BANNER_TYPE = 0;
    private static final int RECY_TYPE = 1;

    @Override
    public int getItemViewType(int position) {
        if (position == 0 && banneList.size() > 0) {
            return BANNER_TYPE;
        } else {
            return RECY_TYPE;
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == BANNER_TYPE) {
            View root = LayoutInflater.from(context).inflate(R.layout.banner_item, parent, false);
            return new BannerViewHolder(root);
        }else {
            View root = LayoutInflater.from(context).inflate(R.layout.recy_item, null, false);
            return new RecyViewHolder(root);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        switch (getItemViewType(position)){
            case BANNER_TYPE:
                BannerViewHolder bannerViewHolder = (BannerViewHolder) holder;
                bannerViewHolder.banner.setImages(banneList).setImageLoader(new ImageLoader() {
                    @Override
                    public void displayImage(Context context, Object path, ImageView imageView) {
                        RecyBean.ResultsBean bean = (RecyBean.ResultsBean) path;
                        Glide.with(context).load(bean.getUrl()).into(imageView);
                    }
                }).start();
                break;

            case RECY_TYPE:
                if (banneList.size()>0){
                    position = position -1;
                }
                RecyBean.ResultsBean bean = list.get(position);
                RecyViewHolder recyViewHolder = (RecyViewHolder) holder;
                Glide.with(context).load(bean.getUrl()).into(recyViewHolder.img);
                recyViewHolder.title.setText(bean.getDesc());
                break;
        }
    }

    @Override
    public int getItemCount() {
        if (banneList.size()>0){
            return list.size()+1;
        }else {
            return list.size();
        }
    }

    private class BannerViewHolder extends RecyclerView.ViewHolder {
        Banner banner;
        public BannerViewHolder(View root) {
            super(root);
            banner = root.findViewById(R.id.banner);
        }
    }

    private class RecyViewHolder extends RecyclerView.ViewHolder {
        ImageView img;
        TextView title;
        public RecyViewHolder(View root) {
            super(root);
            img = root.findViewById(R.id.recy_img);
            title = root.findViewById(R.id.recy_text);
        }
    }
}
