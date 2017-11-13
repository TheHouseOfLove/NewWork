package com.dchz.newwork;

import android.app.Application;

import com.dchz.common.http.HttpEngine;
import com.dchz.newwork.common.Global;

public class DCHZApp extends Application{
	private final String TAG = "DCHZApp";
	
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
			}
			
		});
	}
	
}
