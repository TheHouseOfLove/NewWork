package com.abl.RWD.msglist.item;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.abl.RWD.R;
import com.abl.RWD.listener.IAccptrChangeListener;
import com.abl.RWD.msglist.base.BaseViewHolder;
import com.abl.RWD.util.MyLog;

/**
 * Created by yas on 2017/11/21.
 */

public class AccpterItemHolder extends BaseViewHolder<String> implements CompoundButton.OnCheckedChangeListener {
    private String name;
    private TextView text_name;
    private CheckBox cb;
    private IAccptrChangeListener mListener;
    public AccpterItemHolder(Context mContext, ViewGroup parent,IAccptrChangeListener mListener) {
        super(mContext, parent, R.layout.accpter_list_item);
        this.mListener=mListener;
    }

    @Override
    public void initView() {
        text_name= itemView.findViewById(R.id.text_name);
        cb =  itemView.findViewById(R.id.item_cb);
        cb.setOnCheckedChangeListener(this);
    }

    @Override
    public void setMsg(String t) {
        name=t;
        text_name.setText(name);
    }

    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
        if(mListener!=null){
            if (isChecked) {
                mListener.selectedListener(pos);
            } else {
                mListener.cancelSelectListener(pos);
            }
        }
    }
}
