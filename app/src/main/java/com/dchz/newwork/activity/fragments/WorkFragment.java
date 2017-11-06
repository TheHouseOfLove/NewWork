package com.dchz.newwork.activity.fragments;

import android.view.View;

import com.dchz.newwork.R;
import com.dchz.newwork.activity.base.BaseFragment;
import com.dchz.newwork.component.HeaderSelectView;
import com.dchz.newwork.listener.ITabChangeListener;

/**
 * Created by yas on 2017/11/6.
 */

public class WorkFragment extends BaseFragment {
    private HeaderSelectView mHeader;
    @Override
    public int getLayoutRes() {
        return R.layout.fragment_work;
    }

    @Override
    public void initViews(View rootView) {
        mHeader=rootView.findViewById(R.id.header_work);
        mHeader.addTabChangeListener(mTabChangeListener);
        mHeader.setTabTitle("待办事务", "已办事务");
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
