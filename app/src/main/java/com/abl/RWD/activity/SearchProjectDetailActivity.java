package com.abl.RWD.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;


import com.abl.RWD.R;
import com.abl.RWD.activity.base.BaseNormalActivity;
import com.abl.RWD.component.CommonHeaderView;
import com.abl.RWD.component.ListTopItemView;
import com.abl.RWD.entity.PProjectItemEntity;
import com.abl.RWD.listener.IBtnClickListener;
import com.abl.RWD.util.IntentUtils;

import java.util.ArrayList;

/**
 * Created by yas on 2016/10/11.
 */
public class SearchProjectDetailActivity extends BaseNormalActivity{
    private CommonHeaderView mHeader;
    private ListTopItemView mTopView;
    private PProjectItemEntity mEntity;
    private ArrayList<String> keyList=new ArrayList<String>();
    private ArrayList<String> valList=new ArrayList<String>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_project_detail);
        initExtra();
        initLayout();
    }

    @Override
    protected void handleRsp(Object obj, boolean isSucc, int errorCode, int seqNo, int src) {

    }

    private void initExtra() {
        mEntity= (PProjectItemEntity) getIntent().getSerializableExtra(IntentUtils.KEY_ENTITY);
    }

    private void initLayout() {
        mHeader= (CommonHeaderView) this.findViewById(R.id.header_contractDetail);
        mHeader.updateType(CommonHeaderView.TYPE_ONLY_LEFT_IMAGE);
        mHeader.setTitle("项目详情");
        mHeader.setHeaderClickListener(new IBtnClickListener() {
            @Override
            public void btnLeftClick() {
                finish();
            }
        });
        mTopView= (ListTopItemView) this.findViewById(R.id.topView);
        //项目名称，项目编号，客户名称，主导部门，项目经理，立项日期，配合部门
        keyList.add("项目名称");
        keyList.add("项目编号");
        keyList.add("客户名称");
        keyList.add("承担部门");
        keyList.add("项目负责人");
        keyList.add("立项日期");
        keyList.add("配合部门");
        if (mEntity!=null){
            valList.add(mEntity.XMMingCheng);
            valList.add(mEntity.XMBianHao);
            valList.add(mEntity.KHMingCheng);
            valList.add(mEntity.ZhuDaoBuMen);
            valList.add(mEntity.XMJingLi);
            valList.add(mEntity.XMLiXiangRiQi.split(" ")[0]);
            valList.add(mEntity.PeiHeBuMen);
        }
        mTopView.setData(keyList,valList);
    }
}
