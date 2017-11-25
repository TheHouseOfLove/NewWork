package com.abl.RWD.util;

import android.text.TextUtils;

import com.abl.RWD.component.ChartView;
import com.abl.RWD.entity.PAttInfoItemEntity;
import com.abl.RWD.entity.PAttInfoSubItemEntity;
import com.abl.RWD.entity.PJiDuHeTongItemEntity;
import com.abl.RWD.entity.PJiDuShouKuanItemEntity;
import com.abl.RWD.entity.PYWInfoItemEntity;
import com.abl.RWD.entity.PYWInfoSubItemEntity;
import com.abl.RWD.entity.PYueDuHeTongItemEntiity;
import com.abl.RWD.entity.PYueDuInfoItemEntity;
import com.abl.RWD.entity.VThirdItemEntity;

import java.util.ArrayList;


/**
 * 解析工具类
 * @author yas
 */
public class ParseUtil {
	public static ArrayList<VThirdItemEntity> getThirdList(ArrayList<PJiDuHeTongItemEntity> srcList){
		ArrayList<VThirdItemEntity> mList=new ArrayList<VThirdItemEntity>();
		if(srcList!=null&&srcList.size()>0){
			int ShiJiTotal=0;
			int YuSuanTotal=0;
			VThirdItemEntity mEntity=new VThirdItemEntity();
			PJiDuHeTongItemEntity entity=new PJiDuHeTongItemEntity();
			entity.BMName="部门";
			entity.NianDuHeTongYuSuan="预算";
			entity.NianDuHeTongShiJi="实际";
			entity.WanChengBiLi="比例";
			mEntity.mEntity=entity;
			mEntity.type=VThirdItemEntity.TYPE_HeTong;
			mList.add(mEntity);
			
			for(int i=0;i<srcList.size();i++){
				mEntity=new VThirdItemEntity();
				mEntity.mEntity=srcList.get(i);
				mEntity.type=VThirdItemEntity.TYPE_HeTong;
				mList.add(mEntity);
				if (!"总部小计".equals(srcList.get(i).BMName)
						&& !"分院小计".equals(srcList.get(i).BMName)
						&& !"公司小计".equals(srcList.get(i).BMName)) {
					String str = srcList.get(i).NianDuHeTongShiJi;
					if(!TextUtils.isEmpty(str)){
						ShiJiTotal = ShiJiTotal + Integer.valueOf(str);
					}
					
					String str1 = srcList.get(i).NianDuHeTongYuSuan;
					if(!TextUtils.isEmpty(str1)){
						YuSuanTotal = YuSuanTotal + Integer.valueOf(str1);
					}
				}else{
					MyLog.debug("dd","[getThirdList]");
				}
			}
			MyLog.debug("dd","[getThirdList]  ShiJiTotal:"+ShiJiTotal+"  YuSuanTotal"+YuSuanTotal);
			mEntity=new VThirdItemEntity();
		    entity=new PJiDuHeTongItemEntity();
			entity.BMName="总计";
			entity.NianDuHeTongYuSuan=YuSuanTotal+"";
			entity.NianDuHeTongShiJi=ShiJiTotal+"";
			entity.WanChengBiLi="";
			mEntity.mEntity=entity;
			mEntity.type=VThirdItemEntity.TYPE_HeTong;
			mList.add(mEntity);
		}
		return mList;
	}
	
