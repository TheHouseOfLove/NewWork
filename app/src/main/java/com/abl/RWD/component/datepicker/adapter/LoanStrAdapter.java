package com.abl.RWD.component.datepicker.adapter;

import android.content.Context;

import java.util.List;

/**
 * Created by diaosi on 2016/4/27.
 */
public class LoanStrAdapter extends AbstractWheelTextAdapter{
    private List<String> mList;

    public LoanStrAdapter(Context context, List<String> mList) {
        super(context);
        this.mList = mList;
    }

    @Override
    protected CharSequence getItemText(int index) {
        String str = getItemByPos(index);
        return str;
    }

    public String getItemByPos(int pos){
        String str = "";
        if(mList != null && pos < mList.size()){
            str = mList.get(pos);
        }
        return str;
    }

    @Override
    public int getItemsCount() {
        if(mList != null){
            return mList.size();
        }
        return 0;
    }
}
