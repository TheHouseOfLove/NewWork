package com.abl.RWD.http;

import com.abl.RWD.common.MConfiger;
import com.abl.RWD.controller.LoginController;
import com.abl.RWD.entity.PWorkItemEntity;
import com.abl.RWD.http.req.ReqBanLiYiJianEntity;
import com.abl.RWD.http.req.ReqContractListEntity;
import com.abl.RWD.http.req.ReqFinshWorkListEntity;
import com.abl.RWD.http.req.ReqMonthlyContractEntity;
import com.abl.RWD.http.req.ReqMonthlyPaymentEntity;
import com.abl.RWD.http.req.ReqPendingWorkListEntity;
import com.abl.RWD.http.req.ReqProjectListEntity;
import com.abl.RWD.http.req.ReqQuarterlyContractEntity;
import com.abl.RWD.http.req.ReqQuarterlyPaymentEntity;
import com.abl.RWD.http.req.ReqReturnFlowBusinessEntity;
import com.abl.RWD.http.req.ReqSaveDateEntity;
import com.abl.RWD.http.req.ReqSubmitFlowBusinessEntity;
import com.abl.RWD.http.req.ReqWorkDetailEntity;
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
        req.mVisitType=ReqBaseEntity.TYPE_JAVA_POST;
        return addTask(req,callBack);
    }

    /**
     * 待办列表
     * @param strWhere
     * @param pageIndex
     * @param callBack
     * @return
     */
    public int reqPendingWorkList(String strWhere,int pageIndex,ICallBack<Object> callBack){
        ReqPendingWorkListEntity req=new ReqPendingWorkListEntity();
//        req.YHID=SharePreLoginUtil.loadLoginInfo().YHID;
        req.YHID=LoginController.getInstance().getYHID();
        req.strWhere=strWhere;
        req.pageIndex=pageIndex;
        req.pageSize= MConfiger.PAGE_SIZE;
        return addTask(req,callBack);
    }
    /**
     * 已办列表
     * @param strWhere
     * @param pageIndex
     * @param callBack
     * @return
     */
    public int reqFinishWorkList(String strWhere,int pageIndex,ICallBack<Object> callBack){
        ReqFinshWorkListEntity req=new ReqFinshWorkListEntity();
        req.YHID=LoginController.getInstance().getYHID();
        req.strWhere=strWhere;
        req.pageIndex=pageIndex;
        req.pageSize= MConfiger.PAGE_SIZE;
        return addTask(req,callBack);
    }

    /**
     * 获取事务详情
     * @param entity
     * @param callBack
     * @return
     */
    public int getWorkDetail(PWorkItemEntity entity, ICallBack<Object> callBack){
        ReqWorkDetailEntity req=new ReqWorkDetailEntity();
        req.LCID=entity.LCID;
        req.SLID=entity.SLID;
        req.YWID=entity.YWID;
        req.FormID=entity.FormID;
        req.YHID=LoginController.getInstance().getYHID();
        String BLUrl=entity.BLUrl;
        String[] strs=BLUrl.split("\\?");
        req.UrlParam=strs[1].replaceAll("\\&", "\\$");
        String[] strs1=BLUrl.split(";");
        String[] strs2=strs1[1].split("=");
        String[] strs3=strs2[1].split("@");
        req.JDID=strs3[0];
        return addTask(req,callBack);
    }
    /**
     * 合同查询
     * @param page
     * @param strWhere
     * @param callBack
     * @return
     */
    public int reqContractList(int page,String strWhere,ICallBack<Object> callBack){
        ReqContractListEntity req=new ReqContractListEntity();
//        req.strYHID= SharePreLoginUtil.loadLoginInfo().YHID;
        req.strYHID=LoginController.getInstance().getYHID();
        req.pageIndex=page;
        req.pageSize=MConfiger.PAGE_BIG_SIZE;
        req.strWhere=strWhere;
        return addTask(req,callBack);
    }

    /**
     * 项目查询
     * @param page
     * @param strWhere
     * @param callBack
     * @return
     */
    public int reqProjectList(int page,String strWhere,ICallBack<Object> callBack){
        ReqProjectListEntity req=new ReqProjectListEntity();
//        req.strYHID= SharePreLoginUtil.loadLoginInfo().YHID;
        req.strYHID=LoginController.getInstance().getYHID();
        req.pageIndex=page;
        req.pageSize=MConfiger.PAGE_BIG_SIZE;
        req.strWhere=strWhere;
        return addTask(req,callBack);
    }

    /**
     * 获取季度合同
     * @param year
     * @param callBack
     * @return
     */
    public int getQuarterlyContract(String year,ICallBack<Object> callBack){
        ReqQuarterlyContractEntity req=new ReqQuarterlyContractEntity();
        req.NianValue=year;
        return addTask(req,callBack);
    }
    /**
     * 获取季度收款
     * @param year
     * @param callBack
     * @return
     */
    public int getQuarterlyPayment(String year,ICallBack<Object> callBack){
        ReqQuarterlyPaymentEntity req=new ReqQuarterlyPaymentEntity();
        req.NianValue=year;
        return addTask(req,callBack);
    }
    /**
     * 获取月度合同
     * @param year
     * @param callBack
     * @return
     */
    public int getMonthlyContract(String year,ICallBack<Object> callBack){
        ReqMonthlyContractEntity req=new ReqMonthlyContractEntity();
        req.NianValue=year;
        return addTask(req,callBack);
    }
    /**
     * 获取月度收款
     * @param year
     * @param callBack
     * @return
     */
    public int getMonthlyPayment(String year,ICallBack<Object> callBack){
        ReqMonthlyPaymentEntity req=new ReqMonthlyPaymentEntity();
        req.NianValue=year;
        return addTask(req,callBack);
    }

    /**
     * 办理意见
     * @param SLID
     * @param callBack
     * @return
     */
    public int reqBanLiYiJian(String SLID,ICallBack<Object> callBack){
        ReqBanLiYiJianEntity req=new ReqBanLiYiJianEntity();
        req.SLID=SLID;
        return addTask(req,callBack);
    }

    /**
     * 保存数据
     * @param strJson
     * @param strPKField
     * @param DBName
     * @param callBack
     * @return
     */
    public int saveData(String strJson,String strPKField,String DBName,ICallBack<Object> callBack){
        ReqSaveDateEntity req=new ReqSaveDateEntity();
        req.strJson=strJson;
        req.strPKField=strPKField;
        req.DBName=DBName;
        return addTask(req,callBack);
    }

    /**
     * 提交流程
     * @param entity
     * @param BLUserID
     * @param opinion
     * @param callBack
     * @return
     */
    public int submitFlowBusiness(PWorkItemEntity entity,String BLUserID,String opinion,ICallBack<Object> callBack){
        ReqSubmitFlowBusinessEntity req=new ReqSubmitFlowBusinessEntity();
        req.YHID=LoginController.getInstance().getYHID();
        req.BLUserID=BLUserID;
        req.opinion=opinion;

        String BLUrl=entity.BLUrl;
        String[] strs=BLUrl.split("\\?");
        req.UrlParam=strs[1].replaceAll("\\&", "\\$");
        String[] strs1=BLUrl.split(";");
        String[] strs2=strs1[1].split("=");
        String[] strs3=strs2[1].split("@");
        req.FlowJDID=strs3[0];
        req.FlowSLID=entity.SLID;
        req.YWID=entity.YWID;
        req.FlowVerID=entity.LCID;
        String[] arr=BLUrl.split(";");
        String[] arr1=arr[3].split("=");
        String[] arr2=arr1[1].split("@");
        req.FLowBLID=arr2[0];
        return addTask(req,callBack);
    }

    /**
     * 回退流程
     * @param entity
     * @param BLUserID
     * @param opinion
     * @param callBack
     * @return
     */
    public int returnFlowBusiness(PWorkItemEntity entity,String BLUserID,String opinion,ICallBack<Object> callBack){
        ReqReturnFlowBusinessEntity req=new ReqReturnFlowBusinessEntity();
        req.YHID=LoginController.getInstance().getYHID();
        req.BLUserID=BLUserID;
        req.opinion=opinion;

        String BLUrl=entity.BLUrl;
        String[] strs=BLUrl.split("\\?");
        req.UrlParam=strs[1].replaceAll("\\&", "\\$");
        String[] strs1=BLUrl.split(";");
        String[] strs2=strs1[1].split("=");
        String[] strs3=strs2[1].split("@");
        req.FlowJDID=strs3[0];
        req.FlowSLID=entity.SLID;
        req.YWID=entity.YWID;
        req.FlowVerID=entity.LCID;
        String[] arr=BLUrl.split(";");
        String[] arr1=arr[3].split("=");
        String[] arr2=arr1[1].split("@");
        req.FLowBLID=arr2[0];
        return addTask(req,callBack);
    }
}