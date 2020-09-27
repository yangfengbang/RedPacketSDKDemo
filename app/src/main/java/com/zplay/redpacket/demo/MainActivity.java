package com.zplay.redpacket.demo;


import android.app.Activity;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Toast;

import com.brianbaek.popstar.astore.nearme.gamecenter.R;
import com.zplay.android.sdk.redpacket.ZplayRedPacketSDK;
import com.zplay.android.sdk.redpacket.listener.ZplayCashRedPacketListener;
import com.zplay.android.sdk.redpacket.listener.ZplayFriendsGroupListener;
import com.zplay.android.sdk.redpacket.listener.ZplayRedpacketListener;

import java.util.List;


public class MainActivity extends Activity {
    private static final String TAG = "MainActivity";
    private Button showUserView, destroyUserView, showRedPacket, showRedPacketFinal, initIsSuccess, isReady, showCashRedPacket, showCashRedPacketFinal,
            isCashReady, showFriendsGroupRedPacket, showCatRedPacketCenter, getCatNumber;
    private EditText pointX, pointY, width;

    @RequiresApi(api = Build.VERSION_CODES.P)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            // 延伸显示区域到刘海
            WindowManager.LayoutParams lp = getWindow().getAttributes();
            lp.layoutInDisplayCutoutMode = WindowManager.LayoutParams.LAYOUT_IN_DISPLAY_CUTOUT_MODE_SHORT_EDGES;
            getWindow().setAttributes(lp);
            // 设置页面全屏显示
            final View decorView = getWindow().getDecorView();
            decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
        }

        setContentView(R.layout.activity_main);

        showUserView = findViewById(R.id.showUserView);
        destroyUserView = findViewById(R.id.destroyUserView);
        showRedPacket = findViewById(R.id.showRedPacket);
        showRedPacketFinal = findViewById(R.id.showRedPacketFinal);
        initIsSuccess = findViewById(R.id.initIsSuccess);
        isReady = findViewById(R.id.isReady);
        pointX = findViewById(R.id.pointX);
        pointY = findViewById(R.id.pointY);
        width = findViewById(R.id.width);

        showCashRedPacket = findViewById(R.id.showCashRedPacket);
        showCashRedPacketFinal = findViewById(R.id.showCashRedPacketFinal);
        isCashReady = findViewById(R.id.isCashReady);

        showFriendsGroupRedPacket = findViewById(R.id.showFriendsGroupRedPacket);
        showCatRedPacketCenter = findViewById(R.id.showCatRedPacketCenter);
        getCatNumber = findViewById(R.id.getCatNumber);


        ZplayRedPacketSDK.init(this);

        ZplayRedPacketSDK.setRedpacketListener(new ZplayRedpacketListener() {
            @Override
            public void onInitSuccess(String zplayId) {
                Toast.makeText(MainActivity.this, "初始化成功：" + zplayId, Toast.LENGTH_SHORT).show();
                ZplayRedPacketSDK.openFriendsGroupHelp(MainActivity.this, getIntent());
            }

            @Override
            public void onInitFail() {
                Toast.makeText(MainActivity.this, "初始化失败", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onLeftViewHasBeenShown() {
                Toast.makeText(MainActivity.this, "头像显示成功", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onLeftViewHasBeenClicked() {
                Toast.makeText(MainActivity.this, "头像被点击", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onRedPacketIsShowing() {
                Toast.makeText(MainActivity.this, "展示红包", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onRedPacketIsDismissed() {
                Toast.makeText(MainActivity.this, "用户点击红包关闭按钮", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onRedPacketIsClicked() {
                Toast.makeText(MainActivity.this, "红包点击，请展示广告", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onRedPacketFinalIsShowing() {
                Toast.makeText(MainActivity.this, "红包结算页面展示", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onRedPacketFinalIsDismissed(String catNumber) {
                Toast.makeText(MainActivity.this, "红包结算页面关闭,当前获得招财猫数量：" + catNumber, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onShouldDisplayTheNativeAdWith(FrameLayout nativeAdPlaceholderView) {
                Toast.makeText(MainActivity.this, "请将原生广告View添加到原生广告容器中", Toast.LENGTH_SHORT).show();
                ImageView nativeAd = new ImageView(MainActivity.this);
                nativeAd.setBackgroundColor(Color.YELLOW);
                nativeAdPlaceholderView.addView(nativeAd);
            }
        });

        ZplayRedPacketSDK.setCashRedpacketListener(new ZplayCashRedPacketListener() {
            @Override
            public void onCashRedPacketIsShowing() {
                //现金红包展示
                Toast.makeText(MainActivity.this, "现金红包展示", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCashRedPacketIsDismissed() {
                //现金红包关闭
                Toast.makeText(MainActivity.this, "现金红包关闭", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCashRedPacketIsClicked() {
                //现金红包被点击，请展示广告
                Toast.makeText(MainActivity.this, "现金红包被点击，请展示广告", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCashRedPacketFinalIsShowing() {
                //现金红包结算页面展示
                Toast.makeText(MainActivity.this, "现金红包结算页面展示", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCashRedPacketFinalIsDismissed() {
                //现金红包结算页面关闭
                Toast.makeText(MainActivity.this, "现金红包结算页面关闭", Toast.LENGTH_SHORT).show();
            }
        });

        ZplayRedPacketSDK.setFriendsGroupRedpacketListener(new ZplayFriendsGroupListener() {
            @Override
            public void onShowVideoAdHasBeenClicked() {
                //好友拼团界面中的展示视频广告按钮被点击，请展示视频广告

                //视频广告播放结束，请调用下面的接口通知红包SDK给奖励
                ZplayRedPacketSDK.reportFriendsGroupVideoFinal(MainActivity.this);
            }
        });

        showUserView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(pointX.getText()) || pointX.getText().equals("")) {
                    Toast.makeText(MainActivity.this, "请设置头像展示位置的X坐标", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(pointY.getText()) || pointY.getText().equals("")) {
                    Toast.makeText(MainActivity.this, "请设置头像展示位置的Y坐标", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(width.getText()) || width.getText().equals("")) {
                    Toast.makeText(MainActivity.this, "请设置头像展示位置的宽", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (ZplayRedPacketSDK.initIsSuccess()) {
                    ZplayRedPacketSDK.showUserView(MainActivity.this, Integer.valueOf(pointX.getText().toString()), Integer.valueOf(pointY.getText().toString()), Integer.valueOf(width.getText().toString()));
                }
            }
        });

        destroyUserView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ZplayRedPacketSDK.destroyUserView(MainActivity.this);
            }
        });

        showRedPacket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ZplayRedPacketSDK.isCatRedPacketReady()) {
                    ZplayRedPacketSDK.showCatRedPacket(MainActivity.this);
                }
            }
        });

        showRedPacketFinal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ZplayRedPacketSDK.showCatRedPacketFinal(MainActivity.this);
            }
        });

        showCatRedPacketCenter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ZplayRedPacketSDK.showCatRedPacketCenter(MainActivity.this);
            }
        });

        getCatNumber.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "当前获得招财猫卡片数:" + ZplayRedPacketSDK.getCatNumber(MainActivity.this), Toast.LENGTH_SHORT).show();

            }
        });

        initIsSuccess.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "初始化结果：" + ZplayRedPacketSDK.initIsSuccess(), Toast.LENGTH_SHORT).show();
            }
        });

        isReady.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "招财猫红包是否准备好：" + ZplayRedPacketSDK.isCatRedPacketReady(), Toast.LENGTH_SHORT).show();
            }
        });


        showCashRedPacket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ZplayRedPacketSDK.isCashRedPacketReady()) {
                    ZplayRedPacketSDK.showCashRedPacket(MainActivity.this);
                }
            }
        });

        showCashRedPacketFinal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ZplayRedPacketSDK.showCashRedPacketFinal(MainActivity.this);
            }
        });

        isCashReady.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "现金红包是否准备好：" + ZplayRedPacketSDK.isCashRedPacketReady(), Toast.LENGTH_SHORT).show();
            }
        });

        showFriendsGroupRedPacket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ZplayRedPacketSDK.showFriendsGroupRedPacket(MainActivity.this);
            }
        });

        PackageManager mPackageManager = getPackageManager();
        List<PackageInfo> packate = mPackageManager.getInstalledPackages(PackageManager.GET_UNINSTALLED_PACKAGES);

        for (PackageInfo info : packate) {
            if ((info.applicationInfo.flags & ApplicationInfo.FLAG_SYSTEM) == 0) {
                //添加自己的代码即可
                Log.e(TAG, "---package:" + info.packageName);
            }
        }

    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        Log.e(TAG, "onNewIntent:" + getIntent());
        ZplayRedPacketSDK.openFriendsGroupHelp(MainActivity.this, getIntent());
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }
}
