package com.abl.RWD;

import android.app.Application;

import com.abl.common.http.HttpEngine;
import com.abl.RWD.common.Global;
import com.igexin.sdk.PushManager;

public class RWDApp extends Application{
	private final String TAG = "RWDApp";
	
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
			}
			
		});
	}
	
}