	public static ArrayList<VThirdItemEntity> getThirdList(ArrayList<PJiDuHeTongItemEntity> srcList, String BMID){
		ArrayList<VThirdItemEntity> mList=new ArrayList<VThirdItemEntity>();
		if(srcList!=null&&srcList.size()>0){
			int ShiJiTotal=0;
			int YuSuanTotal=0;
			VThirdItemEntity mEntity=new VThirdItemEntity();
			PJiDuHeTongItemEntity entity=new PJiDuHeTongItemEntity();
			entity.BMName="部门";
			entity.NianDuHeTongYuSuan="预算";
			entity.NianDuHeTongShiJi="实际";
			entity.WanChengBiLi="比例";
			mEntity.mEntity=entity;
			mEntity.type=VThirdItemEntity.TYPE_HeTong;
			mList.add(mEntity);
			
			for(int i=0;i<srcList.size();i++){
				if(srcList.get(i).BMID.equals(BMID)){
					mEntity=new VThirdItemEntity();
					mEntity.mEntity=srcList.get(i);
					mEntity.type=VThirdItemEntity.TYPE_HeTong;
					mList.add(mEntity);
				}
			}
		}
		return mList;
	}
	public static ArrayList<VThirdItemEntity> getThirdList1(ArrayList<PJiDuShouKuanItemEntity> srcList){
		ArrayList<VThirdItemEntity> mList=new ArrayList<VThirdItemEntity>();
		if(srcList!=null&&srcList.size()>0){
			int ShiJiTotal=0;
			int YuSuanTotal=0;
			VThirdItemEntity mEntity=new VThirdItemEntity();
			PJiDuShouKuanItemEntity entity=new PJiDuShouKuanItemEntity();
			entity.BMName="部门";
			entity.NianDuShouKuanYuSuan="预算";
			entity.NianDuShouKuanShiJi="实际";
			entity.WanChengBiLi="比例";
			mEntity.shouKuanEntity=entity;
			mEntity.type=VThirdItemEntity.TYPE_SHOUKUAN;
			mList.add(mEntity);
			
			for(int i=0;i<srcList.size();i++){
				mEntity=new VThirdItemEntity();
				mEntity.shouKuanEntity=srcList.get(i);
				mEntity.type=VThirdItemEntity.TYPE_SHOUKUAN;
				mList.add(mEntity);
				if (!"总部小计".equals(srcList.get(i).BMName)
						&& !"分院小计".equals(srcList.get(i).BMName)
						&&!"公司小计".equals(srcList.get(i).BMName)) {
					String str = srcList.get(i).NianDuShouKuanShiJi;
					if(!TextUtils.isEmpty(str)){
						ShiJiTotal = ShiJiTotal + Integer.valueOf(str);
					}
					
					String str1 = srcList.get(i).NianDuShouKuanYuSuan;
					if(!TextUtils.isEmpty(str1)){
						YuSuanTotal = YuSuanTotal + Integer.valueOf(str1);
					}
				}
			}
			
			mEntity=new VThirdItemEntity();
		    entity=new PJiDuShouKuanItemEntity();
			entity.BMName="总计";
			entity.NianDuShouKuanYuSuan=YuSuanTotal+"";
			entity.NianDuShouKuanShiJi=ShiJiTotal+"";
			entity.WanChengBiLi="";
			mEntity.shouKuanEntity=entity;
			mEntity.type=VThirdItemEntity.TYPE_SHOUKUAN;
			mList.add(mEntity);
		}
		return mList;
	}
	
	
	public static ArrayList<VThirdItemEntity> getThirdList1(ArrayList<PJiDuShouKuanItemEntity> srcList, String BMID){
		ArrayList<VThirdItemEntity> mList=new ArrayList<VThirdItemEntity>();
		if(srcList!=null&&srcList.size()>0){
			int ShiJiTotal=0;
			int YuSuanTotal=0;
			VThirdItemEntity mEntity=new VThirdItemEntity();
			PJiDuShouKuanItemEntity entity=new PJiDuShouKuanItemEntity();
			entity.BMName="部门";
			entity.NianDuShouKuanYuSuan="预算";
			entity.NianDuShouKuanShiJi="实际";
			entity.WanChengBiLi="比例";
			mEntity.shouKuanEntity=entity;
			mEntity.type=VThirdItemEntity.TYPE_SHOUKUAN;
			mList.add(mEntity);
			
			for(int i=0;i<srcList.size();i++){
				if(srcList.get(i).BMID.equals(BMID)){
					mEntity=new VThirdItemEntity();
					mEntity.shouKuanEntity=srcList.get(i);
					mEntity.type=VThirdItemEntity.TYPE_SHOUKUAN;
					mList.add(mEntity);
				}
			}
			
		}
		return mList;
	}

