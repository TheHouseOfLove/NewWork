package com.abl.RWD.msglist.item;

import android.content.Context;
import android.text.TextUtils;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.abl.RWD.R;
import com.abl.RWD.entity.VThirdItemEntity;
import com.abl.RWD.msglist.base.BaseViewHolder;
import com.abl.RWD.util.MyLog;

/**
 * Created by yas on 2017/11/16.
 */

public class ReportItemViewHolder extends BaseViewHolder<VThirdItemEntity>{
    private VThirdItemEntity mEntity;
    private TextView text_item1;
    private TextView text_item2;
    private TextView text_item3;
    private TextView text_item4;
    private RelativeLayout relat_Layout;
    public ReportItemViewHolder(Context mContext, ViewGroup parent) {
        super(mContext, parent, R.layout.third_listview_item);
    }

    @Override
    public void initView() {
        relat_Layout= itemView.findViewById(R.id.relat_Layout);
        text_item1 =  itemView.findViewById(R.id.text_item1);
        text_item2 =  itemView.findViewById(R.id.text_item2);
        text_item3 =  itemView.findViewById(R.id.text_item3);
        text_item4 =  itemView.findViewById(R.id.text_item4);
    }

    @Override
    public void setMsg(VThirdItemEntity t) {
        MyLog.debug(TAG,"[setMsg]===============");
        mEntity = t;
        if(t.type==VThirdItemEntity.TYPE_HeTong){
            text_item1.setText(t.mEntity.BMName);

            if ("0".equals(t.mEntity.NianDuHeTongYuSuan)|| TextUtils.isEmpty(t.mEntity.NianDuHeTongYuSuan)) {
                text_item2.setText("-");
            } else {
                text_item2.setText(t.mEntity.NianDuHeTongYuSuan);
            }
            if ("0".equals(t.mEntity.NianDuHeTongShiJi)||TextUtils.isEmpty(t.mEntity.NianDuHeTongShiJi)) {
                text_item3.setText("-");
            } else {
                text_item3.setText(t.mEntity.NianDuHeTongShiJi);
            }
            if ("0%".equals(t.mEntity.WanChengBiLi)) {
                text_item4.setText("-");
            } else {
                text_item4.setText(t.mEntity.WanChengBiLi);
            }

            relat_Layout.setBackgroundResource(R.color.common_white);
            if ("总部小计".equals(t.mEntity.BMName)
                    || "分院小计".equals(t.mEntity.BMName)
                    || "公司小计".equals(t.mEntity.BMName)) {
                relat_Layout.setBackgroundResource(R.color.thirdList_bg);
            }else if ("部门".equals(t.mEntity.BMName)){
                relat_Layout.setBackgroundResource(R.color.thirdList_first_bg);
            }
        }else{
            text_item1.setText(t.shouKuanEntity.BMName);

            if ("0".equals(t.shouKuanEntity.NianDuShouKuanYuSuan)||TextUtils.isEmpty(t.shouKuanEntity.NianDuShouKuanYuSuan)) {
                text_item2.setText("-");
            } else {
                text_item2.setText(t.shouKuanEntity.NianDuShouKuanYuSuan);
            }
            if ("0".equals(t.shouKuanEntity.NianDuShouKuanShiJi)||TextUtils.isEmpty(t.shouKuanEntity.NianDuShouKuanShiJi)) {
                text_item3.setText("-");
            } else {
                text_item3.setText(t.shouKuanEntity.NianDuShouKuanShiJi);
            }
            if ("0%".equals(t.shouKuanEntity.WanChengBiLi)) {
                text_item4.setText("-");
            } else {
                text_item4.setText(t.shouKuanEntity.WanChengBiLi);
            }

            relat_Layout.setBackgroundResource(R.color.common_white);
            if ("总部小计".equals(t.shouKuanEntity.BMName)
                    || "分院小计".equals(t.shouKuanEntity.BMName)
                    || "公司小计".equals(t.shouKuanEntity.BMName)) {
                relat_Layout.setBackgroundResource(R.color.thirdList_bg);
            }else if ("部门".equals(t.shouKuanEntity.BMName)){
                relat_Layout.setBackgroundResource(R.color.thirdList_first_bg);
            }
        }
    }

    @Override
    public VThirdItemEntity getMsg() {
        return mEntity;
    }
}
