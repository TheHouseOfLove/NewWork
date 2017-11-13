package com.abl.RWD.activity;

import android.os.Bundle;

import com.abl.RWD.R;
import com.abl.RWD.activity.base.BaseNormalActivity;

/**
 * Created by yas on 2017/11/13.
 * 事务详情页
 */

public class TransactionDetailActivity extends BaseNormalActivity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transaction_detail);
    }

    @Override
    protected void handleRsp(Object obj, boolean isSucc, int errorCode, int seqNo, int src) {

    }
}
