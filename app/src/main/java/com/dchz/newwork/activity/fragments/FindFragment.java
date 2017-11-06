package com.dchz.newwork.activity.fragments;

import android.view.View;
import android.widget.RelativeLayout;

import com.dchz.newwork.R;
import com.dchz.newwork.activity.base.BaseFragment;
import com.dchz.newwork.component.CommonHeaderView;

/**
 * Created by yas on 2017/11/6.
 */

public class FindFragment extends BaseFragment implements View.OnClickListener {
    private CommonHeaderView mHeader;
    private RelativeLayout layoutItem01;
    private RelativeLayout layoutItem02;
    @Override
    public int getLayoutRes() {
        return R.layout.fragment_find;
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
    public void onClick(View view) {
        if (view==layoutItem01){

        }else if (view==layoutItem02){

        }
    }
}
