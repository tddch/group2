package com.example.mvpbaaaaaaa;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.MapPoi;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.search.route.BikingRouteResult;
import com.baidu.mapapi.search.route.DrivingRouteResult;
import com.baidu.mapapi.search.route.IndoorRouteResult;
import com.baidu.mapapi.search.route.MassTransitRouteResult;
import com.baidu.mapapi.search.route.OnGetRoutePlanResultListener;
import com.baidu.mapapi.search.route.PlanNode;
import com.baidu.mapapi.search.route.RoutePlanSearch;
import com.baidu.mapapi.search.route.TransitRouteResult;
import com.baidu.mapapi.search.route.WalkingRoutePlanOption;
import com.baidu.mapapi.search.route.WalkingRouteResult;
import com.baidu.mapapi.search.sug.OnGetSuggestionResultListener;
import com.baidu.mapapi.search.sug.SuggestionResult;
import com.baidu.mapapi.search.sug.SuggestionSearch;
import com.baidu.mapapi.search.sug.SuggestionSearchOption;
import com.example.mvpbaaaaaaa.utils.NodeUtils;
import com.example.mvpbaaaaaaa.utils.WalkingRouteOverlay;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class WalkingActivity2 extends AppCompatActivity implements BaiduMap.OnMapClickListener, OnGetRoutePlanResultListener, TextWatcher, OnGetSuggestionResultListener {

    @BindView(R.id.st_city)
    EditText stCity;
    @BindView(R.id.st_node)
    AutoCompleteTextView stNode;
    @BindView(R.id.input_search)
    LinearLayout inputSearch;
    @BindView(R.id.ed_city)
    EditText edCity;
    @BindView(R.id.ed_node)
    AutoCompleteTextView edNode;
    @BindView(R.id.drive)
    Button drive;
    @BindView(R.id.map)
    MapView map;
    @BindView(R.id.customicon)
    Button customicon;
    @BindView(R.id.pre)
    Button pre;
    @BindView(R.id.next)
    Button next;
    private RoutePlanSearch mSearch;
    private BaiduMap mBaidumap;
    private NodeUtils mNodeUtils;
    private SuggestionSearch mSuggestionSearch = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_walking_route);
        ButterKnife.bind(this);
        mSearch = RoutePlanSearch.newInstance();
        // 初始化地图
        map = (MapView) findViewById(R.id.map);
        mBaidumap = map.getMap();
        // 初始化建议搜索模块，注册建议搜索事件监听
        mSuggestionSearch = SuggestionSearch.newInstance();
        mSuggestionSearch.setOnGetSuggestionResultListener(this);

        pre = (Button) findViewById(R.id.pre);
        next = (Button) findViewById(R.id.next);
        pre.setVisibility(View.INVISIBLE);
        next.setVisibility(View.INVISIBLE);

        // 地图点击事件处理
        mBaidumap.setOnMapClickListener(this);

        // 初始化搜索模块，注册事件监听
        mSearch = RoutePlanSearch.newInstance();
        mSearch.setOnGetRoutePlanResultListener(this);
        mNodeUtils = new NodeUtils(this, mBaidumap);
        stNode.addTextChangedListener(this);

    }

    @OnClick({R.id.drive, R.id.customicon, R.id.pre, R.id.next})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.drive:
                PlanNode startNode = PlanNode.withCityNameAndPlaceName(stCity.getText().toString().trim(),
                        stNode.getText().toString().trim());
                // 终点参数
                PlanNode endNode = PlanNode.withCityNameAndPlaceName(edCity.getText().toString().trim(),
                        edNode.getText().toString().trim());
                // 实际使用中请对起点终点城市进行正确的设定
                mSearch.walkingSearch((new WalkingRoutePlanOption())
                        .from(startNode) // 起点
                        .to(endNode)); // 终点
                break;
            case R.id.customicon:
                break;
            case R.id.pre:
                break;
            case R.id.next:
                break;
        }
    }

    @Override
    public void onMapClick(LatLng latLng) {
        mBaidumap
                .hideInfoWindow();
    }

    @Override
    public void onMapPoiClick(MapPoi mapPoi) {

    }
    @Override
    protected void onPause() {
        super.onPause();
        map.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        map.onResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // 释放检索对象
        if (mSearch != null) {
            mSearch.destroy();
        }
        mBaidumap.clear();
        map.onDestroy();
    }

    @Override
    public void onGetWalkingRouteResult(WalkingRouteResult walkingRouteResult) {
        //创建WalkingRouteOverlay实例
        WalkingRouteOverlay overlay = new WalkingRouteOverlay(mBaidumap);
    if(walkingRouteResult.getRouteLines()!=null){
        if (walkingRouteResult.getRouteLines().size() > 0) {
            //获取路径规划数据,(以返回的第一条数据为例)
            //为WalkingRouteOverlay实例设置路径数据
            overlay.setData(walkingRouteResult.getRouteLines().get(0));
            //在地图上绘制WalkingRouteOverlay
            overlay.addToMap();
        }
    }else {
        Toast.makeText(this, "请输入详细地址", Toast.LENGTH_SHORT).show();
    }


}

    @Override
    public void onGetTransitRouteResult(TransitRouteResult transitRouteResult) {

    }

    @Override
    public void onGetMassTransitRouteResult(MassTransitRouteResult massTransitRouteResult) {

    }

    @Override
    public void onGetDrivingRouteResult(DrivingRouteResult drivingRouteResult) {

    }

    @Override
    public void onGetIndoorRouteResult(IndoorRouteResult indoorRouteResult) {

    }

    @Override
    public void onGetBikingRouteResult(BikingRouteResult bikingRouteResult) {

    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        if (charSequence.length() <= 0) {
            return;
        }

        // 使用建议搜索服务获取建议列表，结果在onSuggestionResult()中更新
        mSuggestionSearch.requestSuggestion((new SuggestionSearchOption())
                .keyword(charSequence.toString()) // 关键字
                .city(stNode.getText().toString())); // 城市
    }


    @Override
    public void afterTextChanged(Editable editable) {

    }

    @Override
    public void onGetSuggestionResult(SuggestionResult suggestionResult) {
        List<SuggestionResult.SuggestionInfo> allSuggestions = suggestionResult.getAllSuggestions();
        if(allSuggestions!=null&&allSuggestions.size()>0) {

        }
    }
}