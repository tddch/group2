package com.example.bddt.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.bddt.R;

public class MainActivity2 extends AppCompatActivity implements View.OnClickListener {

    private Button mBtn1;
    private Button mBtn2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        initView();
    }

    private void initView() {
        mBtn1 = (Button) findViewById(R.id.btn1);
        mBtn1.setOnClickListener(this);
        mBtn2 = (Button) findViewById(R.id.btn2);
        mBtn2.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn1:
                // TODO 20/12/24
                Intent intent = new Intent(MainActivity2.this,SouActivity.class);
                startActivity(intent);
                break;
            case R.id.btn2:
                // TODO 20/12/24
                Intent intent1 = new Intent(MainActivity2.this,WeiActivity.class);
                startActivity(intent1);
                break;
            default:
                break;
        }
    }
}