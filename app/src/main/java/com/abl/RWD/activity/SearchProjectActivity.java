package com.abl.RWD.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;

import com.abl.RWD.R;
import com.abl.RWD.activity.base.BaseNormalActivity;
import com.abl.RWD.adapter.AdapterContractList;
import com.abl.RWD.adapter.AdapterProjectList;
import com.abl.RWD.common.MConfiger;
import com.abl.RWD.component.CommonHeaderView;
import com.abl.RWD.component.HeaderSearchView;
import com.abl.RWD.entity.PContractItemEntity;
import com.abl.RWD.entity.PProjectItemEntity;
import com.abl.RWD.http.ProtocalManager;
import com.abl.RWD.http.rsp.RspContractListEntity;
import com.abl.RWD.http.rsp.RspProjectListEntity;
import com.abl.RWD.listener.IBtnClickListener;
import com.abl.RWD.listener.OnItemClickListener;
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
    private int page=1;
    private boolean hasNext=true;
    private boolean isRefresh;
    private String strWhere="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_project);
        initLayout();
        ProtocalManager.getInstance().reqProjectList(page,strWhere,getCallBack());
        showLoading();
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
    }

    @Override
    protected void handleRsp(Object obj, boolean isSucc, int errorCode, int seqNo, int src) {
        hideLoadingDialog();
        if (obj instanceof RspProjectListEntity){
            if (isRefresh){
                isRefresh=false;
                mRecyclerView.refreshComplete();
            }
            if (page>1){
                mRecyclerView.loadMoreComplete();
            }
            RspProjectListEntity rsp= (RspProjectListEntity) obj;
            if (rsp!=null&&isSucc){
                if (mAdapter==null){
                    mAdapter=new AdapterProjectList(SearchProjectActivity.this,rsp.mEntity.XMInfo);
                    mAdapter.setOnItemClickListener(mItemClickListener);
                    mRecyclerView.setAdapter(mAdapter);
                }else{
                    if (page==1){
                        mAdapter.reSetList(rsp.mEntity.XMInfo);
                    }else{
                        mAdapter.appendList(rsp.mEntity.XMInfo);
                    }
                }
                if (rsp.mEntity.XMInfo.size()< MConfiger.PAGE_BIG_SIZE){
                    hasNext=false;
                }
            }else{
                showToast("数据获取失败，请检查您的网络后再试！");
            }
        }
    }
    private OnItemClickListener mItemClickListener=new OnItemClickListener() {
        @Override
        public void onItemClick(View view, int position) {
            if (mAdapter!=null&&mAdapter.getItemCount()>position){
                PProjectItemEntity entity=mAdapter.getItemEntity(position);
                showToast(entity.XMMingCheng);
            }
        }
    };
    /**
     * 下拉刷新，上了加载更多监听
     */
    private XRecyclerView.LoadingListener mLoadingListener=new XRecyclerView.LoadingListener() {
        @Override
        public void onRefresh() {
            isRefresh=true;
            ProtocalManager.getInstance().reqProjectList(refreshPage(), strWhere,getCallBack());
        }

        @Override
        public void onLoadMore() {
            if (hasNext){
                ProtocalManager.getInstance().reqProjectList(nextPage(), strWhere,getCallBack());
            }
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
            strWhere=editable.toString();
            ProtocalManager.getInstance().reqProjectList(refreshPage(), strWhere,getCallBack());
        }
    };

    private int nextPage() {
        page=page+1;
        return page;
    }

    private int refreshPage() {
        page=1;
        return page;
    }
}
