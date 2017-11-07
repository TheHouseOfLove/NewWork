package com.dchz.newwork.activity.fragments;

import android.view.View;

import com.dchz.newwork.R;
import com.dchz.newwork.activity.base.BaseFragment;
import com.dchz.newwork.component.ChartView;
import com.dchz.newwork.component.HeaderSelectView;
import com.dchz.newwork.component.ReportSubView;
import com.dchz.newwork.listener.ITabChangeListener;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

/**
 * Created by yas on 2017/11/6.
 */

public class ReportFragment extends BaseFragment {
    private HeaderSelectView mHeader;
    private XRecyclerView mRecyclerView;
    private ReportSubView mSubView;
    private ChartView mChartView;
    @Override
    public int getLayoutRes() {
        return R.layout.fragment_report;
    }

    @Override
    public void initViews(View rootView) {
        mHeader=rootView.findViewById(R.id.header_report);
        mHeader.addTabChangeListener(mTabChangeListener);
        mHeader.setTabTitle("合同", "收款");
        mRecyclerView=rootView.findViewById(R.id.mRecyclerView);
        mRecyclerView.setLoadingMoreEnabled(false);
        mRecyclerView.setPullRefreshEnabled(false);
        mSubView=rootView.findViewById(R.id.subView);
        mChartView=new ChartView(getActivity());
        mRecyclerView.addHeaderView(mChartView);
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
