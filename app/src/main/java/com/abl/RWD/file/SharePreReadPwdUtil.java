package com.abl.RWD.file;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

import com.abl.RWD.common.Global;
import com.abl.RWD.entity.VReadPwdEntity;


/***
 * LoginInfo信息
 * @date 2015/07/13
 */
public class SharePreReadPwdUtil {
	private final String TAG = "SharePreReadPwdUtil";
	private static final String userName="userName";
	private static final String pwd="pwd";
	private static final String isChecked="isChecked";
	private final static String FILE_NAME = "ReadPwdInfo_FILE";
	
	public static void saveUserInfo(VReadPwdEntity entity){
		if(entity!=null){
			SharedPreferences sp = Global.getContext().getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
			Editor editor = sp.edit();
			editor.putString(userName, entity.userName);
			editor.putString(pwd, entity.pwd);
			editor.putBoolean(isChecked, entity.isChecked);
			editor.commit();
		}
	}
	
	public static VReadPwdEntity loadUserInfo(){
		VReadPwdEntity entity=new VReadPwdEntity();
		SharedPreferences sp = Global.getContext().getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
		entity.userName = sp.getString(userName, "");
		entity.pwd = sp.getString(pwd, "");
		entity.isChecked=sp.getBoolean(isChecked, false);
		return entity;
		
	}
}
