package com.abl.RWD.msglist.item;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.abl.RWD.R;
import com.abl.RWD.entity.VJieShouRenListEntity;
import com.abl.RWD.listener.IAccptrChangeListener;
import com.abl.RWD.msglist.base.BaseViewHolder;

/**
 * Created by yas on 2017/11/21.
 */

public class AccpterItemHolder extends BaseViewHolder<VJieShouRenListEntity> implements CompoundButton.OnCheckedChangeListener {
    private VJieShouRenListEntity mEntity;
    private TextView text_name;
    private CheckBox cb;
    private IAccptrChangeListener mListener;
    protected boolean isSelected;
    public AccpterItemHolder(Context mContext, ViewGroup parent,IAccptrChangeListener mListener) {
        super(mContext, parent, R.layout.accpter_list_item);
        this.mListener=mListener;
    }

    @Override
    public void initView() {
        itemView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                if(mListener!=null){
                    isSelected=!isSelected;
                    cb.setChecked(isSelected);
                }
            }
        });
        text_name= itemView.findViewById(R.id.text_name);
        cb =  itemView.findViewById(R.id.item_cb);
        cb.setOnCheckedChangeListener(this);
    }

    @Override
    public void setMsg(VJieShouRenListEntity t) {
        mEntity=t;
        text_name.setText(t.name);
        cb.setChecked(isSelected);
    }

    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
        if(mListener!=null){
            if (isChecked) {
                isSelected = true;
                mListener.selectedListener(pos);
            } else {
                isSelected = false;
                mListener.cancelSelectListener(pos);
            }
        }
    }
}
