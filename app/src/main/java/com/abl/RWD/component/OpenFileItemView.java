package com.abl.RWD.component;


import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.abl.RWD.R;
import com.abl.RWD.entity.PAttInfoItemEntity;
import com.abl.RWD.entity.PFuJianEntity;
import com.abl.RWD.listener.IWordOpenListener;

public class OpenFileItemView extends RelativeLayout implements OnClickListener {
	private TextView text_desp;
	private TextView text_open;
	private IWordOpenListener mListener;
	private PAttInfoItemEntity entity;
	private View viewLine;
	private TextView text_title;
	public OpenFileItemView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		init();
	}
	public OpenFileItemView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
		init();
	}
	public OpenFileItemView(Context context, AttributeSet attrs,
                            int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		// TODO Auto-generated constructor stub
		init();
	}
	private void init() {
		// TODO Auto-generated method stub
		LayoutInflater li = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		li.inflate(R.layout.list_open_word, this, true);
		text_desp= this.findViewById(R.id.text_desp);
		text_open= this.findViewById(R.id.text_open);
		text_open.setOnClickListener(this);
		viewLine=this.findViewById(R.id.view);
		text_title=  this.findViewById(R.id.text_title);
	}
	
	public void setData(PAttInfoItemEntity entity){
		this.entity=entity;
		String fileName=entity.AttName.replaceAll("&amp;","&");
		text_desp.setText(fileName);
		if (entity.isFirst){
			text_title.setVisibility(View.VISIBLE);
			text_title.setText(entity.FieldName);
		}else{
			text_title.setVisibility(View.GONE);
		}
		if (entity.isLast){
			viewLine.setVisibility(View.VISIBLE);
		}else{
			viewLine.setVisibility(View.GONE);
		}
	}
	public void setEmptyData(){
		text_desp.setText("");
		text_title.setVisibility(View.VISIBLE);
		text_open.setVisibility(View.GONE);
	}
	public void showTitle(){
		text_title.setVisibility(View.VISIBLE);
	}
	public void hideLine(){
		viewLine.setVisibility(View.GONE);
	}
	public void setOpenListener(IWordOpenListener mListener){
		this.mListener=mListener;
	}
	@Override
	public void onClick(View view) {
		// TODO Auto-generated method stub
		if(mListener!=null){
			if(view==text_open){
				mListener.openListener(entity.AttName,entity.FJPath);
			}
		}
	}

}
