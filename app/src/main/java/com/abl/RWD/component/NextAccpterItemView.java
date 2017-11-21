package com.abl.RWD.component;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.abl.RWD.R;
import com.abl.RWD.adapter.AdapterAccpter;
import com.abl.RWD.component.fullrecyclerview.FullyLinearLayoutManager;
import com.abl.RWD.entity.VJieShouRenEntity;
import com.abl.RWD.entity.VJieShouRenListEntity;
import com.abl.RWD.listener.IAccptrChangeListener;
import com.abl.RWD.listener.IItemCheckedListener;
import com.abl.RWD.util.MyLog;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class NextAccpterItemView extends LinearLayout {
	private static final String TAG = "NextAccpterItemView";
	private RecyclerView mMsgPage;
	private AdapterAccpter mAdapter;
	private TextView text_bumen;
	private ArrayList<String> userIds;
	private ArrayList<VJieShouRenListEntity> arr;
	private String isRadio;
	private IItemCheckedListener mCheckedListener;

	private int index;
	private Map<Integer,String> mapName;
	private Map<Integer,String> mapId;
	private String name="";
	private String ids="";
	public NextAccpterItemView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		mapName=new HashMap<Integer, String>();
		mapId=new HashMap<Integer, String>();
		onInflate();
	}

	public void setData(VJieShouRenEntity t, int index) {
		// TODO Auto-generated method stub
		this.index=index;
		userIds = new ArrayList<String>();
		arr = new ArrayList<VJieShouRenListEntity>();
		if (t != null) {
			if (t.type == VJieShouRenEntity.TYPE_HT) {
				String userList = t.HTInfoEntity.usersList;
				if (!TextUtils.isEmpty(userList)) {
					MyLog.debug(TAG, "[setData]  userList:" + userList);
					String[] users = userList.split(",");
					for (int i = 0; i < users.length; i++) {
						userIds.add(users[i].split("\\|")[0]);
						MyLog.debug(TAG,
								"[setData]  userId:" + users[i].split("\\|")[0]);
						VJieShouRenListEntity entity = new VJieShouRenListEntity();
						entity.name = users[i].split("\\|")[1];
						MyLog.debug(TAG,
								"[setData]  name:" + users[i].split("\\|")[1]);
						arr.add(entity);
					}
				}
			} else if (t.type == VJieShouRenEntity.TYPE_TJ) {
				isRadio = t.TJInfoEntity.userBLType;
				String userList = t.TJInfoEntity.usersList;
				if (!TextUtils.isEmpty(userList)) {
					String[] users = userList.split(",");
					for (int i = 0; i < users.length; i++) {
						userIds.add(users[i].split("\\|")[0]);
						VJieShouRenListEntity entity = new VJieShouRenListEntity();
						entity.name = users[i].split("\\|")[1];
						arr.add(entity);
					}
				}
			}
			mAdapter = new AdapterAccpter(getContext(),arr,mListener);
			mMsgPage.setAdapter(mAdapter);
		}
	}

	public void onInflate() {
		// TODO Auto-generated method stub
		LayoutInflater li = (LayoutInflater) getContext().getSystemService(
				Context.LAYOUT_INFLATER_SERVICE);
		li.inflate(R.layout.jieshouren_item, this, true);
		mMsgPage = this.findViewById(R.id.mMsgPage);
		FullyLinearLayoutManager manager=new FullyLinearLayoutManager(getContext());
		mMsgPage.setLayoutManager(manager);
		text_bumen =  this.findViewById(R.id.text_bumen);
	}

	public void showTitle() {
		text_bumen.setVisibility(View.VISIBLE);
	}

	public void setTitle(String str) {
		text_bumen.setText(str);
	}

	public void setCheckedListener(IItemCheckedListener mCheckedListener) {
		this.mCheckedListener = mCheckedListener;
	}

	public void cancelChecked() {
//		for (int i = 0; i < arr.size(); i++) {
//			mAdapter.getIsSelected().put(i, false);
//			mAdapter.notifyDataSetChanged();
//		}
	}
	public void cancelOther(int pos){
//		for (int i = 0; i < arr.size(); i++) {
//			if(i!=pos){
//				mAdapter.getIsSelected().put(i, false);
//			}else{
//				mAdapter.getIsSelected().put(i, true);
//			}
//		}
	}
	public String getName(){
		if("radio".equals(isRadio)){
			return name;
		}else{
			name="";
			for(Integer key:mapName.keySet()){
				name=name+" "+mapName.get(key);
			}
//			MyLog.debug(TAG, "[getName] name="+name);
			if(!TextUtils.isEmpty(name)&&name.length()>1){
				name=name.substring(1);
			}
			return name;
		}
	}
	public String getIds(){
		if("radio".equals(isRadio)){
			return ids;
		}else{
			ids="";
			for(Integer key:mapId.keySet()){
				ids=ids+","+mapId.get(key);
			}
//			MyLog.debug(TAG, "[getName] ids="+ids);
			if(!TextUtils.isEmpty(ids)&&ids.length()>1){
				ids=ids.substring(1);
			}
			return ids;
		}
	}
	private IAccptrChangeListener mListener = new IAccptrChangeListener() {

		@Override
		public void selectedListener(int pos) {
			// TODO Auto-generated method stub
			if (mCheckedListener != null) {
				if("radio".equals(isRadio)){
					mCheckedListener.onlyOn(pos,index);
					name=arr.get(pos).name;
					ids=userIds.get(pos);
				}else{
						if(!mapName.containsKey(pos)){
							mapName.put(pos, arr.get(pos).name);
//							MyLog.debug(TAG, "[IAccptrChangeListener]  pos:"+pos+"  name"+arr.get(pos).name);
						}	
						if(!mapId.containsKey(pos)){
							mapId.put(pos, userIds.get(pos));
						}	
//					}
				}
			}
		}

		@Override
		public void cancelSelectListener(int pos) {
			// TODO Auto-generated method stub
//			mAdapter.getIsSelected().put(pos, false);
//			mAdapter.notifyDataSetChanged();
//			if(!"radio".equals(isRadio)){
//				mapName.remove(pos);
//				mapId.remove(pos);
//			}
		}
	};
}
