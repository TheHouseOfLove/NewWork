package com.abl.RWD.adapter;

import android.content.Context;
import android.view.ViewGroup;

import com.abl.RWD.adapter.base.BaseRecyclerAdapter;
import com.abl.RWD.entity.VAcceptDepartmentEntity;
import com.abl.RWD.msglist.item.AccepterDepartmentHolder;

import java.util.ArrayList;

/**
 * Created by yas on 2017/11/23.
 */

public class AdapterAcceptDepartment extends BaseRecyclerAdapter<AccepterDepartmentHolder,VAcceptDepartmentEntity>{
    public AdapterAcceptDepartment(Context mContext, ArrayList<VAcceptDepartmentEntity> mList) {
        super(mContext, mList);
    }

    @Override
    protected AccepterDepartmentHolder getViewHolder(ViewGroup parent) {
        return new AccepterDepartmentHolder(mContext,parent);
    }
}
