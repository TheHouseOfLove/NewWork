package com.abl.RWD.entity;

import java.io.Serializable;

public class VThirdItemEntity implements Serializable {
	public static final int TYPE_HeTong=1;
	public static final int TYPE_SHOUKUAN=2;
	private static final long serialVersionUID = 1L;
	public PJiDuHeTongItemEntity mEntity;
	public PJiDuShouKuanItemEntity shouKuanEntity;
	public int type;
	public boolean isTop;
}
