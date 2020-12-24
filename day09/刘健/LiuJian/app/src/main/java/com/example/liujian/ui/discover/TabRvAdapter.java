package com.example.liujian.ui.discover;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.example.liujian.R;
import com.example.liujian.bean.DiscoverTabRvBean;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 *
 */
public class TabRvAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private ArrayList<DiscoverTabRvBean.DataBean.ListBean> listBeans;
    private Context context;
    private int TYPE_VIEW_ONE = 1;
    private int TYPE_VIEW_TWO = 2;
    private int TYPE_VIEW_THREE = 3;
    private View view;

    public TabRvAdapter(ArrayList<DiscoverTabRvBean.DataBean.ListBean> listBeans, Context context) {
        this.listBeans = listBeans;
        this.context = context;
    }

    @Override
    public int getItemViewType(int position) {
        if (position % 3 == 0) {
            return TYPE_VIEW_ONE;
        } else if (position % 2 == 1) {
            return TYPE_VIEW_TWO;
        } else {
            return TYPE_VIEW_THREE;
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (TYPE_VIEW_ONE == viewType) {
            View view = LayoutInflater.from(context).inflate(R.layout.layout_tabrv_one, parent, false);
            return new ViewHolderOne(view);
        } else if (TYPE_VIEW_TWO == viewType) {
            View view = LayoutInflater.from(context).inflate(R.layout.layout_tabrv_two, parent, false);
            return new ViewHolderTwo(view);
        } else {
            View view = LayoutInflater.from(context).inflate(R.layout.layout_tabrv_three, parent, false);
            return new ViewHolderThree(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        DiscoverTabRvBean.DataBean.ListBean dataBean = listBeans.get(position);
        if(holder instanceof ViewHolderOne){
                Glide.with(context).load(dataBean.getFilePathList().get(0).getFilePath()).apply(new RequestOptions().transform(new RoundedCorners(10))).into(((ViewHolderOne) holder).iv1Images1);
                Glide.with(context).load(dataBean.getFilePathList().get(1).getFilePath()).apply(new RequestOptions().transform(new RoundedCorners(10))).into(((ViewHolderOne) holder).iv1Images2);
                Glide.with(context).load(dataBean.getFilePathList().get(2).getFilePath()).apply(new RequestOptions().transform(new RoundedCorners(10))).into(((ViewHolderOne) holder).iv1Images3);
            ((ViewHolderOne) holder).tvS1.setText(dataBean.getTitle());
            ((ViewHolderOne) holder).tvTime1.setText(dataBean.getCreateTime());
        }else if(holder instanceof ViewHolderTwo){
            if(dataBean.getFilePathList().size()==1){
                Glide.with(context).load(dataBean.getFilePathList().get(0).getFilePath()).apply(new RequestOptions().transform(new RoundedCorners(10))).into(((ViewHolderTwo) holder).iv2Images4);
            }
            ((ViewHolderTwo) holder).tv2Twer1.setText(dataBean.getTitle());
            ((ViewHolderTwo) holder).tv2Twer2.setText(dataBean.getCreateTime());
        }else if(holder instanceof ViewHolderThree){
            ((ViewHolderThree) holder).tv3S1.setText(dataBean.getTitle());
            ((ViewHolderThree) holder).tv3Twer3.setText(dataBean.getCreateTime());
        }
    }

    @Override
    public int getItemCount() {
        return listBeans.size();
    }

    static
    class ViewHolderOne extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_s1)
        TextView tvS1;
        @BindView(R.id.iv1_images1)
        ImageView iv1Images1;
        @BindView(R.id.iv1_images2)
        ImageView iv1Images2;
        @BindView(R.id.iv1_images3)
        ImageView iv1Images3;
        @BindView(R.id.List1)
        LinearLayout List1;
        @BindView(R.id.tv_time1)
        TextView tvTime1;

        ViewHolderOne(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

    static
    class ViewHolderTwo extends RecyclerView.ViewHolder {
        @BindView(R.id.iv2_images4)
        ImageView iv2Images4;
        @BindView(R.id.tv2_twer1)
        TextView tv2Twer1;
        @BindView(R.id.tv2_twer2)
        TextView tv2Twer2;

        ViewHolderTwo(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }


    static
    class ViewHolderThree extends RecyclerView.ViewHolder {
        @BindView(R.id.tv3_s1)
        TextView tv3S1;
        @BindView(R.id.tv3_twer3)
        TextView tv3Twer3;

        ViewHolderThree(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }


}
