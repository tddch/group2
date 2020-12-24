package com.example.liujian.ui.discover;

import android.content.Context;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.example.liujian.R;
import com.example.liujian.base.BaseAdapter;
import com.example.liujian.bean.DiscoverTabRvBean;

import java.util.List;

/**
 *
 */
public class TabEvAdapter extends BaseAdapter {
    private int position;

    public TabEvAdapter(List mList, Context context) {
        super(mList, context);
    }


    @Override
    protected void bindData(Object data, VH holder, int position, Context context) {
        this.position = position;
        DiscoverTabRvBean.DataBean.ListBean dataBean = (DiscoverTabRvBean.DataBean.ListBean) data;


        if (position % 3 == 0) {
            ImageView iv1_images1 = (ImageView) holder.getViewById(R.id.iv1_images1);
            ImageView iv1_images2 = (ImageView) holder.getViewById(R.id.iv1_images2);
            ImageView iv1_images3 = (ImageView) holder.getViewById(R.id.iv1_images3);
            TextView tv_s1 = (TextView) holder.getViewById(R.id.tv_s1);
            TextView tv_time1 = (TextView) holder.getViewById(R.id.tv_time1);

            Glide.with(context).load(dataBean.getFilePathList().get(0).getFilePath()).apply(new RequestOptions().transform(new RoundedCorners(10))).into(iv1_images1);
            Glide.with(context).load(dataBean.getFilePathList().get(1).getFilePath()).apply(new RequestOptions().transform(new RoundedCorners(10))).into(iv1_images2);
            Glide.with(context).load(dataBean.getFilePathList().get(2).getFilePath()).apply(new RequestOptions().transform(new RoundedCorners(10))).into(iv1_images3);
            tv_s1.setText(dataBean.getTitle());
            tv_time1.setText(dataBean.getCreateTime());
        } else if (position % 3 == 1) {
            ImageView iv2_images4 = (ImageView) holder.getViewById(R.id.iv2_images4);

            TextView tv2_twer1 = (TextView) holder.getViewById(R.id.tv2_twer1);
            TextView tv2_twer2 = (TextView) holder.getViewById(R.id.tv2_twer2);
            if (dataBean.getFilePathList().size() == 1) {

                Glide.with(context).load(dataBean.getFilePathList().get(0).getFilePath()).apply(new RequestOptions().transform(new RoundedCorners(10))).into(iv2_images4);
            }
            tv2_twer1.setText(dataBean.getTitle());
            tv2_twer2.setText(dataBean.getCreateTime());
        } else {
            TextView tv3_s1 = (TextView) holder.getViewById(R.id.tv3_s1);
            TextView tv3_twer3 = (TextView) holder.getViewById(R.id.tv3_twer3);


            tv3_s1.setText(dataBean.getCreateTime());
            tv3_twer3.setText(dataBean.getTitle());


        }

    }

    @Override
    protected int getLayout() {
        if (position % 3 == 0) {
            return R.layout.layout_tabrv_one;
        } else if (position % 3 == 1) {
            return R.layout.layout_tabrv_two;
        } else {
            return R.layout.layout_tabrv_three;
        }
    }
}
