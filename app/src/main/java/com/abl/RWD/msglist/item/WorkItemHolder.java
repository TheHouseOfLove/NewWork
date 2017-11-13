package com.abl.RWD.msglist.item;

import android.content.Context;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.abl.RWD.R;
import com.abl.RWD.entity.PWorkItemEntity;
import com.abl.RWD.msglist.base.BaseViewHolder;

/**
 * Created by yas on 2017/11/6.
 */

public class WorkItemHolder extends BaseViewHolder<PWorkItemEntity>{
    private PWorkItemEntity mEntity;
    private int mType;
    private TextView tvName;
    private TextView tvType;
    private TextView tvMoneny;
    private TextView tvTime;
    private ImageView imgIcon;
    public WorkItemHolder(Context mContext, ViewGroup parent, int layoutId) {
        super(mContext, parent, layoutId);
    }

    @Override
    public void initView() {
        tvName= this.itemView.findViewById(R.id.text_daibanList_name);
        tvType= this.itemView.findViewById(R.id.text_daibanList_type);
        tvMoneny= this.itemView.findViewById(R.id.text_daibanList_moeny);
        tvTime= this.itemView.findViewById(R.id.text_daibanList_time);
        imgIcon=this.itemView.findViewById(R.id.image_yiban);
    }

    @Override
    public void setMsg(PWorkItemEntity s) {
        mEntity=s;
    }

    @Override
    public PWorkItemEntity getMsg() {
        return mEntity;
    }
}
