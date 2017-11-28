package com.abl.RWD.entity;

import java.io.Serializable;

/**
 * Created by yas on 2017/11/21.
 */

public class PYWInfoItemEntity implements Serializable{
    //TextBox_1：单行输入框   TextBox_2：多行输入框  DropDownList、RadioButtonList：单选框
    //CheckBoxList: 多选框    CalendarV2：日期选择框   其他情况：label
    public String ControlType;
    public String ControlValue; //选择框所需的键值对数据
    public String CRName;       //"修改"情况下该项可修改，其余不允许修改
    public String DataLength;   //长度限制，只有单⾏行行或者多⾏行行输⼊入框的时候有⻓长度限制
    public String DataType;     //数据类型，只有当int/float/decimal三种类型的时候是只能填写数字和⼩小数点
    public String Field;      //需要上传的值
    public String FieldName;    //显示每一行的标题
    public String FieldValue;   //显示每一行的值
    public String Required;     //是否必填，当CRName为修改并且Required为是的时候才限制必填，并且每⾏行行的标题都要变红

    public boolean isTop;
}
