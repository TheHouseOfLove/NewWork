package com.abl.RWD.component;

import android.content.Context;
import android.graphics.Color;
import android.text.InputFilter;
import android.text.InputType;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.abl.RWD.R;
import com.abl.RWD.entity.PYWInfoItemEntity;
import com.abl.RWD.listener.IDetailItemClickListener;

/**
 * Created by yas on 2017/11/27.
 */

public class DetailItemView extends LinearLayout implements View.OnClickListener {
    private TextView tvTitle;
    private TextView tvInfo;
    private EditText edInfo;
    private PYWInfoItemEntity mEntity;
    private String paramValue;
    private LinearLayout Layout;
    private View divider;
    private IDetailItemClickListener mListener;
    public DetailItemView(Context context,IDetailItemClickListener mListener) {
        super(context);
        this.mListener=mListener;
        init();
    }
    private void init(){
        LayoutInflater li= (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        li.inflate(R.layout.detail_item_view,this,true);
        tvTitle=findViewById(R.id.tv_title);
        tvInfo=findViewById(R.id.tv_content);
        tvInfo.setOnClickListener(this);
        edInfo=findViewById(R.id.edit_content);
        divider=findViewById(R.id.divider);
        Layout=findViewById(R.id.layout_content);
    }
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
                    if ("是".equals(pDetailItemEntity.Required)){
                        tvTitle.setTextColor(Color.RED);
                    }else{
                        tvTitle.setTextColor(getContext().getResources().getColor(R.color.txt_info_show_title_textColor));
                    }
                } else if ("DropDownList".equals(pDetailItemEntity.ControlType)||"RadioButtonList".equals(pDetailItemEntity.ControlType)
                        ||"CheckBoxList".equals(pDetailItemEntity.ControlType)
                        ||"CalendarV2".equals(pDetailItemEntity.ControlType)) {
                    tvInfo.setEnabled(true);
                    tvInfo.setVisibility(View.VISIBLE);
                    edInfo.setVisibility(View.GONE);
                    if ("是".equals(pDetailItemEntity.Required)){
                        tvTitle.setTextColor(Color.RED);
                    }else{
                        tvTitle.setTextColor(getContext().getResources().getColor(R.color.txt_info_show_title_textColor));
                    }
                } else {
                    tvInfo.setVisibility(View.VISIBLE);
                    edInfo.setVisibility(View.GONE);
                }

            }
        }
    }
    public String getParam(){
        if (mEntity!=null){
            if (edInfo.getVisibility()==View.VISIBLE)
                return mEntity.FieldId+":"+edInfo.getText();
            if (tvInfo.getVisibility()==View.VISIBLE)
                return mEntity.FieldId+":"+tvInfo.getText();
        }
        return "";
    }
    public void setParam(String str){
        if (edInfo.getVisibility()==View.VISIBLE)
            edInfo.setText(str);
        if (tvInfo.getVisibility()==View.VISIBLE)
            tvInfo.setText(str);
    }
    @Override
    public void onClick(View view) {
        if (view==tvInfo){
            if (mListener!=null){
                mListener.itemClick(tvInfo,mEntity.ControlValue);
            }
        }
    }
}
