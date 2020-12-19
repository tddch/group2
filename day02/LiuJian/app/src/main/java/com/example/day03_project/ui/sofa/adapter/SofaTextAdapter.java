package com.example.day03_project.ui.sofa.adapter;

import android.content.Context;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.day03_project.R;
import com.example.day03_project.base.BaseAdapter;
import com.example.day03_project.model.sofa.data.SofaImgBean;
import com.example.day03_project.model.sofa.data.SofaTextBean;

import java.util.List;

/**
 *
 */
public class SofaTextAdapter extends BaseAdapter {
    public SofaTextAdapter(List mList, Context context) {
        super(mList, context);
    }

    @Override
    protected void bindData(Object data, VH vh, int position, Context context) {
        SofaTextBean.DataBeanX.DataBean dataBean= (SofaTextBean.DataBeanX.DataBean) data;
        ImageView iv_user_head = (ImageView) vh.getViewById(R.id.iv_user_head);
        TextView tv_user_name = (TextView) vh.getViewById(R.id.tv_user_name);
        TextView tv_title = (TextView) vh.getViewById(R.id.tv_title);

        Glide.with(iv_user_head).load(dataBean.getAuthor().getAvatar()).apply(new RequestOptions().circleCrop()).into(iv_user_head);

        tv_title.setText(dataBean.getFeeds_text());
        tv_user_name.setText(dataBean.getAuthor().getName());

    }

    @Override
    protected int getLayout() {
        return R.layout.item_sofa_text;
    }
}
