package com.abl.RWD.entity;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Administrator on 2016/8/28.
 * 月度合同info item
 */
public class PYueDuHeTongItemEntiity implements Serializable {
    public String BMID;
    public String BMName;
    public String NianDuHeTongShiJi;
    public String NianDuHeTongYuSuan;
    public String Nian;
    public ArrayList<PYueDuInfoItemEntity> YueDuInfo;
}
