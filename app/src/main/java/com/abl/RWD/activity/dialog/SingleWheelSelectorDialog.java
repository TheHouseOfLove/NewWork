package com.abl.RWD.activity.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.TextView;

import com.abl.RWD.R;
import com.abl.RWD.adapter.AdapterDetailItemSelect;
import com.abl.RWD.component.datepicker.view.WheelView;
import com.abl.RWD.entity.VDetailSelectorItemEntity;
import com.abl.RWD.listener.IWheelViewSelectedListener;
import com.abl.RWD.util.ParseUtil;
import com.abl.common.util.DeviceUtil;

import java.util.ArrayList;


/**
 * Created by yas on 2017/5/2.
 * 联系人关系选择dialog
 */

public class SingleWheelSelectorDialog extends Dialog implements View.OnClickListener {
    private TextView txtCancel;
    private TextView txtOk;
    private TextView txtTitle;
    private WheelView mWheelView;
    private IWheelViewSelectedListener mListener;
    private AdapterDetailItemSelect mAdapter;
    public SingleWheelSelectorDialog(Context context) {
        super(context);
        init();
    }

    public SingleWheelSelectorDialog(Context context, int theme) {
        super(context, theme);
        init();
    }

    protected SingleWheelSelectorDialog(Context context, boolean cancelable, OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
        init();
    }
    private void init(){
        Window window=getWindow();
        window.setGravity(Gravity.BOTTOM);
        window.setWindowAnimations(R.style.popwin_animation);

        setCancelable(true);
        setCanceledOnTouchOutside(true);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LayoutInflater li= (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View mView=li.inflate(R.layout.single_wheel_select_dialog, null);
        txtCancel=  mView.findViewById(R.id.text_cancel);
        txtCancel.setOnClickListener(this);
        txtOk=  mView.findViewById(R.id.text_ok);
        txtOk.setOnClickListener(this);
        txtTitle=  mView.findViewById(R.id.text_title);
        txtTitle.setText("请选择");
        mWheelView= mView.findViewById(R.id.wheel_first);
        this.mWheelView.setDrawShadows(true);
        int mWidth = DeviceUtil.mWidth;
        int mHeight = (int) getContext().getResources().getDimension(R.dimen.datepicker_height);
        ViewGroup.LayoutParams llp = new ViewGroup.LayoutParams(mWidth, mHeight);
        setContentView(mView, llp);
        this.getWindow().setGravity(Gravity.BOTTOM);


    }
    public void setData(String options){
        ArrayList<VDetailSelectorItemEntity> mList= ParseUtil.getSelectData(options);
        if (mList!=null) {
            mAdapter = new AdapterDetailItemSelect(getContext(),mList);
            mAdapter.setItemResource(R.layout.wheelview_item_layout);
            mAdapter.setItemTextResource(R.id.txt_wheel_item);
            mWheelView.setViewAdapter(mAdapter);
        }
    }

    /**
     * 设置监听回调接口
     * @param mListener
     */
    public void setSelectListener(IWheelViewSelectedListener mListener){
        this.mListener=mListener;
    }
    @Override
    public void onClick(View v) {
        if (v==txtOk){
            if (mListener!=null){
                int index = this.mWheelView.getCurrentItem();
                if (mAdapter != null) {
                    VDetailSelectorItemEntity itemEntity = mAdapter.getItemByPos(index);
                    mListener.select(itemEntity);
                }
            }
            dismiss();
        }else if (v==txtCancel){
            dismiss();
        }

    }
}
