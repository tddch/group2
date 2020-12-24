package com.example.bddt.adapter;

import android.content.Context;
import android.widget.TextView;

import com.baidu.mapapi.search.core.PoiInfo;
import com.example.bddt.R;

import java.util.List;

public class SearchItemAdapter extends BaseAdapter<PoiInfo>{
    public SearchItemAdapter(Context context, List<PoiInfo> data) {
        super(context, data);
    }

    @Override
    protected int getLayout() {
        return R.layout.layout_search_item;
    }

    @Override
    protected void bindData(PoiInfo data, VH vh) {
        TextView txtName = (TextView) vh.getViewById(R.id.txt_name);
        txtName.setText(data.name+" "+data.address);
    }
}
