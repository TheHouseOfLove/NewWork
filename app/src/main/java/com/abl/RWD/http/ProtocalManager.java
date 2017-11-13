package com.abl.RWD.http;

import com.abl.RWD.common.MConfiger;
import com.abl.RWD.http.req.ReqFinshWorkListEntity;
import com.abl.RWD.http.req.ReqPendingWorkListEntity;
import com.abl.common.http.HttpEngine;
import com.abl.RWD.common.Common;
import com.abl.RWD.http.base.ReqBaseEntity;
import com.abl.RWD.http.base.TaskCommon;
import com.abl.RWD.http.listener.ICallBack;
import com.abl.RWD.http.req.ReqLoginEntity;
import com.abl.RWD.util.MyLog;

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
        return addTask(req,callBack);
    }

    /**
     * 待办列表
     * @param YHID
     * @param strWhere
     * @param pageIndex
     * @param callBack
     * @return
     */
    public int reqPendingWorkList(String YHID,String strWhere,int pageIndex,ICallBack<Object> callBack){
        ReqPendingWorkListEntity req=new ReqPendingWorkListEntity();
        req.YHID=YHID;
        req.strWhere=strWhere;
        req.pageIndex=pageIndex;
        req.pageSize= MConfiger.PAGE_SIZE;
        return addTask(req,callBack);
    }
    /**
     * 已办列表
     * @param YHID
     * @param strWhere
     * @param pageIndex
     * @param callBack
     * @return
     */
    public int reqFinishWorkList(String YHID,String strWhere,int pageIndex,ICallBack<Object> callBack){
        ReqFinshWorkListEntity req=new ReqFinshWorkListEntity();
        req.YHID=YHID;
        req.strWhere=strWhere;
        req.pageIndex=pageIndex;
        req.pageSize= MConfiger.PAGE_SIZE;
        return addTask(req,callBack);
    }
}