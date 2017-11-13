package com.abl.common.http;


import android.text.TextUtils;

import com.abl.RWD.common.Global;
import com.abl.RWD.util.MyLog;
import com.squareup.okhttp.Call;
import com.squareup.okhttp.Headers;
import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.MultipartBuilder;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

import java.io.File;
import java.io.IOException;
import java.net.FileNameMap;
import java.net.URLConnection;
import java.net.UnknownHostException;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

/****
 * Http相关工具类(使用OkHttp)
 * @date 2016/06/27
 */
public class HttpUtilsOkHttp {

    public static final String TAG = "HttpUtilsOkHttp";

    private static byte netByte = (byte) -1;

    // 未知网络类型
    static final byte NET_TYPE_UNKNOWN = (byte) 99;

    // wap类网络但是具体是哪个wap不清楚
    static final byte NET_TYPE_WAP = (byte) 98;

    // 默认连接超时时间
    static final int DEFAULT_CONNECTION_TIMEOUT = 20;

    // 默认两个数据分片之间超时
    static final int DEFAULT_SO_TIMEOUT = 45;

    // 默认数据分片大小
    static final int DEFAULT_SOCKET_BUFFER_SIZE = 8192;

    public static final byte APN_WIFI = 1;

    public static final byte APN_CMWAP = 4;

    public static final byte APN_CMNET = 5;

    public static final byte APN_UNIWAP = 6;

    public static final byte APN_UNINET = 7;

    public static final byte APN_3GWAP = 8;

    public static final byte APN_3GNET = 9;

    public static final byte APN_CTWAP = 10;

    public static final byte APN_CTNET = 11;
    
    static final HashMap<Integer, Byte> NET_TYPES = new HashMap<Integer, Byte>();

    private static final int NET_TRY_CNT = 2;

    private static long mReleaseTime;
    
    static {
        NET_TYPES.put(NetWorkUtils.APN_WIFI, APN_WIFI);
        NET_TYPES.put(NetWorkUtils.APN_CMWAP, APN_CMWAP);
        NET_TYPES.put(NetWorkUtils.APN_CMNET, APN_CMNET);
        NET_TYPES.put(NetWorkUtils.APN_UNIWAP, APN_UNIWAP);
        NET_TYPES.put(NetWorkUtils.APN_UNINET, APN_UNINET);
        NET_TYPES.put(NetWorkUtils.APN_3GWAP, APN_3GWAP);
        NET_TYPES.put(NetWorkUtils.APN_3GNET, APN_3GNET);
        NET_TYPES.put(NetWorkUtils.APN_CTWAP, APN_CTWAP);
        NET_TYPES.put(NetWorkUtils.APN_CTNET, APN_CTNET);
    }

    protected static void refreshNetByte() {
        synchronized (NET_TYPES) {
            final int apn = NetWorkUtils.getNetworkType(Global.getContext());
            if (NET_TYPES.containsKey(apn)) {
                netByte = NET_TYPES.get(apn);
            } else {
                if (NetWorkUtils.isWap()) {
                    netByte = NET_TYPE_WAP;
                } else {
                    netByte = NET_TYPE_UNKNOWN;
                }
            }
        }
    }

    public static byte getNetByte() {
        if (netByte == (byte) -1) {
            refreshNetByte();
        }
        synchronized (NET_TYPES) {
            return netByte;
        }
    }

    public static String getApnName() {
        return NetWorkUtils.getNetworkName(Global.getContext());
    }

    public static boolean isNetworkActive() {
        return NetWorkUtils.isNetworkActive();
    }

    public static boolean isWifi() {
        return getNetByte() == (byte) 1;
    }

