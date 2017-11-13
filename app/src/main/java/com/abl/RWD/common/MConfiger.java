package com.abl.RWD.common;


/***
 * 配置类
 */
public class MConfiger {

	//debug开关
	public static final boolean isDebug = true;
	//crash buggly上报统计开关
	public static final boolean isBugglyEnable = true;

	//是否上报通讯录,开关
	public static final boolean isUpLoadContact = false;
	public static final boolean isJSONDebug = false;
	//img debug开关
	public static final boolean isImgDebug = false;
	//jpush debug开关
	public static final boolean isJPushDebug = false;
	//贷款相关debug开关
	public static final boolean isLoanDebug = false;
	//下拉刷新相关debug开关
	public static final boolean isNLPullDebug = false;
	//统计相关debug开关
	public static final boolean isReportDebug = false;
	
	//闪屏时间 3秒
	public static final int SPLASH_INTERVAL = 1000 * 2;
	//下拉列表个数
	public static final int PAGE_SIZE = 15;
	//cookie过期时间 30天 1000 * 60 * 60 * 24 * 30 2592000000l
	public static final long COOKIE_EXPIRE_TIME = 2592000000l;
	//自动加载更多item个数
	public static final int AUTO_LOAD_ITEM_CNT = 5;
	//协议超时时间 修改为36秒
	public static final int P_TIMEOUT_INTERVAL = 1000 * 36;

    //首页滚动频率
    public static final int ROLL_INTERVAL = 3500;
    //首页的viewpager的滚动速度
    public static final int VIEWPAGER_SPEED = 700;
    //渠道号码
    public static String CHANNEL_ID = "";
    public static final String BUGLY_ID = "900009636";
}

