package com.example.mvpbaaaaaaa.ui;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.baidu.mapapi.search.sug.OnGetSuggestionResultListener;
import com.baidu.mapapi.search.sug.SuggestionResult;
import com.baidu.mapapi.search.sug.SuggestionSearch;
import com.baidu.mapapi.search.sug.SuggestionSearchOption;
import com.example.mvpbaaaaaaa.R;
import com.example.mvpbaaaaaaa.app.App;
import com.example.mvpbaaaaaaa.base.BaseFragment;
import com.example.mvpbaaaaaaa.base.BasePresenter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;

public class PoiSugSearchFragment extends BaseFragment implements OnGetSuggestionResultListener {
    @BindView(R.id.city)
    EditText mEditCity;
    @BindView(R.id.searchkey)
    AutoCompleteTextView mKeyWordsView;
    @BindView(R.id.sug_list)
    ListView mSugListView;
    private SuggestionSearch mSuggestionSearch = null;

    // 搜索关键字输入窗口

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @Override
    protected void init() {
        // 初始化建议搜索模块，注册建议搜索事件监听
        mSuggestionSearch = SuggestionSearch.newInstance();
        mSuggestionSearch.setOnGetSuggestionResultListener(this);


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

    @Override
    protected int getLayoutId() {
        return R.layout.activity_poisugsearch;
    }
    /**
     * 获取在线建议搜索结果，得到requestSuggestion返回的搜索结果
     *
     * @param suggestionResult    Sug检索结果
     */
    @Override
    public void onGetSuggestionResult(SuggestionResult suggestionResult) {
        if (suggestionResult == null || suggestionResult.getAllSuggestions() == null) {
            return;
        }

        List<HashMap<String, String>> suggest = new ArrayList<>();
        for (SuggestionResult.SuggestionInfo info : suggestionResult.getAllSuggestions()) {
            if (info.getKey() != null && info.getDistrict() != null && info.getCity() != null) {
                HashMap<String, String> map = new HashMap<>();
                map.put("key",info.getKey());
                map.put("city",info.getCity());
                map.put("dis",info.getDistrict());
                suggest.add(map);
            }
        }

        SimpleAdapter simpleAdapter = new SimpleAdapter(App.context().getApplicationContext(),
                suggest,
                R.layout.item_layout,
                new String[]{"key", "city","dis"},
                new int[]{R.id.sug_key, R.id.sug_city, R.id.sug_dis});

        mSugListView.setAdapter(simpleAdapter);
        simpleAdapter.notifyDataSetChanged();
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        mSuggestionSearch.destroy();
    }
}
