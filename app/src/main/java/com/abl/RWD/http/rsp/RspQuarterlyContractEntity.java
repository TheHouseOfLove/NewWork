package com.abl.RWD.http.rsp;

import com.abl.RWD.entity.PJiDuHeTongListEntity;
import com.abl.RWD.http.base.RspBaseEntity;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONObject;


/**
 * Created by yas on 2017/11/16.
 * 季度合同
 */

public class RspQuarterlyContractEntity extends RspBaseEntity{
    public PJiDuHeTongListEntity mEntity;
    @Override
    public void parseData(JSONObject jsonObj, JSONArray jsonArray, boolean isArray) {
        Gson gson=new Gson();
        mEntity=gson.fromJson(jsonObj.toString(),PJiDuHeTongListEntity.class);
    }
}
