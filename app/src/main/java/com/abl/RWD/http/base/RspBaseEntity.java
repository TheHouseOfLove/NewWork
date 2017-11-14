package com.abl.RWD.http.base;

import com.abl.RWD.util.MyLog;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;

/***
 * 回复包的基类
 * @data 2015/06/27
 */
public abstract class RspBaseEntity implements Serializable {
	protected final String TAG = getClass().getSimpleName();
	
	/**返回错误码**/
//	public int code;
	/**返回错误消息提示**/
	public String msg;
	/**返回的数据域信息**/
	public String data;
	/**是否返回正确**/
	public boolean isSucc;
	//请回序列号,客户端自定义使用
	public int seqNo;
	/*时间*/
	public long time;
	/**是否登录**/
	public boolean isLogin;
	/**数据的来源 net cache mem**/
	public int src;

	public RspBaseEntity() {
		// TODO Auto-generated constructor stub
	}
	/***
	 *调用该方法
	 * @param jsonObj
	 * @param seqNo
	 * @return
	 */
	public boolean preParse(JSONObject jsonObj, int seqNo){
		this.seqNo = seqNo;
		if(jsonObj != null){
			this.isSucc = true;
			MyLog.debug(TAG, "[RspBaseEntity]  jsonObj:"+jsonObj.toString());
			parseData(jsonObj,null,false);
		}
		return this.isSucc;
	}
	/***
	 * 框架改造V2调用该方法
	 * @param jsonObj
	 * @param seqNo
	 * @return
	 */
	public boolean preParseV2(JSONObject jsonObj, int seqNo){
		this.seqNo = seqNo;
		try {
			isSucc=jsonObj.getBoolean("result");
			if(isSucc){
				if (jsonObj.has("data")){
					Object obj=jsonObj.get("data");
					if (obj instanceof JSONObject){
						JSONObject jsonObject= (JSONObject) obj;
						parseData(jsonObject, null, false);
					}else if (obj instanceof JSONArray){
						JSONArray jsonArray = (JSONArray) obj;
						parseData(null, jsonArray, true);
					}else{
						this.data = jsonObj.optString("data");
						parseData(null, null, false);
					}
				}
				if(jsonObj.has("msg")){
					this.msg = jsonObj.getString("msg");
				}

			}else{
				if(jsonObj.has("msg")){
					this.msg = jsonObj.getString("msg");
				}
			}
		} catch (JSONException ee) {
			MyLog.error(TAG, ee);
		}
		//返回是否成功
		return this.isSucc;
	}

	public abstract void parseData(JSONObject jsonObj, JSONArray jsonArray, boolean isArray);

}
