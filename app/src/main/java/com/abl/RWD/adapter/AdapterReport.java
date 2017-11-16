package com.abl.RWD.adapter;

import android.content.Context;
import android.view.ViewGroup;

import com.abl.RWD.adapter.base.BaseRecyclerAdapter;
import com.abl.RWD.entity.VThirdItemEntity;
import com.abl.RWD.msglist.item.ReportItemViewHolder;

import java.util.ArrayList;

/**
 * Created by yas on 2017/11/16.
 */

public class AdapterReport extends BaseRecyclerAdapter<ReportItemViewHolder,VThirdItemEntity>{
    public AdapterReport(Context mContext, ArrayList<VThirdItemEntity> mList) {
        super(mContext, mList);
    }

    @Override
    protected ReportItemViewHolder getViewHolder(ViewGroup parent) {
        return new ReportItemViewHolder(mContext,parent);
    }
}
