package com.abl.RWD.activity;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.widget.LinearLayout;

import com.abl.RWD.R;
import com.abl.RWD.activity.base.BaseNormalActivity;
import com.abl.RWD.adapter.AdapterDetailInfo;
import com.abl.RWD.component.CommonHeaderView;
import com.abl.RWD.component.DetailBottomView;
import com.abl.RWD.component.fullrecyclerview.FullyLinearLayoutManager;
import com.abl.RWD.entity.PDetailItemEntity;
import com.abl.RWD.listener.IBtnClickListener;

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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transaction_detail);
        initLayout();
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

        mAdapter=new AdapterDetailInfo(this,getTestData());
        mRecyclerView.setAdapter(mAdapter);
    }

    private ArrayList<PDetailItemEntity> getTestData() {
        ArrayList<PDetailItemEntity> mList=new ArrayList<>();
        for (int i=0;i<10;i++){
            mList.add(new PDetailItemEntity());
        }
        return mList;
    }

    @Override
    protected void handleRsp(Object obj, boolean isSucc, int errorCode, int seqNo, int src) {

    }
}
