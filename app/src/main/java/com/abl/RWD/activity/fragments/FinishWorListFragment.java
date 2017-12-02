package com.abl.RWD.activity.fragments;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;

import com.abl.RWD.R;
import com.abl.RWD.activity.base.BaseFragment;
import com.abl.RWD.adapter.AdapterWorkList;
import com.abl.RWD.common.Common;
import com.abl.RWD.common.MConfiger;
import com.abl.RWD.component.CommonHeaderView;
import com.abl.RWD.component.HeaderSearchView;
import com.abl.RWD.entity.PWorkItemEntity;
import com.abl.RWD.http.ProtocalManager;
import com.abl.RWD.http.rsp.RspWorkListEntity;
import com.abl.RWD.listener.OnItemClickListener;
import com.abl.RWD.msglist.ListViewEmptyView;
import com.abl.RWD.util.IntentUtils;
import com.abl.RWD.util.MyLog;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadmoreListener;

/**
 * Created by Administrator on 2017/11/18.
 */

public class FinishWorListFragment extends BaseFragment implements View.OnClickListener {
    private RecyclerView mRecyclerView;
    private AdapterWorkList mFinishAdapter;
    private int pageIndex = 1;
    private boolean hasNext = true;
    private String strWhere = "";
    private boolean isRefresh;
    private ListViewEmptyView mEmptyView;
    private CommonHeaderView mHeader;
    private HeaderSearchView mSearchView;
    private RefreshLayout mRefreshView;
    @Override
    public int getLayoutRes() {
        return R.layout.fragment_finish_work;
    }

    @Override
    public void initViews(View rootView) {
        mHeader = rootView.findViewById(R.id.mHeader);
        mHeader.updateType(CommonHeaderView.TYPE_ONLY_TITLE);
        mHeader.setTitle("已办事务");
        mSearchView = rootView.findViewById(R.id.header_search);
        mSearchView.setHint("申请人/事务标题/事务类型");
        mSearchView.addTextChangeListener(mSearchTextChangeListener);

        mRecyclerView = rootView.findViewById(R.id.finish_recycler);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mEmptyView = rootView.findViewById(R.id.emptyView);
        mRefreshView=rootView.findViewById(R.id.mRefreshLayout);
        mRefreshView.setOnRefreshLoadmoreListener(mRefreshListener);
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        if (hidden){
            ProtocalManager.getInstance().reqFinishWorkList(strWhere, 1, getCallBack());
            showLoading("正在获取数据。。。");
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        ProtocalManager.getInstance().reqFinishWorkList(strWhere, 1, getCallBack());
        showLoading("正在获取数据。。。");
    }
    @Override
    protected void handleRsp(Object obj, boolean isSucc, int errorCode, int seqNo, int src) {
        hideLoading();
        if (obj instanceof RspWorkListEntity) {
            RspWorkListEntity rsp = (RspWorkListEntity) obj;
            if (isRefresh) {
                isRefresh = false;
                mRefreshView.finishRefresh();
            }

            if (rsp != null && isSucc) {
                if (rsp.mEntity!=null&&rsp.mEntity.daiBan!=null&&rsp.mEntity.daiBan.size()>0) {
                    if (mRecyclerView.getVisibility()==View.GONE) {
                        mRecyclerView.setVisibility(View.VISIBLE);
                        mEmptyView.setVisibility(View.GONE);
                    }
                    if (mFinishAdapter == null) {
                        mFinishAdapter = new AdapterWorkList(getActivity(), rsp.mEntity.daiBan);
                        mFinishAdapter.setOnItemClickListener(itemClickListener);
                        mRecyclerView.setAdapter(mFinishAdapter);
                    } else {
                        if (pageIndex == 1) {
                            mFinishAdapter.reSetList(rsp.mEntity.daiBan);
                        } else {
                            mFinishAdapter.appendList(rsp.mEntity.daiBan);
                            mRefreshView.finishLoadmore();
                        }
                    }
                if (rsp.mEntity.daiBan.size() < MConfiger.PAGE_SIZE) {
                    hasNext = false;
                }
                }else{
                    mRecyclerView.setVisibility(View.GONE);
                    mEmptyView.setVisibility(View.VISIBLE);
                    hasNext = false;
                }
            }else{
                mRecyclerView.setVisibility(View.GONE);
                mEmptyView.setVisibility(View.VISIBLE);
                hasNext = false;
            }
        }
    }
    /**
     * item点击事件
     */
    private OnItemClickListener itemClickListener = new OnItemClickListener() {
        @Override
        public void onItemClick(View view, int position) {
            PWorkItemEntity entity = mFinishAdapter.getItemEntity(position);
            IntentUtils.startTransactionDetailActivity(getActivity(),entity, Common.TYPE_YIBAN);

        }
    };
    /**
     * 下拉刷新，上了加载更多监听
     */
    private OnRefreshLoadmoreListener mRefreshListener=new OnRefreshLoadmoreListener() {
        @Override
        public void onLoadmore(RefreshLayout refreshlayout) {
            if (hasNext) {
                int page = nextPage();
                ProtocalManager.getInstance().reqFinishWorkList(strWhere,
                        page, getCallBack());
            } else {
                showToast("没有更多数据");
                mRefreshView.finishLoadmore();
            }
        }

        @Override
        public void onRefresh(RefreshLayout refreshlayout) {
            isRefresh = true;
            ProtocalManager.getInstance().reqFinishWorkList(strWhere,
                    refreshPage(), getCallBack());
        }
    };
    /**
     * 搜索栏文本变化监听
     */
    private TextWatcher mSearchTextChangeListener = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void afterTextChanged(Editable editable) {
            strWhere = editable.toString();
            ProtocalManager.getInstance().reqFinishWorkList(strWhere,
                    refreshPage(), getCallBack());
        }
    };

    private int nextPage() {
        pageIndex = pageIndex + 1;
        return pageIndex;
    }

    private int refreshPage() {
        pageIndex = 1;
        hasNext = true;
        return pageIndex;

    }

    @Override
    public void onClick(View view) {
        if (view==mEmptyView){
            ProtocalManager.getInstance().reqFinishWorkList("", 1, getCallBack());
            showLoading("正在获取数据。。。");
        }
    }
}
