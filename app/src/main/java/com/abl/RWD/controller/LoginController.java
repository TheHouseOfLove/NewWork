package com.abl.RWD.controller;


import com.abl.RWD.common.Global;
import com.abl.RWD.common.MConfiger;
import com.abl.RWD.entity.PLoginEntity;
import com.abl.RWD.file.SharePreLoginUtil;

/**
 * Created by yas on 2017/4/20.
 * 登录身份控制
 */
public class LoginController {
    private final String TAG="LoginController";

    private static LoginController instance;       //实例
    private PLoginEntity mEntity;

    /**
     * 私有化构造器
     */
    private LoginController(){

    }
    public synchronized static final LoginController getInstance(){
        if (instance==null){
            instance=new LoginController();
        }
        return instance;
    }

    /**
     * 是否登录
     * @return
     */
    public boolean isLogin(){
        return mEntity!=null;
    }

    public String getYHID(){
        if (mEntity!=null){
            return mEntity.YHID;
        }
        return "";
    }

    /**
     * load登录信息
     */
    public void loadLoginInfo(){
        SharePreLoginUtil mSharePre=new SharePreLoginUtil();
         mEntity=mSharePre.loadLoginInfo();
    }

    /**
     * 更新登录信息
     * @param entity
     */
    public void updateLoginInfo(PLoginEntity entity){
        this.mEntity=entity;
        Global.postDelay(new Runnable() {
            @Override
            public void run() {
                SharePreLoginUtil mSharePre=new SharePreLoginUtil();
                mSharePre.saveLoginInfo(mEntity);
            }
        });
    }
}
