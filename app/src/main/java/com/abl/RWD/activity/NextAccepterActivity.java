package com.abl.RWD.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AbsListView.LayoutParams;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;


import com.abl.RWD.R;
import com.abl.RWD.activity.base.BaseActivity;
import com.abl.RWD.component.NextAccpterItemView;
import com.abl.RWD.entity.VJieShouRenEntity;
import com.abl.RWD.listener.IItemCheckedListener;
import com.abl.RWD.util.IntentUtils;
import com.abl.RWD.util.MyLog;

import java.util.ArrayList;

public class NextAccepterActivity extends BaseActivity {
	private ArrayList<VJieShouRenEntity> mList;
	private ScrollView mScrollView;
	private ArrayList<NextAccpterItemView> mViewList;
	private String name;
	private String id;
	private TextView text_back;
	private int number=0;
	private boolean isEnd;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_next_accepter);
		initExtras();
		initLayout();
	}

	private void initLayout() {
		// TODO Auto-generated method stub
		mViewList=new ArrayList<>();
		
		text_back=(TextView) findViewById(R.id.text_back);
		text_back.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				getName();
				if(number>1){
					showToast("只能选一个部门");
					number=0;
				}else{
					Intent intent=new Intent();
					if(!isEnd){
						if(!TextUtils.isEmpty(name)){
							intent.putExtra("name", name);
						}else{
							intent.putExtra("name", "下一环节");
						}
					}else{
						intent.putExtra("name", "结束");
					}
					if(!TextUtils.isEmpty(id)){
						intent.putExtra("id", id);
					}
					setResult(Activity.RESULT_OK, intent);
					finish();
				}
			}
		});
		
		mScrollView = (ScrollView) this.findViewById(R.id.mScrollView);
		LinearLayout layout = new LinearLayout(this);
		layout.setOrientation(LinearLayout.VERTICAL);
		LayoutParams params = new LayoutParams(LayoutParams.MATCH_PARENT,
				LayoutParams.WRAP_CONTENT);
		layout.setLayoutParams(params);
		for (int i = 0; i < mList.size(); i++) {
			NextAccpterItemView itemView = new NextAccpterItemView(this);
			itemView.setData(mList.get(i),i);

			itemView.showTitle();
			
			String title="";
			if (mList.get(i).type == VJieShouRenEntity.TYPE_HT) {
				title=mList.get(i).HTInfoEntity.nodeName;
				itemView.setTitle(title);
				if("结束".equals(title)){
					isEnd=true;
				}
			} else {
				title=mList.get(i).TJInfoEntity.nodeName;
				itemView.setTitle(title);
				if("结束".equals(title)){
					isEnd=true;
				}
			}
			itemView.setCheckedListener(mCheckedListener);
			layout.addView(itemView);
			mViewList.add(itemView);
		}
		mScrollView.addView(layout);
	}

	private void initExtras() {
		mList = new ArrayList<VJieShouRenEntity>();
		Intent intent = getIntent();
		mList = (ArrayList<VJieShouRenEntity>) intent
				.getSerializableExtra(IntentUtils.KEY_ENTITY);
	}
		private void getName(){
				for(int i=0;i<mViewList.size();i++){
					if(!TextUtils.isEmpty(mViewList.get(i).getName())){
						name=mViewList.get(i).getName();
						id=mViewList.get(i).getIds();
						number++;
					}
				}
	}
	private IItemCheckedListener mCheckedListener=new IItemCheckedListener() {
		@Override
		public void onlyOn(int pos,int index) {
			// TODO Auto-generated method stub
			for(int i=0;i<mViewList.size();i++){
				if(i==index){
//					mViewList.get(i).cancelOther(pos);
//					mViewList.get(i).getAdapter().notifyDataSetChanged();
//					MyLog.debug(TAG, "[onlyOn]  adapter: "+mViewList.get(i).getAdapter().toString());
				}else{
//					mViewList.get(i).cancelChecked();
//					mViewList.get(i).getAdapter().notifyDataSetChanged();
				}
			}
		}
	};
}
