package com.dchz.newwork.util;

import android.content.Context;
import android.content.Intent;

import com.dchz.newwork.activity.SearchContractActivity;
import com.dchz.newwork.activity.SearchProjectActivity;

/**
 * Created by yas on 2017/11/6.
 */

public class IntentUtils {

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
}
