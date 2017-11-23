package com.abl.RWD.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;


import com.abl.RWD.R;
import com.abl.RWD.activity.base.BaseActivity;
import com.abl.RWD.adapter.AdapterAcceptDepartment;
import com.abl.RWD.component.CommonHeaderView;
import com.abl.RWD.entity.VAcceptDepartmentEntity;
import com.abl.RWD.listener.IBtnClickListener;

import java.util.ArrayList;

public class NextAccepterActivity extends BaseActivity {
	private CommonHeaderView mHeader;
	private RecyclerView mRecyclerView;
	private AdapterAcceptDepartment mAdapter;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_next_accepter);
		initExtras();
		initLayout();
	}
	private void initExtras() {
	}
	private void initLayout() {
		mHeader= (CommonHeaderView) this.findViewById(R.id.accpter_header);
		mHeader.updateType(CommonHeaderView.TYPE_LEFT_IMAGE_AND_RIGHT_TEXT);
		mHeader.setTitle("下一环节接收人");
		mHeader.setRightText("完成");
		mHeader.setHeaderClickListener(new IBtnClickListener() {
			@Override
			public void btnLeftClick() {
				finish();
			}

			@Override
			public void btnRightClick() {
				super.btnRightClick();
			}
		});
		mRecyclerView= (RecyclerView) this.findViewById(R.id.mRecyclerView);
		LinearLayoutManager manager=new LinearLayoutManager(this);
		mRecyclerView.setLayoutManager(manager);
		mAdapter=new AdapterAcceptDepartment(this,getTestData());
		mRecyclerView.setAdapter(mAdapter);
	}

	private ArrayList<VAcceptDepartmentEntity> getTestData(){
		ArrayList<VAcceptDepartmentEntity> mList=new ArrayList<>();
		for (int i=0;i<10;i++){
			mList.add(new VAcceptDepartmentEntity());
		}
		return mList;
	}
}
