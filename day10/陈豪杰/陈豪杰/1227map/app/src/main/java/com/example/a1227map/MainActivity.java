package com.example.a1227map;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.a1227map.bike.BikeMainActivity;
import com.example.a1227map.poicity.PoiCitySearchDemo;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button mPoisugsearchOne;
    private Button mMarkerClusterTwo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        mPoisugsearchOne = (Button) findViewById(R.id.one_poisugsearch);
        mPoisugsearchOne.setOnClickListener(this);
        mMarkerClusterTwo = (Button) findViewById(R.id.two_MarkerCluster);
        mMarkerClusterTwo.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.one_poisugsearch:
                // TODO 20/12/27
                    startActivity(new Intent(this, PoiCitySearchDemo.class));
                break;
            case R.id.two_MarkerCluster:
                // TODO 20/12/27
                startActivity(new Intent(this, BikeMainActivity.class));
                break;
            default:
                break;
        }
    }
}