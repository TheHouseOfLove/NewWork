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
import com.abl.RWD.component.datepicker.adapter.NumericWheelAdapter;
import com.abl.RWD.component.datepicker.view.OnWheelScrollListener;
import com.abl.RWD.component.datepicker.view.WheelView;
import com.abl.RWD.entity.VDateEntity;
import com.abl.RWD.listener.IDatePickerListener;
import com.abl.RWD.util.DateUtil;
import com.abl.RWD.util.TimeUtils;
import com.abl.common.util.DeviceUtil;

import java.util.Calendar;

/**
 * Created by yas on 2016/3/1.
 */
public class MDateSelectorDialog extends Dialog implements View.OnClickListener {
    private final int START_YEAR = 1980;
    private TextView btnOk;
    private TextView txtTitle;
    private TextView txtCancel;
    private WheelView mWheelFirst;
    private WheelView mWheelSecond;
    private WheelView mWheelThird;
    private IDatePickerListener mListener;
    private NumericWheelAdapter mAdapterFirst = null;
    private NumericWheelAdapter mAdapterSecond = null;
    private NumericWheelAdapter mAdapterThird = null;
    private int currentYear = Calendar.getInstance().get(Calendar.YEAR);
    private int currentMonth = Calendar.getInstance().get(Calendar.MONTH) + 1;
    private int currentDay = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);

    public MDateSelectorDialog(Context context) {
        super(context);
        init();
    }

    public MDateSelectorDialog(Context context, int theme) {
        super(context, theme);
        init();
    }

    protected MDateSelectorDialog(Context context, boolean cancelable, OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
        init();
    }

    private void init() {
        Window window = getWindow();
        window.setGravity(Gravity.BOTTOM);
        window.setWindowAnimations(R.style.popwin_animation);

        setCancelable(true);
        setCanceledOnTouchOutside(true);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LayoutInflater li = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View mView = li.inflate(R.layout.my_time_selector_dialog, null);

        btnOk = (TextView) mView.findViewById(R.id.text_ok);
        btnOk.setOnClickListener(this);
        txtTitle = (TextView) mView.findViewById(R.id.text_title);
        txtCancel = (TextView) mView.findViewById(R.id.text_cancel);
        txtCancel.setOnClickListener(this);
        mWheelFirst = (WheelView) mView.findViewById(R.id.wheel_first);
        mWheelSecond = (WheelView) mView.findViewById(R.id.wheel_second);
        mWheelThird = (WheelView) mView.findViewById(R.id.wheel_third);
        mWheelFirst.setDrawShadows(true);
        mWheelSecond.setDrawShadows(true);
        mWheelThird.setDrawShadows(true);
        int mWidth = DeviceUtil.mWidth;
        int mHeight = (int) getContext().getResources().getDimension(R.dimen.datepicker_height);
        ViewGroup.LayoutParams llp = new ViewGroup.LayoutParams(mWidth, mHeight);
        setContentView(mView, llp);
        this.getWindow().setGravity(Gravity.BOTTOM);
        initDate();
    }

    //    public void updateType(){
//        initDate();
//    }
    private void initDate() {
        int start = currentYear;
        int end = currentYear + 60;
        mAdapterFirst = new NumericWheelAdapter(getContext(), start, end);
        mAdapterFirst.setItemResource(R.layout.wheelview_item_layout);
        mAdapterFirst.setItemTextResource(R.id.txt_wheel_item);
        mAdapterFirst.setLabel("年");
        mWheelFirst.setViewAdapter(mAdapterFirst);
        mWheelFirst.addScrollingListener(mYScrollListener);

        mAdapterSecond = new NumericWheelAdapter(getContext(), 1, 12);
        mAdapterSecond.setItemResource(R.layout.wheelview_item_layout);
        mAdapterSecond.setItemTextResource(R.id.txt_wheel_item);
        mAdapterSecond.setLabel("月");
        mWheelSecond.setViewAdapter(mAdapterSecond);
        mWheelSecond.addScrollingListener(mYScrollListener);
        mWheelSecond.setCurrentItem(currentMonth - 1);

        boolean isLeapYear = DateUtil.isLeapYear(currentYear);
        int numDays = DateUtil.getDaysOfMonth(isLeapYear, currentMonth);
        mAdapterThird = new NumericWheelAdapter(getContext(), 1, numDays);
        mAdapterThird.setItemResource(R.layout.wheelview_item_layout);
        mAdapterThird.setItemTextResource(R.id.txt_wheel_item);
        mAdapterThird.setLabel("日");
        mWheelThird.setViewAdapter(mAdapterThird);
        mWheelThird.setCurrentItem(currentDay - 1);
        txtTitle.setText("选择日期");
    }

    public void setNextDay() {
        Calendar c = Calendar.getInstance();
        long cur = System.currentTimeMillis();
        long nextTime = cur + TimeUtils.ONE_DAY * 2;
        c.setTimeInMillis(nextTime);
        int currentMonth = c.get(Calendar.MONTH) + 1;
        int currentDay = c.get(Calendar.DAY_OF_MONTH);
        mWheelSecond.setCurrentItem(currentMonth - 1);
        mWheelThird.setCurrentItem(currentDay - 1);
    }

    private OnWheelScrollListener mYScrollListener = new OnWheelScrollListener() {
        @Override
        public void onScrollingStarted(WheelView wheel) {

        }

        @Override
        public void onScrollingFinished(WheelView wheel) {
            int year = mWheelFirst.getCurrentItem() + currentYear;
            int month = mWheelSecond.getCurrentItem() + 1;
            reSetDays(year, month);
        }
    };

    private void reSetDays(int year, int month) {
        boolean isLeapYear = DateUtil.isLeapYear(year);
        int numDays = DateUtil.getDaysOfMonth(isLeapYear, month);
        mAdapterThird = new NumericWheelAdapter(getContext(), 1, numDays);
        mAdapterThird.setLabel("日");
        mWheelThird.setViewAdapter(mAdapterThird);
    }

    public void setIDatePickerListener(IDatePickerListener mListener) {
        this.mListener = mListener;
    }

    @Override
    public void onClick(View view) {
        if (mListener != null) {
            if (view == btnOk) {
                VDateEntity entity = getCurDateEntity();
                mListener.onPickerClick(entity);
            }
        }
        dismiss();
    }

    private VDateEntity getCurDateEntity() {
        VDateEntity entity = new VDateEntity();
        int year = mWheelFirst.getCurrentItem() + currentYear;
        int month = mWheelSecond.getCurrentItem() + 1;
        int day = mWheelThird.getCurrentItem() + 1;
        entity.year = year;
        entity.month = month;
        entity.date = day;
        return entity;
    }
}
