package com.example.day03_project.ui.sofa.adapter;

import android.content.Context;
import android.net.Uri;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.VideoView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.day03_project.R;
import com.example.day03_project.base.BaseAdapter;
import com.example.day03_project.model.sofa.data.SofaImgBean;
import com.example.day03_project.model.sofa.data.SofaVideoBean;

import java.util.List;

/**
 *
 */
public class SofaVideoAdapter extends BaseAdapter {

    private VideoView iv_video_url;

    public SofaVideoAdapter(List mList, Context context) {
        super(mList, context);
    }

    @Override
    protected void bindData(Object data, VH vh, int position, Context context) {
        SofaVideoBean.DataBeanX.DataBean dataBean= (SofaVideoBean.DataBeanX.DataBean) data;
        iv_video_url = (VideoView) vh.getViewById(R.id.iv_video_url);
        ImageView iv_user_head = (ImageView) vh.getViewById(R.id.iv_user_head);
        TextView tv_user_name = (TextView) vh.getViewById(R.id.tv_user_name);
        TextView tv_title = (TextView) vh.getViewById(R.id.tv_title);

//        loadCover(iv_img_url,dataBean.getUrl(),context);
        iv_video_url.setMediaController(new MediaController(context));
        Uri uri = Uri.parse(dataBean.getUrl());
        iv_video_url.setVideoURI(uri);
        iv_video_url.start();


        Glide.with(iv_user_head).load(dataBean.getAuthor().getAvatar()).apply(new RequestOptions().circleCrop()).into(iv_user_head);

        tv_title.setText(dataBean.getFeeds_text());
        tv_user_name.setText(dataBean.getAuthor().getName());

    }
    public static void loadCover(ImageView imageView, String url, Context context) {

        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        Glide.with(context)
                .setDefaultRequestOptions(
                        new RequestOptions()
                                .frame(4000000)
                                .centerCrop()
                )
                .load(url)
                .into(imageView);
    }
    @Override
    protected int getLayout() {
        return R.layout.item_sofa_video;
    }
}
