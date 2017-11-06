package com.dchz.newwork.activity.fragments;

import android.view.View;

import com.dchz.newwork.R;
import com.dchz.newwork.activity.base.BaseFragment;
import com.dchz.newwork.component.HeaderSelectView;
import com.dchz.newwork.listener.ITabChangeListener;

/**
 * Created by yas on 2017/11/6.
 */

public class ReportFragment extends BaseFragment {
    private HeaderSelectView mHeader;
    @Override
    public int getLayoutRes() {
        return R.layout.fragment_report;
    }

    @Override
    public void initViews(View rootView) {
        mHeader=rootView.findViewById(R.id.header_report);
        mHeader.addTabChangeListener(mTabChangeListener);
        mHeader.setTabTitle("合同", "收款");
    }
    private ITabChangeListener mTabChangeListener=new ITabChangeListener() {
        @Override
        public void leftTabClick() {

        }

        @Override
        public void rightTabClick() {

        }
    };
}
