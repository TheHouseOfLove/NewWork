package com.dchz.newwork.msglist.item;

import android.content.Context;
import android.view.ViewGroup;
import android.widget.TextView;

import com.dchz.newwork.R;
import com.dchz.newwork.msglist.base.BaseViewHolder;

/**
 * Created by yas on 2017/11/6.
 */

public class PendingItemHolder extends BaseViewHolder<String>{
    private String str;
    private TextView tvName;
    public PendingItemHolder(Context mContext, ViewGroup parent, int layoutId) {
        super(mContext, parent, layoutId);
    }

    @Override
    public void initView() {
        tvName=this.itemView.findViewById(R.id.tv_name);
    }

    @Override
    public void setMsg(String s) {
        str=s;
        tvName.setText(s);
    }

    @Override
    public String getMsg() {
        return str;
    }
}
