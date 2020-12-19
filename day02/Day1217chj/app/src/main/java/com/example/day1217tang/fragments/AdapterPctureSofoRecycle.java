package com.example.day1217tang.fragments;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;


import com.bumptech.glide.Glide;
import com.example.day1217tang.R;
import com.example.day1217tang.base.BaseAdapter;
import com.example.day1217tang.model.bean.SofoPicture;
import com.example.day1217tang.utils.TxtUtils;

import java.util.List;

public class AdapterPctureSofoRecycle extends BaseAdapter {
    private Context context;
    public AdapterPctureSofoRecycle(Context context, List<SofoPicture.DataBeanX.DataBean> mList) {
        super(context, mList);
        this.context = context;
    }

    @Override
    protected int getLayout() {
        return R.layout.sofo_item;
    }

    @Override
    protected void bindData(Object list, VH vh) {
        SofoPicture.DataBeanX.DataBean bean = (SofoPicture.DataBeanX.DataBean) list;
        TextView feed = (TextView) vh.findViewByIdMy(R.id.txt_feeds);
        TextView name = (TextView) vh.findViewByIdMy(R.id.txt_name);
        ImageView cover = (ImageView) vh.findViewByIdMy(R.id.cover);
        ImageView avatar = (ImageView) vh.findViewByIdMy(R.id.image_avatar);
        Glide.with(context).load(bean.getCover()).into(cover);
        Glide.with(context).load(bean.getAuthor().getAvatar()).into(avatar);
        TxtUtils.setTextView(feed,bean.getFeeds_text());
        TxtUtils.setTextView(name,bean.getAuthor().getName());


    }

}
