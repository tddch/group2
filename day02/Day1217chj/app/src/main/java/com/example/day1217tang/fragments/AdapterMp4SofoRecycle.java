package com.example.day1217tang.fragments;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.day1217tang.R;
import com.example.day1217tang.base.BaseAdapter;
import com.example.day1217tang.model.bean.SofoMp4;
import com.example.day1217tang.utils.TxtUtils;

import java.util.List;

import butterknife.BindView;
import cn.jzvd.Jzvd;
import cn.jzvd.JzvdStd;

public class AdapterMp4SofoRecycle extends BaseAdapter {
    @BindView(R.id.image_avatar_mp4)
    ImageView imageAvatar;
    @BindView(R.id.txt_name_mp4)
    TextView txtName;
    @BindView(R.id.txt_feeds_mp4)
    TextView txtFeeds;
    @BindView(R.id.jz_url_mp4)
    JzvdStd jzUrl;
private Context context;
    public AdapterMp4SofoRecycle(Context context, List<SofoMp4.DataBeanX.DataBean> mList) {

        super(context, mList);
        this.context = context;
    }

    @Override
    protected int getLayout() {
        return R.layout.recycle_mp4item;
    }

    @Override
    protected void bindData(Object list, VH vh) {
        SofoMp4.DataBeanX.DataBean bean = (SofoMp4.DataBeanX.DataBean) list;
        TextView feed = (TextView) vh.findViewByIdMy(R.id.txt_feeds_mp4);
        TextView name = (TextView) vh.findViewByIdMy(R.id.txt_name_mp4);
        ImageView cover = (ImageView) vh.findViewByIdMy(R.id.image_avatar_mp4);
        JzvdStd jz = (JzvdStd) vh.findViewByIdMy(R.id.jz_url_mp4);
        if(feed==null){
            TxtUtils.setTextView(feed,"暂无昵称");
        }else {
            TxtUtils.setTextView(feed,bean.getFeeds_text()+"");
        }
        TxtUtils.setTextView(name,bean.getAuthor().getName());
        jz.setUp("LOL",bean.getUrl());
        Glide.with(context).load(bean.getCover()).into(cover);

    }
}
