package com.example.liujian.ui.discover.baidu;

import android.graphics.Point;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.baidu.location.BDAbstractLocationListener;
import com.baidu.location.BDLocation;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.animation.Animation;
import com.baidu.mapapi.animation.RotateAnimation;
import com.baidu.mapapi.animation.Transformation;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.InfoWindow;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.Marker;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.map.OverlayOptions;
import com.baidu.mapapi.map.PolygonOptions;
import com.baidu.mapapi.map.PolylineOptions;
import com.baidu.mapapi.map.Stroke;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.model.LatLngBounds;
import com.baidu.mapapi.search.core.PoiInfo;
import com.baidu.mapapi.search.core.SearchResult;
import com.baidu.mapapi.search.district.DistrictResult;
import com.baidu.mapapi.search.district.DistrictSearch;
import com.baidu.mapapi.search.district.DistrictSearchOption;
import com.baidu.mapapi.search.district.OnGetDistricSearchResultListener;
import com.baidu.mapapi.search.poi.OnGetPoiSearchResultListener;
import com.baidu.mapapi.search.poi.PoiCitySearchOption;
import com.baidu.mapapi.search.poi.PoiDetailResult;
import com.baidu.mapapi.search.poi.PoiDetailSearchResult;
import com.baidu.mapapi.search.poi.PoiIndoorResult;
import com.baidu.mapapi.search.poi.PoiResult;
import com.baidu.mapapi.search.poi.PoiSearch;
import com.example.liujian.R;
import com.example.liujian.base.BaseAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class BaiduActivity extends AppCompatActivity implements OnGetDistricSearchResultListener {

    @BindView(R.id.baidumap)
    MapView mMapView;
    @BindView(R.id.tv_city)
    TextView tvCity;
    @BindView(R.id.et_city)
    EditText etCity;
    @BindView(R.id.tv_region)
    TextView tvRegion;
    @BindView(R.id.et_region)
    EditText etRegion;
    @BindView(R.id.rv_item)
    RecyclerView rvCity;
    private BaiduMap baiduMap;
    private PoiSearch poiSearch;
    private ArrayList<PoiInfo> poiInfos;
    private SearchItemAdapter adapter;
    private Marker marker;
    private DistrictSearch districtSearch;
    private InfoWindow infoWindow;
    private LocationClient locationClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_baidu);
        ButterKnife.bind(this);

        //
        initMap();

        initDistrict();
        initLocation();
        //初始化索引
        initPoi();
    }

    public class MyItem implements ClusterItem {
        private final LatLng mPosition;

        private MyItem(LatLng latLng) {
            mPosition = latLng;
        }

        @Override
        public LatLng getPosition() {
            return mPosition;
        }

        @Override
        public BitmapDescriptor getBitmapDescriptor() {
            return BitmapDescriptorFactory.fromResource(R.mipmap.city_img);
        }
    }

    private void initDistrict() {
        districtSearch = DistrictSearch.newInstance();
        districtSearch.setOnDistrictSearchListener(this);
    }


    @Override
    public void onGetDistrictResult(DistrictResult districtResult) {
        baiduMap.clear();
        if (districtResult == null) {
            return;
        }
        new Thread(new Runnable() {

            private LatLngBounds.Builder builder;

            @Override
            public void run() {
                if (districtResult.error == SearchResult.ERRORNO.NO_ERROR) {
                    List<List<LatLng>> polyLines = districtResult.getPolylines();
                    if (polyLines == null) {
                        return;
                    }
                    builder = new LatLngBounds.Builder();

                    for (List<LatLng> polyline : polyLines) {
                        OverlayOptions ooPolyline = new PolylineOptions().width(10).points(polyline).dottedLine(true).color(0xAA00FF00);
                        baiduMap.addOverlay(ooPolyline);
                        OverlayOptions ooPolygon = new PolygonOptions().points(polyline).stroke(new Stroke(5, 0xAA00FF88))
                                .fillColor(0xAAFFFF00);
                        baiduMap.addOverlay(ooPolygon);
                        for (LatLng latLng : polyline) {
                            builder.include(latLng);
                        }
                    }

                }
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        baiduMap.setMapStatus(MapStatusUpdateFactory.newLatLngBounds(builder.build()));

                    }
                });
            }
        }).start();

    }


    private void initPoi() {
        poiInfos = new ArrayList<>();
        adapter = new SearchItemAdapter(this, poiInfos);
        rvCity.setLayoutManager(new LinearLayoutManager(this));
        rvCity.setAdapter(adapter);

        poiSearch = PoiSearch.newInstance();
        poiSearch.setOnGetPoiSearchResultListener(listener);

        etCity.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus){
                    rvCity.setVisibility(View.VISIBLE);
                }
            }
        });

        etCity.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                PoiCitySearchOption option = new PoiCitySearchOption();
                option.city("北京");
                option.keyword(s.toString());
                poiSearch.searchInCity(option);
            }
        });

        adapter.setClick(new BaseAdapter.IListClick() {
            @Override
            public void itemClick(int position) {
                if(marker!=null){
                    marker.remove();

                }


                PoiInfo poiInfo = poiInfos.get(position);

                etCity.setText(poiInfo.name);

                MapStatusUpdate status = MapStatusUpdateFactory.newLatLngZoom(poiInfos.get(position).getLocation(),18);
                baiduMap.setMapStatus(status);

                addaddMark(poiInfo.getLocation());
//                rvCity.setVisibility(View.GONE);
            }
        });

    }

    private void addaddMark(LatLng location) {
        //定义Maker坐标点
//        LatLng point = new LatLng(lat, gt);
        //构建Marker图标
//        BitmapDescriptor bitmap = BitmapDescriptorFactory
//                .fromResource(R.mipmap.city_img);
//        //构建MarkerOption，用于在地图上添加Marker
//        OverlayOptions option = new MarkerOptions()
//                .position(location)
//                .icon(bitmap);
        //在地图上添加Marker，并显示

        marker = addOverlayToMap(baiduMap, location,  R.mipmap.city_img);

        marker.setAnimation(getAnimation());
        marker.setAnimation(getAnimation(location));

        marker.startAnimation();

        //用来构造InfoWindow的Button
        Button button = new Button(getApplicationContext());
        button.setBackgroundResource(R.mipmap.c);
        button.setText(etCity.getText().toString());

//构造InfoWindow
//point 描述的位置点
//-100 InfoWindow相对于point在y轴的偏移量
        infoWindow = new InfoWindow(button, location, -100);

//使InfoWindow生效
        baiduMap.showInfoWindow(infoWindow);
//        baiduMap.addOverlay(option);
    }

    OnGetPoiSearchResultListener listener = new OnGetPoiSearchResultListener() {
        @Override
        public void onGetPoiResult(PoiResult poiResult) {
            poiInfos.clear();
            if(poiResult.getAllPoi()!=null&&poiResult.getAllPoi().size()>0){
                poiInfos.addAll(poiResult.getAllPoi());
                adapter.notifyDataSetChanged();
            }
        }
        @Override
        public void onGetPoiDetailResult(PoiDetailSearchResult poiDetailSearchResult) {

        }
        @Override
        public void onGetPoiIndoorResult(PoiIndoorResult poiIndoorResult) {

        }
        //废弃
        @Override
        public void onGetPoiDetailResult(PoiDetailResult poiDetailResult) {

        }
    };

    private Animation getAnimation() {
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


    private Animation getAnimation(LatLng location) {
        Point point = baiduMap.getProjection().toScreenLocation(location);
        LatLng latLngB = baiduMap.getProjection().fromScreenLocation(new Point(point.x, point.y - 100));
        Transformation transformation = new Transformation(location, latLngB, location);
        transformation.setDuration(500);
        transformation.setRepeatMode(Animation.RepeatMode.RESTART);// 动画重复模式
        transformation.setRepeatCount(1);// 动画重复次数
        transformation.setAnimationListener(new Animation.AnimationListener() {
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

        return transformation;
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

    public static Marker addOverlayToMap(BaiduMap baiduMap, LatLng latLng, int overlayId) {
        BitmapDescriptor mIconMaker = BitmapDescriptorFactory.fromResource(overlayId);
        OverlayOptions overlayOptions = new MarkerOptions().position(latLng).icon(mIconMaker);
        return (Marker) baiduMap.addOverlay(overlayOptions);
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
        //在activity执行onDestroy时执行mMapView.onDestroy()，实现地图生命周期管理
        mMapView.onDestroy();
    }



    private void seach() {
        String string = etCity.getText().toString();
        if(!TextUtils.isEmpty(string)){
            PoiCitySearchOption poiCitySearchOption = new PoiCitySearchOption();
            poiCitySearchOption.city("北京");
            poiCitySearchOption.keyword(string);
            poiSearch.searchInCity(poiCitySearchOption);
        }
    }

    private void initMap() {
        baiduMap = mMapView.getMap();
        baiduMap.setMyLocationEnabled(true);
        //设置为普通类型的地图
        baiduMap.setMapType(BaiduMap.MAP_TYPE_NORMAL);
    }

    private void initView() {
        baiduMap = mMapView.getMap();
        baiduMap.setMyLocationEnabled(true);
    }
}