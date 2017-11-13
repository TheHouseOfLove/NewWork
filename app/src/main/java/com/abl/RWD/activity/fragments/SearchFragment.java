package com.abl.RWD.activity.fragments;

import android.view.View;
import android.widget.RelativeLayout;

import com.abl.RWD.R;
import com.abl.RWD.activity.base.BaseFragment;
import com.abl.RWD.component.CommonHeaderView;
import com.abl.RWD.util.IntentUtils;

/**
 * Created by yas on 2017/11/6.
 */

public class SearchFragment extends BaseFragment implements View.OnClickListener {
    private CommonHeaderView mHeader;
    private RelativeLayout layoutItem01;
    private RelativeLayout layoutItem02;
    @Override
    public int getLayoutRes() {
        return R.layout.fragment_search;
    }

    @Override
    public void initViews(View rootView) {
        mHeader=rootView.findViewById(R.id.header_find);
        mHeader.updateType(CommonHeaderView.TYPE_ONLY_TITLE);
        mHeader.setTitle("查询");

        layoutItem01=rootView.findViewById(R.id.layout01);
        layoutItem02=rootView.findViewById(R.id.layout02);

        layoutItem01.setOnClickListener(this);
        layoutItem02.setOnClickListener(this);
    }

    @Override
    protected void handleRsp(Object obj, boolean isSucc, int errorCode, int seqNo, int src) {

    }

    @Override
    public void onClick(View view) {
        if (view==layoutItem01){
            IntentUtils.startSearchProjectActivity(getActivity());
        }else if (view==layoutItem02){
            IntentUtils.startSearchContractActivity(getActivity());
        }
    }
}
