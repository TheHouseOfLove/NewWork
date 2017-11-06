package com.dchz.newwork.msglist.item;

import android.content.Context;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.dchz.newwork.R;
import com.dchz.newwork.msglist.base.BaseViewHolder;

/**
 * Created by yas on 2017/11/6.
 */

public class PendingItemHolder extends BaseViewHolder<String>{
    private String str;
    private int mType;
    private TextView tvName;
    private TextView tvType;
    private TextView tvMoneny;
    private TextView tvTime;
    private ImageView imgIcon;
    public PendingItemHolder(Context mContext, ViewGroup parent, int layoutId) {
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
    public void setMsg(String s) {
        str=s;
    }

    @Override
    public String getMsg() {
        return str;
    }
}
