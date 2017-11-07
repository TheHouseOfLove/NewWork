package com.dchz.newwork.component;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.dchz.newwork.R;
import com.dchz.newwork.common.Global;
import com.dchz.newwork.listener.IReportSubClickListener;

public class ReportSubView extends RelativeLayout implements OnClickListener {

	private TextView text_lastYear;
	private TextView text_thisYear;
	private TextView text_nextYear;
	private TextView text_info;
	private IReportSubClickListener mListener;
	public ReportSubView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		init();
	}
	public ReportSubView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
		init();
	}
	public ReportSubView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		// TODO Auto-generated constructor stub
		init();
	}
	private void init() {
		// TODO Auto-generated method stub
		LayoutInflater li = (LayoutInflater)getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		li.inflate(R.layout.report_sub_view,this,true);
		text_lastYear=(TextView) this.findViewById(R.id.text_lastYear);
		text_lastYear.setOnClickListener(this);
		text_thisYear=(TextView) this.findViewById(R.id.text_thisYear);
		text_thisYear.setOnClickListener(this);
		text_nextYear=(TextView) this.findViewById(R.id.text_nextYear);
		text_nextYear.setOnClickListener(this);
		text_info=(TextView) this.findViewById(R.id.text_info);
	}
	public void setClickListener(IReportSubClickListener mListener){
		this.mListener=mListener;
	}
//	public void setInfo(String info){
//		text_info.setText(info);
//	}
	@Override
	public void onClick(View view) {
		if(mListener!=null){
		// TODO Auto-generated method stub
			if(view==text_lastYear){
				mListener.lastYearClickListener();
			}else if(view==text_thisYear){
				mListener.thistYearClickListener();
			}else if(view==text_nextYear){
				mListener.nextYearClickListener();
			}
		}
	}

}
