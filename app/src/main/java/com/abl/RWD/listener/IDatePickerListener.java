package com.abl.RWD.listener;


import com.abl.RWD.entity.VDateEntity;

/**
 * Created by diaosi on 2016/2/29.
 */
public abstract class IDatePickerListener {

    /****
     * 获取相关日期
     * @param vEntity
     */
    public void onPickerClick(VDateEntity vEntity){};

    public void onPickCancle(){};
}
