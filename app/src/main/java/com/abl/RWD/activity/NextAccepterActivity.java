package com.abl.RWD.activity;

import android.app.Activity;
import android.os.Bundle;
import android.widget.LinearLayout;


import com.abl.RWD.R;
import com.abl.RWD.activity.base.BaseActivity;
import com.abl.RWD.component.AcceptDepartmentView;
import com.abl.RWD.component.CommonHeaderView;
import com.abl.RWD.entity.VAcceptDepartmentEntity;
import com.abl.RWD.listener.IBtnClickListener;
import com.abl.RWD.listener.IItemCheckedListener;
import com.abl.RWD.util.MyLog;

import java.util.ArrayList;

public class NextAccepterActivity extends BaseActivity {
	private CommonHeaderView mHeader;
	private LinearLayout mAcceptDepartmentLayout;
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
		mAcceptDepartmentLayout= (LinearLayout) this.findViewById(R.id.layout_AcceptDepartment);

		ArrayList<VAcceptDepartmentEntity> mList= getTestData();
		initDepartView(mList);
	}

	private void initDepartView(ArrayList<VAcceptDepartmentEntity> mList) {
		if (mList!=null&&mList.size()>0){
			for (int i=0;i<mList.size();i++){
				AcceptDepartmentView itemView=new AcceptDepartmentView(this,itemCheckedListener);
				itemView.setData(mList.get(i),i);
				mAcceptDepartmentLayout.addView(itemView);

			}
		}
	}

	private IItemCheckedListener itemCheckedListener=new IItemCheckedListener() {
		@Override
		public void itemChecked(int pos) {
			MyLog.debug(TAG,"[itemChecked]  pos:"+pos);
		}
	};
	private ArrayList<VAcceptDepartmentEntity> getTestData(){
		ArrayList<VAcceptDepartmentEntity> mList=new ArrayList<>();
		for (int i=0;i<10;i++){
			mList.add(new VAcceptDepartmentEntity());
		}
		return mList;
	}
}
