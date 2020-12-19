package com.example.gushi;

import android.os.Bundle;
import android.widget.SearchView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {

    private SearchView sv;
    private ConstraintLayout c1;
    private RecyclerView rcy;
    private TabLayout tab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        sv = (SearchView) findViewById(R.id.sv);
        c1 = (ConstraintLayout) findViewById(R.id.c1);
        rcy = (RecyclerView) findViewById(R.id.rcy);
        tab = (TabLayout) findViewById(R.id.tab);

        rcy = (RecyclerView) findViewById(R.id.rcy);
        rcy.setLayoutManager(new LinearLayoutManager(this));
        com.example.mengshiyun2.HomeAdapter myAdapter = new com.example.mengshiyun2.HomeAdapter(this);
        rcy.setAdapter(myAdapter);
        tab = (TabLayout) findViewById(R.id.tab);
    }
}