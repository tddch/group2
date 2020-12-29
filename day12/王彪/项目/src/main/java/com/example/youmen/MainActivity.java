package com.example.youmen;

import android.Manifest;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.github.dfqin.grantor.PermissionListener;
import com.github.dfqin.grantor.PermissionsUtil;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;
import com.umeng.socialize.media.UMWeb;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btn_share_pop;
    private Button btn_share_qq;
    private Button btn_share_wechat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        checkPermission();
    }

    private void checkPermission() {
        PermissionsUtil.requestPermission(MainActivity.this, new PermissionListener() {
            //已经授权
            @Override
            public void permissionGranted(@NonNull String[] permission) {
                init();
            }

            //未授权
            @Override
            public void permissionDenied(@NonNull String[] permission) {

            }
        }, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE});
    }


    private void init() {


    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        UMShareAPI.get(this).onActivityResult(requestCode, resultCode, data);
    }

    private void initView() {
        btn_share_pop = (Button) findViewById(R.id.btn_share_pop);
        btn_share_qq = (Button) findViewById(R.id.btn_share_qq);
        btn_share_wechat = (Button) findViewById(R.id.btn_share_wechat);
        btn_share_pop.setOnClickListener(this);
        btn_share_qq.setOnClickListener(this);
        btn_share_wechat.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        UMWeb web = new UMWeb("http://www.baidu.com");
        web.setTitle(getString(R.string.app_name));
        web.setThumb(new UMImage(this, R.drawable.ic_launcher));
        web.setDescription(getString(R.string.app_name));
        switch (v.getId()) {
            //调用分享面板分享
            case R.id.btn_share_pop:
                new ShareAction(MainActivity.this).withText("hello").
                        withMedia(web).
                        setDisplayList(SHARE_MEDIA.SINA, SHARE_MEDIA.QQ, SHARE_MEDIA.WEIXIN)
                        .setCallback(shareListener).open();
                break;
            case R.id.btn_share_qq:
                new ShareAction(MainActivity.this)
                        .setPlatform(SHARE_MEDIA.QQ)//传入平台
                        .withMedia(web).
                        setCallback(shareListener)//回调监听器
                        .share();
                break;
            case R.id.btn_share_wechat:
                new ShareAction(MainActivity.this)
                        .setPlatform(SHARE_MEDIA.WEIXIN)//传入平台
                        .withMedia(web).
                        setCallback(shareListener)//回调监听器
                        .share();
                break;
        }
    }


    private UMShareListener shareListener = new UMShareListener() {
        /**
         * @descrption 分享开始的回调
         * @param platform 平台类型
         */
        @Override
        public void onStart(SHARE_MEDIA platform) {

        }

        /**
         * @descrption 分享成功的回调
         * @param platform 平台类型
         */
        @Override
        public void onResult(SHARE_MEDIA platform) {
            Toast.makeText(MainActivity.this, "成功了", Toast.LENGTH_LONG).show();
        }

        /**
         * @descrption 分享失败的回调
         * @param platform 平台类型
         * @param t 错误原因
         */
        @Override
        public void onError(SHARE_MEDIA platform, Throwable t) {
            Toast.makeText(MainActivity.this, "失败了", Toast.LENGTH_LONG).show();
            Log.e("TAG", "失败了" + t.getMessage());

        }

        /**
         * @descrption 分享取消的回调
         * @param platform 平台类型
         */
        @Override
        public void onCancel(SHARE_MEDIA platform) {
            Toast.makeText(MainActivity.this, "取消了", Toast.LENGTH_LONG).show();

        }
    };
}
