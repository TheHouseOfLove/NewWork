package com.abl.common.http;

import android.support.v4.util.ArrayMap;

import com.abl.RWD.http.rsp.RspBanLiYiJianEntity;
import com.abl.RWD.http.rsp.RspCommonEntity;
import com.abl.RWD.http.rsp.RspContractListEntity;
import com.abl.RWD.http.rsp.RspLoginEntity;
import com.abl.RWD.http.rsp.RspMonthlyContractEntity;
import com.abl.RWD.http.rsp.RspMonthlyPaymentEntity;
import com.abl.RWD.http.rsp.RspProjectListEntity;
import com.abl.RWD.http.rsp.RspQuarterlyContractEntity;
import com.abl.RWD.http.rsp.RspQuarterlyPaymentEntity;
import com.abl.RWD.http.rsp.RspReturnFlowBusinessEntity;
import com.abl.RWD.http.rsp.RspSaveDataEntity;
import com.abl.RWD.http.rsp.RspSubmitFlowBusinessEntity;
import com.abl.RWD.http.rsp.RspWorkDetailEntity;
import com.abl.RWD.http.rsp.RspWorkListEntity;

import java.util.Map;

/***
 * 网络相关常量
 * @date 2015/06/27
 */
public class NetCommon {
    /**域名信息*/
    public static final String KEY_DOMAIN = ".menkoudai.cn";
    /**路径名*/
    public static final String KEY_PATH = "/";

    /** FromType, PC Java端：0， PC PHP: 1， Mobile PHP:11, Mobile IOS:12, Mobile Android:13, Mobile_M: 14 */
    public static final String KEY_FROM_TYPE = "13";
    /**java请求加密串*/
    public static final String JAVA_KEY_CODE="ptDQl4I2RhhgplV&S4A8LNSAS4A8L";

    public static final Map<String,Class<?>> mMap = new ArrayMap<>();

    /**登录注册接口*/
    public static final String URL_LOGIN="/MobileLogin";
    //待办列表
    public static final String URL_PENDING_WORK_LIST="/daiBanList";
    //已办列表
    public static final String URL_FINISH_WORK_LIST="/yiBanList";
    /**项目查询**/
    public static final String URL_PROJECT_LIST_SEARCH="/GetXiangMuInfo";
    /**合同查询**/
    public static final String URL_CONTRACT_LIST_SEARCH="/GetHeTongInfo";
    /**季度合同**/
    public static final String URL_JIDU_HETONG="/JiDuHeTongInfo";
    /**季度收款**/
    public static final String URK_JIDU_SHOUKUAN="/JiDuShouKuanInfo";
    /**月度合同信息**/
    public static final String URL_YUEDU_HETONG_INFO="/YueDuHeTongInfo";
    /**月度收款信息**/
    public static final String URL_YUEDU_SHOUKUAN_INFO="/YueDuShouKuanInfo";
    /**事务详情**/
    public static final String URL_WORK_DETAIL="/GetYWInfo";
    /**办理意见**/
    public static final String URL_BANLIYIJIAN="/banLiYiJian";
    /**保存数据**/
    public static final String URL_SAVE_DATA="/SaveData";
    /**流程提交**/
    public static final String URL_SUBMIT_BUSINESS="/SaveFlowBusiness";
    /**退回**/
    public static final String URL_RETURN_BUSINESS="/SaveReturnFlowBusiness";
    static {
        //登录
        mMap.put(URL_LOGIN, RspLoginEntity.class);
        //待办列表
        mMap.put(URL_PENDING_WORK_LIST, RspWorkListEntity.class);
        //已办列表
        mMap.put(URL_FINISH_WORK_LIST, RspWorkListEntity.class);
        //项目查询
        mMap.put(URL_PROJECT_LIST_SEARCH, RspProjectListEntity.class);
        //合同查询
        mMap.put(URL_CONTRACT_LIST_SEARCH, RspContractListEntity.class);
        //季度合同
        mMap.put(URL_JIDU_HETONG, RspQuarterlyContractEntity.class);
        //季度收款
        mMap.put(URK_JIDU_SHOUKUAN, RspQuarterlyPaymentEntity.class);
        //月度合同
        mMap.put(URL_YUEDU_HETONG_INFO, RspMonthlyContractEntity.class);
        //月度收款
        mMap.put(URL_YUEDU_SHOUKUAN_INFO, RspMonthlyPaymentEntity.class);
        //事务详情
        mMap.put(URL_WORK_DETAIL, RspWorkDetailEntity.class);
        //办理意见
        mMap.put(URL_BANLIYIJIAN, RspBanLiYiJianEntity.class);
        //保存数据
        mMap.put(URL_SAVE_DATA, RspSaveDataEntity.class);
        //提交流程
        mMap.put(URL_SUBMIT_BUSINESS, RspSubmitFlowBusinessEntity.class);
        //退回流程
        mMap.put(URL_RETURN_BUSINESS, RspReturnFlowBusinessEntity.class);

    }


	/** 协议异常**/
	public static final int ERROR_HTTP_CLENT_PROTOCOL_EXCEPTION = 105;
	/** 连接超时 */
    public static final int ERROR_HTTP_CONNECT_TIMEOUT = 102;
    /** 分片接收超时 */
    public static final int ERROR_HTTP_SO_TIMEOUT = 103;
    /** IO异常 */
    public static final int ERROR_HTTP_IO_EXCEPTION = 104;
    /** 其他http连接异常 */
    public static final int ERROR_HTTP_EXCEPTION = 106;
    /** HTTP回包为空异常 */
    public static final int ERROR_HTTP_RESPONSE_NULL = 111;
    /** HTTP解包异常 */
    public static final int ERROR_HTTP_RESPONSE_EXCEPTION = 113;
    /** 服务器发生异常 */
    public static final int ERROR_HTTP_RESPONSE_HTTP_CODE = 112;
    /**服务器发生500异常**/
    public static final int ERROR_HTTP_RESPONSE_HTTP_CODE_500 = 113;

    /**正式域名**/
    public static final int NET_TYPE_INDEX_OFFICAL = 0;
    /**开发域名**/
    public static final int NET_TYPE_INDEX_DEV = 1;
    /**测试域名**/
    public static final int NET_TYPE_INDEX_TEST = 2;

    //接口类型;
    public static final int NET_INTERFACE_TYPE_KEZHAN = 0;
}
