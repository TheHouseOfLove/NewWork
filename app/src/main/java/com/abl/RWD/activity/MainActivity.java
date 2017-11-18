package com.abl.RWD.activity;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.widget.RadioGroup;
import com.abl.RWD.R;
import com.abl.RWD.activity.base.BaseActivity;
import com.abl.RWD.activity.fragments.FinishWorListFragment;
import com.abl.RWD.activity.fragments.PendingWorkListFragment;
import com.abl.RWD.activity.fragments.SearchFragment;
import com.abl.RWD.activity.fragments.ReportFragment;
import com.abl.RWD.activity.fragments.SettingFragment;
import com.abl.RWD.activity.fragments.WorkFragment;

public class MainActivity extends BaseActivity {
    private RadioGroup rgTabs;
    private Fragment mContent;

    private SettingFragment settingFragment;
//    private WorkFragment workFragment;
//    private ReportFragment reportFragment;
//    private SearchFragment searchFragment;
    private PendingWorkListFragment pendingWorkListFragment;
    private FinishWorListFragment finishWorListFragment;
    private FragmentManager manager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
        initFragment();
    }
    private void initViews() {
        rgTabs = (RadioGroup) findViewById(R.id.rg_tabs);
        rgTabs.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.tab1) {
                    switchContent(mContent, pendingWorkListFragment);
                } else if (checkedId == R.id.tab2) {
                    switchContent(mContent, finishWorListFragment);
                } else if (checkedId == R.id.tab3) {
                    switchContent(mContent, settingFragment);
//                }else if (checkedId==R.id.tab4){
//                    switchContent(mContent, settingFragment);
                }
            }
        });
    }
    private void initFragment() {
        pendingWorkListFragment = new PendingWorkListFragment();
        finishWorListFragment = new FinishWorListFragment();
//        reportFragment=new ReportFragment();
        settingFragment=new SettingFragment();
        manager = getFragmentManager();
        FragmentTransaction fragmentTransaction = manager.beginTransaction();
        fragmentTransaction.add(R.id.content, pendingWorkListFragment, "flag1").commitAllowingStateLoss();
        mContent = pendingWorkListFragment;
    }
    public void switchContent(Fragment from, Fragment to) {
        if (mContent != to) {
            mContent = to;
            FragmentTransaction transaction = manager.beginTransaction();
            if (!to.isAdded()) {    // 先判断是否被add过
                String flag = "";
                if (to instanceof PendingWorkListFragment)
                    flag = "flag1";
                if (to instanceof FinishWorListFragment)
                    flag = "flag2";
                if (to instanceof SettingFragment)
                    flag = "flag3";
                transaction.hide(from).add(R.id.content, to, flag).commitAllowingStateLoss(); // 隐藏当前的fragment，add下一个到Activity中
            } else {
                transaction.hide(from).show(to).commitAllowingStateLoss(); // 隐藏当前的fragment，显示下一个
            }
        }

    }
}
