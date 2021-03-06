package com.brianbaek.popstar.astore.nearme.gamecenter.wxapi;

import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;
import com.zplay.android.sdk.redpacket.share.weixin.ZplayWechatWXEntry;
import com.zplay.android.sdk.redpacket.wetch.ZplayRedpackLoginWXEntry;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;

public class WXEntryActivity extends Activity implements IWXAPIEventHandler {

	private static final String TAG = "WXEntryActivity";

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		ZplayRedpackLoginWXEntry.onCreate(this, getIntent(), this);
		ZplayWechatWXEntry.onCreate(this, getIntent(), this);
	}

	@Override
	protected void onNewIntent(Intent intent) {
		super.onNewIntent(intent);
		setIntent(intent);
		ZplayRedpackLoginWXEntry.onNewIntent(intent, this);
		ZplayWechatWXEntry.onNewIntent(intent, this);
	}

	@Override
	public void onReq(BaseReq req) {

	}

	@Override
	public void onResp(BaseResp resp) {
		Log.i(TAG, "--onloginFinish, errCode = " + resp.errCode);
		ZplayRedpackLoginWXEntry.onResp(resp, this);
		ZplayWechatWXEntry.onResp(resp, this);
		finish();
	}
}