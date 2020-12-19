package com.example.day1217tang.fragments;

import android.content.Context;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.day1217tang.R;
import com.example.day1217tang.base.BaseAdapter;
import com.example.day1217tang.model.bean.SofoText;
import com.example.day1217tang.utils.TxtUtils;

import java.util.ArrayList;
import java.util.List;

public class AdapterTextSofoRecycle extends BaseAdapter {
private Context context;

    public AdapterTextSofoRecycle(Context context, List<SofoText.DataBeanX.DataBean> mList) {
        super(context, mList);
        this.context = context;
    }

    @Override
    protected int getLayout() {
        return R.layout.recycle_textitem;
    }

    @Override
    protected void bindData(Object list, VH vh) {
        SofoText.DataBeanX.DataBean bean = (SofoText.DataBeanX.DataBean) list;
        TextView feed = (TextView) vh.findViewByIdMy(R.id.txt_feeds_text);
        TextView name = (TextView) vh.findViewByIdMy(R.id.txt_name_text);
        ImageView avatar = (ImageView) vh.findViewByIdMy(R.id.image_avatar_text);
        TxtUtils.setTextView(feed,bean.getFeeds_text()+"");
        TxtUtils.setTextView(name,bean.getAuthor().getName());
        Glide.with(context).load(bean.getAuthor().getAvatar()).into(avatar);
    }
}
