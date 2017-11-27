package com.abl.RWD.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.LinearLayout;


import com.abl.RWD.R;
import com.abl.RWD.activity.base.BaseActivity;
import com.abl.RWD.component.AcceptDepartmentView;
import com.abl.RWD.component.CommonHeaderView;
import com.abl.RWD.entity.PWorkDetailEntity;
import com.abl.RWD.listener.IBtnClickListener;
import com.abl.RWD.listener.IItemCheckedListener;
import com.abl.RWD.util.IntentUtils;
import com.google.gson.Gson;


public class NextAccepterActivity extends BaseActivity {
	private CommonHeaderView mHeader;
	private LinearLayout mAcceptDepartmentLayout;
	private int mType;
	private PWorkDetailEntity mDetailEntity;
	private boolean isEnd;
	private int number=0;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_next_accepter);
		initExtras();
		initLayout();
	}
	private void initExtras() {
		mType=getIntent().getIntExtra(IntentUtils.KEY_TYPE,0);
		mDetailEntity= (PWorkDetailEntity) getIntent().getSerializableExtra(IntentUtils.KEY_ENTITY);
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
				String name = getNameStr();
				String id = getUserIdstr();
				if(number>1){
					showToast("只能选一个部门");
					number=0;
				}else {
					Intent intent = new Intent();
					if (!isEnd) {
						if (!TextUtils.isEmpty(name)) {
							intent.putExtra("name", name);
						} else {
							intent.putExtra("name", "下一环节");
						}
					} else {
						intent.putExtra("name", "结束");
					}
					if (!TextUtils.isEmpty(id)) {
						intent.putExtra("id", id);
					}
					setResult(Activity.RESULT_OK, intent);
					finish();
				}
			}
		});
		mAcceptDepartmentLayout= (LinearLayout) this.findViewById(R.id.layout_AcceptDepartment);
		initDepartView(mDetailEntity);
	}

	private void initDepartView(PWorkDetailEntity entity) {
		if (entity!=null){
			switch (mType){
				case 1:
					if (entity.ReferInfo!=null&&entity.ReferInfo.size()>0){
						for (int i=0;i<entity.ReferInfo.size();i++){
							AcceptDepartmentView itemView=new AcceptDepartmentView(this,itemCheckedListener);
							itemView.setReferInfo(entity.ReferInfo.get(i),i);
							mAcceptDepartmentLayout.addView(itemView);
							if ("结束".equals(entity.ReferInfo.get(i).nodeName)){
								isEnd=true;
								return;
							}
						}
					}
					break;
				case 2:
					if (entity.ReturnInfo!=null&&entity.ReturnInfo.size()>0){
						for (int i=0;i<entity.ReturnInfo.size();i++){
							AcceptDepartmentView itemView=new AcceptDepartmentView(this,itemCheckedListener);
							itemView.setReturnInfo(entity.ReturnInfo.get(i),i);
							mAcceptDepartmentLayout.addView(itemView);
							if ("结束".equals(entity.ReturnInfo.get(i).nodeName)){
								isEnd=true;
								return;
							}
						}
					}
					break;
				default:
					break;
			}
		}

	}

	/**
	 * 获取接收人姓名参数
	 * @return
	 */
	private String getNameStr(){
		String name="";
		int size=mAcceptDepartmentLayout.getChildCount();
		for(int i=0;i<size;i++){
			View child=mAcceptDepartmentLayout.getChildAt(i);
			if (child instanceof AcceptDepartmentView){
				AcceptDepartmentView view= (AcceptDepartmentView) child;
				if (!TextUtils.isEmpty(view.getName())){
					name=view.getName();
					number++;
				}
			}
		}
		return name;
	}

	/**
	 * 获取接收人id参数
	 * @return
	 */
	private String getUserIdstr(){
		String id="";
		int size=mAcceptDepartmentLayout.getChildCount();
		for(int i=0;i<size;i++){
			View child=mAcceptDepartmentLayout.getChildAt(i);
			if (child instanceof AcceptDepartmentView){
				AcceptDepartmentView view= (AcceptDepartmentView) child;
				if (!TextUtils.isEmpty(view.getIds())){
					id=view.getIds();
				}
			}
		}
		return id;
	}

	private IItemCheckedListener itemCheckedListener=new IItemCheckedListener() {
		@Override
		public void itemChecked(int pos) {
			for (int i=0;i<mAcceptDepartmentLayout.getChildCount();i++){
				if (i!=pos){
					AcceptDepartmentView itemView= (AcceptDepartmentView) mAcceptDepartmentLayout.getChildAt(i);
					itemView.cancelChecked();
				}
			}
		}
	};
}
