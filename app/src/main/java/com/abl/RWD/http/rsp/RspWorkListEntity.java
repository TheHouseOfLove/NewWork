package com.abl.RWD.http.rsp;

import com.abl.RWD.entity.PWorkListEntity;
import com.abl.RWD.http.base.RspBaseEntity;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Created by Administrator on 2017/11/11.
 */

public class RspWorkListEntity extends RspBaseEntity {
    public PWorkListEntity mEntity;
    @Override
    public void parseData(JSONObject jsonObj, JSONArray jsonArray, boolean isArray) {
        Gson gson=new Gson();
        mEntity=gson.fromJson(jsonObj.toString(),PWorkListEntity.class);
    }
}
