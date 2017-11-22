package com.abl.RWD;

import android.app.Application;
import android.content.Context;
import android.support.annotation.NonNull;

import com.abl.RWD.controller.LoginController;
import com.abl.common.http.HttpEngine;
import com.abl.RWD.common.Global;
import com.igexin.sdk.PushManager;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.DefaultRefreshHeaderCreater;
import com.scwang.smartrefresh.layout.api.RefreshHeader;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.constant.SpinnerStyle;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;

public class RWDApp extends Application{
	private final String TAG = "RWDApp";
	static {
		SmartRefreshLayout.setDefaultRefreshHeaderCreater(new DefaultRefreshHeaderCreater() {
			@NonNull
			@Override
			public RefreshHeader createRefreshHeader(Context context, RefreshLayout layout) {
				layout.setPrimaryColorsId(R.color.colorPrimary, android.R.color.white);//全局设置主题颜色
				return new ClassicsHeader(context).setSpinnerStyle(SpinnerStyle.Translate);
			}
		});
	}
	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		//init context
		Global.mContext = getApplicationContext();
		delayTask();
	}

	private void delayTask() {
		// TODO Auto-generated method stub
		Global.postDelay(new Runnable(){

			@Override
			public void run() {
				// TODO Auto-generated method stub
				HttpEngine.getInstance().start();
				PushManager.getInstance().initialize(RWDApp.this);
				LoginController.getInstance().loadLoginInfo();
			}
			
		});
	}
	
}
