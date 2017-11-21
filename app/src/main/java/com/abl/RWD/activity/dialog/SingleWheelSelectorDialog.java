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
import com.abl.RWD.component.datepicker.view.WheelView;
import com.abl.RWD.listener.IWheelViewSelectedListener;
import com.abl.common.util.DeviceUtil;


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

    private int mType;

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
    public void updateType(int type){
        mType=type;
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
            dismiss();
        }else if (v==txtCancel){
            dismiss();
        }

    }
}
