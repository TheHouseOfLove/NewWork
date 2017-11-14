package com.abl.RWD.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;

import com.abl.RWD.R;
import com.abl.RWD.activity.base.BaseNormalActivity;
import com.abl.RWD.adapter.AdapterContractList;
import com.abl.RWD.common.MConfiger;
import com.abl.RWD.component.CommonHeaderView;
import com.abl.RWD.component.HeaderSearchView;
import com.abl.RWD.entity.PContractItemEntity;
import com.abl.RWD.http.ProtocalManager;
import com.abl.RWD.http.rsp.RspContractListEntity;
import com.abl.RWD.listener.IBtnClickListener;
import com.abl.RWD.listener.OnItemClickListener;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;

/**
 * Created by yas on 2017/11/6.
 * 合同查询
 */

public class SearchContractActivity extends BaseNormalActivity{
    private CommonHeaderView mHeader;
    private HeaderSearchView mSearchView;
    private XRecyclerView mRecyclerView;
    private AdapterContractList mAdapter;
    private int page=1;
    private boolean hasNext=true;
    private boolean isRefresh;
    private String strWhere="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_contract);
        initLayout();
        ProtocalManager.getInstance().reqContractList(page,strWhere,getCallBack());
        showLoading();
    }

    private void initLayout() {
        mHeader= (CommonHeaderView) this.findViewById(R.id.header_searchContract);
        mHeader.updateType(CommonHeaderView.TYPE_ONLY_LEFT_IMAGE);
        mHeader.setTitle("合同查询");
        mHeader.setHeaderClickListener(new IBtnClickListener() {
            @Override
            public void btnLeftClick() {
                finish();
            }
        });
        mSearchView= (HeaderSearchView) this.findViewById(R.id.header_search);
        mSearchView.setHint("合同名称/签订部门/客户名称");
        mSearchView.addTextChangeListener(mSearchTextChangeListener);
        mRecyclerView= (XRecyclerView) this.findViewById(R.id.mMsgPage);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mRecyclerView.setLoadingListener(mLoadingListener);
//        mAdapter=new AdapterContractList(this,getTestData());
//        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    protected void handleRsp(Object obj, boolean isSucc, int errorCode, int seqNo, int src) {
        hideLoadingDialog();
        if (obj instanceof RspContractListEntity){
            if (isRefresh){
                isRefresh=false;
                mRecyclerView.refreshComplete();
            }
            if (page>1){
                mRecyclerView.loadMoreComplete();
            }
            RspContractListEntity rsp= (RspContractListEntity) obj;
            if (rsp!=null&&isSucc){
                if (mAdapter==null){
                    mAdapter=new AdapterContractList(SearchContractActivity.this,rsp.mEntity.HTInfo);
                    mAdapter.setOnItemClickListener(mItemClickListener);
                    mRecyclerView.setAdapter(mAdapter);
                }else{
                    if (page==1){
                        mAdapter.reSetList(rsp.mEntity.HTInfo);
                    }else{
                        mAdapter.appendList(rsp.mEntity.HTInfo);
                    }
                }
                if (rsp.mEntity.HTInfo.size()< MConfiger.PAGE_BIG_SIZE){
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
                PContractItemEntity entity=mAdapter.getItemEntity(position);
                showToast(entity.HTMingCheng);
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
            ProtocalManager.getInstance().reqContractList(refreshPage(), strWhere,getCallBack());
        }

        @Override
        public void onLoadMore() {
            if (hasNext){
                ProtocalManager.getInstance().reqContractList(nextPage(), strWhere,getCallBack());
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
            ProtocalManager.getInstance().reqContractList(refreshPage(), strWhere,getCallBack());
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
