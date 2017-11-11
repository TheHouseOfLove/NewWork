package com.dchz.newwork.http.rsp;


import com.dchz.newwork.entity.PLoginEntity;
import com.dchz.newwork.http.base.RspBaseEntity;
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
