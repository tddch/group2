package com.example.xmu.adapter;


import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.VideoView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.request.RequestOptions;
import com.example.xmu.R;
import com.example.xmu.bean.ShiBean;
import com.example.xmu.bean.TuBean;

import java.util.ArrayList;

public class ShiAdapter extends RecyclerView.Adapter<ShiAdapter.ViewHolder> {
    Context context;
    ArrayList<ShiBean.DataBeanX.DataBean> data;

    public ShiAdapter(Context context, ArrayList<ShiBean.DataBeanX.DataBean> data) {
        this.context = context;
        this.data = data;
    }

    @NonNull
    @Override
    public ShiAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.shi_item, parent, false);
        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull ShiAdapter.ViewHolder holder, int position) {
        ViewHolder viewHolder = holder;
        ShiBean.DataBeanX.DataBean dataBean = data.get(position);
        viewHolder.txt11.setText(dataBean.getAuthor().getName());
        viewHolder.tv11.setText((Integer) dataBean.getFeeds_text());
        Glide.with(context).load(dataBean.getCover()).apply(RequestOptions.bitmapTransform(new CircleCrop())).into(viewHolder.img111);

        viewHolder.video.setVideoURI(Uri.parse(dataBean.getUrl()));
        MediaController mediaController = new MediaController(context);
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewHolder.video.start();
            }
        });
        viewHolder.video.setMediaController(mediaController);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tv11;
        TextView txt11;
        ImageView img111;
        VideoView video;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tv11 = itemView.findViewById(R.id.tv11);
            txt11 = itemView.findViewById(R.id.txt11);
            img111 = itemView.findViewById(R.id.img111);
            video = itemView.findViewById(R.id.video);
        }
    }
}
