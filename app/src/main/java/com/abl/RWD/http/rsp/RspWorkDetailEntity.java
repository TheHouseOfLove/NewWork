package com.abl.RWD.http.rsp;

import com.abl.RWD.entity.PWorkDetailEntity;
import com.abl.RWD.http.base.RspBaseEntity;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Created by yas on 2017/11/24.
 */

public class RspWorkDetailEntity extends RspBaseEntity{
    public PWorkDetailEntity mEntity;
    @Override
    public void parseData(JSONObject jsonObj, JSONArray jsonArray, boolean isArray) {
        Gson gson=new Gson();
        mEntity=gson.fromJson(jsonObj.toString(),PWorkDetailEntity.class);
    }
}
