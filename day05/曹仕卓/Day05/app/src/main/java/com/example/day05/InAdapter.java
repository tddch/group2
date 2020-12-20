package com.example.day05;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class InAdapter extends RecyclerView.Adapter {
    private Context context;

    public InAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == 0) {
            View inflate = LayoutInflater.from(context).inflate(R.layout.activity_item, null);
            return new ViewHolder_item(inflate);
        } else if (viewType == 1) {
            View inflate = LayoutInflater.from(context).inflate(R.layout.activity_item2, null);
            return new ViewHolder_item2(inflate);
        } else if (viewType == 2) {
            View inflate = LayoutInflater.from(context).inflate(R.layout.activity_item3, null);
            return new ViewHolder_item3(inflate);
        }
            View inflate = LayoutInflater.from(context).inflate(R.layout.activity_item4, null);
            return new ViewHolder_item4(inflate);

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
    }

    @Override
    public int getItemCount() {
        return 4;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return 0;
        } else if (position % 2 == 1) {
            return 1;
        } else if (position % 2 == 0) {
            return 2;
        } else {
            return 4;
        }
    }

    public static
    class ViewHolder_item extends RecyclerView.ViewHolder {
        public View rootView;
        public ImageView image1;

        public ViewHolder_item(View rootView) {
            super(rootView);
            this.rootView = rootView;
            this.image1 = (ImageView) rootView.findViewById(R.id.image1);
        }

    }

    public static
    class ViewHolder_item2 extends RecyclerView.ViewHolder {
        public View rootView;
        public ImageView img1;
        public TextView tv1;
        public ImageView img2;
        public TextView tv2;
        public ImageView img3;
        public TextView tv3;
        public ImageView img4;
        public TextView tv4;
        public ImageView img5;
        public TextView tv5;

        public ViewHolder_item2(View rootView) {
            super(rootView);
            this.rootView = rootView;
            this.img1 = (ImageView) rootView.findViewById(R.id.img1);
            this.tv1 = (TextView) rootView.findViewById(R.id.tv1);
            this.img2 = (ImageView) rootView.findViewById(R.id.img2);
            this.tv2 = (TextView) rootView.findViewById(R.id.tv2);
            this.img3 = (ImageView) rootView.findViewById(R.id.img3);
            this.tv3 = (TextView) rootView.findViewById(R.id.tv3);
            this.img4 = (ImageView) rootView.findViewById(R.id.img4);
            this.tv4 = (TextView) rootView.findViewById(R.id.tv4);
            this.img5 = (ImageView) rootView.findViewById(R.id.img5);
            this.tv5 = (TextView) rootView.findViewById(R.id.tv5);
        }

    }

    public static
    class ViewHolder_item3 extends RecyclerView.ViewHolder {
        public View rootView;
        public TextView tvt1;
        public TextView tvt2;
        public ImageView img6;
        public Button btn_an;
        public Button btn_an1;
        public TextView tvt3;

        public ViewHolder_item3(View rootView) {
            super(rootView);
            this.rootView = rootView;
            this.tvt1 = (TextView) rootView.findViewById(R.id.tvt1);
            this.tvt2 = (TextView) rootView.findViewById(R.id.tvt2);
            this.img6 = (ImageView) rootView.findViewById(R.id.img6);
            this.btn_an = (Button) rootView.findViewById(R.id.btn_an);
            this.btn_an1 = (Button) rootView.findViewById(R.id.btn_an1);
            this.tvt3 = (TextView) rootView.findViewById(R.id.tvt3);
        }

    }

    public static
    class ViewHolder_item4 extends RecyclerView.ViewHolder {
        public View rootView;
        public TextView tvti1;
        public TextView tvti2;
        public ImageView img1;
        public TextView tv1;
        public ImageView img2;
        public TextView tv2;
        public ImageView img3;
        public TextView tv3;
        public ImageView img4;
        public TextView tv4;
        public ImageView img5;
        public TextView tv5;
        public ImageView img6;
        public TextView tv6;
        public ImageView img7;
        public TextView tv7;
        public ImageView img8;
        public TextView tv8;
        public ImageView img9;
        public TextView tv9;
        public ImageView img10;
        public TextView tv10;

        public ViewHolder_item4(View rootView) {
            super(rootView);
            this.rootView = rootView;
            this.tvti1 = (TextView) rootView.findViewById(R.id.tvti1);
            this.tvti2 = (TextView) rootView.findViewById(R.id.tvti2);
            this.img1 = (ImageView) rootView.findViewById(R.id.img1);
            this.tv1 = (TextView) rootView.findViewById(R.id.tv1);
            this.img2 = (ImageView) rootView.findViewById(R.id.img2);
            this.tv2 = (TextView) rootView.findViewById(R.id.tv2);
            this.img3 = (ImageView) rootView.findViewById(R.id.img3);
            this.tv3 = (TextView) rootView.findViewById(R.id.tv3);
            this.img4 = (ImageView) rootView.findViewById(R.id.img4);
            this.tv4 = (TextView) rootView.findViewById(R.id.tv4);
            this.img5 = (ImageView) rootView.findViewById(R.id.img5);
            this.tv5 = (TextView) rootView.findViewById(R.id.tv5);
            this.img6 = (ImageView) rootView.findViewById(R.id.img6);
            this.tv6 = (TextView) rootView.findViewById(R.id.tv6);
            this.img7 = (ImageView) rootView.findViewById(R.id.img7);
            this.tv7 = (TextView) rootView.findViewById(R.id.tv7);
            this.img8 = (ImageView) rootView.findViewById(R.id.img8);
            this.tv8 = (TextView) rootView.findViewById(R.id.tv8);
            this.img9 = (ImageView) rootView.findViewById(R.id.img9);
            this.tv9 = (TextView) rootView.findViewById(R.id.tv9);
            this.img10 = (ImageView) rootView.findViewById(R.id.img10);
            this.tv10 = (TextView) rootView.findViewById(R.id.tv10);
        }

    }
}
