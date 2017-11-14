package com.abl.RWD.msglist.item;

import android.content.Context;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.abl.RWD.R;
import com.abl.RWD.common.Common;
import com.abl.RWD.entity.PWorkItemEntity;
import com.abl.RWD.msglist.base.BaseViewHolder;

/**
 * Created by yas on 2017/11/6.
 */

public class WorkItemHolder extends BaseViewHolder<PWorkItemEntity>{
    private PWorkItemEntity mEntity;
    private int mType;
    private TextView text_daibanList_name;
    private TextView text_daibanList_type;
//    private TextView text_daibanList_moeny;
    private TextView text_daibanList_time;
    private ImageView image_yiban;
    private Context mContext;
    public WorkItemHolder(Context mContext, ViewGroup parent, int layoutId) {
        super(mContext, parent, layoutId);
    }

    @Override
    public void initView() {
        text_daibanList_name=this.itemView.findViewById(R.id.text_daibanList_name);
        text_daibanList_type= itemView.findViewById(R.id.text_daibanList_type);
//        text_daibanList_moeny= itemView.findViewById(R.id.text_daibanList_moeny);
        text_daibanList_time= itemView.findViewById(R.id.text_daibanList_time);

        image_yiban= itemView.findViewById(R.id.image_yiban);

    }

    @Override
    public void setMsg(PWorkItemEntity s) {
        mEntity=s;
        //set name
        String name=mEntity.ShenQingRen;
        text_daibanList_name.setText(name);
        //set type
        String type=mEntity.FormName;
        text_daibanList_type.setText(type);
        //set moeny
//        if(!"项目下达".equals(type)&&!"法人授权申请".equals(type)&&!"盖章申请".equals(type)){
//            String moeny=mEntity.YWTypeValue;
//            text_daibanList_moeny.setText(moeny);
//        }else{
//            text_daibanList_moeny.setText("");
//        }

        //set time
        String time=mEntity.BLAcceptDate;
        text_daibanList_time.setText(time);


        mType= Common.TYPE_DAIBAN;
    }

    @Override
    public PWorkItemEntity getMsg() {
        return mEntity;
    }
}
