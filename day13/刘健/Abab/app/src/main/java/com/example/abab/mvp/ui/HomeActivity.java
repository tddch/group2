package com.example.abab.mvp.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.abab.R;
import com.example.abab.mvp.ui.adapter.HomeAdapter;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HomeActivity extends AppCompatActivity {

    @BindView(R.id.tv_course)
    TextView tvCourse;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.rv_item)
    RecyclerView rvItem;
    @BindView(R.id.btn_item)
    Button btnItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        ArrayList<String> list = new ArrayList<>();
        list.add("热选从业");
        list.add("基金从业");
        list.add("银行从业");
        list.add("期货从业");
        list.add("证券从业");


        rvItem.setLayoutManager(new LinearLayoutManager(this));
        HomeAdapter adapter = new HomeAdapter(list, this);
        rvItem.setAdapter(adapter);

        adapter.notifyDataSetChanged();

        btnItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeActivity.this,HomeTabActivity.class));
                finish();
            }
        });
    }
}