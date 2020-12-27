package com.example.liujian.bean;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Checkable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.liujian.R;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 *
 */
public class SuggestTabAdapter extends RecyclerView.Adapter<SuggestTabAdapter.ViewHolder> {
    private List<HashMap<String, Object>> suggest;
    private Context context;
    private String[] mFrom;
    private OnItemClickListener onItemClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public SuggestTabAdapter(List<HashMap<String, Object>> suggest, Context context, String[] strings) {
        this.suggest = suggest;
        this.context = context;
        mFrom = strings;
    }

    public interface OnItemClickListener{
        void onItemClick(int position);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.item_layout, parent, false);
        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Map dataSet = suggest.get(position);
        if (dataSet == null) {
            return;
        }
        final String[] from = mFrom;
        for (int i = 0; i < mFrom.length; i++) {
            final Object data = dataSet.get(from[i]);
            String text = data == null ? "" : data.toString();
            if (text == null) {
                text = "";
            }
            if(i==0){
                holder.sugKey.setText(text);
            }
            if(i==1){
                holder.sugCity.setText(text);
            }
            if(i==2){
                holder.sugDis.setText(text);
            }

        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(onItemClickListener!=null){
                    onItemClickListener.onItemClick(position);
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return suggest.size();
    }

    static
    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.sug_key)
        TextView sugKey;
        @BindView(R.id.sug_city)
        TextView sugCity;
        @BindView(R.id.sug_dis)
        TextView sugDis;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
