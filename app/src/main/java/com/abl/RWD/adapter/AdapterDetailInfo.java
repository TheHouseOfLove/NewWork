package com.abl.RWD.adapter;

import android.content.Context;
import android.view.ViewGroup;

import com.abl.RWD.adapter.base.BaseRecyclerAdapter;
import com.abl.RWD.entity.PWorkDetailItemEntity;
import com.abl.RWD.msglist.item.DetailItemHolder;

import java.util.ArrayList;

/**
 * Created by yas on 2017/11/21.
 */

public class AdapterDetailInfo extends BaseRecyclerAdapter<DetailItemHolder,PWorkDetailItemEntity>{
    public AdapterDetailInfo(Context mContext, ArrayList<PWorkDetailItemEntity> mList) {
        super(mContext, mList);
    }

    @Override
    protected DetailItemHolder getViewHolder(ViewGroup parent) {
        return new DetailItemHolder(mContext,parent);
    }
}
