package com.abl.RWD.http.req;

import com.abl.RWD.http.base.ReqBaseEntity;
import com.abl.common.http.NetCommon;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by yas on 2017/11/16.
 * 月度收款
 */

public class ReqMonthlyPaymentEntity extends ReqBaseEntity{
    public String NianValue;
    @Override
    public String getReqUrl() {
        return NetCommon.URL_YUEDU_SHOUKUAN_INFO;
    }

    @Override
    public Map<String, Object> getReqData() {
        Map<String,Object> mMap=new HashMap();
        mMap.put("NianValue", NianValue);
        return mMap;
    }
}
