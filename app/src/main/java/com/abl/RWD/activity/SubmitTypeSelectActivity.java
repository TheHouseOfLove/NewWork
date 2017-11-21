package com.abl.RWD.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;

import com.abl.RWD.R;
import com.abl.RWD.activity.base.BaseActivity;

public class SubmitTypeSelectActivity extends BaseActivity implements OnClickListener {
	private LinearLayout layout_ok;
	private LinearLayout layout_no;
	private String noTag;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_submit_type);
		initExtras();
		initLayout();
	}
	private void initExtras() {
		// TODO Auto-generated method stub
		noTag=getIntent().getStringExtra("noTag");
	}
	private void initLayout() {
		// TODO Auto-generated method stub
		layout_ok=(LinearLayout) this.findViewById(R.id.layout_ok);
		layout_ok.setOnClickListener(this);
		layout_no=(LinearLayout) this.findViewById(R.id.layout_no);
		layout_no.setOnClickListener(this);
		
		if("A".equals(noTag)){
			layout_no.setVisibility(View.GONE);
		}
	}
	@Override
	public void onClick(View view) {
		// TODO Auto-generated method stub
		if(view==layout_ok){
			Intent intent=new Intent();
			intent.putExtra("type", "同意");
			setResult(Activity.RESULT_OK, intent);
			finish();
		}else if(view==layout_no){
			Intent intent=new Intent();
			intent.putExtra("type", "退回");
			setResult(Activity.RESULT_OK, intent);
			finish();
		}
	}

}
