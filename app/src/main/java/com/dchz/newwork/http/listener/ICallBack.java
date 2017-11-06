package com.dchz.newwork.http.listener;

/**
 * Created by diaosi on 2015/12/29.
 */
public interface ICallBack<Object>{
    public void getResponse(Object rsp, boolean isSucc, int errorCode, int seqNo, int src);
}

