package com.example.day03_project.ui.sofa.adapter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.day03_project.R;
import com.example.day03_project.base.BaseAdapter;
import com.example.day03_project.model.sofa.data.SofaImgBean;

import java.math.RoundingMode;
import java.util.List;

/**
 *
 */
public class SofaImgAdapter extends BaseAdapter {
    public SofaImgAdapter(List mList, Context context) {
        super(mList, context);
    }

    @Override
    protected void bindData(Object data, VH vh, int position, Context context) {
        SofaImgBean.DataBeanX.DataBean dataBean= (SofaImgBean.DataBeanX.DataBean) data;
        ImageView iv_img_url = (ImageView) vh.getViewById(R.id.iv_img_url);
        ImageView iv_user_head = (ImageView) vh.getViewById(R.id.iv_user_head);
        TextView tv_user_name = (TextView) vh.getViewById(R.id.tv_user_name);
        TextView tv_title = (TextView) vh.getViewById(R.id.tv_title);

        Glide.with(iv_user_head).load(dataBean.getAuthor().getAvatar()).apply(new RequestOptions().circleCrop()).into(iv_user_head);
        Glide.with(iv_img_url).load(dataBean.getCover()).into(iv_img_url);

        tv_title.setText(dataBean.getFeeds_text());
        tv_user_name.setText(dataBean.getAuthor().getName());

    }

    @Override
    protected int getLayout() {
        return R.layout.item_sofa_img;
    }
}
