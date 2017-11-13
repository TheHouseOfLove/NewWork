package com.abl.RWD.adapter;

import android.content.Context;
import android.view.ViewGroup;

import com.abl.RWD.R;
import com.abl.RWD.adapter.base.BaseRecyclerAdapter;
import com.abl.RWD.msglist.item.PendingItemHolder;

import java.util.ArrayList;

/**
 * Created by yas on 2017/11/6.
 *未完成订单
 */

public class AdapterPendingOrder extends BaseRecyclerAdapter<PendingItemHolder,String>{
    public AdapterPendingOrder(Context mContext, ArrayList<String> mList) {
        super(mContext, mList);
    }

    @Override
    protected PendingItemHolder getViewHolder(ViewGroup parent) {
        return new PendingItemHolder(mContext,parent, R.layout.pending_order_item);
    }
}
