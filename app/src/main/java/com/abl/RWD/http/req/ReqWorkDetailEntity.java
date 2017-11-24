package com.abl.RWD.http.req;

import com.abl.RWD.http.base.ReqBaseEntity;
import com.abl.common.http.NetCommon;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by yas on 2017/11/24.
 */

public class ReqWorkDetailEntity extends ReqBaseEntity{
    public String YWID;
    public String SLID;
    public String LCID;
    public String JDID;
    public String UrlParam;
    public String YHID;
    @Override
    public String getReqUrl() {
        return NetCommon.URL_WORK_DETAIL;
    }

    @Override
    public Map<String, Object> getReqData() {
        Map<String,Object> mMap=new HashMap<>();
        mMap.put("YWID", YWID);
        mMap.put("SLID", SLID);
        mMap.put("LCID", LCID);
        mMap.put("JDID", JDID);
        mMap.put("UrlParam", UrlParam);
        mMap.put("YHID", YHID);
        return mMap;
    }
}
