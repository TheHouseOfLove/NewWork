package com.abl.RWD.msglist;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.abl.RWD.R;
import com.abl.RWD.common.Global;


/**
 * listView为空时显示的View
 * @author yas
 *
 */
public class ListViewEmptyView extends RelativeLayout {
	private ImageView image_empty;
	private TextView text_empty;
	public ListViewEmptyView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		init();
	}
	public ListViewEmptyView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
		init();
	}
	public ListViewEmptyView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		// TODO Auto-generated constructor stub
		init();
	}
	private void init() {
		// TODO Auto-generated method stub
		LayoutInflater li=(LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		li.inflate(R.layout.list_empty, this,true);
		image_empty=(ImageView) this.findViewById(R.id.image_empty);
		text_empty=(TextView) this.findViewById(R.id.text_empty);
	}

	//设置类型
	public void setType(int type){
		Drawable drawable=Global.getContext().getResources().getDrawable(R.mipmap.icon_listview_empty);
		String	str="暂无数据";
		image_empty.setImageDrawable(drawable);
		text_empty.setText(str);
	}
}
