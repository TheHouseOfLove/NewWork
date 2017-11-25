package com.abl.RWD.http.rsp;


import com.abl.RWD.entity.PBanLiYiJianListEntity;
import com.abl.RWD.http.base.RspBaseEntity;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONObject;

public class RspBanLiYiJianEntity extends RspBaseEntity {
	public PBanLiYiJianListEntity mEntity;
	@Override
	public void parseData(JSONObject jsonObj, JSONArray jsonArray, boolean isArray) {
		Gson gson=new Gson();
		mEntity=gson.fromJson(jsonObj.toString(),PBanLiYiJianListEntity.class);
	}
}
