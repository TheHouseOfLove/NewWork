package com.dchz.newwork.listener;

/**
 * Created by yas on 2017/3/21.
 */
public abstract class IBtnClickListener {
    //左键的返回
    public void btnLeftClick(){};
    //右键的点击
    public void btnRightClick(){};
    //只有一个按钮是点击
    public void btnClick(){};
}
