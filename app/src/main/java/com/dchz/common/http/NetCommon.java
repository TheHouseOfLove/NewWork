package com.dchz.common.http;

import android.support.v4.util.ArrayMap;

import com.dchz.newwork.http.rsp.RspFinshWorkListEntity;
import com.dchz.newwork.http.rsp.RspPendingWorkListEntity;

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
    public static final String URL_FINSH_WORK_LIST="/yiBanList";
    static {
        //待办列表
        mMap.put(URL_PENDING_WORK_LIST, RspPendingWorkListEntity.class);
        //已办列表
        mMap.put(URL_FINSH_WORK_LIST, RspFinshWorkListEntity.class);
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
