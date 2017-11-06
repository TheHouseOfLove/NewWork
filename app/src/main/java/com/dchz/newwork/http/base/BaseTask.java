package com.dchz.newwork.http.base;

import android.text.TextUtils;

import com.dchz.common.http.HttpBaseTask;
import com.dchz.common.http.HttpUtilsOkHttp;
import com.dchz.common.http.NetCommon;
import com.dchz.common.util.DeviceUtil;
import com.dchz.common.util.MD5;
import com.dchz.newwork.common.Common;
import com.dchz.newwork.common.MConfiger;
import com.dchz.newwork.http.listener.ITaskListener;
import com.dchz.newwork.util.MyLog;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONObject;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import static com.dchz.newwork.common.Common.seqNo;

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
                    String params = getJavaParams(reqBaseEntity.getReqData());
                    buffer = HttpUtilsOkHttp.getTypeByte(url, params);

                } else if (reqBaseEntity.mVisitType == ReqBaseEntity.TYPE_JAVA_POST) {
                     /* Post方式访问 */
                    Map<String, Object> map = reqBaseEntity.getReqData();
                    //添加token作为最后一个参数，java后端做校验使用;
//                    map.put("token", LoginController.getInstance().getToken());
                    ArrayList<File> files = new ArrayList<>();
                    ArrayList<String> fileNames = new ArrayList<>();
                    Map<String, Object> mMap = new HashMap<>();
                    if (map!=null) {
                        for (Map.Entry<String, Object> entry : map.entrySet()) {
                            String key = entry.getKey();
                            Object val = entry.getValue();
                            if (val instanceof PFileEntity) {
                                PFileEntity fileEntity = (PFileEntity) val;
                                if (fileEntity != null) {
                                    File file = new File(fileEntity.filePath);
                                    files.add(file);
                                    fileNames.add("myfile");
                                }
                            } else {
                                mMap.put(key, val);
                            }
                        }
                    }
                    if (files.size() > 0) {
                        //带文件上传的请求
//                        mMap = putCommonParams(mMap);
                        buffer = HttpUtilsOkHttp.postTypeByte(url, files, fileNames, putCommonParams(mMap));
                    } else {
                        String jsonStr = getPostJavaParams(map);
                        buffer = HttpUtilsOkHttp.postTypeByte(url, jsonStr);
                    }
                }

                if (buffer != null && buffer.length > 2) {
                    String str = new String(buffer, "utf-8");
                    JSONObject jsonObj = new JSONObject(str);
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

    /**
     * 上传文件时参数处理
     * @param map
     * @return
     */
    private Map<String, Object> putCommonParams(Map<String, Object> map) {
        if (map == null) {
            map = new HashMap<>();
        }
        String sign= getSignParam(map);
        if (!TextUtils.isEmpty(sign)) {
            map.put("sign", sign);
        }
        //添加fromType
        map.put("fromType", NetCommon.KEY_FROM_TYPE);
        //添加时间戳
        map.put("timestamp", System.currentTimeMillis() + "");
        //添加渠道号
        map.put("channel", MConfiger.CHANNEL_ID);
        //添加设备号
        String strIMEI = DeviceUtil.getIMEI();
        if (!TextUtils.isEmpty(strIMEI)) {
            map.put("deviceSn", strIMEI);
        }
        //添加请求ip
        String deviceIp = DeviceUtil.getHostIP();
        if (!TextUtils.isEmpty(deviceIp)) {
            map.put("deviceIp", deviceIp);
        }
//
//           String deviceGps = LBSController.getInstance().getLocInfo();
//        MyLog.debug(TAG,"[putCommonParams]  deviceGps:"+deviceGps);
//            map.put("deviceGps", deviceGps);
        return map;
    }
    /**
     * java get请求时参数处理
     * @param map
     * @return
     */
    private static final String getJavaParams(Map<String, Object> map) {
        if (map == null) {
            map = new HashMap<>();
        }
        String sign= getSignParam(map);
        if (!TextUtils.isEmpty(sign)) {
            map.put("sign", sign);
        }

        //添加fromType
        map.put("fromType", NetCommon.KEY_FROM_TYPE);
        //添加时间戳
        map.put("timestamp", System.currentTimeMillis());
        //添加渠道号
        map.put("channel", MConfiger.CHANNEL_ID);
        //添加设备号
        String strIMEI = DeviceUtil.getIMEI();
        if (!TextUtils.isEmpty(strIMEI)) {
            map.put("deviceSn", strIMEI);
        }
        //添加请求ip
        String deviceIp = DeviceUtil.getHostIP();
        if (!TextUtils.isEmpty(deviceIp)) {
            map.put("deviceIp", deviceIp);
        }

//        String deviceGps = LBSController.getInstance().getLocInfo() ;
//            map.put("deviceGps", deviceGps);

        return getParams(map);
    }
    /**
     * java 普通post请求时参数处理
     * @param map
     * @return
     */
    private static final String getPostJavaParams(Map<String, Object> map) {

        String sign = getSignParam(map);
        if(!TextUtils.isEmpty(sign)) {
            map.put("sign", sign);
        }
        if (map == null) {
            map = new HashMap<>();
        }
        if (map != null) {
            //添加fromType
            map.put("fromType", NetCommon.KEY_FROM_TYPE);
            //添加时间戳
            map.put("timestamp", System.currentTimeMillis());
            //添加渠道号
            map.put("channel", MConfiger.CHANNEL_ID);

            //添加设备号
            String strIMEI = DeviceUtil.getIMEI();
            if (!TextUtils.isEmpty(strIMEI)) {
                map.put("deviceSn", strIMEI);
            }
            //添加请求ip
            String deviceIp = DeviceUtil.getHostIP();
            if (!TextUtils.isEmpty(deviceIp)) {
                map.put("deviceIp", deviceIp);
            }

//            String deviceGps = LBSController.getInstance().getLocInfo() ;
//                map.put("deviceGps", deviceGps);
//            map.put("sign", "xx");
            Gson gson = new Gson();
            Type type = new TypeToken<Map<String, Object>>() {
            }.getType();
            String str = gson.toJson(map, type);
            return str;
        } else {
            return "";
        }
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

    /**
     * 获取sign签名验证
     * @param map
     * @return
     */
    private static final String getSignParam(Map<String, Object> map) {
        StringBuilder builder = new StringBuilder();
        String sign = "";

        if (map != null && map.size() > 0) {
            TreeMap<String, Object> treeMap = new TreeMap<>(new Comparator<String>() {
                @Override
                public int compare(String s, String t1) {
                    int compare = 0;
                    if (!TextUtils.isEmpty(s) && !TextUtils.isEmpty(t1)) {
                        compare = s.compareTo(t1);
                    }

                    if (compare > 0) {
                        return 1;
                    } else if (compare < 0) {
                        return -1;
                    } else {
                        return 0;
                    }
                }
            });

            treeMap.putAll(map);
//            if (treeMap.containsKey("pageSize"))
//                treeMap.remove("pageSize");
//            if (treeMap.containsKey("pageIndex"))
//                treeMap.remove("pageIndex");
            if (treeMap.size() > 0) {
                for (Map.Entry<String, Object> entry : treeMap.entrySet()) {
                    String key = entry.getKey();
                    Object val = entry.getValue();
                    if(val == null){
                        builder.append(key + "=0&");
                    } else {
                        if (val instanceof String){
                            String str= (String) val;
                            if (TextUtils.isEmpty(str)){
                                builder.append(key + "=0&");
                            }else{
                                builder.append(key + "=" + val + "&");
                            }
                        }else if(val instanceof ArrayList){
                            //list暂时不参与签名
                        }else {
                            builder.append(key + "=" + val + "&");
                        }
                    }
                }
                if(builder.length()>0) {
                    builder.append(NetCommon.JAVA_KEY_CODE);
                }
            }
            MyLog.debug("BaseTask", "be sign:" + builder.toString());
            sign = MD5.getMd5(builder.toString(), "utf-8");
            if (!TextUtils.isEmpty(sign)) {
                sign=sign.toLowerCase();
            }
            MyLog.debug("BaseTask", "sign:" + sign);
        }
        return sign;
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
