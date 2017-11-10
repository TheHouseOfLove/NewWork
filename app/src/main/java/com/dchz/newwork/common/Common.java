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


	//表单类型
	public static final int TYPE_DAIBAN=1;//待办
	public static final int TYPE_YIBAN=2;//已办
	/**费用报销单**/
	public static final String FEIYONG="ccc4b31c-b09c-4b8d-a422-b5372e34a492";
	/**请假申请**/
	public static final String QINGJIA="0aad3f42-3e3a-4c3a-8377-9ea66809caee";
	/**借款单**/
	public static final String JIEKUAN="515ba74c-4ed2-4384-aac5-31556a1d914d";
	/**应收合同评审**/
	public static final String YINGSHOUHT="12ebdb58-aaf7-4995-8a89-76a854e66740";
	/**应付合同评审**/
	public static final String YINGFUHT="c309098f-26d0-40d1-a15f-71d85e6d421a";
	/**发票开具申请**/
	public static final String FAPIAO="8022a195-a8b3-4510-824c-7227f519bbd0";
	/**投标或履约保证金**/
	public static final String TOUBIAO="d2a4e705-b5ab-428f-8447-c0a6c804c1a8";
	/**分包方评审**/
	public static final String FENBAO="c3aeaae0-b582-4f89-ac77-023f38a651a2";
	/**法人授权**/
	public static final String FAREN="1d7f32e6-b642-4a03-8e9e-abd5c59b9486";
	/**盖章申请**/
	public static final String GAIZHANG="b3bd4b58-9e8b-434f-977b-84f69d72bbd4";
	/**项目下达**/
	public static final String XIANGMUXIADA="fbedc4e3-d528-4982-9283-19ee368df6c8";
	/**工程拨款**/
	public static final String GONGCHENG="dd271305-d12b-4edf-8042-537877d6e33d";
	/**公章借出**/
	public static final String GONGZHANGJIECHU="2710d672-5b98-4271-bb14-caa5b49ec53f";
	/**固定资产购置申请**/
	public static final String GUDINGZICHAN="23560e65-30ab-4970-a1e4-c6f0c74f1ed8";
	/**低值易耗品**/
	public static final String DIZHIYIHAOPIN="312a46e4-f4c2-4a2e-8c4b-0390f129b589";
	/**投标费用**/
	public static final String TOUBIAOFEIYONG="b9c5fe4f-dc56-4738-a584-5696a0df1e04";
	/**承包费**/
	public static final String CHENGBAOFEI="9394c518-15dc-462d-b38e-0d31ae4c7990";


}
