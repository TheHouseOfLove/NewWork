package com.abl.RWD.activity.fragments;

import android.view.View;

import com.abl.RWD.R;
import com.abl.RWD.activity.base.BaseFragment;
import com.abl.RWD.component.CommonHeaderView;

/**
 * Created by yas on 2017/11/6.
 */

public class SettingFragment extends BaseFragment {
    private CommonHeaderView mHeaderView;

    @Override
    public int getLayoutRes() {
        return R.layout.fragment_setting;
    }

    @Override
    public void initViews(View rootView) {
        mHeaderView=rootView.findViewById(R.id.header_setting);
        mHeaderView.updateType(CommonHeaderView.TYPE_ONLY_TITLE);
        mHeaderView.setTitle("设置");

    }
}
