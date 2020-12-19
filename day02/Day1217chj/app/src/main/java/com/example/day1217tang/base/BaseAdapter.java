package com.example.day1217tang.base;

import android.content.Context;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public abstract class BaseAdapter<D> extends RecyclerView.Adapter {

    Context context;
    List<D> mList;
    ItemLisOnClick itemLisOnClick;

    public BaseAdapter(Context context, List<D> mList) {
        this.context = context;
        this.mList = mList;
    }

    public interface ItemLisOnClick {
        void itemOnClickLis(int position);
    }

    public void setOnClickItemLister(ItemLisOnClick itemLisOnClick) {
        this.itemLisOnClick = itemLisOnClick;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = View.inflate(context, getLayout(), null);
        VH vh = new VH(inflate);
        vh.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               if(itemLisOnClick!=null){
                   itemLisOnClick.itemOnClickLis(vh.getLayoutPosition());
               }
            }
        });
        return vh;
    }

    protected abstract int getLayout();

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        bindData(mList.get(position), (VH) holder);
    }

    protected abstract void bindData(D list, VH vh);

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class VH extends RecyclerView.ViewHolder {
        SparseArray views = new SparseArray();

        public VH(@NonNull View itemView) {
            super(itemView);
        }

        public View findViewByIdMy(int id) {
            View view = (View) views.get(id);
            if(view == null){
                view = itemView.findViewById(id);
                views.append(id,view);
            }
            return view;
        }
    }
}