    /**
     * get方式
     * @param url
     * @param params
     * @return
     */
    public static byte[] getTypeByte(String url, String params){
        OkHttpClient client=getHttpClient();
        String refer = HttpEngine.getInstance().getRefer();
        String reqUrl = refer+url + params;
        String useragent = System.getProperty("http.agent");
        Request.Builder builder=new Request.Builder();
        builder.addHeader("Accept-Language", "zh-cn")
                .addHeader("User-Agent", useragent);
        if (!TextUtils.isEmpty(getLoginStatus())) {
            builder.addHeader("Cookie", getLoginStatus());
        }
        Request request = builder.url(reqUrl)
                .build();
        Call call=client.newCall(request);
        try {
            Response response=call.execute();
            return decodeResponse(response);
        } catch (UnknownHostException exception){
            exception.printStackTrace();
            return new byte[] { NetCommon.ERROR_HTTP_IO_EXCEPTION };
        } catch (IOException e) {
            e.printStackTrace();
            return new byte[] { NetCommon.ERROR_HTTP_IO_EXCEPTION };
        }
    }

    public static byte[] postTypeByte(String url, String params){
        OkHttpClient client=getHttpClient();
        String refer = HttpEngine.getInstance().getRefer();
        String reqUrl = refer+url;
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json;charset:utf-8"), params);
        String useragent = System.getProperty("http.agent");
        Request.Builder builder=new Request.Builder();
        builder .addHeader("Accept-Language","zh-cn")
                .addHeader("User-Agent", useragent)
                .addHeader("Content-Type","application/json;charset:utf-8");
        if (!TextUtils.isEmpty(getLoginStatus())) {
            builder.addHeader("Cookie", getLoginStatus());
        }
        Request request = builder
                .url(reqUrl)
                .post(requestBody)
                .build();
        Call call=client.newCall(request);
        try {
            Response response=call.execute();
            return decodeResponse(response);
        } catch (IOException e) {
            e.printStackTrace();
            return new byte[] { NetCommon.ERROR_HTTP_IO_EXCEPTION };
        }
    }
    public static byte[] postTypeByte(String url, ArrayList<File> files,
                                      ArrayList<String> fileKeys, Map<String,Object> params){

        OkHttpClient client=getHttpClient();
        String refer = HttpEngine.getInstance().getRefer();
        String reqUrl = refer+url;
        Request request=buildMultipartFormRequest(reqUrl,files,fileKeys,params);
        Call call=client.newCall(request);
        try {
            Response response=call.execute();
            return decodeResponse(response);
        } catch (IOException e) {
            e.printStackTrace();
            return new byte[] { NetCommon.ERROR_HTTP_IO_EXCEPTION };
        }
    }


    /**
     * 异步基于post的文件上传
     * @param url
     * @param files
     * @return
     */
    private static Request buildMultipartFormRequest(String url, ArrayList<File> files,
                                                     ArrayList<String> fileKeys, Map<String,Object> params)
    {
        MultipartBuilder builder = new MultipartBuilder()
                .type(MultipartBuilder.FORM);
        String useragent = System.getProperty("http.agent");


        for (Map.Entry<String, Object> entry : params.entrySet()) {
            String key = entry.getKey();
            Object val = entry.getValue();
            builder.addPart(Headers.of("Content-Disposition", "form-data; name=\"" + key + "\""),
                    RequestBody.create(null, val+""));
        }
        if (files != null)
        {
            RequestBody fileBody = null;
            for (int i = 0; i < files.size(); i++)
            {
                File file = files.get(i);
                String fileName = file.getName();
                String id="";
                if (i==0) {
                    id="myfile";
                }else{
                    id="myfile"+(i+1);
                }
                fileBody = RequestBody.create(MediaType.parse(guessMimeType(fileName)), file);
                //TODO 根据文件名设置contentType
                builder.addPart(Headers.of("Content-Disposition",
                        "form-data; type=\"file\";name=\"" + fileKeys.get(i) + "\"; filename=\"" + fileName + "\";id=\""+id),
                        fileBody);
            }
        }

        RequestBody requestBody = builder.build();
        return new Request.Builder()
                .url(url)
                .addHeader("Accept-Language","zh-cn")
                .addHeader("User-Agent", useragent)
                .addHeader("Cookie", getLoginStatus())
                .post(requestBody)
                .build();
    }

