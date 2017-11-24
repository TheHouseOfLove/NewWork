package com.abl.RWD.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.widget.LinearLayout;

import com.abl.RWD.R;
import com.abl.RWD.activity.base.BaseNormalActivity;
import com.abl.RWD.adapter.AdapterDetailInfo;
import com.abl.RWD.component.CommonHeaderView;
import com.abl.RWD.component.DetailBottomView;
import com.abl.RWD.component.fullrecyclerview.FullyLinearLayoutManager;
import com.abl.RWD.entity.PWorkItemEntity;
import com.abl.RWD.entity.PYWInfoItemEntity;
import com.abl.RWD.listener.IBtnClickListener;
import com.abl.RWD.listener.IDetailBottomClickListener;
import com.abl.RWD.util.IntentUtils;

import java.util.ArrayList;

/**
 * Created by yas on 2017/11/13.
 * 事务详情页
 */

public class WorkDetailActivity extends BaseNormalActivity{
    private CommonHeaderView mHeader;
    private RecyclerView mRecyclerView;
    private LinearLayout layoutFiles;
    private DetailBottomView mBottomView;
    private AdapterDetailInfo mAdapter;
    private int mType;
    private PWorkItemEntity entity;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transaction_detail);
        initExtras();
        initLayout();
    }
    private void initExtras(){
        Intent intent=getIntent();
        mType=intent.getIntExtra(IntentUtils.KEY_TYPE, 0);
        entity=(PWorkItemEntity) intent.getSerializableExtra(IntentUtils.KEY_ENTITY);
    }
    private void initLayout() {
        mHeader= (CommonHeaderView) this.findViewById(R.id.header_detail);
        mHeader.updateType(CommonHeaderView.TYPE_ONLY_LEFT_IMAGE);
        mHeader.setTitle("详情");
        mHeader.setHeaderClickListener(new IBtnClickListener() {
            @Override
            public void btnLeftClick() {
                finish();
            }
        });
        mRecyclerView= (RecyclerView) this.findViewById(R.id.recycler_items);
        FullyLinearLayoutManager manager=new FullyLinearLayoutManager(this);
        mRecyclerView.setLayoutManager(manager);
        layoutFiles= (LinearLayout) this.findViewById(R.id.layout_files);
        mBottomView= (DetailBottomView) this.findViewById(R.id.bottomView);
        mBottomView.setClickListener(mBottomClickListener);
        mAdapter=new AdapterDetailInfo(this,getTestData());
        mRecyclerView.setAdapter(mAdapter);
    }
    private IDetailBottomClickListener mBottomClickListener=new IDetailBottomClickListener() {
        @Override
        public void typeClickListener() {
            //TODO 提交类型选择
            IntentUtils.starSubmitTypeSelectActivity(WorkDetailActivity.this,100,"");
        }

        @Override
        public void nextClickListener() {
            //TODO 接收人选择
            IntentUtils.starNextAccepterActivity(WorkDetailActivity.this,null,1,200);
        }

        @Override
        public void btnClickListener() {
            //TODO 提交按钮点击
        }

        @Override
        public void noNextListener() {
            showToast("请选择下一环节");
        }
    };
    private ArrayList<PYWInfoItemEntity> getTestData() {
        ArrayList<PYWInfoItemEntity> mList=new ArrayList<>();
        for (int i=0;i<10;i++){
            mList.add(new PYWInfoItemEntity());
        }
        return mList;
    }

    @Override
    protected void handleRsp(Object obj, boolean isSucc, int errorCode, int seqNo, int src) {

    }
}
