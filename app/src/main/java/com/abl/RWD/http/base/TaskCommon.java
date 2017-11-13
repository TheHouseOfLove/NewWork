package com.abl.RWD.http.base;

import com.abl.common.http.NetCommon;
import com.abl.RWD.common.Global;
import com.abl.RWD.http.listener.ICallBack;
import com.abl.RWD.http.listener.ITaskListener;
import com.abl.RWD.util.MyLog;

import org.json.JSONObject;

import java.lang.ref.WeakReference;

/**
 * 基础Task对应的业务全部提出来
 * Created by diaosi on 2015/12/28.
 */
public class TaskCommon<REQ> extends BaseTask implements ITaskListener {
    private final String TAG = "TaskCommon";

    private WeakReference<ICallBack<Object>> callBack;

    public TaskCommon(ReqBaseEntity req, ICallBack<Object> listener) {
        super(req);
        // TODO Auto-generated constructor stub
        this.callBack = new WeakReference<ICallBack<Object>>(listener);
    }


    @Override
    public void getResponse(JSONObject jsonObj, final boolean isSucc, final int errorCode, final int seqNo, final int src) {
        super.getResponse(jsonObj, isSucc, errorCode, seqNo, src);
        final ReqBaseEntity req = (ReqBaseEntity) getReq();
        String url = req.getReqUrl();
        Class<?> clazz = NetCommon.mMap.get(url);
        if(clazz != null){
            MyLog.debug(TAG, "[getResponse]  clazz:" + clazz.getSimpleName() + ", isSucc:" + isSucc);
            try {
                Object obj = clazz.newInstance();
                if(obj instanceof RspBaseEntity){
                    final RspBaseEntity rsp = (RspBaseEntity) obj;
                    rsp.seqNo = seqNo;

                    if(isSucc){
                        //解析操作
                        rsp.preParseV2(jsonObj, seqNo);
                    }

                    //data notify to ui
                    Global.post2UI(new Runnable() {
                        @Override
                        public void run() {
                            if (callBack != null) {
                                ICallBack call = callBack.get();
                                if (call != null) {
                                    boolean succ = rsp.result;
//                                    succ = (rsp.code == Common.ERROR_CODE_SUCC || rsp.code == Common.ERROR_CODE_SUCCV2);
                                    call.getResponse(rsp, succ, errorCode, seqNo, src);
                                }
                            }
                        }
                    });

                }else{
                    MyLog.error(TAG, "Rsp need extends RspBaseEntity...");
                    //data notify to ui
                    Global.post2UI(new Runnable() {
                        @Override
                        public void run() {
                            if (callBack != null) {
                                ICallBack call = callBack.get();
                                if (call != null) {
                                    call.getResponse(null, false, errorCode, seqNo, src);
                                    MyLog.debug(TAG, "[getResponse] Rsp need extends RspBaseEntity...");
                                }
                            }
                        }
                    });
                }
            } catch (InstantiationException e) {
                MyLog.error(TAG,e);
                //data notify to ui
                Global.post2UI(new Runnable() {
                    @Override
                    public void run() {
                        if (callBack != null) {
                            ICallBack call = callBack.get();
                            if (call != null) {
                                call.getResponse(null, false, errorCode, seqNo, src);
                                MyLog.debug(TAG, "[getResponse] InstantiationException");
                            }
                        }
                    }
                });
            } catch (IllegalAccessException e) {
                MyLog.error(TAG, e);
                //data notify to ui
                Global.post2UI(new Runnable() {
                    @Override
                    public void run() {
                        if (callBack != null) {
                            ICallBack call = callBack.get();
                            if (call != null) {
                                call.getResponse(null, false, errorCode, seqNo, src);
                                MyLog.debug(TAG, "[getResponse] IllegalAccessException");
                            }
                        }
                    }
                });
            }
        }
    }
}
