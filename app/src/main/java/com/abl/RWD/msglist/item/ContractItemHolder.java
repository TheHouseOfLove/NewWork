package com.abl.RWD.msglist.item;

import android.content.Context;
import android.graphics.Color;
import android.view.ViewGroup;
import android.widget.TextView;

import com.abl.RWD.R;
import com.abl.RWD.entity.PContractItemEntity;
import com.abl.RWD.msglist.base.BaseViewHolder;
import com.abl.RWD.util.TimeUtils;

/**
 * Created by yas on 2017/11/6.
 */

public class ContractItemHolder extends BaseViewHolder<PContractItemEntity>{
    private TextView txtJinE;
    private TextView txtName;
    private TextView txtTime;
    private PContractItemEntity mEntity;
    public ContractItemHolder(Context mContext, ViewGroup parent) {
        super(mContext, parent, R.layout.search_contract_itemview);
    }

    @Override
    public void initView() {
        txtJinE=  this.itemView.findViewById(R.id.txtJinE);
        txtName=  this.itemView.findViewById(R.id.txtName);
        txtTime=  this.itemView.findViewById(R.id.txTime);
    }

    @Override
    public void setMsg(PContractItemEntity pContractItemEntity) {
        mEntity=pContractItemEntity;

        txtJinE.setText(mEntity.HTJinE);
        txtName.setText(mEntity.HTMingCheng);
        String[] strs=mEntity.HTQianDingRiQi.split("/");
        if (strs!=null&&strs.length>0){
            int month= Integer.parseInt(strs[1]);
            String year=strs[0];
            if (TimeUtils.getCurrYear().equals(year)){
                this.itemView.setBackgroundColor(Color.parseColor("#ffffff"));
                if (month%2==0){
                    txtTime.setBackgroundColor(Color.parseColor("#FFE3D0"));
                }else{
                    txtTime.setBackgroundColor(Color.parseColor("#CEEEBD"));
                }
            }else{
                this.itemView.setBackgroundColor(Color.parseColor("#f1f1f1"));
                txtTime.setBackgroundColor(Color.parseColor("#f1f1f1"));

            }
            txtTime.setText(month+"月");
        }
    }

    @Override
    public PContractItemEntity getMsg() {
        return mEntity;
    }
}