    /**
     * 获取文件类型
     * @param path
     * @return
     */
    private static String guessMimeType(String path)
    {
        FileNameMap fileNameMap = URLConnection.getFileNameMap();
        String contentTypeFor = fileNameMap.getContentTypeFor(path);
        if (contentTypeFor == null)
        {
            contentTypeFor = "application/octet-stream";
        }
        return contentTypeFor;
    }

    /**
     * 获取OkHttpClient
     * @return
     */
    private static OkHttpClient getHttpClient(){

        int connectionTimeout = DEFAULT_CONNECTION_TIMEOUT;
        int soTimeout = DEFAULT_SO_TIMEOUT;
        int bufferSize = DEFAULT_SOCKET_BUFFER_SIZE;
        final byte apn = getNetByte();
        switch (apn) {
            case 1: // wifi环境
                connectionTimeout = 5;
                soTimeout = 30;
                break;
            case 4:
            case 5:
            case 6:
            case 7: // 2g网络
                connectionTimeout = 15;
                soTimeout = 45;
                break;
            case 8:
            case 9:
            case 10:
            case 11:
                connectionTimeout = 10;
                soTimeout = 40;
                break;
            default:
                // 未知网络类型，给用默认超时设置
        }


        OkHttpClient okHttpClient=new OkHttpClient();
        okHttpClient.setConnectTimeout(connectionTimeout, TimeUnit.SECONDS);
        okHttpClient.setSslSocketFactory(createSSLSocketFactory());
        okHttpClient.setHostnameVerifier(new HostnameVerifier() {
            @Override
            public boolean verify(String hostname, SSLSession session) {
                return true;
            }
        });
        return okHttpClient;
    }
    private static byte[] decodeResponse(Response response){
        int code=response.code();
        MyLog.debug(TAG,"[decodeResponse]   code:"+code);
        if (code==200){
            try {
                return response.body().bytes();
            } catch (IOException e) {
//                e.printStackTrace();
                return new byte[] { NetCommon.ERROR_HTTP_IO_EXCEPTION };
            }
        }else if(code == 500){
            return new byte[] { NetCommon.ERROR_HTTP_RESPONSE_HTTP_CODE_500 };
        }else{
            return new byte[] { NetCommon.ERROR_HTTP_RESPONSE_HTTP_CODE };
        }
    }
    private static javax.net.ssl.SSLSocketFactory createSSLSocketFactory() {
        javax.net.ssl.SSLSocketFactory ssfFactory = null;

        try {
            SSLContext sc = SSLContext.getInstance("TLS");
            sc.init(null, new TrustManager[]{new TrustAllCerts()}, new SecureRandom());

            ssfFactory = sc.getSocketFactory();
        } catch (Exception e) {
        }

        return ssfFactory;
    }
    public static class TrustAllCerts implements X509TrustManager {

        @Override
        public void checkClientTrusted(java.security.cert.X509Certificate[] chain, String authType) throws CertificateException {

        }

        @Override
        public void checkServerTrusted(java.security.cert.X509Certificate[] chain, String authType) throws CertificateException {

        }

        @Override
        public java.security.cert.X509Certificate[] getAcceptedIssuers() {
            return new java.security.cert.X509Certificate[0];
        }
    }
    /***
     * 登录身份信息
     * @return
     */
    public static String getLoginStatus(){
//        String cookie = LoginController.getInstance().getCookie();
//        if(MyLog.isDebugable()){
//            MyLog.debug(TAG,"[getLoginStatus]" + " cookie:" + cookie);
//        }
//        //现进行整体编码处理
//        if (!TextUtils.isEmpty(cookie)){
//            try {
//                cookie= URLEncoder.encode(cookie, "UTF-8");
//            } catch (UnsupportedEncodingException e) {
//                e.printStackTrace();
//            }
//        }
        return "";
    }

    public static class Param
    {
        public Param()
        {
        }

        public Param(String key, String value)
        {
            this.key = key;
            this.value = value;
        }

        String key;
        String value;
    }
}
