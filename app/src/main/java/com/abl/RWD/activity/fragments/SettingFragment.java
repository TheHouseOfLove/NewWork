package com.abl.RWD.activity.fragments;

import android.view.View;
import android.widget.TextView;

import com.abl.RWD.R;
import com.abl.RWD.activity.base.BaseFragment;
import com.abl.RWD.component.CommonHeaderView;
import com.abl.RWD.controller.LoginController;
import com.abl.RWD.util.IntentUtils;

/**
 * Created by yas on 2017/11/6.
 */

public class SettingFragment extends BaseFragment implements View.OnClickListener {
    private CommonHeaderView mHeaderView;
    private TextView tvOutLogin;
    @Override
    public int getLayoutRes() {
        return R.layout.fragment_setting;
    }

    @Override
    public void initViews(View rootView) {
        mHeaderView=rootView.findViewById(R.id.header_setting);
        mHeaderView.updateType(CommonHeaderView.TYPE_ONLY_TITLE);
        mHeaderView.setTitle("设置");
        tvOutLogin=rootView.findViewById(R.id.text_out);
        tvOutLogin.setOnClickListener(this);
    }

    @Override
    protected void handleRsp(Object obj, boolean isSucc, int errorCode, int seqNo, int src) {

    }

    @Override
    public void onClick(View view) {
        if (view==tvOutLogin){
            LoginController.getInstance().clearLoginInfo();
            IntentUtils.startLoginActivity(getActivity());
        }
    }
}
