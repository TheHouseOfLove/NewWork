package com.dchz.newwork.activity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;

import com.dchz.newwork.R;
import com.dchz.newwork.activity.base.BaseNormalActivity;
import com.dchz.newwork.component.CommonHeaderView;
import com.dchz.newwork.component.HeaderSearchView;
import com.dchz.newwork.listener.IBtnClickListener;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

/**
 * Created by yas on 2017/11/6.
 * 合同查询
 */

public class SearchContractActivity extends BaseNormalActivity{
    private CommonHeaderView mHeader;
    private HeaderSearchView mSearchView;
    private XRecyclerView mRecyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_contract);
        initLayout();
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
    }

    @Override
    protected void handleRsp(Object obj, boolean isSucc, int errorCode, int seqNo, int src) {

    }
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
}
