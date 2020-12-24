package com.example.liujian.ui.discover.baidu;

import android.content.Context;
import android.widget.TextView;

import com.baidu.mapapi.search.core.PoiInfo;
import com.example.liujian.R;
import com.example.liujian.base.BaseAdapter;

import java.util.List;

public class SearchItemAdapter extends BaseAdapter<PoiInfo> {

    public SearchItemAdapter(Context context, List<PoiInfo> list){
        super(list, context);
    }

    @Override
    protected void bindData(PoiInfo poiInfo, VH vh, int position, Context context) {
        TextView txtName = (TextView) vh.getViewById(R.id.txt_name);
        txtName.setText(poiInfo.name+" "+poiInfo.address);
    }

    @Override
    protected int getLayout() {
        return R.layout.layout_search_item;
    }


}