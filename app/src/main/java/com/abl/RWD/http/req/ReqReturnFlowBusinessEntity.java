package com.abl.RWD.http.req;


import com.abl.RWD.http.base.ReqBaseEntity;
import com.abl.common.http.NetCommon;

import java.util.HashMap;
import java.util.Map;

public class ReqReturnFlowBusinessEntity extends ReqBaseEntity {
	public String FlowVerID;
	public String FlowSLID;
	public String YWID;
	public String FlowJDID;
	public String FLowBLID;
	public String BLUserID;
	public String CYUserID="";
	public String opinion;
	public String UrlParam;
	public String YHID;
	@Override
	public String getReqUrl() {
		// TODO Auto-generated method stub
		return NetCommon.URL_RETURN_BUSINESS;
	}

	@Override
	public Map<String, Object> getReqData() {
		// TODO Auto-generated method stub
		Map<String ,Object> mMap=new HashMap<>();
		mMap.put("FlowVerID", FlowVerID);
		mMap.put("FlowSLID", FlowSLID);
		mMap.put("YWID", YWID);
		mMap.put("FlowJDID", FlowJDID);
		mMap.put("FLowBLID", FLowBLID);
		mMap.put("BLUserID", BLUserID);
		mMap.put("CYUserID", CYUserID);
		mMap.put("opinion", opinion);
		mMap.put("UrlParam", UrlParam);
		mMap.put("YHID", YHID);
		return mMap;
	}

}