	/**
	 * 获取业务list
	 * @param YWInfo
	 * @return
	 */
	public static ArrayList<PYWInfoItemEntity> getDetailItemList(ArrayList<PYWInfoSubItemEntity> YWInfo){
		ArrayList<PYWInfoItemEntity> mList=new ArrayList<>();
		if (YWInfo!=null){
			for (int i=0;i<YWInfo.size();i++){
				PYWInfoSubItemEntity itemEntity=YWInfo.get(i);
				if (itemEntity!=null&&itemEntity.Item!=null){
					mList.addAll(itemEntity.Item);
					if (i!=YWInfo.size()-1) {
						mList.add(new PYWInfoItemEntity());
					}
				}
			}
		}
		return mList;
	}

	/**
	 * 获取文件
	 * @param files
	 * @return
	 */
	public static ArrayList<PAttInfoItemEntity> getDetailAttItemList(ArrayList<PAttInfoSubItemEntity> files){
		ArrayList<PAttInfoItemEntity> mList=new ArrayList<>();
		if (files!=null){
			for (int i=0;i<files.size();i++) {
				PAttInfoSubItemEntity itemEntity = files.get(i);
				itemEntity.Item.get(0).isFirst=true;
				itemEntity.Item.get(itemEntity.Item.size()-1).isLast=true;
				mList.addAll(itemEntity.Item);
			}
		}
		return mList;
	}
	/**
	 * 计算所有分部的月度信息
	 * @param srcList
	 * @return
	 */
	public static String[] getYueduTeHongList(final ArrayList<PYueDuHeTongItemEntiity> srcList, int type) {
		if (srcList != null) {
			MyLog.debug("dd", "[getYueduTeHongList]  srcList:" + srcList.size());
		}
		int yiYue = 0, erYue = 0, sanYue = 0, siYue = 0, wuYue = 0, liuYue = 0, qiYue = 0, baYue = 0, jiuYue = 0, shiYue = 0, shiyiYue = 0, shierYue = 0;
			if (srcList != null && srcList.size() > 0) {
				for (int i = 0; i < srcList.size(); i++) {
					PYueDuHeTongItemEntiity entity = srcList.get(i);
					if (entity != null && entity.YueDuInfo != null && entity.YueDuInfo.size() > 0) {
						for (int j = 0; j < entity.YueDuInfo.size(); j++) {
							PYueDuInfoItemEntity infoItem = entity.YueDuInfo.get(j);
							String yue = infoItem.Yue;
							int jine = 0;
							if (type== ChartView.TYPE_HETONG){
								 jine = infoItem.HeTongJinE;
							}else if(type==ChartView.TYPE_SHOUKUAN){
								 jine = infoItem.ShouKuanJinE;
							}
							if ("1".equals(yue)) {
								yiYue += jine;
							} else if ("2".equals(yue)) {
								erYue += jine;
							} else if ("3".equals(yue)) {
								sanYue += jine;
							} else if ("4".equals(yue)) {
								siYue += jine;
							} else if ("5".equals(yue)) {
								wuYue += jine;
							} else if ("6".equals(yue)) {
								liuYue += jine;
							} else if ("7".equals(yue)) {
								qiYue += jine;
							} else if ("8".equals(yue)) {
								baYue += jine;
							} else if ("9".equals(yue)) {
								jiuYue += jine;
							} else if ("10".equals(yue)) {
								shiYue += jine;
							} else if ("11".equals(yue)) {
								shiyiYue += jine;
							} else if ("12".equals(yue)) {
								shierYue += jine;
							}
						}
					}
				}
			}
		String[] mList =new String[]{yiYue/2 + "", erYue/2 + "", sanYue/2 + "", siYue/2 + "", wuYue/2 + "", liuYue/2 + "", qiYue/2 + "", baYue/2 + "", jiuYue/2 + "", shiYue/2 + "", shiyiYue/2 + "", shierYue/2 + ""};
		return mList;
	}
}
