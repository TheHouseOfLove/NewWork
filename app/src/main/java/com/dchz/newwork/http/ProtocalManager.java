package com.dchz.newwork.http;

import com.dchz.common.http.HttpEngine;
import com.dchz.newwork.common.Common;
import com.dchz.newwork.http.base.ReqBaseEntity;
import com.dchz.newwork.http.base.TaskCommon;
import com.dchz.newwork.http.listener.ICallBack;
import com.dchz.newwork.http.req.ReqLoginEntity;
import com.dchz.newwork.util.MyLog;

/**
 * Created by diaosi on 2015/12/29.
 */
public class ProtocalManager {
    private final String TAG = "ProtocalManagerV2";

    private static ProtocalManager instance;

    private ProtocalManager() {

    }

    public synchronized static final ProtocalManager getInstance() {
        if (instance == null) {
            instance = new ProtocalManager();
        }
        return instance;
    }

    /***
     * 对应的Task任务添加到Http请求引擎中
     * @param reqEntity
     * @param callBack
     * @return
     */
    private int addTask(ReqBaseEntity reqEntity, ICallBack<Object> callBack) {
        int seqNo = Common.getSeqNo();
        reqEntity.seqNo = seqNo;
        TaskCommon task = new TaskCommon(reqEntity, callBack);
        HttpEngine.getInstance().addTask(task);
        MyLog.debug(TAG,"[addTask]=====================");
        return seqNo;
    }
    /**
     * 登录注册
     * @param userName
     * @param pwd
     * @param callBack
     * @return
     */
    public int login(String userName,String pwd,ICallBack<Object> callBack){
        ReqLoginEntity req=new ReqLoginEntity();
        req.UserName=userName;
        req.UserPwd=pwd;
        req.mVisitType=ReqBaseEntity.TYPE_JAVA_GET;
        MyLog.debug(TAG,"[login]=====================");
        return addTask(req,callBack);
    }
}