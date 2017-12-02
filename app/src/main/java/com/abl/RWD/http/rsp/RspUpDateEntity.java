package com.abl.RWD.http.rsp;


import com.abl.RWD.entity.PUpdateListEntity;
import com.abl.RWD.http.base.RspBaseEntity;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONObject;

public class RspUpDateEntity extends RspBaseEntity {
	public PUpdateListEntity mEntity;
	@Override
	public void parseData(JSONObject jsonObj, JSONArray jsonArray, boolean isArray) {
		Gson gson=new Gson();
		mEntity=gson.fromJson(jsonObj.toString(),PUpdateListEntity.class);
	}
}
