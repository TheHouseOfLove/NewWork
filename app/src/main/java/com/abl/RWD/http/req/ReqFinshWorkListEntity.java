package com.abl.RWD.http.req;

import com.abl.common.http.NetCommon;
import com.abl.RWD.http.base.ReqBaseEntity;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2017/11/11.
 */

public class ReqFinshWorkListEntity extends ReqBaseEntity {
    public String YHID;
    public int pageSize;
    public int pageIndex;
    public String strWhere;
    @Override
    public String getReqUrl() {
        return NetCommon.URL_FINSH_WORK_LIST;
    }

    @Override
    public Map<String, Object> getReqData() {
        Map<String,Object> mMap=new HashMap<>();
        mMap.put("YHID",YHID);
        mMap.put("pageSize",pageSize);
        mMap.put("pageIndex",pageIndex);
        mMap.put("strWhere",strWhere);
        return mMap;
    }
}
