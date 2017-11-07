package com.dchz.newwork.util;

import android.text.TextUtils;

import com.dchz.newwork.component.ChartView;
import com.dchz.newwork.entity.PJiDuHeTongItemEntity;
import com.dchz.newwork.entity.PJiDuShouKuanItemEntity;
import com.dchz.newwork.entity.PYueDuHeTongItemEntiity;
import com.dchz.newwork.entity.PYueDuInfoItemEntity;
import com.dchz.newwork.entity.VThirdItemEntity;

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
//	public static ArrayList<VJieShouRenEntity> getJieShouRen(ArrayList<PDetailHTInfoEntity> srcList){
//		 ArrayList<VJieShouRenEntity> mList=new ArrayList<VJieShouRenEntity>();
//		 if(srcList!=null&&srcList.size()>0){
//			 for(int i=0;i<srcList.size();i++){
//				 VJieShouRenEntity mEntity=new VJieShouRenEntity();
//				 mEntity.HTInfoEntity=srcList.get(i);
//				 mEntity.type=VJieShouRenEntity.TYPE_HT;
//				 mList.add(mEntity);
//			 }
//		 }
//		 return mList;
//	}
//
//	public static ArrayList<VJieShouRenEntity> getJieShouRen1(ArrayList<PDetailTJInfoInfoEntity> srcList){
//		 ArrayList<VJieShouRenEntity> mList=new ArrayList<VJieShouRenEntity>();
//		 if(srcList!=null&&srcList.size()>0){
//			 for(int i=0;i<srcList.size();i++){
//				 VJieShouRenEntity mEntity=new VJieShouRenEntity();
//				 mEntity.TJInfoEntity=srcList.get(i);
//				 mEntity.type=VJieShouRenEntity.TYPE_TJ;
//				 mList.add(mEntity);
//			 }
//		 }
//		 return mList;
//	}

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
