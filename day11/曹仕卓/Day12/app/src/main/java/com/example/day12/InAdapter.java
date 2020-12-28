package com.example.day12;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class InAdapter extends RecyclerView.Adapter {
    private Context context;

    public InAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == 1) {
            View inflate = LayoutInflater.from(context).inflate(R.layout.activity_item1, null);
            return new ViewHolder_item1(inflate);
        } else if (viewType == 2) {
            View inflate = LayoutInflater.from(context).inflate(R.layout.activity_item2, null);
            return new ViewHolder_item2(inflate);
        } else if (viewType == 3) {
            View inflate = LayoutInflater.from(context).inflate(R.layout.activity_item3, null);
            return new ViewHolder_item3(inflate);
        } else if (viewType == 4) {
            View inflate = LayoutInflater.from(context).inflate(R.layout.activity_item4, null);
            return new ViewHolder_item4(inflate);
        } else if (viewType == 5) {
            View inflate = LayoutInflater.from(context).inflate(R.layout.activity_item5, null);
            return new ViewHolder_item5(inflate);
        } else if (viewType == 6) {
            View inflate = LayoutInflater.from(context).inflate(R.layout.activity_item6, null);
            return new ViewHolder_item6(inflate);
        }
        View inflate = LayoutInflater.from(context).inflate(R.layout.activity_item7, null);
        return new ViewHolder_item7(inflate);

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 7;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return 1;
        } else if (position == 1) {
            return 2;
        } else if (position == 2) {
            return 3;
        } else if (position == 3) {
            return 4;
        } else if (position == 4) {
            return 5;
        } else if (position == 5) {
            return 6;
        } else {
            return 7;
        }
    }

    public static
    class ViewHolder_item1 extends RecyclerView.ViewHolder {
        public View rootView;
        public ImageView item1_image;

        public ViewHolder_item1(View rootView) {
            super(rootView);
            this.rootView = rootView;
            this.item1_image = (ImageView) rootView.findViewById(R.id.item1_image);
        }

    }

    public static
    class ViewHolder_item2 extends RecyclerView.ViewHolder {
        public View rootView;
        public TextView tv1;
        public ImageView item2_image;
        public ImageView item2_img;

        public ViewHolder_item2(View rootView) {
            super(rootView);
            this.rootView = rootView;
            this.tv1 = (TextView) rootView.findViewById(R.id.tv1);
            this.item2_image = (ImageView) rootView.findViewById(R.id.item2_image);
            this.item2_img = (ImageView) rootView.findViewById(R.id.item2_img);
        }

    }


    public static
    class ViewHolder_item3 extends RecyclerView.ViewHolder {
        public View rootView;
        public TextView tv2;
        public ImageView item3_image;
        public ImageView item3_img;

        public ViewHolder_item3(View rootView) {
            super(rootView);
            this.rootView = rootView;
            this.tv2 = (TextView) rootView.findViewById(R.id.tv2);
            this.item3_image = (ImageView) rootView.findViewById(R.id.item3_image);
            this.item3_img = (ImageView) rootView.findViewById(R.id.item3_img);
        }

    }

    public static
    class ViewHolder_item4 extends RecyclerView.ViewHolder {
        public View rootView;
        public TextView tv3;
        public TextView tv4;
        public ImageView item4_image;
        public ImageView item4_img;
        public ImageView item4_iv;

        public ViewHolder_item4(View rootView) {
            super(rootView);
            this.rootView = rootView;
            this.tv3 = (TextView) rootView.findViewById(R.id.tv3);
            this.tv4 = (TextView) rootView.findViewById(R.id.tv4);
            this.item4_image = (ImageView) rootView.findViewById(R.id.item4_image);
            this.item4_img = (ImageView) rootView.findViewById(R.id.item4_img);
            this.item4_iv = (ImageView) rootView.findViewById(R.id.item4_iv);
        }

    }

    public static
    class ViewHolder_item5 extends RecyclerView.ViewHolder {
        public View rootView;
        public TextView tv5;
        public TextView tv4;
        public ImageView item5_in;

        public ViewHolder_item5(View rootView) {
            super(rootView);
            this.rootView = rootView;
            this.tv5 = (TextView) rootView.findViewById(R.id.tv5);
            this.tv4 = (TextView) rootView.findViewById(R.id.tv4);
            this.item5_in = (ImageView) rootView.findViewById(R.id.item5_in);
        }

    }

    public static
    class ViewHolder_item6 extends RecyclerView.ViewHolder {
        public View rootView;
        public TextView tv7;
        public ImageView item6_in;

        public ViewHolder_item6(View rootView) {
            super(rootView);
            this.rootView = rootView;
            this.tv7 = (TextView) rootView.findViewById(R.id.tv7);
            this.item6_in = (ImageView) rootView.findViewById(R.id.item6_in);
        }

    }

    public static
    class ViewHolder_item7 extends RecyclerView.ViewHolder {
        public View rootView;
        public ImageView item7_in;

        public ViewHolder_item7(View rootView) {
            super(rootView);
            this.rootView = rootView;
            this.item7_in = (ImageView) rootView.findViewById(R.id.item7_in);
        }

    }
}
