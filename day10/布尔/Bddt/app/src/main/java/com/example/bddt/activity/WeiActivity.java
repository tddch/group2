package com.example.bddt.activity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.baidu.location.BDAbstractLocationListener;
import com.baidu.location.BDLocation;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.search.route.BikingRouteResult;
import com.baidu.mapapi.search.route.DrivingRouteResult;
import com.baidu.mapapi.search.route.IndoorRouteResult;
import com.baidu.mapapi.search.route.MassTransitRouteResult;
import com.baidu.mapapi.search.route.OnGetRoutePlanResultListener;
import com.baidu.mapapi.search.route.PlanNode;
import com.baidu.mapapi.search.route.RoutePlanSearch;
import com.baidu.mapapi.search.route.TransitRouteResult;
import com.baidu.mapapi.search.route.WalkingRouteLine;
import com.baidu.mapapi.search.route.WalkingRoutePlanOption;
import com.baidu.mapapi.search.route.WalkingRouteResult;
import com.baidu.mapapi.search.sug.OnGetSuggestionResultListener;
import com.baidu.mapapi.search.sug.SuggestionResult;
import com.baidu.mapapi.search.sug.SuggestionSearch;
import com.baidu.mapapi.search.sug.SuggestionSearchOption;
import com.example.bddt.R;
import com.example.bddt.adapter.BaseAdapter;
import com.example.bddt.adapter.SuggestAdapter;
import com.example.bddt.util.WalkingRouteOverlay;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class WeiActivity extends AppCompatActivity implements View.OnClickListener {

    @BindView(R.id.bmapView)
    MapView bmapView;
    //路径规划
    @BindView(R.id.input_start)
    EditText inputStart;
    @BindView(R.id.input_end)
    EditText inputEnd;
    @BindView(R.id.btn_routePlan)
    Button btnRoutePlan;
    @BindView(R.id.recy_nodes)
    RecyclerView recyNodes;
    //百度地图的数据操作
    BaiduMap baiduMap;
    RoutePlanSearch routePlanSearch;
    LocationClient locationClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wei);
        ButterKnife.bind(this);
        initView();
        initMap();
        initRoutePlan();
    }

    private void initView() {
        btnRoutePlan.setOnClickListener(this);

    }

    private void initMap() {
        baiduMap = bmapView.getMap();
        baiduMap.setMyLocationEnabled(true);
        //设置为普通类型的地图
        baiduMap.setMapType(BaiduMap.MAP_TYPE_NORMAL);
    }

    private void initLocation() {
        //定位初始化
        locationClient = new LocationClient(this);

        //通过LocationClientOption设置LocationClient相关参数
        LocationClientOption option = new LocationClientOption();
        option.setOpenGps(true); // 打开gps
        option.setCoorType("bd09ll"); // 设置坐标类型
        option.setScanSpan(1000);

        //设置locationClientOption
        locationClient.setLocOption(option);

        //注册LocationListener监听器
        WeiActivity.MyLocationListener myLocationListener = new MyLocationListener();
        locationClient.registerLocationListener(myLocationListener);
        //开启地图定位图层
        locationClient.start();
    }

    private class MyLocationListener extends BDAbstractLocationListener {

        @Override
        public void onReceiveLocation(BDLocation bdLocation) {
            //mapView 销毁后不在处理新接收的位置
            if (bdLocation == null || bmapView == null) {
                return;
            }
            MyLocationData locData = new MyLocationData.Builder()
                    .accuracy(bdLocation.getRadius())
                    // 此处设置开发者获取到的方向信息，顺时针0-360
                    .direction(bdLocation.getDirection()).latitude(bdLocation.getLatitude())
                    .longitude(bdLocation.getLongitude()).build();
            baiduMap.setMyLocationData(locData);
            //drawCircle(bdLocation.getLatitude(),bdLocation.getLongitude());
        }
    }
    /*******************************路径规划*************************/
    private PlanNode startNode, endNode; //开始和结束的坐标点
    SuggestionSearch suggestionSearch; //地点检索的类
    SuggestAdapter suggestAdapter; //路径规划搜索出来的列表
    List<SuggestionResult.SuggestionInfo> suggestList; //地点检索的结果
    boolean isStart = true; //当前处理的是否是起点
    LatLng startLatLng; //起点的经纬度
    private void initRoutePlan() {
        suggestionSearch = SuggestionSearch.newInstance();
        suggestList = new ArrayList<>();
        suggestAdapter = new SuggestAdapter(this, suggestList);
        recyNodes.setLayoutManager(new LinearLayoutManager(this));
        recyNodes.setAdapter(suggestAdapter);
        //设置监听地点检索
        suggestionSearch.setOnGetSuggestionResultListener(suggestionResultListener);

        inputStart.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    isStart = true;
                    recyNodes.setVisibility(View.VISIBLE);
                }
            }
        });
        //监听起点输入框的变化
        inputStart.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                //起点输入改变以后获取对应的起点数据
                SuggestionSearchOption option = new SuggestionSearchOption();
                option.city("北京");
                option.citylimit(true);
                option.keyword(s.toString());
                suggestionSearch.requestSuggestion(option);
            }
        });
        //监听终点输入框的光标
        inputEnd.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    isStart = false;
                    recyNodes.setVisibility(View.VISIBLE);
                }
            }
        });
        //监听终点输入框的输入
        inputEnd.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                //获取终点框对应的数据
                SuggestionSearchOption option = new SuggestionSearchOption();
                option.city("北京");
                option.citylimit(true);
                option.keyword(s.toString());
                suggestionSearch.requestSuggestion(option);
            }
        });


        routePlanSearch = RoutePlanSearch.newInstance();
        routePlanSearch.setOnGetRoutePlanResultListener(routePlanResultListener);

        suggestAdapter.addListClick(new BaseAdapter.IListClick() {
            @Override
            public void itemClick(int pos) {
                SuggestionResult.SuggestionInfo suggestionInfo = suggestList.get(pos);
                if (isStart) {
                    inputStart.setText(suggestionInfo.getKey());
                    startLatLng = suggestionInfo.getPt();
                } else {
                    inputEnd.setText(suggestionInfo.getKey());
                }
                recyNodes.setVisibility(View.GONE);
            }
        });
    }

    /**
     * 地点检索监听
     */
    OnGetSuggestionResultListener suggestionResultListener = new OnGetSuggestionResultListener() {
        @Override
        public void onGetSuggestionResult(SuggestionResult suggestionResult) {
            //地点检索结果
            suggestList.clear();
            if (suggestionResult.getAllSuggestions() != null && suggestionResult.getAllSuggestions().size() > 0) {
                suggestList.addAll(suggestionResult.getAllSuggestions());
                suggestAdapter.notifyDataSetChanged();
            }
        }
    };

    /**
     * 路径搜索的监听
     */
    final OnGetRoutePlanResultListener routePlanResultListener = new OnGetRoutePlanResultListener() {
        @Override
        public void onGetWalkingRouteResult(WalkingRouteResult walkingRouteResult) {
            Log.i(TAG, "onGetWalkingRouteResult");

            //创建一个路径规划的类
            WalkingRouteOverlay walkingRouteOverlay = new WalkingRouteOverlay(baiduMap);
            //判断当前查找出来的路线
            if (walkingRouteResult.getRouteLines() != null && walkingRouteResult.getRouteLines().size() > 0) {
                WalkingRouteLine walkingRouteLine = walkingRouteResult.getRouteLines().get(0);
                walkingRouteOverlay.setData(walkingRouteLine);
                walkingRouteOverlay.addToMap();
                //当前的定位移动到开始点并放大地图
                MapStatusUpdate status = MapStatusUpdateFactory.newLatLngZoom(startLatLng, 16);
                baiduMap.setMapStatus(status);
            } else {
                Toast.makeText(WeiActivity.this, "未搜索到相关路径", Toast.LENGTH_SHORT).show();
            }
        }

        @Override
        public void onGetTransitRouteResult(TransitRouteResult transitRouteResult) {
            Log.i(TAG, "onGetTransitRouteResult");
        }

        @Override
        public void onGetMassTransitRouteResult(MassTransitRouteResult massTransitRouteResult) {
            Log.i(TAG, "onGetMassTransitRouteResult");
        }

        @Override
        public void onGetDrivingRouteResult(DrivingRouteResult drivingRouteResult) {
            Log.i(TAG, "onGetDrivingRouteResult");
        }

        @Override
        public void onGetIndoorRouteResult(IndoorRouteResult indoorRouteResult) {
            Log.i(TAG, "onGetIndoorRouteResult");
        }

        @Override
        public void onGetBikingRouteResult(BikingRouteResult bikingRouteResult) {
            Log.i(TAG, "onGetBikingRouteResult");
        }
    };

    private void searchRoute() {
        String startName, endName;
        startName = inputStart.getText().toString();
        endName = inputEnd.getText().toString();
        if (TextUtils.isEmpty(startName) || TextUtils.isEmpty(endName)) {
            Toast.makeText(this, "请输入正确起点和终点", Toast.LENGTH_SHORT).show();
        } else {
            startNode = PlanNode.withCityNameAndPlaceName("北京", startName);
            endNode = PlanNode.withCityNameAndPlaceName("北京", endName);
            WalkingRoutePlanOption option = new WalkingRoutePlanOption();
            option.from(startNode);
            option.to(endNode);
            //搜索路径
            routePlanSearch.walkingSearch(option);
        }
    }

    private static final String TAG = "WeiActivity";

    @Override
    public void onClick(View v) {

    }
}