package com.example.chuanzkt.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.example.chuanzkt.R;
import com.youth.banner.Banner;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {
    private Banner mBanner;
    List<Integer> draw = new ArrayList<>();
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.home, null);
        initView(inflate);
        return inflate;
    }

    private void initView(@NonNull final View itemView) {
        mBanner = (Banner) itemView.findViewById(R.id.banner);
        draw.add(R.drawable.guide_page_one);
        draw.add(R.drawable.guide_page_two);
        draw.add(R.drawable.guide_page_three);
        mBanner.setImages(draw).setImageLoader(new ImageLoader() {
            @Override
            public void displayImage(Context context, Object path, ImageView imageView) {
                Glide.with(context).load(path).into(imageView);
            }
        }).start();
    }
}
