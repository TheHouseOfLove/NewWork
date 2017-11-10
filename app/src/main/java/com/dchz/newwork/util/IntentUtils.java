package com.dchz.newwork.util;

import android.content.Context;
import android.content.Intent;

import com.dchz.newwork.activity.MainActivity;
import com.dchz.newwork.activity.SearchContractActivity;
import com.dchz.newwork.activity.SearchContractDetailActivity;
import com.dchz.newwork.activity.SearchProjectActivity;
import com.dchz.newwork.activity.SearchProjectDetailActivity;

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
    public static void startSearchContractDetailActivity(Context mContext){
        Intent intent=new Intent(mContext, SearchContractDetailActivity.class);
        mContext.startActivity(intent);
    }
    /**
     * 启动合同查询页
     * @param mContext
     */
    public static void startSearchProjectDetailActivity(Context mContext){
        Intent intent=new Intent(mContext, SearchProjectDetailActivity.class);
        mContext.startActivity(intent);
    }
}
