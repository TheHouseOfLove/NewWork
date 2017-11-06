package com.dchz.newwork.activity.base;

import android.os.Bundle;
import android.os.Message;

import com.dchz.newwork.http.listener.ICallBack;


/**
 * 框架改造V2版本
 * Created by diaosi on 2015/12/29.
 */
public abstract class BaseNormalActivity extends BaseHandlerActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void handleMsg(Message msg) {

    }

    private ICallBack<Object> mCallBack = new ICallBack<Object>() {
        @Override
        public void getResponse(Object obj, boolean isSucc, int errorCode, int seqNo, int src) {
            if(!isFinishing()){
                handleRsp(obj,isSucc,errorCode,seqNo,src);
            }
        }
    };

    protected abstract void handleRsp(Object obj, boolean isSucc, int errorCode, int seqNo, int src);

    /***
     * 获取协议回调
     * @return
     */
    protected ICallBack<Object> getCallBack(){
        return this.mCallBack;
    }

}
