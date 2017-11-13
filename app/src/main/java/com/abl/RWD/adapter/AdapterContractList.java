package com.abl.RWD.adapter;

import android.content.Context;
import android.view.ViewGroup;

import com.abl.RWD.R;
import com.abl.RWD.adapter.base.BaseRecyclerAdapter;
import com.abl.RWD.entity.PContractItemEntity;
import com.abl.RWD.msglist.item.ContractItemHolder;

import java.util.ArrayList;

/**
 * Created by yas on 2017/11/6.
 */

public class AdapterContractList extends BaseRecyclerAdapter<ContractItemHolder,PContractItemEntity> {
    public AdapterContractList(Context mContext, ArrayList<PContractItemEntity> mList) {
        super(mContext, mList);
    }

    @Override
    protected ContractItemHolder getViewHolder(ViewGroup parent) {
        return new ContractItemHolder(mContext,parent, R.layout.search_contract_itemview);
    }
}
