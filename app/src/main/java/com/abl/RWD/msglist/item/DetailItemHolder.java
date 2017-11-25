package com.abl.RWD.msglist.item;

import android.content.Context;
import android.text.InputFilter;
import android.text.InputType;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.abl.RWD.R;
import com.abl.RWD.entity.PYWInfoItemEntity;
import com.abl.RWD.msglist.base.BaseViewHolder;

/**
 * Created by yas on 2017/11/21.
 */

public class DetailItemHolder extends BaseViewHolder<PYWInfoItemEntity> implements View.OnClickListener {
    private TextView tvTitle;
    private TextView tvInfo;
    private EditText edInfo;
    private PYWInfoItemEntity mEntity;
    private String paramValue;
    private LinearLayout Layout;
    private View divider;
    public DetailItemHolder(Context mContext, ViewGroup parent) {
        super(mContext, parent, R.layout.detail_item_view);
    }

    @Override
    public void initView() {
        tvTitle=itemView.findViewById(R.id.tv_title);
        tvInfo=itemView.findViewById(R.id.tv_content);
        tvInfo.setOnClickListener(this);
        edInfo=itemView.findViewById(R.id.edit_content);
        divider=itemView.findViewById(R.id.divider);
        Layout=itemView.findViewById(R.id.layout_content);
    }

    @Override
    public void setMsg(PYWInfoItemEntity pDetailItemEntity) {
        mEntity=pDetailItemEntity;
        if (pDetailItemEntity!=null){
            if (TextUtils.isEmpty(pDetailItemEntity.FieldName)){
                divider.setVisibility(View.VISIBLE);
                Layout.setVisibility(View.GONE);
                return;
            }
            divider.setVisibility(View.GONE);
            Layout.setVisibility(View.VISIBLE);
            tvTitle.setText(pDetailItemEntity.FieldName);
            tvInfo.setText(pDetailItemEntity.FieldValue);
            edInfo.setText(pDetailItemEntity.FieldValue);
            if (!"修改".equals(pDetailItemEntity.CRName)){
                //不能修改时，使用textView显示数据
                tvInfo.setEnabled(false);
                tvInfo.setVisibility(View.VISIBLE);
                edInfo.setVisibility(View.GONE);
            }else{
                //可以修改时，根据不同限制条件作出不同操作
                tvInfo.setEnabled(true);
                if ("TextBox_1".equals(pDetailItemEntity.ControlType)||"TextBox_2".equals(pDetailItemEntity.ControlType)){
                    if ("TextBox_1".equals(pDetailItemEntity.ControlType)){
                        edInfo.setSingleLine();
                    }
                    //单行输入框
                    tvInfo.setVisibility(View.GONE);
                    edInfo.setVisibility(View.VISIBLE);
                    InputFilter[] filters = {new InputFilter.LengthFilter(Integer.valueOf(pDetailItemEntity.DataLength))};
                    edInfo.setFilters(filters);
                    if ("int".equals(pDetailItemEntity.DataType)||"float".equals(pDetailItemEntity.DataType)
                            ||"decimal".equals(pDetailItemEntity.DataType)){
                        edInfo.setInputType(InputType.TYPE_CLASS_NUMBER);
                    }
                }else{
                    tvInfo.setVisibility(View.VISIBLE);
                    edInfo.setVisibility(View.GONE);
                }
            }
        }
    }
    public void setParamValue(String value){
        paramValue=value;
    }
    public String getParam(){
        if (mEntity!=null){
            if (edInfo.getVisibility()==View.VISIBLE)
                return mEntity.FieldId+":"+edInfo.getText();
            if (tvInfo.getVisibility()==View.VISIBLE)
                return mEntity.FieldId+":"+paramValue;
        }
        return "";
    }
    @Override
    public void onClick(View view) {
        if (view==tvInfo){

        }
    }
}
