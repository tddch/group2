package com.example.baidu;

import android.Manifest;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BaiduMapOptions;
import com.baidu.mapapi.map.MapView;
import com.github.dfqin.grantor.PermissionListener;
import com.github.dfqin.grantor.PermissionsUtil;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private MapView mMapView=null;
    private RadioButton main_rb1;
    private RadioButton main_rb2;
    private RadioButton main_rb3;
    private RadioButton main_rb4;
    private BaiduMap mbaiduMap;
    private Unbinder bind;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bind = ButterKnife.bind(this);
        initView();
        initPermission();
        mMapView = findViewById(R.id.bmapView);
        mbaiduMap = mMapView.getMap();
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
        bind.unbind();
    }
    private void initPermission() {
        PermissionsUtil.requestPermission(this, new PermissionListener() {
            @Override
            public void permissionGranted(@NonNull String[] permission) {

            }

            @Override
            public void permissionDenied(@NonNull String[] permission) {

            }
        }, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE});

    }
    private void initView() {
        main_rb1 = (RadioButton) findViewById(R.id.main_rb1);
        main_rb2 = (RadioButton) findViewById(R.id.main_rb2);
        main_rb3 = (RadioButton) findViewById(R.id.main_rb3);
        main_rb4 = (RadioButton) findViewById(R.id.main_rb4);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.main_rb1:
                mbaiduMap.setMapType(BaiduMap.MAP_TYPE_NORMAL);
                break;
            case R.id.main_rb2:
                mbaiduMap.setMapType(BaiduMap.MAP_TYPE_SATELLITE);
                break;
            case R.id.main_rb3:
                mbaiduMap.setBaiduHeatMapEnabled(true);
                break;
            case R.id.main_rb4:
                mbaiduMap.setTrafficEnabled(true);
                break;

        }
    }
}