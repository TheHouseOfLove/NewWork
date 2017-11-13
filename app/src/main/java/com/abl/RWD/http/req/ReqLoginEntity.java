package com.abl.RWD.http.req;

import com.abl.common.http.NetCommon;
import com.abl.RWD.http.base.ReqBaseEntity;
import com.abl.RWD.util.MyLog;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2017/11/11.
 */

    public class ReqLoginEntity extends ReqBaseEntity {
        public String UserName;
        public String UserPwd;
//        public String strClientID="";
//        public String strDeviceToken;
        @Override
        public String getReqUrl() {
            // TODO Auto-generated method stub
            return NetCommon.URL_LOGIN;
        }

        @Override
        public Map<String, Object> getReqData() {
            // TODO Auto-generated method stub
            MyLog.debug("ReqLoginEntity","[getReqData]=====================");
            Map<String ,Object> mMap=new HashMap<String, Object>();
            mMap.put("UserName", UserName);
            mMap.put("UserPwd", UserPwd);
            mMap.put("strClientID", "");
            mMap.put("strDeviceToken", "");
            return mMap;
        }
}
