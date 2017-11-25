package com.abl.RWD.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.abl.RWD.R;
import com.abl.RWD.activity.base.BaseNormalActivity;
import com.abl.RWD.common.Common;
import com.abl.RWD.util.IntentUtils;

/**
 * Created by yas on 2017/11/21.
 */

public class TestActivity extends BaseNormalActivity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        initLayout();
    }

    private void initLayout() {
        Button btn_detail= (Button) this.findViewById(R.id.btn_detail);
        btn_detail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                IntentUtils.startTransactionDetailActivity(TestActivity.this,null, Common.TYPE_DAIBAN);
            }
        });
        Button btn_next= (Button) this.findViewById(R.id.btn_next);
        btn_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                IntentUtils.starNextAccepterActivity(TestActivity.this,null,2,100);
            }
        });

    }

    @Override
    protected void handleRsp(Object obj, boolean isSucc, int errorCode, int seqNo, int src) {

    }
}
