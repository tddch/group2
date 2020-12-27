package com.example.liujian.baidu;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.baidu.location.BDAbstractLocationListener;
import com.baidu.location.BDLocation;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.animation.Animation;
import com.baidu.mapapi.animation.RotateAnimation;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.CircleOptions;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.Marker;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.map.Overlay;
import com.baidu.mapapi.map.Stroke;
import com.baidu.mapapi.map.UiSettings;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.overlayutil.WalkingRouteOverlay;
import com.baidu.mapapi.search.core.PoiInfo;
import com.baidu.mapapi.search.poi.OnGetPoiSearchResultListener;
import com.baidu.mapapi.search.poi.PoiCitySearchOption;
import com.baidu.mapapi.search.poi.PoiDetailResult;
import com.baidu.mapapi.search.poi.PoiDetailSearchResult;
import com.baidu.mapapi.search.poi.PoiIndoorResult;
import com.baidu.mapapi.search.poi.PoiResult;
import com.baidu.mapapi.search.poi.PoiSearch;
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
import com.example.liujian.R;
import com.example.liujian.base.BaseAdapter;
import com.example.liujian.ui.discover.baidu.SearchItemAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class NavigationActivity extends AppCompatActivity {
    @BindView(R.id.bmapView)
    MapView mMapView;
    @BindView(R.id.et_search)
    EditText etSearch;
    @BindView(R.id.rv)
    RecyclerView rv;
    @BindView(R.id.et_start)
    EditText etStart;
    @BindView(R.id.et_end)
    EditText etEnd;
    @BindView(R.id.rv_start)
    RecyclerView rvStart;
    @BindView(R.id.btn_start)
    Button btnStart;
    @BindView(R.id.btn_search)
    Button btnSearch;
    private BaiduMap baiduMap;
    private LocationClient mLocationClient;
    /********************检索********************/
    SearchItemAdapter searchItemAdapter;
    List<PoiInfo> poiList;
    private PoiSearch poiSearch;
    private BitmapDescriptor bitmapA = BitmapDescriptorFactory.fromResource(R.mipmap.city_img);

    /********************检索***********************/

    RoutePlanSearch routePlanSearch;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation);
        ButterKnife.bind(this);

        initMap();
        initLocation();
        initPoi();

        initRoutePlan();
    }

    private void initLocation() {
        //定位初始化
        mLocationClient = new LocationClient(this);

        //通过LocationClientOption设置LocationClient相关参数
        LocationClientOption option = new LocationClientOption();
        option.setOpenGps(true); // 打开gps
        option.setCoorType("bd09ll"); // 设置坐标类型
        option.setScanSpan(1000);

        //设置locationClientOption
        mLocationClient.setLocOption(option);

        //注册LocationListener监听器
        MyLocationListener myLocationListener = new MyLocationListener();
        mLocationClient.registerLocationListener(myLocationListener);
        //开启地图定位图层
        mLocationClient.start();


    }

    private void initMap() {
        baiduMap = mMapView.getMap();
        baiduMap.setMyLocationEnabled(true);
        //设置为普通类型的地图
        baiduMap.setMapType(BaiduMap.MAP_TYPE_NORMAL);
        baiduMap.setMyLocationEnabled(true);
        mMapView.showZoomControls(true);
//        setCustomTrafficColor(String severeCongestion,String congestion,String slow,String smooth)

        //实例化UiSettings类对象
        UiSettings mUiSettings = baiduMap.getUiSettings();
//通过设置enable为true或false 选择是否显示指南针
        mUiSettings.setCompassEnabled(true);
        mUiSettings.setOverlookingGesturesEnabled(true);
    }

    private void initPoi() {
        poiList = new ArrayList<>();
        searchItemAdapter = new SearchItemAdapter(this, poiList);
        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.setAdapter(searchItemAdapter);

        poiSearch = PoiSearch.newInstance();
        poiSearch.setOnGetPoiSearchResultListener(poiSearchResultListener);

        searchItemAdapter.setClick(new BaseAdapter.IListClick() {
            @Override
            public void itemClick(int pos) {
                //点击条目进行定位
                PoiInfo poiInfo = poiList.get(pos);
                MapStatusUpdate status = MapStatusUpdateFactory.newLatLng(poiInfo.location);
                baiduMap.setMapStatus(status);
                drawCircle(poiInfo.location.latitude, poiInfo.location.longitude);
                addMark(poiInfo.location.latitude, poiInfo.location.longitude);
            }
        });
    }

    /**
     * 搜索的监听
     */
    OnGetPoiSearchResultListener poiSearchResultListener = new OnGetPoiSearchResultListener() {
        @Override
        public void onGetPoiResult(PoiResult poiResult) {
            Log.i("TAG", "onGetPoiResult");
            poiList.clear();
            if (poiResult.getAllPoi() != null && poiResult.getAllPoi().size() > 0) {
                poiList.addAll(poiResult.getAllPoi());
                searchItemAdapter.notifyDataSetChanged();
            }
        }

        @Override
        public void onGetPoiDetailResult(PoiDetailResult poiDetailResult) {
            Log.i("TAG", "onGetPoiDetailResult");
        }

        @Override
        public void onGetPoiDetailResult(PoiDetailSearchResult poiDetailSearchResult) {
            Log.i("TAG", "onGetPoiDetailResult");
        }

        @Override
        public void onGetPoiIndoorResult(PoiIndoorResult poiIndoorResult) {
            Log.i("TAG", "onGetPoiIndoorResult");
        }
    };


    /*******************************路径规划*************************/
    private PlanNode startNode, endNode; //开始和结束的坐标点
    SuggestionSearch suggestionSearch; //地点检索的类
    SuggestAdapter suggestAdapter; //路径规划搜索出来的列表
    List<SuggestionResult.SuggestionInfo> suggestList; //地点检索的结果
    boolean isStart = true; //当前处理的是否是起点
    LatLng startLatLng; //起点的经纬度


    //初始化路径规划
    private void initRoutePlan() {

        suggestionSearch = SuggestionSearch.newInstance();
        suggestList = new ArrayList<>();
        suggestAdapter = new SuggestAdapter(this, suggestList);
        rvStart.setLayoutManager(new LinearLayoutManager(this));
        rvStart.setAdapter(suggestAdapter);
        //设置监听地点检索
        suggestionSearch.setOnGetSuggestionResultListener(suggestionResultListener);

        etStart.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    isStart = true;
                    rvStart.setVisibility(View.VISIBLE);
                }
            }
        });
        //监听起点输入框的变化
        etStart.addTextChangedListener(new TextWatcher() {
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
        etEnd.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    isStart = false;
                    rvStart.setVisibility(View.VISIBLE);
                }
            }
        });
        //监听终点输入框的输入
        etEnd.addTextChangedListener(new TextWatcher() {
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

        suggestAdapter.setClick(new BaseAdapter.IListClick() {
            @Override
            public void itemClick(int pos) {
                SuggestionResult.SuggestionInfo suggestionInfo = suggestList.get(pos);
                if (isStart) {
                    etStart.setText(suggestionInfo.getKey());
                    startLatLng = suggestionInfo.getPt();
                } else {
                    etEnd.setText(suggestionInfo.getKey());
                }
                rvStart.setVisibility(View.GONE);


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
            if (suggestList != null) {
                suggestList.clear();
            }
            if(suggestionResult.getAllSuggestions()!=null){
                suggestList.addAll(suggestionResult.getAllSuggestions());
                suggestAdapter.notifyDataSetChanged();
            }

        }
    };

    /**
     * 路径搜索的监听
     */
    OnGetRoutePlanResultListener routePlanResultListener = new OnGetRoutePlanResultListener() {
        @Override
        public void onGetWalkingRouteResult(WalkingRouteResult walkingRouteResult) {
            Log.i("TAG", "onGetWalkingRouteResult");

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
                Toast.makeText(NavigationActivity.this, "未搜索到相关路径", Toast.LENGTH_SHORT).show();
            }
        }

        @Override
        public void onGetTransitRouteResult(TransitRouteResult transitRouteResult) {
            Log.i("TAG", "onGetTransitRouteResult");
        }

        @Override
        public void onGetMassTransitRouteResult(MassTransitRouteResult massTransitRouteResult) {
            Log.i("TAG", "onGetMassTransitRouteResult");
        }

        @Override
        public void onGetDrivingRouteResult(DrivingRouteResult drivingRouteResult) {
            Log.i("TAG", "onGetDrivingRouteResult");
        }

        @Override
        public void onGetIndoorRouteResult(IndoorRouteResult indoorRouteResult) {
            Log.i("TAG", "onGetIndoorRouteResult");
        }

        @Override
        public void onGetBikingRouteResult(BikingRouteResult bikingRouteResult) {
            Log.i("TAG", "onGetBikingRouteResult");
        }
    };

    private void searchRoute() {
        String startName, endName;
        startName = etStart.getText().toString();
        endName = etEnd.getText().toString();
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

    private void drawCircle(double lat, double gt) {
        //设置圆心位置
        LatLng center = new LatLng(lat, gt);
        //设置圆对象
        CircleOptions circleOptions = new CircleOptions().center(center)
                .radius(2000)
                .fillColor(0x50ff0000)
                .stroke(new Stroke(10, 0x5500000)); //设置边框的宽度和颜色
        //在地图上添加显示圆
        Overlay circle = baiduMap.addOverlay(circleOptions);


    }

    private void addMark(double lat, double gt) {
        //定义Maker坐标点
//        LatLng point = new LatLng(lat, gt);
//        //构建Marker图标
//        BitmapDescriptor bitmap = BitmapDescriptorFactory
//                .fromResource(R.mipmap.city_img);
//        //构建MarkerOption，用于在地图上添加Marker
//        OverlayOptions option = new MarkerOptions()
//                .position(point)
//                .icon(bitmap);

        LatLng latLngA = new LatLng(lat, gt);

        MarkerOptions markerOptionsA = new MarkerOptions().position(latLngA).icon(bitmapA);
        Marker marker = (Marker) baiduMap.addOverlay(markerOptionsA);

        marker.setAnimation(getRotateAnimation());
        marker.startAnimation();
//        Point llC=new Point();
//
//        Transformation transformation = new Transformation(llC, latLng1, llC);

        //在地图上添加Marker，并显示
//        baiduMap.addOverlay(option);
    }


    private Animation getRotateAnimation() {
        RotateAnimation rotateAnimation = new RotateAnimation(0f, 360f);

        rotateAnimation.setDuration(1000);// 设置动画旋转时间
        rotateAnimation.setRepeatMode(Animation.RepeatMode.RESTART);// 动画重复模式
        rotateAnimation.setRepeatCount(1);// 动画重复次数
        rotateAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart() {
            }

            @Override
            public void onAnimationEnd() {
            }

            @Override
            public void onAnimationCancel() {
            }

            @Override
            public void onAnimationRepeat() {
            }
        });

        return rotateAnimation;
    }

    @OnClick({R.id.btn_start, R.id.btn_search})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_start:
                searchRoute();
                break;
            case R.id.btn_search:
                String search = etSearch.getText().toString();
                if (!TextUtils.isEmpty(search)) {
                    PoiCitySearchOption option = new PoiCitySearchOption();
                    option.city("北京");
                    option.keyword(search);
                    poiSearch.searchInCity(option);

                }
                break;
        }
    }


    public class MyLocationListener extends BDAbstractLocationListener {
        @Override
        public void onReceiveLocation(BDLocation location) {
            //mapView 销毁后不在处理新接收的位置
            if (location == null || mMapView == null) {
                return;
            }
            MyLocationData locData = new MyLocationData.Builder()
                    .accuracy(location.getRadius())
                    // 此处设置开发者获取到的方向信息，顺时针0-360
                    .direction(location.getDirection()).latitude(location.getLatitude())
                    .longitude(location.getLongitude()).build();
            baiduMap.setMyLocationData(locData);
        }
    }


    @Override
    protected void onResume() {
        super.onResume();
        //在activity执行onResume时执行mMapView. onResume ()，实现地图生命周期管理
        mMapView.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        //在activity执行onPause时执行mMapView. onPause ()，实现地图生命周期管理
        mMapView.onPause();
    }

    @Override
    protected void onDestroy() {

        super.onDestroy();
        mLocationClient.stop();
        baiduMap.setMyLocationEnabled(false);
        mMapView.onDestroy();
        mMapView = null;
        //在activity执行onDestroy时执行mMapView.onDestroy()，实现地图生命周期管理
    }
}