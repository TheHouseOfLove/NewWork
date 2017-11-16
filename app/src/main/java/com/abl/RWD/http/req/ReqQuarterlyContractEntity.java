package com.abl.RWD.http.req;

import com.abl.RWD.http.base.ReqBaseEntity;
import com.abl.common.http.NetCommon;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by yas on 2017/11/16.
 * 季度合同
 */

public class ReqQuarterlyContractEntity extends ReqBaseEntity{
    public String NianValue;
    @Override
    public String getReqUrl() {
        return NetCommon.URL_JIDU_HETONG;
    }

    @Override
    public Map<String, Object> getReqData() {
        Map<String,Object> mMap=new HashMap();
        mMap.put("NianValue", NianValue);
        return mMap;
    }
}
