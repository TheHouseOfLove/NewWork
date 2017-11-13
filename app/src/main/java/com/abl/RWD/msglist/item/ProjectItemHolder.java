package com.abl.RWD.msglist.item;

import android.content.Context;
import android.view.ViewGroup;
import android.widget.TextView;

import com.abl.RWD.R;
import com.abl.RWD.entity.PProjectItemEntity;
import com.abl.RWD.msglist.base.BaseViewHolder;

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
