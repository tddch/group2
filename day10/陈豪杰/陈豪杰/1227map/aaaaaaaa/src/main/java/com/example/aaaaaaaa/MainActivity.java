package com.example.aaaaaaaa;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView mIiImg;
    private Button mBtn;
    private ImageView mImgIi;
    private Button mBtnHg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        mIiImg = (ImageView) findViewById(R.id.img_ii);
        mBtn = (Button) findViewById(R.id.btn);
        mBtn.setOnClickListener(this);

        mImgIi = (ImageView) findViewById(R.id.img_ii);
        mBtnHg = (Button) findViewById(R.id.btn_hg);
        mBtnHg.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn:
                // TODO 20/12/27
                mIiImg.setVisibility(View.VISIBLE);
                break;
            default:
                break;
            case R.id.btn_hg:
                mIiImg.setVisibility(View.GONE);
                break;
        }
    }
}
