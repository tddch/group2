package com.example.mvpbaaaaaaa.utils;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.baidu.mapapi.search.sug.SuggestionResult;
import com.example.mvpbaaaaaaa.R;

import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ToiAdapter extends RecyclerView.Adapter {
    private Context context;
    private  List<SuggestionResult.SuggestionInfo> allSuggestions;

    public void setMyItemOnClick(MyItemOnClick myItemOnClick) {
        this.myItemOnClick = myItemOnClick;
    }

    private MyItemOnClick myItemOnClick;

    public ToiAdapter(Context context, List<SuggestionResult.SuggestionInfo> allSuggestions) {
        this.context = context;
        this.allSuggestions = allSuggestions;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = View.inflate(context, R.layout.item_layout, null);
        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        SuggestionResult.SuggestionInfo suggestionInfo = allSuggestions.get(position);

        String city = suggestionInfo.city;
        String key = suggestionInfo.key;
        String district=  suggestionInfo.district;

        Log.e("TAG",city+"========"+key+"========"+district);
        ViewHolder suggestViewHolder = (ViewHolder) holder;
        suggestViewHolder.sugKey.setText(key);
        suggestViewHolder.sugCity.setText(city+district);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myItemOnClick.ItemOnClick(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return allSuggestions.size();
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
