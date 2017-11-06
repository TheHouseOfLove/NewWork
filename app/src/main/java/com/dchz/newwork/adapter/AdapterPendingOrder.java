package com.dchz.newwork.adapter;

import android.content.Context;
import android.view.ViewGroup;

import com.dchz.newwork.R;
import com.dchz.newwork.adapter.base.BaseRecyclerAdapter;
import com.dchz.newwork.msglist.item.PendingItemHolder;

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
