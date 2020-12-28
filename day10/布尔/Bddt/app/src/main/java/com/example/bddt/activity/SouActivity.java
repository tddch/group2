package com.example.bddt.activity;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.baidu.location.BDAbstractLocationListener;
import com.baidu.location.BDLocation;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.CircleOptions;
import com.baidu.mapapi.map.InfoWindow;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.map.Overlay;
import com.baidu.mapapi.map.OverlayOptions;
import com.baidu.mapapi.map.Stroke;
import com.baidu.mapapi.map.TextOptions;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.search.core.PoiInfo;
import com.baidu.mapapi.search.poi.OnGetPoiSearchResultListener;
import com.baidu.mapapi.search.poi.PoiCitySearchOption;
import com.baidu.mapapi.search.poi.PoiDetailResult;
import com.baidu.mapapi.search.poi.PoiDetailSearchResult;
import com.baidu.mapapi.search.poi.PoiIndoorResult;
import com.baidu.mapapi.search.poi.PoiResult;
import com.baidu.mapapi.search.poi.PoiSearch;
import com.example.bddt.R;
import com.example.bddt.adapter.BaseAdapter;
import com.example.bddt.adapter.SearchItemAdapter;
import com.example.bddt.widget.JuHeList;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SouActivity extends AppCompatActivity {

    @BindView(R.id.input)
    EditText input;
    @BindView(R.id.btn_search)
    Button btn_search;
    @BindView(R.id.bmapView)
    MapView bmapView;

    //百度地图的数据操作
    BaiduMap baiduMap;
    //百度地图定位的类
    LocationClient locationClient;
    /********************检索********************/
    SearchItemAdapter searchItemAdapter;
    List<PoiInfo> poiList;
    @BindView(R.id.rv)
    RecyclerView rv;
    List<JuHeList> lists = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sou);
        ButterKnife.bind(this);
        initView();
        initMap();
        initLocation();
        initPoi();

    }

    private void initView() {
        btn_search.setOnClickListener(this::onClick);
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
        MyLocationListener myLocationListener = new MyLocationListener();
        locationClient.registerLocationListener(myLocationListener);
        //开启地图定位图层
        locationClient.start();
    }
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_search:
                search();
                break;
        }
    }
    /**
     * 搜索
     */
    private void search() {
        String word = input.getText().toString();
        if (!TextUtils.isEmpty(word)) {
            PoiCitySearchOption option = new PoiCitySearchOption();
            option.city("北京");
            option.keyword(word);
            poiSearch.searchInCity(option);
        } else {

        }
    }
    /**
     * 地图定位的监听
     */
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


    PoiSearch poiSearch;

    private void initPoi() {
        poiList = new ArrayList<>();
        searchItemAdapter = new SearchItemAdapter(this, poiList);
        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.setAdapter(searchItemAdapter);

        poiSearch = PoiSearch.newInstance();
        poiSearch.setOnGetPoiSearchResultListener(poiSearchResultListener);

        searchItemAdapter.addListClick(new BaseAdapter.IListClick() {
            @Override
            public void itemClick(int pos) {
                //点击条目进行定位
                PoiInfo poiInfo = poiList.get(pos);
                String name = poiInfo.getName();
                LatLng location = poiInfo.location;
                MapStatusUpdate status = MapStatusUpdateFactory.newLatLng(location);
                baiduMap.setMapStatus(status);
                addMark(location.latitude, location.longitude);
                drawCircle(location.latitude, location.longitude);
                addMarks(lists);

                inForWindow(name, location);
                //文字覆盖
                initText(name);
            }
        });

    }
    /**
     * 搜索的监听
     */
    OnGetPoiSearchResultListener poiSearchResultListener = new OnGetPoiSearchResultListener() {
        @Override
        public void onGetPoiResult(PoiResult poiResult) {
            Log.i(TAG, "onGetPoiResult");
            poiList.clear();
            if (poiResult.getAllPoi() != null && poiResult.getAllPoi().size() > 0) {
                poiList.addAll(poiResult.getAllPoi());
                searchItemAdapter.notifyDataSetChanged();
                List<PoiInfo> allPoi = poiResult.getAllPoi();
                for (PoiInfo poi : allPoi) {
                    LatLng location = poi.location;
                    double latitude = location.latitude;
                    double longitude = location.longitude;
                    lists.add(new JuHeList(latitude, longitude));
                }
            }
        }

        @Override
        public void onGetPoiDetailResult(PoiDetailResult poiDetailResult) {
            Log.i(TAG, "onGetPoiDetailResult");
        }

        @Override
        public void onGetPoiDetailResult(PoiDetailSearchResult poiDetailSearchResult) {
            Log.i(TAG, "onGetPoiDetailResult");
        }

        @Override
        public void onGetPoiIndoorResult(PoiIndoorResult poiIndoorResult) {
            Log.i(TAG, "onGetPoiIndoorResult");
        }

        private static final String TAG = "SouActivity";
    };

    private void addMark(double lat, double gt){
        //定义Maker坐标点
        LatLng point = new LatLng(lat, gt);
        //构建Marker图标
        BitmapDescriptor bitmap = BitmapDescriptorFactory
                .fromResource(R.drawable.icon_marka);
        //构建MarkerOption，用于在地图上添加Marker
        OverlayOptions option = new MarkerOptions()
                .position(point)
                .icon(bitmap);
        //在地图上添加Marker，并显示
        baiduMap.addOverlay(option);
    }
    /**
     * 以当前的经纬度为圆心绘制一个圆
     */
    private void drawCircle(double lat, double gt) {
        //设置圆心位置
        LatLng center = new LatLng(lat, gt);
        //设置圆对象
        CircleOptions circleOptions = new CircleOptions().center(center)
                .radius(2000)
                .fillColor(0x50ff0000)
                .stroke(new Stroke(2, 0xff000000)); //设置边框的宽度和颜色
        baiduMap.clear();
        //在地图上添加显示圆
        Overlay circle = baiduMap.addOverlay(circleOptions);
    }
    private void addMarks(List<JuHeList> list) {
        for (int i = 0; i < list.size(); i++) {
            JuHeList juHeList = list.get(i);
            LatLng lng = new LatLng(juHeList.getLot(), juHeList.getGt());
            BitmapDescriptor resource = BitmapDescriptorFactory.fromResource(R.drawable.icon_marka);
            MarkerOptions icons = new MarkerOptions()
                    .position(lng)
                    .icon(resource);
            baiduMap.addOverlay(icons);
        }
    }

    private void initText(String name) {
//文字覆盖物位置坐标
        LatLng llText = new LatLng(39.86923, 116.397428);

//构建TextOptions对象
        OverlayOptions mTextOptions = new TextOptions()
                .text(name) //文字内容
                .bgColor(0xAAFFFF00) //背景色
                .fontSize(30) //字号
                .fontColor(0xFFFF00FF) //文字颜色
                .rotate(0) //旋转角度
                .position(llText);
//在地图上显示文字覆盖物
        Overlay mText = baiduMap.addOverlay(mTextOptions);
    }

    InfoWindow mInfoWindow;

    private void inForWindow(String name, LatLng location) {
        //用来构造InfoWindow的Button
        Button button = new Button(getApplicationContext());
        button.setBackgroundResource(R.drawable.fugai);
        button.setText(name);
//构造InfoWindow
//point 描述的位置点
//-100 InfoWindow相对于point在y轴的偏移量
        mInfoWindow = new InfoWindow(button, location, -100);

//使InfoWindow生效
        baiduMap.showInfoWindow(mInfoWindow);
    }

}