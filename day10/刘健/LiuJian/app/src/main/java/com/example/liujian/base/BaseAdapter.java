package com.example.liujian.base;

import android.content.Context;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

/**
 *
 */
public abstract class BaseAdapter<D> extends RecyclerView.Adapter {
    public List<D> mList;
    public Context context;
    public IListClick click;

    public BaseAdapter(List<D> mList, Context context) {
        this.mList = mList;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(getLayout(), parent, false);
        VH vh = new VH(view);
        vh.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //接口回调的调用
                if(click != null){
                    click.itemClick(vh.getLayoutPosition());
                }
            }
        });
        return vh;
    }


    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        bindData(mList.get(position),(VH)holder,position,context);
    }

    protected abstract void bindData(D data, VH vh, int position, Context context);

    protected  List<D> getData(){
        return mList;
    }


    public void setClick(IListClick click) {
        this.click = click;
    }

    public interface IListClick{
        void itemClick(int position);
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    protected abstract int getLayout();

    public class VH extends RecyclerView.ViewHolder {
        SparseArray views=new SparseArray();
        public VH(@NonNull View itemView) {
            super(itemView);
        }
        public View getViewById(int id){
            View view= (View) views.get(id);
            if(view==null){
                view = itemView.findViewById(id);
                views.append(id,view);
            }
            return view;
        }
    }
}
