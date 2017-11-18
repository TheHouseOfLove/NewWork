package com.abl.RWD.activity.fragments;

import android.support.v7.widget.LinearLayoutManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;

import com.abl.RWD.R;
import com.abl.RWD.activity.base.BaseFragment;
import com.abl.RWD.adapter.AdapterWorkList;
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

/**
 * Created by Administrator on 2017/11/18.
 */

public class FinishWorListFragment extends BaseFragment{
    private XRecyclerView xrvVisiting;
    private AdapterWorkList mFinishAdapter;
    private int pageIndex = 1;
    private boolean hasNext = true;
    private String strWhere = "";
    private boolean isRefresh;
    private ListViewEmptyView mEmptyView;
    private CommonHeaderView mHeader;
    private HeaderSearchView mSearchView;
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

        xrvVisiting = rootView.findViewById(R.id.xrv_visiting);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        xrvVisiting.setLayoutManager(linearLayoutManager);
        xrvVisiting.setLoadingListener(mLoadingListener);
        mEmptyView = rootView.findViewById(R.id.emptyView);
        xrvVisiting.setEmptyView(mEmptyView);
    }
    @Override
    public void onResume() {
        super.onResume();
        ProtocalManager.getInstance().reqFinishWorkList("", 1, getCallBack());
        showLoading("正在获取数据。。。");
    }
    @Override
    protected void handleRsp(Object obj, boolean isSucc, int errorCode, int seqNo, int src) {
        hideLoading();
        if (obj instanceof RspWorkListEntity) {
            RspWorkListEntity rsp = (RspWorkListEntity) obj;
            if (isRefresh) {
                isRefresh = false;
                xrvVisiting.refreshComplete();
            }

            if (rsp != null && isSucc) {
                if (mFinishAdapter == null) {
                    mFinishAdapter = new AdapterWorkList(getActivity(), rsp.mEntity.daiBan);
                    mFinishAdapter.setOnItemClickListener(itemClickListener);
                    xrvVisiting.setAdapter(mFinishAdapter);
                } else {
                    if (pageIndex == 1) {
                        mFinishAdapter.reSetList(rsp.mEntity.daiBan);
                    } else {
                        mFinishAdapter.appendList(rsp.mEntity.daiBan);
                        xrvVisiting.loadMoreComplete();
                    }
                }
                if (rsp.mEntity.daiBan.size() < MConfiger.PAGE_SIZE) {
                    hasNext = false;
                }
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
            showToast(entity.FormName);
            IntentUtils.startTransactionDetailActivity(getActivity());

        }
    };
    /**
     * 下拉刷新，上了加载更多监听
     */
    private XRecyclerView.LoadingListener mLoadingListener = new XRecyclerView.LoadingListener() {
        @Override
        public void onRefresh() {
            isRefresh = true;
            ProtocalManager.getInstance().reqFinishWorkList(strWhere,
                    refreshPage(), getCallBack());

        }

        @Override
        public void onLoadMore() {
            MyLog.debug("Pending","[onLoadMore]  hasNext:"+hasNext);
            if (hasNext) {
                int page = nextPage();
                ProtocalManager.getInstance().reqFinishWorkList(strWhere,
                        page, getCallBack());
            } else {
                showToast("没有更多数据");
                xrvVisiting.loadMoreComplete();
            }

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
}
