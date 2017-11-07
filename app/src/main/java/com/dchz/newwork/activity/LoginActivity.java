package com.dchz.newwork.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Message;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.dchz.newwork.R;
import com.dchz.newwork.activity.base.BaseNormalActivity;
import com.dchz.newwork.http.ProtocalManager;
import com.dchz.newwork.util.IntentUtils;

import java.util.ArrayList;
import java.util.List;

/****
 * 登录页
 * @author wys
 *
 */
public class LoginActivity extends BaseNormalActivity implements OnClickListener, TextWatcher {
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
		etUserName.addTextChangedListener(this);
		etPwd=(EditText) findViewById(R.id.edit_pwd);
		etPwd.addTextChangedListener(this);
		btnLogin=(Button) findViewById(R.id.btn_login);
		btnLogin.setEnabled(false);
		btnLogin.setOnClickListener(this);
		imgReadPwd=(ImageView) findViewById(R.id.img_switch);
		imgReadPwd.setOnClickListener(this);
		text_update=(TextView) findViewById(R.id.text_update);
		text_update.setOnClickListener(this);
		server_adress=(TextView) findViewById(R.id.server_adress);
		server_adress.setOnClickListener(this);
		text_help=(TextView) findViewById(R.id.text_help);
		text_help.setOnClickListener(this);
	}


	@Override
	protected void handleRsp(Object obj, boolean isSucc, int errorCode, int seqNo, int src) {

	}

	@Override
	public void onClick(View view) {
		// TODO Auto-generated method stub
		if(view==btnLogin){
			IntentUtils.startMainActivity(this);
		}else if(view==imgReadPwd){
			ischecked=!ischecked;
			imgReadPwd.setSelected(ischecked);
		}else if(view==server_adress){
		}else if(view==text_help){
		}else if(view==text_update){
			   Uri uri = Uri.parse("http://www.dchzsoft.com/jnapk.html");
			   Intent intent = new Intent(Intent.ACTION_VIEW, uri);
			   startActivity(intent);
		}
	}

	private void initBtn(){
		String userName=etUserName.getText().toString();
		String pwd=etPwd.getText().toString();
		btnLogin.setEnabled(!TextUtils.isEmpty(userName)&&!TextUtils.isEmpty(pwd));
	}
	@Override
	public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

	}

	@Override
	public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

	}

	@Override
	public void afterTextChanged(Editable editable) {
		initBtn();
	}
}
