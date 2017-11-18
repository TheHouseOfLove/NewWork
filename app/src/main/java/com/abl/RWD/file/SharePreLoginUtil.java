package com.abl.RWD.file;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.preference.TwoStatePreference;
import android.text.TextUtils;

import com.abl.RWD.common.Global;
import com.abl.RWD.entity.PLoginEntity;
import com.google.gson.Gson;


/***
 * LoginInfo信息
 * @date 2015/07/13
 */
public class SharePreLoginUtil {
	private final String TAG = "SharePreLoginInfo";
//	private static final String UID="uid";
//	private static final String LOGIN="login";
//	private static final String RIGHT="right";
//	private static final String YHName="YHName";
//	private static final String YHAccount="YHAccount";
//	private static final String YHCode="YHCode";
//	private static final String BMID="BMID";
//	private static final String BMName="BMName";
//	private static final String LeaderRole="LeaderRole";
//	private static final String IsBMLeader="IsBMLeader";
	public static final String USER_INFO="userInfo";
	
	private final static String FILE_NAME = "LoginInfo_FILE";
	
	public static void saveLoginInfo(PLoginEntity entity){
		if(entity!=null){
			SharedPreferences sp = Global.getContext().getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
			Editor editor = sp.edit();
//			editor.putString(LOGIN, entity.Login);
//			editor.putString(UID, entity.YHID);
//			editor.putString(RIGHT, entity.Right);
//			editor.putString(YHName, entity.YHName);
//			editor.putString(YHAccount, entity.YHAccount);
//			editor.putString(YHCode, entity.YHCode);
//			editor.putString(BMID, entity.BMID);
//			editor.putString(BMName, entity.BMName);
//			editor.putString(LeaderRole, entity.LeaderRole);
//			editor.putString(IsBMLeader, entity.IsBMLeader);
			Gson gson=new Gson();
			String str=gson.toJson(entity,PLoginEntity.class);
			editor.putString(USER_INFO,str);
			editor.commit();
		}
	}
	
	public static PLoginEntity loadLoginInfo(){
		PLoginEntity entity=null;
		SharedPreferences sp = Global.getContext().getSharedPreferences(FILE_NAME,Context.MODE_PRIVATE);
//		entity.YHID = sp.getString(UID, "");
//		entity.Login = sp.getString(LOGIN, "");
//		entity.Right = sp.getString(RIGHT,"");
//		entity.YHName = sp.getString(YHName, "");
//		entity.YHAccount = sp.getString(YHAccount, "");
//		entity.YHCode = sp.getString(YHCode, "");
//		entity.BMID = sp.getString(BMID, "");
//		entity.BMName = sp.getString(BMName, "");
//		entity.LeaderRole = sp.getString(LeaderRole, "");
//		entity.IsBMLeader = sp.getString(IsBMLeader, "");
		String str=sp.getString(USER_INFO,"");
		if (!TextUtils.isEmpty(str)){
			Gson gson=new Gson();
			entity=gson.fromJson(str,PLoginEntity.class);
		}
		return entity;
	}
}
