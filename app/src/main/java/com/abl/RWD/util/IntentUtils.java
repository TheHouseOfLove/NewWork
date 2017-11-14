package com.abl.RWD.util;

import android.content.Context;
import android.content.Intent;

import com.abl.RWD.activity.MainActivity;
import com.abl.RWD.activity.SearchContractActivity;
import com.abl.RWD.activity.SearchContractDetailActivity;
import com.abl.RWD.activity.SearchProjectActivity;
import com.abl.RWD.activity.SearchProjectDetailActivity;
import com.abl.RWD.activity.WorkDetailActivity;
import com.abl.RWD.entity.PContractItemEntity;
import com.abl.RWD.entity.PProjectItemEntity;

/**
 * Created by yas on 2017/11/6.
 */

public class IntentUtils {
    public static final String KEY_ENTITY="entity";
    /**
     * 启动主页
     * @param mContext
     */
    public static void startMainActivity(Context mContext){
        Intent intent=new Intent(mContext, MainActivity.class);
        mContext.startActivity(intent);
    }
    /**
     * 启动项目查询页
     * @param mContext
     */
    public static void startSearchProjectActivity(Context mContext){
        Intent intent=new Intent(mContext, SearchProjectActivity.class);
        mContext.startActivity(intent);
    }
    /**
     * 启动合同查询页
     * @param mContext
     */
    public static void startSearchContractActivity(Context mContext){
        Intent intent=new Intent(mContext, SearchContractActivity.class);
        mContext.startActivity(intent);
    }
    /**
     * 启动合同查询页
     * @param mContext
     */
    public static void startSearchContractDetailActivity(Context mContext, PContractItemEntity entity){
        Intent intent=new Intent(mContext, SearchContractDetailActivity.class);
        intent.putExtra(KEY_ENTITY,entity);
        mContext.startActivity(intent);
    }
    /**
     * 启动合同查询页
     * @param mContext
     */
    public static void startSearchProjectDetailActivity(Context mContext, PProjectItemEntity entity){
        Intent intent=new Intent(mContext, SearchProjectDetailActivity.class);
        intent.putExtra(KEY_ENTITY,entity);
        mContext.startActivity(intent);
    }
    /**
     * 启动事务详情页
     * @param mContext
     */
    public static void startTransactionDetailActivity(Context mContext){
        Intent intent=new Intent(mContext, WorkDetailActivity.class);
        mContext.startActivity(intent);
    }
}
