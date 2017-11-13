package com.abl.RWD.activity.base;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;


/***
 * UI Handler相关
 * @date 2015/07/04
 */
public abstract class BaseHandlerActivity extends BaseActivity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	protected Handler uiHandler = new Handler(Looper.getMainLooper()){
		@Override
		public void handleMessage(Message msg) {
			if(isFinishing()){
				return;
			}
			handleMsg(msg);
		};
	};
	
	@Override
	protected void onDestroy() {
		super.onDestroy();
		if(uiHandler != null){
			uiHandler.removeCallbacksAndMessages(null);
		}
	};

	/****
	 * 发送消息到UiHandler
	 * @param msg
	 */
	protected void sendMsg(Message msg){
		uiHandler.sendMessage(msg);
	}

	/***
	 * 延时发送消息到UiHandler
	 * @param msg
	 * @param delay
	 */
	protected void sendMsgDelay(Message msg, long delay){
		uiHandler.sendMessageDelayed(msg, delay);
	}
	
	/***
	 * 由队列中移除MsgDelay
	 * @param what
	 */
	protected void removeMsgDelay(int what){
		uiHandler.removeMessages(what);
	}
	
	protected abstract void handleMsg(Message msg);
}
