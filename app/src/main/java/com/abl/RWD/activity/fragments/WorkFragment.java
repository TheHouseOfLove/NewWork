package com.abl.RWD.activity.fragments;

import android.support.v7.widget.LinearLayoutManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;

import com.abl.RWD.R;
import com.abl.RWD.activity.base.BaseFragment;
import com.abl.RWD.adapter.AdapterWorkList;
import com.abl.RWD.common.MConfiger;
import com.abl.RWD.component.HeaderSearchView;
import com.abl.RWD.component.HeaderSelectView;
import com.abl.RWD.entity.PWorkItemEntity;
import com.abl.RWD.http.ProtocalManager;
import com.abl.RWD.http.rsp.RspWorkListEntity;
import com.abl.RWD.listener.ITabChangeListener;
import com.abl.RWD.listener.OnItemClickListener;
import com.abl.RWD.util.IntentUtils;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;

/**
 * Created by yas on 2017/11/6.
 */

public class WorkFragment extends BaseFragment {
    private final int TYPE_PENDING=1;
    private final int TYPE_FINISH=2;
    private HeaderSelectView mHeader;
    private XRecyclerView xrvVisiting;
    private AdapterWorkList mPendingAdapter;
    private AdapterWorkList mFinishAdapter;
    private HeaderSearchView mSearchView;
    private int mType=TYPE_PENDING;
    private int pageIndexLeft = 1;
    private int pageIndexRight=1;
    private boolean hasNextLeft = true;
    private boolean hasNextRight=true;
    private String strWhereYiBan="";
    private String strWhereDaiBan="";
    private boolean isRefresh;
    @Override
    public int getLayoutRes() {
        return R.layout.fragment_work;
    }

    @Override
    public void initViews(View rootView) {
        mHeader=rootView.findViewById(R.id.header_work);
        mHeader.addTabChangeListener(mTabChangeListener);
        mHeader.setTabTitle("待办事务", "已办事务");

        mSearchView=rootView.findViewById(R.id.header_search);
        mSearchView.setHint("申请人/事务标题/事务类型");
        mSearchView.addTextChangeListener(mSearchTextChangeListener);

        xrvVisiting=rootView.findViewById(R.id.xrv_visiting);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        xrvVisiting.setLayoutManager(linearLayoutManager);
        xrvVisiting.setLoadingListener(mLoadingListener);
    }

    @Override
    protected void handleRsp(Object obj, boolean isSucc, int errorCode, int seqNo, int src) {
        hideLoading();
        if (obj instanceof RspWorkListEntity){
            RspWorkListEntity rsp= (RspWorkListEntity) obj;
            if (isRefresh){
                isRefresh=false;
                xrvVisiting.refreshComplete();
            }
            xrvVisiting.loadMoreComplete();
            if (rsp!=null&&isSucc){
                if (mType==TYPE_PENDING){
                    if (mPendingAdapter==null){
                        mPendingAdapter=new AdapterWorkList(getActivity(),rsp.mEntity.daiBan);
                        mPendingAdapter.setOnItemClickListener(itemClickListener);
                        xrvVisiting.setAdapter(mPendingAdapter);
                    }else{
                        if (pageIndexLeft==1){
                            mPendingAdapter.reSetList(rsp.mEntity.daiBan);
                        }else {
                            mPendingAdapter.appendList(rsp.mEntity.daiBan);
                        }
                    }
                    if (rsp.mEntity.daiBan.size() < MConfiger.PAGE_SIZE) {
                        hasNextLeft = false;
                    }
                }else if (mType==TYPE_FINISH){
                    if (mFinishAdapter==null){
                        mFinishAdapter=new AdapterWorkList(getActivity(),rsp.mEntity.daiBan);
                        mFinishAdapter.setOnItemClickListener(itemClickListener);
                        xrvVisiting.setAdapter(mFinishAdapter);
                    }else{
                        if (pageIndexLeft==1){
                            mFinishAdapter.reSetList(rsp.mEntity.daiBan);
                        }else {
                            mFinishAdapter.appendList(rsp.mEntity.daiBan);
                        }
                    }
                    if (rsp.mEntity.daiBan.size() < MConfiger.PAGE_SIZE) {
                        hasNextRight = false;
                    }
                }
            }
        }
    }

