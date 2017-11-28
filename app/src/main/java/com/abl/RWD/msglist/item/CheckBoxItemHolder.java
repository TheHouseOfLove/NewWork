package com.abl.RWD.msglist.item;

import android.content.Context;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.abl.RWD.R;
import com.abl.RWD.entity.VDetailSelectorItemEntity;
import com.abl.RWD.msglist.base.BaseViewHolder;

/**
 * Created by yas on 2017/11/21.
 */

public class CheckBoxItemHolder extends BaseViewHolder<VDetailSelectorItemEntity> implements CompoundButton.OnCheckedChangeListener {
    private VDetailSelectorItemEntity mEntity;
    private TextView text_name;
    private CheckBox cb;
    public CheckBoxItemHolder(Context mContext, ViewGroup parent) {
        super(mContext, parent, R.layout.accpter_list_item);

    }

    @Override
    public void initView() {
        text_name= itemView.findViewById(R.id.text_name);
        cb =  itemView.findViewById(R.id.item_cb);
        cb.setOnCheckedChangeListener(this);
    }

    @Override
    public void setMsg(VDetailSelectorItemEntity t) {
        mEntity=t;
        text_name.setText(mEntity.value);
        cb.setChecked(mEntity.isChecked);
    }

    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
        mEntity.isChecked=isChecked;
    }
}
