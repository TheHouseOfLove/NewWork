package com.abl.RWD.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;

import com.abl.RWD.R;
import com.abl.RWD.activity.base.BaseActivity;
import com.abl.RWD.adapter.AdapterCheckBox;
import com.abl.RWD.component.CommonHeaderView;
import com.abl.RWD.entity.VDetailSelectorItemEntity;
import com.abl.RWD.listener.IBtnClickListener;
import com.abl.RWD.util.IntentUtils;
import com.abl.RWD.util.ParseUtil;

import java.util.ArrayList;

/**
 * Created by yas on 2017/11/28.
 */

public class DetailCheckBoxActivity extends BaseActivity{
    private CommonHeaderView mHeader;
    private RecyclerView mRecyclerView;
    private AdapterCheckBox mAdapter;
    private String options;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkbox);
        initExtras();
        initLayout();
    }

    private void initExtras() {
        options=getIntent().getStringExtra(IntentUtils.KEY_STR);
    }

    private void initLayout() {
        mHeader= (CommonHeaderView) this.findViewById(R.id.mHeader);
        mHeader.updateType(CommonHeaderView.TYPE_LEFT_IMAGE_AND_RIGHT_TEXT);
        mHeader.setTitle("请选择");
        mHeader.setRightText("完成");
        mHeader.setHeaderClickListener(new IBtnClickListener() {
            @Override
            public void btnLeftClick() {
                finish();
            }

            @Override
            public void btnRightClick() {
                ArrayList<VDetailSelectorItemEntity> mList=mAdapter.getSelectedItems();
                if (mList==null||mList.size()<=0){
                    showToast("请选择");
                }else{
                    Intent intent=new Intent();
                    intent.putExtra(IntentUtils.KEY_ENTITY,mList);
                    setResult(Activity.RESULT_OK,intent);
                    finish();
                }
            }
        });
        mRecyclerView= (RecyclerView) this.findViewById(R.id.mRecyclerView);
        LinearLayoutManager manager=new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(manager);

        initData();
    }

    private void initData() {
        if (TextUtils.isEmpty(options)){
            return;
        }
        ArrayList<VDetailSelectorItemEntity> mList= ParseUtil.getSelectData(options);
        mAdapter=new AdapterCheckBox(this,mList);
        mRecyclerView.setAdapter(mAdapter);
    }
}
