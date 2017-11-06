package com.dchz.newwork.common;

/***
 * 基础常量
 * @date 2015/06/27
 */
public class Common {
	private static final String TAG = "Common";

	public static int seqNo = 200;
	public static final int MAX_SIZE_PIC_ABLUEM_SIZE = 9;
	/***
	 * 生成全局序列号
	 * @return
	 */
	public static final int getSeqNo(){
		return ++seqNo;
	}


	/**===========================================================================**/
	/**协议返回成功**/
	public static final int ERROR_CODE_SUCC = 1;
	public static final int ERROR_CODE_SUCCV2 = 100;

	/**协议返回错误,服务器内部异常也会出现这个问题**/
	public static final int ERROR_CODE_PROTOCAL = -100;
	/**网络错误**/
	public static final int ERROR_CODE_NET_ERROR = -101;
	/**发生Exception错误**/
	public static final int ERROR_CODE_EXCEPTION = -102;
	/**Exception UnSupportEncoding**/
	public static final int ERROR_CODE_UNSUPPORT_ENCODING = -103;
	/**服务器返回超时**/
	public static final int ERROR_CODE_TIME_OUT = -105;


	public static volatile boolean isPageStop = true;


	/**默认的每页数据条数**/
	public static final int PAGE_SIZE=20;

	/** 角色权限，1，无权限；2，有权限 */
	public static final int STATUS_NO_PERMISSION =1;
	public static final int STATUS_WITH_PERMISSION =2;


}
