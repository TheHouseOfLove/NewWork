package com.abl.RWD.msglist.item;

import android.content.Context;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.abl.RWD.R;
import com.abl.RWD.entity.VAccepterItemEntity;
import com.abl.RWD.listener.IAcceptrChangeListener;
import com.abl.RWD.msglist.base.BaseViewHolder;

/**
 * Created by yas on 2017/11/21.
 */

public class AccpterItemHolder extends BaseViewHolder<VAccepterItemEntity> implements CompoundButton.OnCheckedChangeListener {
    private VAccepterItemEntity mEntity;
    private TextView text_name;
    private CheckBox cb;
    private IAcceptrChangeListener mListener;
    public AccpterItemHolder(Context mContext, ViewGroup parent,IAcceptrChangeListener mListener) {
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
    public void setMsg(VAccepterItemEntity t) {
        mEntity=t;
        text_name.setText(mEntity.name);
        cb.setChecked(mEntity.isChecked);
    }

    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
        mEntity.isChecked=isChecked;
        if(mListener!=null){
            if (isChecked) {
                mListener.selectedListener(pos);
            } else {
                mListener.cancelSelectListener(pos);
            }
        }
    }
}
