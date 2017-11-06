package com.dchz.newwork.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.text.Editable;
import android.text.TextWatcher;

import com.dchz.newwork.R;
import com.dchz.newwork.activity.base.BaseNormalActivity;
import com.dchz.newwork.adapter.AdapterProjectList;
import com.dchz.newwork.common.Global;
import com.dchz.newwork.component.CommonHeaderView;
import com.dchz.newwork.component.HeaderSearchView;
import com.dchz.newwork.entity.PProjectItemEntity;
import com.dchz.newwork.listener.IBtnClickListener;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;

/**
 * Created by yas on 2017/11/6.
 * 项目查询
 */

public class SearchProjectActivity extends BaseNormalActivity{
    private CommonHeaderView mHeader;
    private HeaderSearchView mSearchView;
    private XRecyclerView mRecyclerView;
    private AdapterProjectList mAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_project);
        initLayout();
    }

    private void initLayout() {
        mHeader= (CommonHeaderView) this.findViewById(R.id.header_searchProject);
        mHeader.updateType(CommonHeaderView.TYPE_ONLY_LEFT_IMAGE);
        mHeader.setTitle("项目查询");
        mHeader.setHeaderClickListener(new IBtnClickListener() {
            @Override
            public void btnLeftClick() {
                finish();
            }
        });
        mSearchView= (HeaderSearchView) this.findViewById(R.id.header_search);
        mSearchView.setHint("项目名称/项目负责人/承担部门/客户名称");
        mSearchView.addTextChangeListener(mSearchTextChangeListener);
        mRecyclerView= (XRecyclerView) this.findViewById(R.id.mMsgPage);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mRecyclerView.setLoadingListener(mLoadingListener);
        mAdapter=new AdapterProjectList(this,getTestData());
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    protected void handleRsp(Object obj, boolean isSucc, int errorCode, int seqNo, int src) {

    }
    /**
     * 下拉刷新，上了加载更多监听
     */
    private XRecyclerView.LoadingListener mLoadingListener=new XRecyclerView.LoadingListener() {
        @Override
        public void onRefresh() {
        }

        @Override
        public void onLoadMore() {
        }
    };
    /**
     * 搜索栏文本变化监听
     */
    private TextWatcher mSearchTextChangeListener=new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void afterTextChanged(Editable editable) {

        }
    };

    protected ArrayList<PProjectItemEntity> getTestData(){
        ArrayList<PProjectItemEntity> mList=new ArrayList<>();
        for (int i=1;i<11;i++){
            PProjectItemEntity entity=new PProjectItemEntity();
            entity.XMMingCheng="客户名称"+i;
            mList.add(entity);
        }
        return mList;
    }
}
