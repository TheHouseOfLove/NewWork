package com.abl.RWD.adapter;

import android.content.Context;
import android.view.ViewGroup;

import com.abl.RWD.adapter.base.BaseRecyclerAdapter;
import com.abl.RWD.listener.IAccptrChangeListener;
import com.abl.RWD.msglist.item.AccpterItemHolder;

import java.util.ArrayList;

/**
 * Created by yas on 2017/11/21.
 */

public class AdapterAccpterItem extends BaseRecyclerAdapter<AccpterItemHolder,String>{
    private IAccptrChangeListener mListener;
    public AdapterAccpterItem(Context mContext, ArrayList<String> mList, IAccptrChangeListener listener) {
        super(mContext, mList);
        mListener=listener;
    }

    @Override
    protected AccpterItemHolder getViewHolder(ViewGroup parent) {
        return new AccpterItemHolder(mContext,parent,mListener);
    }
}
