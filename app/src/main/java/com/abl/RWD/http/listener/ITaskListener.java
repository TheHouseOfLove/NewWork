package com.abl.RWD.http.listener;

import org.json.JSONObject;

public interface ITaskListener {
	public static final int TYPE_SRC_NET = 100;
	public static final int TYPE_SRC_CACHE = 101;
	public static final int TYPE_SRC_MEM = 102;
	
	public void recyle();
	public void getResponse(JSONObject jsonObj, boolean isSucc, int errorCode, int seqNo, int src);
	public Object getReq();
}
