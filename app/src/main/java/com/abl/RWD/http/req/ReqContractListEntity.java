package com.abl.RWD.http.req;

import com.abl.RWD.http.base.ReqBaseEntity;
import com.abl.common.http.NetCommon;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by yas on 2017/11/13.
 */

public class ReqContractListEntity extends ReqBaseEntity{
    public int pageSize;
    public int pageIndex;
    public String strWhere;
    public String strYHID;
    @Override
    public String getReqUrl() {
        return NetCommon.URL_CONTRACT_LIST_SEARCH;
    }

    @Override
    public Map<String, Object> getReqData() {
        Map<String,Object> mMap=new HashMap<>();
        mMap.put("pageSize",pageSize+"");
        mMap.put("pageIndex",pageIndex+"");
        try {
            strWhere= URLEncoder.encode(strWhere, "GBK");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        mMap.put("strWhere",strWhere);
        mMap.put("strYHID",strYHID);
        return mMap;
    }
}
