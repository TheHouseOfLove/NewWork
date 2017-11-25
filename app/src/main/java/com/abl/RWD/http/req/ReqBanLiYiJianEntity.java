package com.abl.RWD.http.req;


import com.abl.RWD.http.base.ReqBaseEntity;
import com.abl.common.http.NetCommon;

import java.util.HashMap;
import java.util.Map;

/**
 * 获取办理意见信息请求类
 * @author wys
 *
 */
public class ReqBanLiYiJianEntity extends ReqBaseEntity {
	public String SLID;//实例id
	@Override
	public String getReqUrl() {
		// TODO Auto-generated method stub
		return NetCommon.URL_BANLIYIJIAN;
	}

	@Override
	public Map<String, Object> getReqData() {
		// TODO Auto-generated method stub
		Map<String ,Object> mMap=new HashMap<>();
		mMap.put("SLID", SLID);
		return mMap;
	}

}
