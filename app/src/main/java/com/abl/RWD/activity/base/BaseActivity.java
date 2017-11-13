package com.abl.RWD.activity.base;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.abl.common.util.DeviceUtil;
import com.abl.RWD.R;
import com.abl.RWD.activity.dialog.MLoadingDialog;
import com.abl.RWD.common.Global;


/***
 * Activity
 */
public class BaseActivity extends AppCompatActivity {
	protected final String TAG = getClass().getSimpleName();
	private Toast toast;
	private MLoadingDialog mLoadingDialog;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
	}
	protected void showLoading(String msg){
		showLoading(msg,true,false);
	}

	protected void showLoading(){
		String str = getResources().getString(R.string.dialog_loading_loaddata_tips);
		showLoading(str);
	}
	private Runnable rTimeout = new Runnable() {
		@Override
		public void run() {
			hideLoadingDialog();
		}
	};

	/**
	 * loading弹窗
	 * @param msg
	 * @param isCancle
	 * @param hasTimeOut
	 */
	protected void showLoading(String msg, boolean isCancle, boolean hasTimeOut){
		hideLoadingDialog();
		if(!isFinishing() && mLoadingDialog == null){
			mLoadingDialog = new MLoadingDialog(this, R.style.MyDialogBg);
			mLoadingDialog.setCanceledOnTouchOutside(isCancle);
			mLoadingDialog.setCancelable(isCancle);
			mLoadingDialog.show();
			if (hasTimeOut) {
				Global.post2UIDelay(rTimeout, 6000);
			}

			if(TextUtils.isEmpty(msg)){
				msg = getResources().getString(R.string.dialog_loading_loaddata_tips);
			}
			if(mLoadingDialog != null){
				mLoadingDialog.setMyTips(msg);
			}
		}
	}
	protected void showLoading(String msg, boolean isCancle){
		showLoading(msg,isCancle,true);
	}

	protected void hideLoadingDialog(){
		Global.removeDelay(rTimeout);
		if(mLoadingDialog != null){
			mLoadingDialog.dismiss();
			mLoadingDialog = null;
		}
	}

	protected void showToast(String str){
		showToast(str,500);
	}

	protected void showToast(String str, int duration){
		if(!TextUtils.isEmpty(str)){
			if(toast != null){
				toast.cancel();
				toast = null;
			}
			toast = new Toast(this);
			View view=getLayoutInflater().inflate(R.layout.my_toast,null);
			TextView textView=(TextView) view.findViewById(R.id.text);
			textView.setText(str);
			toast.setView(view);
			toast.setDuration(duration);
			toast.setGravity(Gravity.TOP, 0, DeviceUtil.mHeight/4+20);
			toast.show();
		}
	}

	protected void hideToast(){
		if(toast != null){
			toast.cancel();
			toast = null;
		}
	}

	/****
	 * Toast
	 * @param str
	 * @param isLong
	 */
	protected void showToast(String str, boolean isLong){
		if(!TextUtils.isEmpty(str)){
			if(toast != null){
				toast.cancel();
				toast = null;
			}
			toast = new Toast(this);
			View view = getLayoutInflater().inflate(R.layout.my_toast, (ViewGroup)findViewById(R.id.toast));
			TextView textView = (TextView) view.findViewById(R.id.text);
			textView.setText(str);
			toast.setView(view);
			toast.setGravity(Gravity.TOP, 0, DeviceUtil.mHeight/4+20);
			toast.setDuration(Toast.LENGTH_LONG);
			toast.show();
		}
	}


	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		hideLoadingDialog();
		hideToast();
	}
}
