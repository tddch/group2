package com.example.mengshiyun2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.gushi.R;

public class HomeAdapter extends RecyclerView.Adapter {
    private Context context;

    public HomeAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return 0;
        } else if (position == 1) {
            return 1;
        } else if (position == 2) {
            return 2;
        } else if (position == 3) {
            return 3;
        } else if (position == 4) {
            return 4;
        } else if (position == 5) {
            return 5;
        } else {
            return 6;
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == 0) {
            View view = LayoutInflater.from(context).inflate(R.layout.item1, parent, false);
            return new ViewHolder0(view);
        } else if (viewType == 1) {
            View view = LayoutInflater.from(context).inflate(R.layout.item2, parent, false);
            return new ViewHolder1(view);}
//        } else if (viewType == 2) {
//            View view = LayoutInflater.from(context).inflate(R.layout.item1, parent, false);
//            return new ViewHolder2(view);
//        } else if (viewType == 3) {
//            View view = LayoutInflater.from(context).inflate(R.layout.item1, parent, false);
//            return new ViewHolder3(view);
//        } else if (viewType == 4) {
//            View view = LayoutInflater.from(context).inflate(R.layout.item1, parent, false);
//            return new ViewHolder4(view);
//        } else if (viewType == 5) {
//            View view = LayoutInflater.from(context).inflate(R.layout.item1, parent, false);
//            return new ViewHolder5(view);
//        } else if (viewType == 6) {
//            View view = LayoutInflater.from(context).inflate(R.layout.item1, parent, false);
//            return new ViewHolder6(view);
//        }
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        int itemViewType = getItemViewType(position);
        switch (itemViewType){
            case 0:
                ViewHolder0 holder0= (ViewHolder0) holder;
                Glide.with(context).load(R.drawable.mao).into(holder0.img1);
                break;
            case 1:
                ViewHolder1 holder1= (ViewHolder1) holder;
                Glide.with(context).load(R.drawable.a).into(holder1.img1);
                Glide.with(context).load(R.drawable.b).into(holder1.img2);
                Glide.with(context).load(R.drawable.c).into(holder1.img3);
                Glide.with(context).load(R.drawable.d).into(holder1.img4);
                Glide.with(context).load(R.drawable.e).into(holder1.img5);
                break;
            case 2:
                break;
            case 3:
                break;
            case 4:
                break;
            case 5:
                break;
            case 6:
                break;
        }
    }

    @Override
    public int getItemCount() {
        return 2;
    }

    public static
    class ViewHolder0 extends RecyclerView.ViewHolder {
        public View rootView;
        public ImageView img1;

        public ViewHolder0(View rootView) {
            super(rootView);
            this.rootView = rootView;
            this.img1 = (ImageView) rootView.findViewById(R.id.img1);
        }

    }

    public static
    class ViewHolder1 extends RecyclerView.ViewHolder{
        public View rootView;
        public ImageView img1;
        public ImageView img5;
        public ImageView img2;
        public ImageView img3;
        public ImageView img4;

        public ViewHolder1(View rootView) {
            super(rootView);
            this.rootView = rootView;
            this.img1 = (ImageView) rootView.findViewById(R.id.img1);
            this.img5 = (ImageView) rootView.findViewById(R.id.img5);
            this.img2 = (ImageView) rootView.findViewById(R.id.img2);
            this.img3 = (ImageView) rootView.findViewById(R.id.img3);
            this.img4 = (ImageView) rootView.findViewById(R.id.img4);
        }

    }
}
