package com.abl.RWD.util;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import com.abl.RWD.activity.DetailCheckBoxActivity;
import com.abl.RWD.activity.LoginActivity;
import com.abl.RWD.activity.MOdifyIPActivity;
import com.abl.RWD.activity.MainActivity;
import com.abl.RWD.activity.NextAccepterActivity;
import com.abl.RWD.activity.SearchContractActivity;
import com.abl.RWD.activity.SearchContractDetailActivity;
import com.abl.RWD.activity.SearchProjectActivity;
import com.abl.RWD.activity.SearchProjectDetailActivity;
import com.abl.RWD.activity.SubmitTypeSelectActivity;
import com.abl.RWD.activity.WorkDetailActivity;
import com.abl.RWD.entity.PContractItemEntity;
import com.abl.RWD.entity.PProjectItemEntity;
import com.abl.RWD.entity.PWorkDetailEntity;
import com.abl.RWD.entity.PWorkItemEntity;
import com.abl.RWD.entity.VAcceptDepartmentEntity;

import java.util.ArrayList;

/**
 * Created by yas on 2017/11/6.
 */

public class IntentUtils {
    public static final String KEY_ENTITY="entity";
    public static final String KEY_TYPE="type";
    public static final String KEY_STR="string";
    /**
     * 启动登录页
     * @param mContext
     */
    public static void startLoginActivity(Context mContext){
        Intent intent = new Intent(mContext, LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        mContext.startActivity(intent);
    }
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
    public static void startTransactionDetailActivity(Context mContext, PWorkItemEntity entity,int type){
        Intent intent=new Intent(mContext, WorkDetailActivity.class);
        intent.putExtra(KEY_ENTITY,entity);
        intent.putExtra(KEY_TYPE,type);
        mContext.startActivity(intent);
    }
    /**
     * 启动提交方式
     * @param mContext
     * @param noTag
     */
    public static void starSubmitTypeSelectActivity(Activity mContext, int requestCode, String noTag){
        Intent intent=new Intent(mContext,SubmitTypeSelectActivity.class);
        intent.putExtra("noTag", noTag);
        mContext.startActivityForResult(intent, requestCode);
    }
    /**
     * 启动接收人
     * @param mContext
     */
    public static void starNextAccepterActivity(Activity mContext, PWorkDetailEntity entity,int type, int requestCode){
        Intent intent=new Intent(mContext,NextAccepterActivity.class);
        intent.putExtra(KEY_ENTITY, entity);
        intent.putExtra(KEY_TYPE, type);
        mContext.startActivityForResult(intent, requestCode);
    }

    /**
     * 多选页面
     * @param mContext
     * @param options
     * @param requestCode
     */
    public static void startDetailCheckBoxActivity(Activity mContext, String options, int requestCode){
        Intent intent=new Intent(mContext,DetailCheckBoxActivity.class);
        intent.putExtra(KEY_STR, options);
        mContext.startActivityForResult(intent, requestCode);
    }
    public static void startModifyIpActivity(Activity mContext){
        Intent intent=new Intent(mContext,MOdifyIPActivity.class);
        mContext.startActivity(intent);
    }
}