    /**
     * item点击事件
     */
    private OnItemClickListener itemClickListener=new OnItemClickListener() {
        @Override
        public void onItemClick(View view, int position) {
            if (mType==TYPE_PENDING) {
                PWorkItemEntity entity = mPendingAdapter.getItemEntity(position);
                showToast(entity.FormName);
                IntentUtils.startTransactionDetailActivity(getActivity());
            }else if (mType==TYPE_FINISH){
                PWorkItemEntity entity = mFinishAdapter.getItemEntity(position);
                showToast(entity.FormName);
                IntentUtils.startTransactionDetailActivity(getActivity());
            }
        }
    };
    @Override
    public void onResume() {
        super.onResume();
        ProtocalManager.getInstance().reqPendingWorkList("","",1,getCallBack());
        showLoading("正在获取数据。。。");
    }

    /**
     * 下拉刷新，上了加载更多监听
     */
    private XRecyclerView.LoadingListener mLoadingListener=new XRecyclerView.LoadingListener() {
        @Override
        public void onRefresh() {
            isRefresh=true;
            if (mType == TYPE_PENDING) {
                mPendingAdapter=null;
                ProtocalManager.getInstance().reqPendingWorkList("",strWhereDaiBan,
                        refreshPage(),getCallBack());
            } else if (mType == TYPE_FINISH) {
                mFinishAdapter=null;
                 ProtocalManager.getInstance().reqFinishWorkList("",strWhereYiBan,
                        refreshPage(),getCallBack());
            }
        }

        @Override
        public void onLoadMore() {
            if (mType == TYPE_PENDING) {
                if (hasNextLeft) {
                    int page = nextPage();
                    ProtocalManager.getInstance().reqPendingWorkList("",strWhereDaiBan,
                            page, getCallBack());
                }else{
                    showToast("没有更多数据");
                }
            } else if (mType == TYPE_FINISH) {
                if (hasNextRight) {
                    int page = nextPage();
                    ProtocalManager.getInstance().reqFinishWorkList("",strWhereYiBan,
                            page, getCallBack());
                }else{
                    showToast("没有更多数据");
                }
            }
        }
    };
    /**
     * 头部tab切换监听
     */
    private ITabChangeListener mTabChangeListener=new ITabChangeListener() {
        @Override
        public void leftTabClick() {
            mType=TYPE_PENDING;
            if (mPendingAdapter==null){
                ProtocalManager.getInstance().reqPendingWorkList("",strWhereDaiBan,refreshPage(),getCallBack());
                showLoading("正在获取数据。。。");
            }else{
                xrvVisiting.setAdapter(mPendingAdapter);
            }
            mSearchView.setText(strWhereDaiBan);
        }

        @Override
        public void rightTabClick() {
            mType=TYPE_FINISH;
            if (mFinishAdapter==null){
                ProtocalManager.getInstance().reqFinishWorkList("",strWhereYiBan,refreshPage(),getCallBack());
                showLoading("正在获取数据。。。");
            }else{
                xrvVisiting.setAdapter(mFinishAdapter);
            }
            mSearchView.setText(strWhereYiBan);
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

    private int nextPage() {
        if (mType==TYPE_PENDING){
            pageIndexLeft=pageIndexLeft+1;
            return pageIndexLeft;
        }else if (mType==TYPE_FINISH){
            pageIndexRight=pageIndexRight+1;
            return pageIndexRight;
        }
        return 1;
    }

    private int refreshPage() {
        if (mType==TYPE_PENDING){
            pageIndexLeft=1;
            hasNextLeft=true;
            return pageIndexLeft;
        }else if (mType==TYPE_FINISH){
            pageIndexRight=1;
            hasNextRight=true;
            return pageIndexRight;
        }
        return 1;
    }
}
