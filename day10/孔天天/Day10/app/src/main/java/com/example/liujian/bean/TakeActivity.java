package com.example.liujian.bean;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.baidu.location.BDAbstractLocationListener;
import com.baidu.location.BDLocation;
import com.baidu.location.Poi;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.search.sug.OnGetSuggestionResultListener;
import com.baidu.mapapi.search.sug.SuggestionResult;
import com.baidu.mapapi.search.sug.SuggestionSearch;
import com.baidu.mapapi.search.sug.SuggestionSearchOption;
import com.example.liujian.R;
import com.example.liujian.app.MyApp;
import com.example.liujian.baidu.SuggestAdapter;
import com.example.liujian.service.LocationService;
import com.example.liujian.utils.Utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class TakeActivity extends AppCompatActivity implements OnGetSuggestionResultListener {

    @BindView(R.id.tv_location)
    TextView tvLocation;
    @BindView(R.id.btn_location)
    Button btnLocation;
    LocationService locationService;
    @BindView(R.id.city)
    EditText city;
    @BindView(R.id.searchkey)
    AutoCompleteTextView searchkey;
    @BindView(R.id.ll)
    LinearLayout ll;
    @BindView(R.id.sug_list)
    RecyclerView sugList;
    @BindView(R.id.tv_hot)
    TextView tvHot;
    private SuggestionSearch mSuggestionSearch = null;

    // 搜索关键字输入窗口
    private EditText mEditCity = null;
    private AutoCompleteTextView mKeyWordsView = null;
    private RecyclerView mSugListView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_take);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
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
        if (suggestionResult == null || suggestionResult.getAllSuggestions() == null) {
            return;
        }

        List<HashMap<String, Object>> suggest = new ArrayList<>();
        for (SuggestionResult.SuggestionInfo info : suggestionResult.getAllSuggestions()) {
            if (info.getKey() != null && info.getDistrict() != null && info.getCity() != null) {
                HashMap<String, Object> map = new HashMap<>();
                map.put("key",info.getKey());
                map.put("city",info.getCity());
                map.put("dis",info.getDistrict());
                map.put("pt",info.pt);
                suggest.add(map);
            }
        }


        mSugListView.setLayoutManager(new LinearLayoutManager(this));
        mSugListView.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));
        SuggestTabAdapter adapter = new SuggestTabAdapter(suggest,this,new String[]{"key", "city","dis"});
        mSugListView.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        adapter.setOnItemClickListener(new SuggestTabAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                HashMap<String, Object> map = suggest.get(position);
                LatLng pt = (LatLng) map.get("pt");
                if(pt!=null){
                    Toast.makeText(TakeActivity.this, ""+pt.latitude, Toast.LENGTH_SHORT).show();
                }else{

                    Toast.makeText(TakeActivity.this, "请精确一下呢", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }


    @Override
    protected void onStart() {
        // TODO Auto-generated method stub
        super.onStart();
        // -----------location config ------------
        locationService = ((MyApp) getApplication()).locationService;
        //获取locationservice实例，建议应用中只初始化1个location实例，然后使用，可以参考其他示例的activity，都是通过此种方式获取locationservice实例的
        locationService.registerListener(mListener);
        //注册监听
        int type = getIntent().getIntExtra("from", 0);
        if (type == 0) {
            locationService.setLocationOption(locationService.getDefaultLocationClientOption());
        } else if (type == 1) {
            locationService.start();
        }
        btnLocation.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (btnLocation.getText().toString().equals(getString(R.string.startlocation))) {
                    locationService.start();// 定位SDK
                    // start之后会默认发起一次定位请求，开发者无须判断isstart并主动调用request
                    btnLocation.setText(getString(R.string.stoplocation));
                } else {
                    locationService.stop();
                    btnLocation.setText(getString(R.string.startlocation));
                }
            }
        });
    }

    private BDAbstractLocationListener mListener = new BDAbstractLocationListener() {

        /**
         * 定位请求回调函数
         * @param location 定位结果
         */
        @Override
        public void onReceiveLocation(BDLocation location) {

            // TODO Auto-generated method stub
            if (null != location && location.getLocType() != BDLocation.TypeServerError) {
                int tag = 1;
                StringBuffer sb = new StringBuffer(256);
//                sb.append("time : ");

                /**
                 * 时间也可以使用systemClock.elapsedRealtime()方法 获取的是自从开机以来，每次回调的时间
                 * location.getTime() 是指服务端出本次结果的时间，如果位置不发生变化，则时间不变
                 */
//                sb.append(location.getTime());
//                sb.append("\nlocType : ");// 定位类型
//                sb.append(location.getLocType());
//                sb.append("\nlocType description : ");// *****对应的定位类型说明*****
//                sb.append(location.getLocTypeDescription());
//                sb.append("\nlatitude : ");// 纬度
//                sb.append(location.getLatitude());
//                sb.append("\nlongtitude : ");// 经度
//                sb.append(location.getLongitude());
//                sb.append("\nradius : ");// 半径
//                sb.append(location.getRadius());
//                sb.append("\nCountryCode : ");// 国家码
//                sb.append(location.getCountryCode());
//                sb.append("\nProvince : ");// 获取省份
//                sb.append(location.getProvince());
//                sb.append("\nCountry : ");// 国家名称
//                sb.append(location.getCountry());
//                sb.append("\ncitycode : ");// 城市编码
//                sb.append(location.getCityCode());
//                sb.append("\ncity : ");// 城市
//                sb.append(location.getCity());
//                sb.append("\nDistrict : ");// 区
//                sb.append(location.getDistrict());
//                sb.append("\nTown : ");// 获取镇信息
//                sb.append(location.getTown());
//                sb.append("\nStreet : ");// 街道
//                sb.append(location.getStreet());
//                sb.append("\naddr : ");// 地址信息
//                sb.append(location.getAddrStr());
//                sb.append("\nStreetNumber : ");// 获取街道号码
//                sb.append(location.getStreetNumber());
//                sb.append("\nUserIndoorState: ");// *****返回用户室内外判断结果*****
//                sb.append(location.getUserIndoorState());
//                sb.append("\nDirection(not all devices have value): ");
//                sb.append(location.getDirection());// 方向
//                sb.append("\nlocationdescribe: ");
//                sb.append(location.getLocationDescribe());// 位置语义化信息
//                sb.append("\nPoi: ");// POI信息
                if (location.getPoiList() != null && !location.getPoiList().isEmpty()) {
                    Poi poi = (Poi) location.getPoiList().get(0);
//                    sb.append("poiName:");
                    tvLocation.setText(poi.getName());
//                    sb.append(poi.getName());
//                    for (int i = 0; i < location.getPoiList().size(); i++) {
//
//
//                    }
                }
//                if (location.getPoiRegion() != null) {
//                    sb.append("PoiRegion: ");// 返回定位位置相对poi的位置关系，仅在开发者设置需要POI信息时才会返回，在网络不通或无法获取时有可能返回null
//                    PoiRegion poiRegion = location.getPoiRegion();
//                    sb.append("DerectionDesc:"); // 获取POIREGION的位置关系，ex:"内"
//                    sb.append(poiRegion.getDerectionDesc() + "; ");
//                    sb.append("Name:"); // 获取POIREGION的名字字符串
//                    sb.append(poiRegion.getName() + "; ");
//                    sb.append("Tags:"); // 获取POIREGION的类型
//                    sb.append(poiRegion.getTags() + "; ");
//                    sb.append("\nSDK版本: ");
//                }
//                sb.append(locationService.getSDKVersion()); // 获取SDK版本
//                if (location.getLocType() == BDLocation.TypeGpsLocation) {// GPS定位结果
//                    sb.append("\nspeed : ");
//                    sb.append(location.getSpeed());// 速度 单位：km/h
//                    sb.append("\nsatellite : ");
//                    sb.append(location.getSatelliteNumber());// 卫星数目
//                    sb.append("\nheight : ");
//                    sb.append(location.getAltitude());// 海拔高度 单位：米
//                    sb.append("\ngps status : ");
//                    sb.append(location.getGpsAccuracyStatus());// *****gps质量判断*****
//                    sb.append("\ndescribe : ");
//                    sb.append("gps定位成功");
//                } else if (location.getLocType() == BDLocation.TypeNetWorkLocation) {// 网络定位结果
//                    // 运营商信息
//                    if (location.hasAltitude()) {// *****如果有海拔高度*****
//                        sb.append("\nheight : ");
//                        sb.append(location.getAltitude());// 单位：米
//                    }
//                    sb.append("\noperationers : ");// 运营商信息
//                    sb.append(location.getOperators());
//                    sb.append("\ndescribe : ");
//                    sb.append("网络定位成功");
//                } else if (location.getLocType() == BDLocation.TypeOffLineLocation) {// 离线定位结果
//                    sb.append("\ndescribe : ");
//                    sb.append("离线定位成功，离线定位结果也是有效的");
//                } else if (location.getLocType() == BDLocation.TypeServerError) {
//                    sb.append("\ndescribe : ");
//                    sb.append("服务端网络定位失败，可以反馈IMEI号和大体定位时间到loc-bugs@baidu.com，会有人追查原因");
//                } else if (location.getLocType() == BDLocation.TypeNetWorkException) {
//                    sb.append("\ndescribe : ");
//                    sb.append("网络不同导致定位失败，请检查网络是否通畅");
//                } else if (location.getLocType() == BDLocation.TypeCriteriaException) {
//                    sb.append("\ndescribe : ");
//                    sb.append("无法获取有效定位依据导致定位失败，一般是由于手机的原因，处于飞行模式下一般会造成这种结果，可以试着重启手机");
//                }
//                logMsg(sb.toString(), tag);
            }
        }

        @Override
        public void onConnectHotSpotMessage(String s, int i) {
            super.onConnectHotSpotMessage(s, i);
        }

        /**
         * 回调定位诊断信息，开发者可以根据相关信息解决定位遇到的一些问题
         * @param locType 当前定位类型
         * @param diagnosticType 诊断类型（1~9）
         * @param diagnosticMessage 具体的诊断信息释义
         */
//        @Override
//        public void onLocDiagnosticMessage(int locType, int diagnosticType, String diagnosticMessage) {
//            super.onLocDiagnosticMessage(locType, diagnosticType, diagnosticMessage);
//            int tag = 2;
//            StringBuffer sb = new StringBuffer(256);
//            sb.append("诊断结果: ");
//            if (locType == BDLocation.TypeNetWorkLocation) {
//                if (diagnosticType == 1) {
//                    sb.append("网络定位成功，没有开启GPS，建议打开GPS会更好");
//                    sb.append("\n" + diagnosticMessage);
//                } else if (diagnosticType == 2) {
//                    sb.append("网络定位成功，没有开启Wi-Fi，建议打开Wi-Fi会更好");zAQ
//                    sb.append("\n" + diagnosticMessage);
//                }
//            } else if (locType == BDLocation.TypeOffLineLocationFail) {
//                if (diagnosticType == 3) {
//                    sb.append("定位失败，请您检查您的网络状态");
//                    sb.append("\n" + diagnosticMessage);
//                }
//            } else if (locType == BDLocation.TypeCriteriaException) {
//                if (diagnosticType == 4) {
//                    sb.append("定位失败，无法获取任何有效定位依据");
//                    sb.append("\n" + diagnosticMessage);
//                } else if (diagnosticType == 5) {
//                    sb.append("定位失败，无法获取有效定位依据，请检查运营商网络或者Wi-Fi网络是否正常开启，尝试重新请求定位");
//                    sb.append(diagnosticMessage);
//                } else if (diagnosticType == 6) {
//                    sb.append("定位失败，无法获取有效定位依据，请尝试插入一张sim卡或打开Wi-Fi重试");
//                    sb.append("\n" + diagnosticMessage);
//                } else if (diagnosticType == 7) {
//                    sb.append("定位失败，飞行模式下无法获取有效定位依据，请关闭飞行模式重试");
//                    sb.append("\n" + diagnosticMessage);
//                } else if (diagnosticType == 9) {
//                    sb.append("定位失败，无法获取任何有效定位依据");
//                    sb.append("\n" + diagnosticMessage);
//                }
//            } else if (locType == BDLocation.TypeServerError) {
//                if (diagnosticType == 8) {
//                    sb.append("定位失败，请确认您定位的开关打开状态，是否赋予APP定位权限");
//                    sb.append("\n" + diagnosticMessage);
//                }
//            }
//            logMsg(sb.toString(), tag);
//        }
    };

    public void logMsg(final String str, final int tag) {

        try {
            if (tvLocation != null) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        tvLocation.post(new Runnable() {
                            @Override
                            public void run() {
                                if (tag == Utils.RECEIVE_TAG) {
                                    tvLocation.setText(str);
                                } else if (tag == Utils.DIAGNOSTIC_TAG) {
                                    tvLocation.setText(str);
                                }
                            }
                        });
                    }
                }).start();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        locationService.unregisterListener(mListener);
        mSuggestionSearch.destroy();
    }

}