package com.abl.RWD.http.rsp;


import com.abl.RWD.entity.PLoginEntity;
import com.abl.RWD.http.base.RspBaseEntity;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONObject;

public class RspLoginEntity extends RspBaseEntity {
	public PLoginEntity mEntity;

	@Override
	public void parseData(JSONObject jsonObj, JSONArray jsonArray, boolean isArray) {
		Gson gson=new Gson();
		mEntity=gson.fromJson(jsonObj.toString(),PLoginEntity.class);
	}
}
