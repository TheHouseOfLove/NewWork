package com.dchz.newwork.activity;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.widget.RadioGroup;
import com.dchz.newwork.R;
import com.dchz.newwork.activity.base.BaseActivity;
import com.dchz.newwork.activity.fragments.SearchFragment;
import com.dchz.newwork.activity.fragments.ReportFragment;
import com.dchz.newwork.activity.fragments.SettingFragment;
import com.dchz.newwork.activity.fragments.WorkFragment;

public class MainActivity extends BaseActivity {
    private RadioGroup rgTabs;
    private Fragment mContent;

    private SettingFragment settingFragment;
    private WorkFragment workFragment;
    private ReportFragment reportFragment;
    private SearchFragment searchFragment;
    private FragmentManager manager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
        initFragment();/////
    }
    private void initViews() {
        rgTabs = (RadioGroup) findViewById(R.id.rg_tabs);
        rgTabs.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.tab1) {
                    switchContent(mContent, workFragment);
                } else if (checkedId == R.id.tab2) {
                    switchContent(mContent, searchFragment);
                } else if (checkedId == R.id.tab3) {
                    switchContent(mContent, reportFragment);
                }else if (checkedId==R.id.tab4){
                    switchContent(mContent, settingFragment);
                }
            }
        });
    }
    private void initFragment() {
        workFragment = new WorkFragment();
        searchFragment = new SearchFragment();
        reportFragment=new ReportFragment();
        settingFragment=new SettingFragment();
        manager = getFragmentManager();
        FragmentTransaction fragmentTransaction = manager.beginTransaction();
        fragmentTransaction.add(R.id.content, workFragment, "flag1").commitAllowingStateLoss();
        mContent = workFragment;
    }
    public void switchContent(Fragment from, Fragment to) {
        if (mContent != to) {
            mContent = to;
            FragmentTransaction transaction = manager.beginTransaction();
            if (!to.isAdded()) {    // 先判断是否被add过
                String flag = "";
                if (to instanceof WorkFragment)
                    flag = "flag1";
                if (to instanceof SearchFragment)
                    flag = "flag2";
                if (to instanceof ReportFragment)
                    flag = "flag3";
                if (to instanceof SettingFragment)
                    flag="flag4";
                transaction.hide(from).add(R.id.content, to, flag).commitAllowingStateLoss(); // 隐藏当前的fragment，add下一个到Activity中
            } else {
                transaction.hide(from).show(to).commitAllowingStateLoss(); // 隐藏当前的fragment，显示下一个
            }
        }

    }
}
