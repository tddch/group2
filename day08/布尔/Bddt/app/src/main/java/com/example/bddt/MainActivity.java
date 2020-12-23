package com.example.bddt;

import android.Manifest;
import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.RadioButton;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.baidu.location.BDAbstractLocationListener;
import com.baidu.location.BDLocation;
import com.baidu.location.LocationClient;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.MyLocationData;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnCheckedChanged;
import butterknife.Unbinder;

public class MainActivity extends AppCompatActivity {

    private MapView mMapView = null;
    private LocationClient mLocationClient;
    private RadioButton home_rb1;
    private RadioButton home_rb2;
    private RadioButton home_rb3;
    private RadioButton home_rb4;
    private BaiduMap mMap;
    private Unbinder mBind;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mBind = ButterKnife.bind(this);
        initView();
        //获取地图控件引用
        mMapView = findViewById(R.id.bmapView);
        mMap = mMapView.getMap();
        mMap.setMapType(BaiduMap.MAP_TYPE_NORMAL);

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
        //在activity执行onDestroy时执行mMapView.onDestroy()，实现地图生命周期管理
        mMapView.onDestroy();
        mBind.unbind();
    }

    private void initView() {
        home_rb1 = (RadioButton) findViewById(R.id.home_rb1);
        home_rb2 = (RadioButton) findViewById(R.id.home_rb2);
        home_rb3 = (RadioButton) findViewById(R.id.home_rb3);
        home_rb4 = (RadioButton) findViewById(R.id.home_rb4);

    }


    @OnCheckedChanged({R.id.home_rb1, R.id.home_rb2, R.id.home_rb3, R.id.home_rb4})
    public void onCehcedChange(CompoundButton button, boolean isChecked) {
        switch (button.getId()) {
            case R.id.home_rb1:
                //卫星图
                mMap.setMapType(BaiduMap.MAP_TYPE_SATELLITE);
                break;
            case R.id.home_rb2:
                //普通地图

                break;
            case R.id.home_rb3:
                //开启热力图
                mMap.setBaiduHeatMapEnabled(true);
                break;
            case R.id.home_rb4:
                //开启实时交通图
                mMap.setTrafficEnabled(true);
                break;
        }

    }





    public class MyLocationListener extends BDAbstractLocationListener {
        @Override
        public void onReceiveLocation(BDLocation location) {
            //mapView 销毁后不在处理新接收的位置
            if (location == null || mMapView == null){
                return;
            }
            MyLocationData locData = new MyLocationData.Builder()
                    .accuracy(location.getRadius())
                    // 此处设置开发者获取到的方向信息，顺时针0-360
                    .direction(location.getDirection()).latitude(location.getLatitude())
                    .longitude(location.getLongitude()).build();
            mMap.setMyLocationData(locData);
        }
    }

}