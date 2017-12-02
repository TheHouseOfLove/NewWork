package com.abl.RWD.http.base;

import com.abl.common.http.HttpBaseTask;
import com.abl.common.http.HttpUtilsOkHttp;
import com.abl.RWD.common.Common;
import com.abl.RWD.http.listener.ITaskListener;
import com.abl.RWD.util.MyLog;

import org.json.JSONObject;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static com.abl.RWD.common.Common.seqNo;

/**
 * 框架改造版本2使用（使用okHttp）
 * Created by wys on 2017/4/21.
 */
public class BaseTask<T> extends HttpBaseTask implements ITaskListener {
    private final String TAG = "BaseTask";

    public BaseTask(Object o) {
        super(o);
    }

    @Override
    public void doTask() {
        MyLog.debug(TAG,"[doTask]  ==========================");
        taskPre();
        Object o = getReq();
        if (o instanceof ReqBaseEntity) {
            ReqBaseEntity reqBaseEntity = (ReqBaseEntity) o;
            String url = reqBaseEntity.getReqUrl();
            MyLog.debug(TAG, "[doTask] url:"+url);
            //网络请求
            try {
                byte[] buffer = null;
                if (reqBaseEntity.mVisitType == ReqBaseEntity.TYPE_JAVA_GET) {
                    /* Get方式访问 */
                    String params = getParams(reqBaseEntity.getReqData());
                    buffer = HttpUtilsOkHttp.getTypeByte(url, params);

                } else if (reqBaseEntity.mVisitType == ReqBaseEntity.TYPE_JAVA_POST) {
                    String params = getParamsV2(reqBaseEntity.getReqData());
                    buffer = HttpUtilsOkHttp.postTypeByte(url, params);
                }

                if (buffer != null && buffer.length > 2) {
                    String str = new String(buffer, "utf-8");
                    JSONObject jsonObj = new JSONObject(getJSOAN(str));
                    if (MyLog.isDebugable()) {
                        String tempStr = jsonObj.toString();
                        if (MyLog.isDebugable()) {
                            MyLog.debug(TAG, "[doTask]" + " rsp str:" + tempStr);
                        }
                    }
                    getResponse(jsonObj, true, Common.ERROR_CODE_SUCC, seqNo, ITaskListener.TYPE_SRC_NET);

                } else {
                    if (buffer != null && buffer.length == 1) {
                        int code = buffer[0];
                        getResponse(null, false, code, seqNo, ITaskListener.TYPE_SRC_NET);
                        MyLog.debug(TAG, "[doTask]" + " code:" + code);
                    } else {
                        MyLog.debug(TAG, "Failed! Net Src response!");
                        getResponse(null, false, Common.ERROR_CODE_PROTOCAL, seqNo, ITaskListener.TYPE_SRC_NET);
                    }
                }
            } catch (UnsupportedEncodingException ee) {
                MyLog.error(TAG, "", ee);
                getResponse(null, false, Common.ERROR_CODE_EXCEPTION, seqNo, ITaskListener.TYPE_SRC_NET);
            } catch (Exception ee) {
                // TODO Auto-generated catch block
                MyLog.debug(TAG, "Exception:" + ee.getStackTrace().toString());
                ee.printStackTrace();
                MyLog.error(TAG, "", ee);
                getResponse(null, false, Common.ERROR_CODE_EXCEPTION, seqNo, ITaskListener.TYPE_SRC_NET);
            }
        }
    }

    public static String getJSOAN(String str){
        String[] strs1=str.split(">");
        String[] strs=strs1[2].split("<");
        return strs[0];
    }

    /**
     * 拼凑参数
     * @param mReqMap
     * @return
     */
    private static final String getParams(Map<String, Object> mReqMap) {
        StringBuilder builder = new StringBuilder();
        if (mReqMap != null) {
            builder.append("?");
            for (Map.Entry<String, Object> entry : mReqMap.entrySet()) {
                String key = entry.getKey();
                Object val = entry.getValue();
                builder.append(key + "=" + val + "&");
            }
        }
        if (builder.length() > 0) {
            builder.deleteCharAt(builder.length() - 1);
        }

        return builder.toString();
    }
    private static final String getParamsV2(Map<String, Object> mReqMap) {
        StringBuilder builder = new StringBuilder();
        if (mReqMap != null) {
            for (Map.Entry<String, Object> entry : mReqMap.entrySet()) {
                String key = entry.getKey();
                Object val = entry.getValue();
                builder.append(key + "=" + val + "&");
            }
        }
        if (builder.length() > 0) {
            builder.deleteCharAt(builder.length() - 1);
        }

        return builder.toString();
    }
    @Override
    public void recyle() {

    }

    @Override
    public void getResponse(JSONObject jsonObj, boolean isSucc, int errorCode, int seqNo, int src) {

    }

    @Override
    public Object getReq() {
        return this.t;
    }

    /***
     * doTask任务之前需要做的事情
     */
    protected void taskPre() {
    }

    private String getReqKey() {
        String key = "";
        Object obj = getReq();
        if (obj instanceof ReqBaseEntity) {
            ReqBaseEntity reqBaseEntity = (ReqBaseEntity) obj;
            key = reqBaseEntity.getReqUrl() + "_" + reqBaseEntity.getID();
            //如果登录，则存储缓存时带入登录信息
//            key += ("_" + UserController.getInstance().getUid());
        }
        return key;
    }
}
