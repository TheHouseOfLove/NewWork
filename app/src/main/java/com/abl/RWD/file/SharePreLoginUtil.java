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
	public static final String USER_INFO="userInfo";
	
	private final static String FILE_NAME = "LoginInfo_FILE";
	
	public static void saveLoginInfo(PLoginEntity entity){
		if(entity!=null){
			SharedPreferences sp = Global.getContext().getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
			Editor editor = sp.edit();
			Gson gson=new Gson();
			String str=gson.toJson(entity,PLoginEntity.class);
			editor.putString(USER_INFO,str);
			editor.commit();
		}
	}
	
	public static PLoginEntity loadLoginInfo(){
		PLoginEntity entity=null;
		SharedPreferences sp = Global.getContext().getSharedPreferences(FILE_NAME,Context.MODE_PRIVATE);
		String str=sp.getString(USER_INFO,"");
		if (!TextUtils.isEmpty(str)){
			Gson gson=new Gson();
			entity=gson.fromJson(str,PLoginEntity.class);
		}
		return entity;
	}
	/**
	 * 清除cookie信息
	 */
	public void clearLoginInfo(){
		SharedPreferences sp = Global.getContext().getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
		SharedPreferences.Editor editor=sp.edit();
		editor.putString(USER_INFO,"");
		editor.commit();
	}
}
