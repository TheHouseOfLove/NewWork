package com.abl.RWD.entity;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Administrator on 2016/8/28.
 */
public class PYueDuHeTongInfoEntity implements Serializable {
    public ArrayList<PNianDuInfoItemENtity> NianDuInfo;
    public String CurrentNian;
    public String DataDanWei;
    public ArrayList<PYueDuHeTongItemEntiity> YueDuHeTongInfo;
}
