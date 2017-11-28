package com.abl.RWD.adapter;

import android.content.Context;
import android.view.ViewGroup;

import com.abl.RWD.adapter.base.BaseRecyclerAdapter;
import com.abl.RWD.entity.VDetailSelectorItemEntity;
import com.abl.RWD.msglist.item.CheckBoxItemHolder;

import java.util.ArrayList;

/**
 * Created by yas on 2017/11/28.
 */

public class AdapterCheckBox extends BaseRecyclerAdapter<CheckBoxItemHolder,VDetailSelectorItemEntity>{
    public AdapterCheckBox(Context mContext, ArrayList<VDetailSelectorItemEntity> mList) {
        super(mContext, mList);
    }

    @Override
    protected CheckBoxItemHolder getViewHolder(ViewGroup parent) {
        return new CheckBoxItemHolder(mContext,parent);
    }
    public ArrayList<VDetailSelectorItemEntity> getSelectedItems(){
        ArrayList<VDetailSelectorItemEntity> mList=new ArrayList<>();
        ArrayList<VDetailSelectorItemEntity> datas=getList();
        if (datas!=null&&datas.size()>0){
            for (int i=0;i<datas.size();i++){
                VDetailSelectorItemEntity itemEntity=datas.get(i);
                if (itemEntity.isChecked){
                    mList.add(itemEntity);
                }
            }
        }
        return mList;
    }
}
