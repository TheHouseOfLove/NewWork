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
import com.abl.RWD.common.Common;
import com.abl.RWD.entity.PYWInfoItemEntity;
import com.abl.RWD.entity.VDateEntity;
import com.abl.RWD.entity.VDetailSelectorItemEntity;
import com.abl.RWD.listener.IDetailItemClickListener;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;

/**
 * Created by yas on 2017/11/27.
 */

public class DetailItemView extends LinearLayout implements View.OnClickListener {
    private TextView tvTitle;
    private TextView tvInfo;
    private EditText edInfo;
    private PYWInfoItemEntity mEntity;
    private String paramValue;
    private View divider;
    private IDetailItemClickListener mListener;

    private int mType;

    public DetailItemView(Context context, IDetailItemClickListener mListener, int type) {
        super(context);
        this.mListener = mListener;
        mType = type;
        init();
    }

    private void init() {
        LayoutInflater li = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        li.inflate(R.layout.detail_item_view, this, true);
        tvTitle = findViewById(R.id.tv_title);
        tvInfo = findViewById(R.id.tv_content);
        tvInfo.setOnClickListener(this);
        edInfo = findViewById(R.id.edit_content);
        divider = findViewById(R.id.divider);
    }

    public void setMsg(PYWInfoItemEntity pDetailItemEntity) {
        mEntity = pDetailItemEntity;
        if (pDetailItemEntity != null) {
            if (pDetailItemEntity.isTop) {
                divider.setVisibility(View.VISIBLE);
            } else {
                divider.setVisibility(View.GONE);
            }
            tvTitle.setText(pDetailItemEntity.FieldName);
            tvInfo.setText(pDetailItemEntity.FieldValue);
            edInfo.setText(pDetailItemEntity.FieldValue);
            if (this.mType == Common.TYPE_DAIBAN) {
                if (!"修改".equals(pDetailItemEntity.CRName)) {
                    //不能修改时，使用textView显示数据
                    tvInfo.setEnabled(false);
                    tvInfo.setVisibility(View.VISIBLE);
                    edInfo.setVisibility(View.GONE);
                } else {
                    //可以修改时，根据不同限制条件作出不同操作
                    if ("TextBox_1".equals(pDetailItemEntity.ControlType) || "TextBox_2".equals(pDetailItemEntity.ControlType)) {
                        if ("TextBox_1".equals(pDetailItemEntity.ControlType)) {
                            edInfo.setSingleLine();
                        }
                        //单行输入框
                        tvInfo.setVisibility(View.GONE);
                        edInfo.setVisibility(View.VISIBLE);
                        InputFilter[] filters = {new InputFilter.LengthFilter(Integer.valueOf(pDetailItemEntity.DataLength))};
                        edInfo.setFilters(filters);
                        if ("int".equals(pDetailItemEntity.DataType) || "float".equals(pDetailItemEntity.DataType)
                                || "decimal".equals(pDetailItemEntity.DataType)) {
                            edInfo.setInputType(InputType.TYPE_CLASS_NUMBER);
                        }
                        if ("是".equals(pDetailItemEntity.Required)) {
                            tvTitle.setTextColor(Color.RED);
                        } else {
                            tvTitle.setTextColor(getContext().getResources().getColor(R.color.txt_info_show_title_textColor));
                        }
                    } else if ("DropDownList".equals(pDetailItemEntity.ControlType) || "RadioButtonList".equals(pDetailItemEntity.ControlType)
                            || "CheckBoxList".equals(pDetailItemEntity.ControlType)
                            || "CalendarV2".equals(pDetailItemEntity.ControlType)) {
                        tvInfo.setEnabled(true);
                        tvInfo.setVisibility(View.VISIBLE);
                        edInfo.setVisibility(View.GONE);
                        if ("是".equals(pDetailItemEntity.Required)) {
                            tvTitle.setTextColor(Color.RED);
                        } else {
                            tvTitle.setTextColor(getContext().getResources().getColor(R.color.txt_info_show_title_textColor));
                        }
                    } else {
                        tvInfo.setVisibility(View.VISIBLE);
                        edInfo.setVisibility(View.GONE);
                    }
                }
            } else if (mType == Common.TYPE_YIBAN) {
                tvInfo.setEnabled(false);
                tvInfo.setVisibility(View.VISIBLE);
                edInfo.setVisibility(View.GONE);
            }
        }
    }

    public String getParam() {
        if (mEntity != null && "修改".equals(mEntity.CRName)) {
            String param = "";
            if (edInfo.getVisibility() == View.VISIBLE)
                param = edInfo.getText().toString();
            if (tvInfo.getVisibility() == View.VISIBLE)
                param = paramValue;
            if (!TextUtils.isEmpty(param)) {
//                try {
//                    param = URLEncoder.encode(param, "GBK");
//                } catch (UnsupportedEncodingException e) {
//                    e.printStackTrace();
//                }
                return "\"" + mEntity.Field + "\":\"" + param + "\"";
            } else {
                if ("是".equals(mEntity.Required) && TextUtils.isEmpty(mEntity.FieldValue)) {
                    return "empty";
                }
            }
        }
        return "";
    }

    /**
     * 单选结果回填
     *
     * @param itemEntity
     */
    public void setParam(VDetailSelectorItemEntity itemEntity) {
        if (edInfo.getVisibility() == View.VISIBLE)
            edInfo.setText(itemEntity.value);
        if (tvInfo.getVisibility() == View.VISIBLE)
            tvInfo.setText(itemEntity.value);
        paramValue = itemEntity.key;
    }

    /**
     * 多选结果回填
     *
     * @param list
     */
    public void setParam(ArrayList<VDetailSelectorItemEntity> list) {
        if (list != null) {
            String value = "";
            String key = "";
            for (int i = 0; i < list.size(); i++) {
                VDetailSelectorItemEntity itemEntity = list.get(i);
                if (i == 0) {
                    value = itemEntity.value;
                    key = itemEntity.key;
                } else {
                    value = value + "," + itemEntity.value;
                    key = key + "," + itemEntity.key;
                }
            }
            if (edInfo.getVisibility() == View.VISIBLE)
                edInfo.setText(value);
            if (tvInfo.getVisibility() == View.VISIBLE)
                tvInfo.setText(value);
            paramValue = key;
        }
    }

    public void seDate(VDateEntity vEntity) {
        if (vEntity != null) {
            String strDate = vEntity.getDate();
            if (edInfo.getVisibility() == View.VISIBLE)
                edInfo.setText(strDate);
            if (tvInfo.getVisibility() == View.VISIBLE)
                tvInfo.setText(strDate);
            paramValue = strDate;
        }
    }

    @Override
    public void onClick(View view) {
        if (view == tvInfo) {
            if (mListener != null) {
                if ("DropDownList".equals(mEntity.ControlType) || "RadioButtonList".equals(mEntity.ControlType)) {
                    mListener.itemRadioClick(this, mEntity.ControlValue);
                } else if ("CheckBoxList".equals(mEntity.ControlType)) {
                    mListener.itemCheckBoxClick(this, mEntity.ControlValue);
                } else if ("CalendarV2".equals(mEntity.ControlType)) {
                    mListener.itemCalendarClick(this);
                }
            }
        }
    }
}
