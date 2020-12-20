package com.example.day05;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private RecyclerView main_rec;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        main_rec = (RecyclerView) findViewById(R.id.main_rec);
        main_rec.setLayoutManager(new LinearLayoutManager(this));
        InAdapter inAdapter = new InAdapter(this);
        main_rec.setAdapter(inAdapter);
    }
}