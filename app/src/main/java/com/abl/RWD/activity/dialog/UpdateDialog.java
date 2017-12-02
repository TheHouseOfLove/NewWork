package com.abl.RWD.activity.dialog;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;

import com.abl.RWD.R;
import com.abl.common.util.DeviceUtil;

public class UpdateDialog extends Dialog implements OnClickListener {
	private final String TAG = "UpdateDialog";

	private Button btnOk;
	private String downloadUrl;
	public UpdateDialog(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		init();
	}

	public UpdateDialog(Context context, int theme) {
		super(context, theme);
		// TODO Auto-generated constructor stub
		init();
	}
	
	private void init(){
		Window window = getWindow();
		window.setGravity(Gravity.CENTER);
		setCancelable(true);
		setCanceledOnTouchOutside(false);
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		LayoutInflater li = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View mView = li.inflate(R.layout.dialog_update_layout,null);
		
		this.btnOk = mView.findViewById(R.id.dialog_upgrade_btn_ok);
		this.btnOk.setOnClickListener(this);
		
		int mWidth = DeviceUtil.mWidth;
		mWidth = mWidth - 300;
		ViewGroup.LayoutParams llp = new ViewGroup.LayoutParams(mWidth, ViewGroup.LayoutParams.WRAP_CONTENT);
		setContentView(mView, llp);
	}

	public void setDownloadUrl(String url){
		downloadUrl=url;
	}
	@Override
	public void onClick(View view) {
		// TODO Auto-generated method stub
			if(view == this.btnOk){
				Uri uri = Uri.parse(downloadUrl);
				Intent intent = new Intent(Intent.ACTION_VIEW, uri);
				getContext().startActivity(intent);
			}
			dismiss();
		}
	}

