package com.abl.RWD.adapter;

import android.content.Context;
import android.view.ViewGroup;

import com.abl.RWD.R;
import com.abl.RWD.adapter.base.BaseRecyclerAdapter;
import com.abl.RWD.entity.PWorkItemEntity;
import com.abl.RWD.msglist.item.WorkItemHolder;

import java.util.ArrayList;

/**
 * Created by yas on 2017/11/6.
 *未完成订单
 */

public class AdapterWorkList extends BaseRecyclerAdapter<WorkItemHolder,PWorkItemEntity>{
    public AdapterWorkList(Context mContext, ArrayList<PWorkItemEntity> mList) {
        super(mContext, mList);
    }

    @Override
    protected WorkItemHolder getViewHolder(ViewGroup parent) {
        return new WorkItemHolder(mContext,parent, R.layout.pending_order_item);
    }
}
