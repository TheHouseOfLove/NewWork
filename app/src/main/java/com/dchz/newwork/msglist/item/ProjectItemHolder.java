package com.dchz.newwork.msglist.item;

import android.content.Context;
import android.view.ViewGroup;
import android.widget.TextView;

import com.dchz.newwork.R;
import com.dchz.newwork.entity.PProjectItemEntity;
import com.dchz.newwork.msglist.base.BaseViewHolder;

/**
 * Created by yas on 2017/11/6.
 */

public class ProjectItemHolder extends BaseViewHolder<PProjectItemEntity>{
    private TextView txtName;
    public PProjectItemEntity mEntity;
    public ProjectItemHolder(Context mContext, ViewGroup parent, int layoutId) {
        super(mContext, parent, layoutId);
    }

    @Override
    public void initView() {
        txtName= (TextView) this.itemView.findViewById(R.id.text_XMName);
    }

    @Override
    public void setMsg(PProjectItemEntity pProjectItemEntity) {
        mEntity=pProjectItemEntity;
        txtName.setText(mEntity.XMMingCheng);
    }

    @Override
    public PProjectItemEntity getMsg() {
        return mEntity;
    }
}
