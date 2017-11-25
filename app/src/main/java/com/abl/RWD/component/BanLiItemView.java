package com.abl.RWD.component;


import android.content.Context;
import android.view.LayoutInflater;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.abl.RWD.R;
import com.abl.RWD.entity.PBanLiYiJianEntity;
import com.abl.RWD.util.DateUtil;


public class BanLiItemView extends RelativeLayout {
	private TextView text_item_name;
	private TextView text_item_content;
	private TextView text_item_time;
	private TextView text_item_isOk;
	private PBanLiYiJianEntity mEntity;
	public BanLiItemView(Context context) {
		super(context);
		onInflate();
		// TODO Auto-generated constructor stub
	}
	public void setMsg(PBanLiYiJianEntity entity) {
		// TODO Auto-generated method stub
		mEntity=entity;
//		set name
		String name=entity.JDName;
		String[] str=name.split(":");
		text_item_name.setText(str[1]);
		text_item_content.setText(str[0]);
		
		//set content
		String content=entity.BLOpinion;
		
		//set time
		String time=entity.BLAcceptDate;
//		time= DateUtil.DateToStr(DateUtil.StrToDate(time));
		time.replace(" ","\n");
		text_item_time.setText(time);
		
		//set isOk
		text_item_isOk.setText(content);
	}
	public PBanLiYiJianEntity getMsg() {
		// TODO Auto-generated method stub
		return mEntity;
	}
	public void onInflate() {
		// TODO Auto-generated method stub
		LayoutInflater li = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		li.inflate(R.layout.banliyijian_list_item, this, true);
		this.text_item_name= this.findViewById(R.id.text_item_name);
		this.text_item_content= this.findViewById(R.id.text_item_content);
		this.text_item_time= this.findViewById(R.id.text_item_time);
		this.text_item_isOk= this.findViewById(R.id.text_item_isOk);
	}
}
