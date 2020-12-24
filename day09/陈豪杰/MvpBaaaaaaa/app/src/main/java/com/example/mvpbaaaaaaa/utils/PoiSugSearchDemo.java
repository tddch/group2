package com.example.mvpbaaaaaaa.utils;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import androidx.appcompat.app.AppCompatActivity;

import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.search.sug.OnGetSuggestionResultListener;
import com.baidu.mapapi.search.sug.SuggestionResult;
import com.baidu.mapapi.search.sug.SuggestionSearch;
import com.baidu.mapapi.search.sug.SuggestionSearchOption;
import com.example.mvpbaaaaaaa.R;
import com.example.mvpbaaaaaaa.app.App;
import com.example.mvpbaaaaaaa.mvp.model.bean.Lat;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.greenrobot.eventbus.EventBus;

/**
 * 介绍地点检索输入提示
 */
public class PoiSugSearchDemo extends AppCompatActivity implements OnGetSuggestionResultListener {

    private SuggestionSearch mSuggestionSearch = null;

    // 搜索关键字输入窗口
    private EditText mEditCity = null;
    private AutoCompleteTextView mKeyWordsView = null;
    private RecyclerView mSugListView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_poisugsearch);

        // 初始化建议搜索模块，注册建议搜索事件监听
        mSuggestionSearch = SuggestionSearch.newInstance();
        mSuggestionSearch.setOnGetSuggestionResultListener(this);

        // 初始化view
        mEditCity = (EditText) findViewById(R.id.city);
        mSugListView = (RecyclerView) findViewById(R.id.sug_list);
        mKeyWordsView = (AutoCompleteTextView) findViewById(R.id.searchkey);
        mKeyWordsView.setThreshold(1);

        // 当输入关键字变化时，动态更新建议列表
        mKeyWordsView.addTextChangedListener(new TextWatcher() {
            @Override
            public void afterTextChanged(Editable arg0) {

            }

            @Override
            public void beforeTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {

            }

            @Override
            public void onTextChanged(CharSequence cs, int arg1, int arg2, int arg3) {
                if (cs.length() <= 0) {
                    return;
                }

                // 使用建议搜索服务获取建议列表，结果在onSuggestionResult()中更新
                mSuggestionSearch.requestSuggestion((new SuggestionSearchOption())
                        .keyword(cs.toString()) // 关键字
                        .city(mEditCity.getText().toString())); // 城市
            }
        });
    }


    /**
     * 获取在线建议搜索结果，得到requestSuggestion返回的搜索结果
     *
     * @param suggestionResult    Sug检索结果
     */
    @Override
    public void onGetSuggestionResult(SuggestionResult suggestionResult) {

            //处理sug检索结果
            List<SuggestionResult.SuggestionInfo> allSuggestions = suggestionResult.getAllSuggestions();
            if(allSuggestions!=null&&allSuggestions.size()>0) {
                mSugListView.setLayoutManager(new LinearLayoutManager(PoiSugSearchDemo.this));
                mSugListView.addItemDecoration(new DividerItemDecoration(PoiSugSearchDemo.this, DividerItemDecoration.VERTICAL));
                ToiAdapter actRlAdapter = new ToiAdapter(PoiSugSearchDemo.this, allSuggestions);
                mSugListView.setAdapter(actRlAdapter);
                actRlAdapter.setMyItemOnClick(new MyItemOnClick() {
                    @Override
                    public void ItemOnClick(int position) {
                        SuggestionResult.SuggestionInfo suggestionInfo = allSuggestions.get(position);
                        LatLng pt = suggestionInfo.pt;
                        Intent intent = new Intent(PoiSugSearchDemo.this, MarkerDemo.class);
//                        EventBus.getDefault().post(new Lat(pt));
                        Lat lat = new Lat(pt);
                        intent.putExtra("aaa",lat.getLatLng()+"");
                        Log.e("tag",pt+"ddddddddd");
                        startActivity(intent);
                    }
                });
            }

    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        mSuggestionSearch.destroy();
    }
}
