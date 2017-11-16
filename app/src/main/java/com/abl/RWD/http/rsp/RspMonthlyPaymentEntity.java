package com.abl.RWD.http.rsp;

import com.abl.RWD.entity.PYueDuSHouKuanInfoEntity;
import com.abl.RWD.http.base.RspBaseEntity;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Created by yas on 2017/11/16.
 * 月度收款
 */

public class RspMonthlyPaymentEntity extends RspBaseEntity{
    public PYueDuSHouKuanInfoEntity mEntity;
    @Override
    public void parseData(JSONObject jsonObj, JSONArray jsonArray, boolean isArray) {
        Gson gson=new Gson();
        mEntity=gson.fromJson(jsonObj.toString(),PYueDuSHouKuanInfoEntity.class);
    }
}
