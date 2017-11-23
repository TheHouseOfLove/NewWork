package com.abl.RWD.adapter;

import android.content.Context;
import android.view.ViewGroup;

import com.abl.RWD.adapter.base.BaseRecyclerAdapter;
import com.abl.RWD.entity.VJieShouRenEntity;
import com.abl.RWD.msglist.item.AccpterHolder;

import java.util.ArrayList;

/**
 * Created by yas on 2017/11/23.
 */

public class AdapterAccpter extends BaseRecyclerAdapter<AccpterHolder,VJieShouRenEntity>{
    public AdapterAccpter(Context mContext, ArrayList<VJieShouRenEntity> mList) {
        super(mContext, mList);
    }

    @Override
    protected AccpterHolder getViewHolder(ViewGroup parent) {
        return new AccpterHolder(mContext,parent);
    }
}
