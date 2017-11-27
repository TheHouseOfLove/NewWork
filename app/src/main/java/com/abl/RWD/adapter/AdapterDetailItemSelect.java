package com.abl.RWD.adapter;

import android.content.Context;

import com.abl.RWD.component.datepicker.adapter.AbstractWheelTextAdapter;
import com.abl.RWD.entity.VDetailSelectorItemEntity;

import java.util.List;

/**
 * Created by yas on 2017/08/17
 * 门店选择adapter
 */

public class AdapterDetailItemSelect extends AbstractWheelTextAdapter {
    private List<VDetailSelectorItemEntity> mList;
    public AdapterDetailItemSelect(Context context, List<VDetailSelectorItemEntity> mList) {
        super(context);
        this.mList=mList;
    }

    @Override
    public int getItemsCount() {
        if (mList!=null){
            return mList.size();
        }
        return 0;
    }

    @Override
    protected CharSequence getItemText(int index) {
        VDetailSelectorItemEntity entity=mList.get(index);
        if (entity!=null){
            return entity.value;
        }
        return null;
    }

    public VDetailSelectorItemEntity getItemByPos(int pos){
        VDetailSelectorItemEntity entity = null;
        if(mList != null && pos < mList.size()){
            entity = mList.get(pos);
        }
        return entity;
    }
}
