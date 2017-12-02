package com.abl.RWD.http.req;

import com.abl.RWD.http.base.ReqBaseEntity;
import com.abl.common.http.NetCommon;

import java.util.Map;

public class ReqUpDateEntity extends ReqBaseEntity {

	@Override
	public String getReqUrl() {
		// TODO Auto-generated method stub
		return NetCommon.URL_UPDATE;
	}

	@Override
	public Map<String, Object> getReqData() {
		// TODO Auto-generated method stub
		return null;
	}

}
