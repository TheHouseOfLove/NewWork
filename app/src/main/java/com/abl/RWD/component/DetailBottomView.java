package com.abl.RWD.component;


import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.abl.RWD.R;
import com.abl.RWD.listener.IDetailBottomClickListener;

public class DetailBottomView extends RelativeLayout implements OnClickListener {
	private EditText edit_opinion;
	private LinearLayout layout_type;
	private LinearLayout layout_next;
	private Button btn_do;
	private IDetailBottomClickListener mListener;
	private TextView text_type;
	private TextView text_next;
	public DetailBottomView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		init();
	}
	public DetailBottomView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
		init();
	}
	public DetailBottomView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		// TODO Auto-generated constructor stub
		init();
	}
	private void init() {
		// TODO Auto-generated method stub
		LayoutInflater li = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		li.inflate(R.layout.detail_bottom_view, this, true);
		edit_opinion= this.findViewById(R.id.edit_opinion);
		layout_type= this.findViewById(R.id.layout_type);
		layout_type.setOnClickListener(this);
		layout_next= this.findViewById(R.id.layout_next);
		layout_next.setOnClickListener(this);
		btn_do= this.findViewById(R.id.btn_do);
		btn_do.setOnClickListener(this);
		
		text_type= this.findViewById(R.id.text_type);
		text_next= this.findViewById(R.id.text_next);
	}
	
	public void setType(String type){
		text_type.setText(type);
	}
	public void setname(String name){
		text_next.setText(name);
	}
	public String getOpinion(){
		return edit_opinion.getText().toString();
	}
	@Override
	public void onClick(View view) {
		// TODO Auto-generated method stub
		if(mListener!=null){
			if(view==layout_type){
				mListener.typeClickListener();
			}else if(view==layout_next){
				mListener.nextClickListener();
			}else if(view==btn_do){
				if(TextUtils.isEmpty(text_next.getText().toString())||"下一环节".endsWith(text_next.getText().toString())){
					mListener.noNextListener();
				}else{
					mListener.btnClickListener();
				}
			}
		}
	}
	
	public void setClickListener(IDetailBottomClickListener mListener){
		this.mListener=mListener;
	}
}
