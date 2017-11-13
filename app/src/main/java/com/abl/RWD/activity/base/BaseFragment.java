package com.abl.RWD.activity.base;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.abl.RWD.http.listener.ICallBack;


/**
 * Created by yas on 2017/11/6.
 */

public abstract class BaseFragment extends Fragment{
    private View rootView;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        rootView=inflater.inflate(getLayoutRes(),null);
        initViews(rootView);
        return rootView;
    }

    /**
     * 获取布局文件id
     * @return
     */
    public abstract int getLayoutRes();

    /***
     * 初始化控件
     * @param rootView
     */
    public abstract void initViews(View rootView);

    /**
     * toast弹窗提示
     * @param str
     */
    protected void showToast(String str){
        if (getActivity() instanceof BaseActivity){
            ((BaseActivity) getActivity()).showToast(str);
        }
    }

    /**
     * loading
     * @param str
     */
    protected void showLoading(String str){
        if (getActivity() instanceof BaseActivity){
            ((BaseActivity) getActivity()).showLoading(str);
        }
    }

    /**
     * 隐藏loading
     */
    protected void hideLoading(){
        if (getActivity() instanceof BaseActivity){
            ((BaseActivity) getActivity()).hideLoadingDialog();
        }
    }
    private ICallBack<Object> mCallBack = new ICallBack<Object>() {
        @Override
        public void getResponse(Object obj, boolean isSucc, int errorCode, int seqNo, int src) {
            if(!getActivity().isFinishing()){
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
