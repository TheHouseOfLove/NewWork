package com.dchz.newwork.http.base;


import java.util.Map;

/***
 * 请求包的基类
 * @date 2015/06/27
 */
public abstract class ReqBaseEntity {
	//请求链接为全链接
	public boolean useAllUrl = false;
	//是否需要cache系统
	public boolean needCache = false;
	//是否需要内存缓存
	public boolean needMemCache = false;


	/* 请求方式指定 */
	public static final int TYPE_JAVA_GET=0;
	public static final int TYPE_JAVA_POST = 1;

	public int mVisitType = TYPE_JAVA_GET;

	//新的java接口中，json串中的"data"字段可以是单一的一个域；也可以为一个Object; 还可以为一个Object Array;
	//默认时，“data”字段为单一的域；
//	public int dataJsonType = TYPE_FIELD;

//	public static final int TYPE_FIELD = 0;
//	public static final int TYPE_JSON_OBJECT = 1;
//	public static final int TYPE_JSON_OBJECTARRAY = 2;
	/**分页请求**/
	public long begin;
	public int pageSize;
	public int pageReqType = REQ_TYPE_DOWN;
	
	public static final int REQ_TYPE_UP = 1;	//向上分页请求
	public static final int REQ_TYPE_DOWN = 2;	//向下分页请求
	
	/**请求序列号**/
	public int seqNo = -1;
	
	/**获取请求地址**/
	public abstract String getReqUrl();

	/***
	 * 数据库Cache generaralID使用
	 * @return
	 */
	protected String getID(){
		return "";
	}

//	protected String getJsonString(){
//		return "";
//	}

	public abstract Map<String, Object> getReqData();
}
