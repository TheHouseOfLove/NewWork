package com.abl.RWD.http.rsp;

import com.abl.RWD.entity.PSaveDataEntity;
import com.abl.RWD.http.base.RspBaseEntity;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Created by Administrator on 2017/11/26.
 */

public class RspSaveDataEntity extends RspBaseEntity{
    public PSaveDataEntity mEntity;
    @Override
    public void parseData(JSONObject jsonObj, JSONArray jsonArray, boolean isArray) {
        Gson gson=new Gson();
        mEntity=gson.fromJson(jsonObj.toString(),PSaveDataEntity.class);
    }
}
