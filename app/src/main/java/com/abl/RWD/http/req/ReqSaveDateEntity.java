package com.abl.RWD.http.req;

import com.abl.RWD.http.base.ReqBaseEntity;
import com.abl.common.http.NetCommon;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2017/11/25.
 * 保存数据
 */

public class ReqSaveDateEntity extends ReqBaseEntity{
    public String strJson;          //修改数据
    public String strPKField;       //主键（列表中FormPKField的值）
    public String strDelPKValue="";
    public String DBName;           //（列表中FormDB的值）

    @Override
    public String getReqUrl() {
        return NetCommon.URL_SAVE_DATA;
    }

    @Override
    public Map<String, Object> getReqData() {
        Map<String ,Object> mMap=new HashMap<>();
//        try {
//            strJson= URLEncoder.encode(strJson, "GBK");
//        } catch (UnsupportedEncodingException e) {
//            e.printStackTrace();
//        }
        mMap.put("strJson",strJson);
        mMap.put("strPKField",strPKField);
        mMap.put("strDelPKValue",strDelPKValue);
        mMap.put("DBName",DBName);
        return mMap;
    }
}
