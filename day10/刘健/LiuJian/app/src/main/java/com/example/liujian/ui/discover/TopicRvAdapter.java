package com.example.liujian.ui.discover;

import android.content.Context;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.example.liujian.R;
import com.example.liujian.base.BaseAdapter;
import com.example.liujian.bean.DiscoverTopicBean;

import java.util.List;

/**
 *
 */
public class TopicRvAdapter extends BaseAdapter {
    public TopicRvAdapter(List mList, Context context) {
        super(mList, context);
    }

    @Override
    protected void bindData(Object data, VH vh, int position, Context context) {
        DiscoverTopicBean.DataBean dataBean= (DiscoverTopicBean.DataBean) data;
        ImageView iv_topic_image = (ImageView) vh.getViewById(R.id.iv_topic_image);
        TextView tv_title = (TextView) vh.getViewById(R.id.tv_title);
        TextView tv_city = (TextView) vh.getViewById(R.id.tv_city);
        TextView tv_time = (TextView) vh.getViewById(R.id.tv_time);

        tv_city.setText(dataBean.getLocation());
        tv_time.setText(dataBean.getEndTime());
        tv_title.setText(dataBean.getTitle());

        Glide.with(context).load(dataBean.getCover()).apply(new RequestOptions().transform(new RoundedCorners(10))).into(iv_topic_image);

    }

    @Override
    protected int getLayout() {
        return R.layout.layout_disciver_topic;
    }
}
