package com.abl.RWD.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.abl.RWD.R;
import com.abl.RWD.activity.base.BaseNormalActivity;
import com.abl.RWD.controller.LoginController;
import com.abl.RWD.entity.VReadPwdEntity;
import com.abl.RWD.file.SharePreLoginUtil;
import com.abl.RWD.file.SharePreReadPwdUtil;
import com.abl.RWD.http.ProtocalManager;
import com.abl.RWD.http.rsp.RspLoginEntity;
import com.abl.RWD.util.IntentUtils;

import java.util.ArrayList;
import java.util.List;

/****
 * 登录页
 * @author wys
 *
 */
public class LoginActivity extends BaseNormalActivity implements OnClickListener {
	private final int FLAG_LOAGIN=0x100;
	private final int FLAG_UPDATE=0x101;
	private EditText etUserName;
	private EditText etPwd;
	private Button btnLogin;
	private ImageView imgReadPwd;
	private List<Integer> mReqList = new ArrayList<Integer>();
	private boolean ischecked;
	private TextView server_adress;
	private TextView text_help;
	private TextView text_update;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		initLayout();
	}
	
	private void initLayout() {
		// TODO Auto-generated method stub
		etUserName=(EditText) findViewById(R.id.edit_userName);
		etPwd=(EditText) findViewById(R.id.edit_pwd);
		btnLogin=(Button) findViewById(R.id.btn_login);
		btnLogin.setOnClickListener(this);
		imgReadPwd=(ImageView) findViewById(R.id.img_switch);
		imgReadPwd.setOnClickListener(this);
		text_update=(TextView) findViewById(R.id.text_update);
		text_update.setOnClickListener(this);
		server_adress=(TextView) findViewById(R.id.server_adress);
		server_adress.setOnClickListener(this);
		text_help=(TextView) findViewById(R.id.text_help);
		text_help.setOnClickListener(this);

		VReadPwdEntity entity= SharePreReadPwdUtil.loadUserInfo();
		if(entity!=null){
			ischecked=entity.isChecked;
			imgReadPwd.setSelected(ischecked);
			if(ischecked){
				etUserName.setText(entity.userName);
				etPwd.setText(entity.pwd);
			}
		}
		if (LoginController.getInstance().isLogin()){
			IntentUtils.startMainActivity(this);
			finish();
		}
	}


	@Override
	protected void handleRsp(Object obj, boolean isSucc, int errorCode, int seqNo, int src) {
		hideLoadingDialog();
		if (obj instanceof RspLoginEntity){
			RspLoginEntity rsp= (RspLoginEntity) obj;
			if (rsp!=null&&isSucc){
				showToast("登录成功");
				LoginController.getInstance().updateLoginInfo(rsp.mEntity);
//				SharePreLoginUtil.saveLoginInfo(rsp.mEntity);
				IntentUtils.startMainActivity(this);
			}else{
				showToast("网络错误!  errorCode:"+errorCode);
			}
		}
	}

	@Override
	public void onClick(View view) {
		// TODO Auto-generated method stub
		if(view==btnLogin){
//			IntentUtils.startMainActivity(this);
			login();
		}else if(view==imgReadPwd){
			ischecked=!ischecked;
			imgReadPwd.setSelected(ischecked);
		}else if(view==server_adress){
		}else if(view==text_help){
		}else if(view==text_update){
		}
	}

	private void login() {
		String userName=etUserName.getText().toString();
		String pwd=etPwd.getText().toString();
		VReadPwdEntity entity=new VReadPwdEntity();
		entity.isChecked=ischecked;
		entity.pwd=pwd;
		entity.userName=userName;
		SharePreReadPwdUtil.saveUserInfo(entity);
		ProtocalManager.getInstance().login(userName,pwd,getCallBack());
		showLoading("正在登录...");
	}

	private void initBtn(){
		String userName=etUserName.getText().toString();
//		String pwd=etPwd.getText().toString();
//		btnLogin.setEnabled(!TextUtils.isEmpty(userName)&&!TextUtils.isEmpty(pwd));
		btnLogin.setEnabled(!TextUtils.isEmpty(userName));
	}

}
