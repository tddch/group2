package com.example.day_07.view.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;

import androidx.appcompat.app.AppCompatActivity;

import com.baidu.location.BDAbstractLocationListener;
import com.baidu.location.BDLocation;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.CoordType;
import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.MyLocationData;
import com.example.day_07.R;

public class BaiDuActivity extends AppCompatActivity implements View.OnClickListener {

    private MapView mMapView;
    private LocationClient mLocationClient;
    private BaiduMap mBaiduMap;
    private CheckBox ch_box_a;
    private CheckBox ch_box_b;
    private CheckBox ch_box_c;
    private Button btn_qing;
    private MapView bmapView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bai_du);
        initView();
    }

    private void initView() {
        mMapView = (MapView) findViewById(R.id.bmapView);
        ch_box_a = (CheckBox) findViewById(R.id.ch_box_a);
        ch_box_a.setOnClickListener(this);
        ch_box_b = (CheckBox) findViewById(R.id.ch_box_b);
        ch_box_b.setOnClickListener(this);
        ch_box_c = (CheckBox) findViewById(R.id.ch_box_c);
        ch_box_c.setOnClickListener(this);
        btn_qing = (Button) findViewById(R.id.btn_qing);
        btn_qing.setOnClickListener(this);
        bmapView = (MapView) findViewById(R.id.bmapView);
        bmapView.setOnClickListener(this);


        mBaiduMap = mMapView.getMap();
        //在使用SDK各组件之前初始化context信息，传入ApplicationContext
        SDKInitializer.initialize(this);
        //自4.3.0起，百度地图SDK所有接口均支持百度坐标和国测局坐标，用此方法设置您使用的坐标类型.
        //包括BD09LL和GCJ02两种坐标，默认是BD09LL坐标。
        SDKInitializer.setCoordType(CoordType.BD09LL);


        mBaiduMap.setMyLocationEnabled(true);


        //定位初始化
        mLocationClient = new LocationClient(this);

//通过LocationClientOption设置LocationClient相关参数
        LocationClientOption option = new LocationClientOption();
        option.setOpenGps(true); // 打开gps
        option.setCoorType("bd0922"); // 设置坐标类型
        option.setScanSpan(1000);

//设置locationClientOption
        mLocationClient.setLocOption(option);

//注册LocationListener监听器
        MyLocationListener myLocationListener = new MyLocationListener();
        mLocationClient.registerLocationListener(myLocationListener);
//开启地图定位图层
        mLocationClient.start();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_qing:

                break;
            case R.id.ch_box_a:

                //普通地图 ,mBaiduMap是地图控制器对象
                mBaiduMap.setMapType(BaiduMap.MAP_TYPE_NORMAL);
                break;

            case R.id.ch_box_b:
                //卫星地图
                mBaiduMap.setMapType(BaiduMap.MAP_TYPE_SATELLITE);
                break;

            case R.id.ch_box_c:
                //空白地图
                mBaiduMap.setMapType(BaiduMap.MAP_TYPE_NONE);
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
            mBaiduMap.setMyLocationData(locData);
        }
    }


    @Override
    protected void onResume() {
        //在activity执行onResume时执行mMapView. onResume ()，实现地图生命周期管理
        mMapView.onResume();
        super.onResume();

        //定位
//        mLocationClient.stop();
    }

    @Override
    protected void onPause() {
        //在activity执行onPause时执行mMapView. onPause ()，实现地图生命周期管理
        mMapView.onPause();

        super.onPause();
    }

    @Override
    protected void onDestroy() {

        mLocationClient.stop();
        mBaiduMap.setMyLocationEnabled(false);
        //在activity执行onDestroy时执行mMapView.onDestroy()，实现地图生命周期管理
        mMapView.onDestroy();
        mMapView = null;
        super.onDestroy();
    }

}
