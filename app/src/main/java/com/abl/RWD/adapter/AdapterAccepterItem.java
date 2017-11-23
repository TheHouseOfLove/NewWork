package com.abl.RWD.adapter;

import android.content.Context;
import android.view.ViewGroup;

import com.abl.RWD.adapter.base.BaseRecyclerAdapter;
import com.abl.RWD.entity.VAccepterItemEntity;
import com.abl.RWD.listener.IAcceptrChangeListener;
import com.abl.RWD.msglist.item.AccpterItemHolder;

import java.util.ArrayList;

/**
 * Created by yas on 2017/11/21.
 */

public class AdapterAccepterItem extends BaseRecyclerAdapter<AccpterItemHolder,VAccepterItemEntity>{
    private IAcceptrChangeListener mListener;
    public AdapterAccepterItem(Context mContext, ArrayList<VAccepterItemEntity> mList, IAcceptrChangeListener listener) {
        super(mContext, mList);
        mListener=listener;
    }

    @Override
    protected AccpterItemHolder getViewHolder(ViewGroup parent) {
        return new AccpterItemHolder(mContext,parent,mListener);
    }

    public void clearAllChecked(){
        ArrayList<VAccepterItemEntity> mList=getList();
        if (mList!=null&&mList.size()>0){
            for (int i=0;i<mList.size();i++){
                VAccepterItemEntity itemEntity=mList.get(i);
                itemEntity.isChecked=false;
            }
            notifyDataSetChanged();
        }
    }
    public void clearOtherChecked(int pos){
        ArrayList<VAccepterItemEntity> mList=getList();
        if (mList!=null&&mList.size()>0){
            for (int i=0;i<mList.size();i++){
                if (i!=pos) {
                    VAccepterItemEntity itemEntity = mList.get(i);
                    itemEntity.isChecked = false;
                }
            }
            notifyDataSetChanged();
        }
    }
    public boolean hasItemChecked(){
        ArrayList<VAccepterItemEntity> mList=getList();
        if (mList!=null&&mList.size()>0){
            for (int i=0;i<mList.size();i++){
                VAccepterItemEntity itemEntity = mList.get(i);
                if (itemEntity.isChecked){
                    return true;
                }
            }
            notifyDataSetChanged();
        }
        return false;
    }
}
