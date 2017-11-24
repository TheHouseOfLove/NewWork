package com.abl.RWD.activity;

import android.app.Activity;
import android.os.Bundle;
import android.widget.LinearLayout;


import com.abl.RWD.R;
import com.abl.RWD.activity.base.BaseActivity;
import com.abl.RWD.component.AcceptDepartmentView;
import com.abl.RWD.component.CommonHeaderView;
import com.abl.RWD.entity.PWorkDetailEntity;
import com.abl.RWD.listener.IBtnClickListener;
import com.abl.RWD.listener.IItemCheckedListener;
import com.abl.RWD.util.IntentUtils;
import com.google.gson.Gson;


public class NextAccepterActivity extends BaseActivity {
	private CommonHeaderView mHeader;
	private LinearLayout mAcceptDepartmentLayout;
	private int mType;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_next_accepter);
		initExtras();
		initLayout();
	}
	private void initExtras() {
		mType=getIntent().getIntExtra(IntentUtils.KEY_TYPE,0);
	}
	private void initLayout() {
		mHeader= (CommonHeaderView) this.findViewById(R.id.accpter_header);
		mHeader.updateType(CommonHeaderView.TYPE_LEFT_IMAGE_AND_RIGHT_TEXT);
		mHeader.setTitle("下一环节接收人");
		mHeader.setRightText("完成");
		mHeader.setHeaderClickListener(new IBtnClickListener() {
			@Override
			public void btnLeftClick() {
				finish();
			}

			@Override
			public void btnRightClick() {
				super.btnRightClick();
			}
		});
		mAcceptDepartmentLayout= (LinearLayout) this.findViewById(R.id.layout_AcceptDepartment);

		PWorkDetailEntity entity= getTestData();
		initDepartView(entity);
	}

	private void initDepartView(PWorkDetailEntity entity) {
		if (entity!=null){
			switch (mType){
				case 1:
					if (entity.ReferInfo!=null&&entity.ReferInfo.size()>0){
						for (int i=0;i<entity.ReferInfo.size();i++){
							AcceptDepartmentView itemView=new AcceptDepartmentView(this,itemCheckedListener);
							itemView.setReferInfo(entity.ReferInfo.get(i),i);
							mAcceptDepartmentLayout.addView(itemView);
						}
					}
					break;
				case 2:
					if (entity.ReturnInfo!=null&&entity.ReturnInfo.size()>0){
						for (int i=0;i<entity.ReturnInfo.size();i++){
							AcceptDepartmentView itemView=new AcceptDepartmentView(this,itemCheckedListener);
							itemView.setReturnInfo(entity.ReturnInfo.get(i),i);
							mAcceptDepartmentLayout.addView(itemView);
						}
					}
					break;
				default:
					break;
			}
		}

	}

	private IItemCheckedListener itemCheckedListener=new IItemCheckedListener() {
		@Override
		public void itemChecked(int pos) {
			for (int i=0;i<mAcceptDepartmentLayout.getChildCount();i++){
				if (i!=pos){
					AcceptDepartmentView itemView= (AcceptDepartmentView) mAcceptDepartmentLayout.getChildAt(i);
					itemView.cancelChecked();
				}
			}
		}
	};
	private PWorkDetailEntity getTestData(){
		PWorkDetailEntity detailEntity=null;
		String str="{\"AttInfo\":[{\"Item\":[{\"AttName\":\"装修附件1.docx\",\"FJPath\":\"zhuangxiufujian1\",\"FieldName\":\"技术附件\"},{\"AttName\":\"软装附件2.docx\",\"FJPath\":\"ruanzhuangfujian2\",\"FieldName\":\"技术附件\"}]},{\"Item\":[{\"AttName\":\"大观园装修合同.jpg\",\"FJPath\":\"daguanyuan\",\"FieldName\":\"合同附件\"}]}],\"CirculationInfo\":[],\"ReferInfo\":[{\"nodeID\":\"e72e034f-4eed-45c9-b1df-1f68ee6faa0c\",\"nodeName\":\"部门负责人\",\"nodeTJType\":\"checkbox\",\"nodeTag\":\"3\",\"userBLType\":\"checkbox\",\"userchange\":\"否\",\"usersList\":\"A601CF4B-0451-4DA3-AE94-F58D3EFEA1DB|黄丹\"}],\"ReturnInfo\":[{\"nodeID\":\"1e7a9990-434a-4e68-9ded-1320c2e40e86\",\"nodeName\":\"经办人\",\"nodeTag\":\"2\",\"usersList\":\"A601CF4B-0451-4DA3-AE94-F58D3EFEA1DB|黄丹\"}],\"YWInfo\":[{\"Item\":[{\"CRName\":\"只读\",\"ControlType\":\"AutoCode\",\"ControlValue\":\"\",\"DataLength\":\"50\",\"DataType\":\"varchar\",\"Field\":\"key1\",\"FieldName\":\"单据编号\",\"FieldValue\":\"BXD17110001\",\"Required\":\"否\"},{\"CRName\":\"只读\",\"ControlType\":\"SelectDepartment\",\"ControlValue\":\"\",\"DataLength\":\"50\",\"DataType\":\"varchar\",\"Field\":\"key2\",\"FieldName\":\"费用所属部门\",\"FieldValue\":\"市场营销\",\"Required\":\"是\"},{\"CRName\":\"只读\",\"ControlType\":\"SelectDepartment\",\"ControlValue\":\"\",\"DataLength\":\"50\",\"DataType\":\"varchar\",\"Field\":\"key3\",\"FieldName\":\"费用所属公司\",\"FieldValue\":\"深圳分公司\",\"Required\":\"否\"},{\"CRName\":\"只读\",\"ControlType\":\"TextBox\",\"ControlValue\":\"\",\"DataLength\":\"4\",\"DataType\":\"int\",\"Field\":\"key4\",\"FieldName\":\"凭证张数\",\"FieldValue\":\"5\",\"Required\":\"是\"},{\"CRName\":\"只读\",\"ControlType\":\"Label\",\"ControlValue\":\"\",\"DataLength\":\"50\",\"DataType\":\"varchar\",\"Field\":\"key5\",\"FieldName\":\"报销类型\",\"FieldValue\":\"直接费用\",\"Required\":\"否\"},{\"CRName\":\"只读\",\"ControlType\":\"SelectUser\",\"ControlValue\":\"\",\"DataLength\":\"50\",\"DataType\":\"varchar\",\"Field\":\"key6\",\"FieldName\":\"报销人\",\"FieldValue\":\"黄丹\",\"Required\":\"是\"},{\"CRName\":\"只读\",\"ControlType\":\"SelectDepartment\",\"ControlValue\":\"\",\"DataLength\":\"50\",\"DataType\":\"varchar\",\"Field\":\"key7\",\"FieldName\":\"报销人部门\",\"FieldValue\":\"市场营销\",\"Required\":\"否\"},{\"CRName\":\"只读\",\"ControlType\":\"TextBox\",\"ControlValue\":\"\",\"DataLength\":\"50\",\"DataType\":\"varchar\",\"Field\":\"key8\",\"FieldName\":\"代报销人\",\"FieldValue\":\"黄丹\",\"Required\":\"是\"},{\"CRName\":\"\",\"ControlType\":\"HiddenInput\",\"ControlValue\":\"\",\"DataLength\":\"8\",\"DataType\":\"float\",\"Field\":\"key9\",\"FieldName\":\"报销金额\",\"FieldValue\":\"3070.6\",\"Required\":\"否\"},{\"CRName\":\"只读\",\"ControlType\":\"TextBox\",\"ControlValue\":\"\",\"DataLength\":\"100\",\"DataType\":\"varchar\",\"Field\":\"key10\",\"FieldName\":\"大写金额\",\"FieldValue\":\"叁仟零柒拾元陆角\",\"Required\":\"否\"},{\"CRName\":\"只读\",\"ControlType\":\"TextBox\",\"ControlValue\":\"\",\"DataLength\":\"8\",\"DataType\":\"float\",\"Field\":\"key11\",\"FieldName\":\"本次冲抵金额\",\"FieldValue\":\"0\",\"Required\":\"是\"},{\"CRName\":\"只读\",\"ControlType\":\"TextBox\",\"ControlValue\":\"\",\"DataLength\":\"8\",\"DataType\":\"float\",\"Field\":\"key12\",\"FieldName\":\"补领/退回\",\"FieldValue\":\"3070.6\",\"Required\":\"否\"},{\"CRName\":\"修改\",\"ControlType\":\"TextBox_1\",\"ControlValue\":\"\",\"DataLength\":\"8\",\"DataType\":\"float\",\"Field\":\"key13\",\"FieldName\":\"未还借款总金额\",\"FieldValue\":\"0\",\"Required\":\"否\"},{\"CRName\":\"修改\",\"ControlType\":\"TextBox_1\",\"ControlValue\":\"\",\"DataLength\":\"8\",\"DataType\":\"float\",\"Field\":\"key14\",\"FieldName\":\"未冲抵金额\",\"FieldValue\":\"0\",\"Required\":\"否\"},{\"CRName\":\"修改\",\"ControlType\":\"CalendarV2\",\"ControlValue\":\"\",\"DataLength\":\"50\",\"DataType\":\"varchar\",\"Field\":\"key15\",\"FieldName\":\"报销日期\",\"FieldValue\":\"2015-02-05\",\"Required\":\"否\"},{\"CRName\":\"修改\",\"ControlType\":\"TextBox_2\",\"ControlValue\":\"\",\"DataLength\":\"50\",\"DataType\":\"varchar\",\"Field\":\"key16\",\"FieldName\":\"备注\",\"FieldValue\":\"郑州鲁能花园一号院项目社区综合楼、样板间及住宅公区精装项目；珠海横琴、灏怡地产，财富中心公寓及loft部分精装修项目室内设计。\",\"Required\":\"否\"},{\"CRName\":\"只读\",\"ControlType\":\"TextBox_2\",\"ControlValue\":\"\",\"DataLength\":\"5000\",\"DataType\":\"varchar\",\"Field\":\"key17\",\"FieldName\":\"事项说明\",\"FieldValue\":\"郑州鲁能花园一号院项目社区综合楼、样板间及住宅公区精装项目，项目管理费1050元；珠海横琴、灏怡地产，财富中心公寓及loft部分精装修项目室内设计，邮寄费2020.6元。\",\"Required\":\"否\"},{\"CRName\":\"修改\",\"ControlType\":\"RadioButtonList\",\"ControlValue\":\"银行转账|yinhangzhuanzhang,现金支付|xianjinzhifu,汇票支付|huipiaozhifu\",\"DataLength\":\"50\",\"DataType\":\"varchar\",\"Field\":\"key18\",\"FieldName\":\"付款方式\",\"FieldValue\":\"\",\"Required\":\"是\"},{\"CRName\":\"修改\",\"ControlType\":\"CheckBoxList\",\"ControlValue\":\"取值1|quzhi1,取值2|quzhi2,取值3|quzhi3,取值4|quzhi4,取值5|quzhi5,取值6|quzhi6,取值7|quzhi7,取值8|quzhi8,取值9|quzhi9\",\"DataLength\":\"50\",\"DataType\":\"varchar\",\"Field\":\"key18\",\"FieldName\":\"多选框\",\"FieldValue\":\"quzhi1,quzhi2\",\"Required\":\"是\"},{\"CRName\":\"只读\",\"ControlType\":\"DropDownList\",\"ControlValue\":\"账户1|账户2|账户3|账户4\",\"DataLength\":\"200\",\"DataType\":\"varchar\",\"Field\":\"key19\",\"FieldName\":\"银行账户\",\"FieldValue\":\"\",\"Required\":\"否\"}]},{\"Item\":[{\"CRName\":\"只读\",\"ControlType\":\"Label\",\"ControlValue\":\"\",\"DataLength\":\"50\",\"DataType\":\"varchar\",\"Field\":\"key20\",\"FieldName\":\"一级类型\",\"FieldValue\":\"项目管理费\",\"Required\":\"否\"},{\"CRName\":\"只读\",\"ControlType\":\"Label\",\"ControlValue\":\"\",\"DataLength\":\"50\",\"DataType\":\"varchar\",\"Field\":\"key21\",\"FieldName\":\"二级类型\",\"FieldValue\":\"\",\"Required\":\"否\"},{\"CRName\":\"只读\",\"ControlType\":\"Label\",\"ControlValue\":\"\",\"DataLength\":\"50\",\"DataType\":\"varchar\",\"Field\":\"key22\",\"FieldName\":\"归属项目号\",\"FieldValue\":\"R2016015-0\",\"Required\":\"否\"},{\"CRName\":\"只读\",\"ControlType\":\"Label\",\"ControlValue\":\"\",\"DataLength\":\"500\",\"DataType\":\"varchar\",\"Field\":\"key23\",\"FieldName\":\"归属项目名称\",\"FieldValue\":\"郑州鲁能花园一号院项目社区综合楼、样板间及住宅公区精装项目\",\"Required\":\"否\"},{\"CRName\":\"只读\",\"ControlType\":\"Label\",\"ControlValue\":\"\",\"DataLength\":\"50\",\"DataType\":\"varchar\",\"Field\":\"key24\",\"FieldName\":\"子项目编号\",\"FieldValue\":\"R2016015-0-C\",\"Required\":\"否\"},{\"CRName\":\"只读\",\"ControlType\":\"Label\",\"ControlValue\":\"\",\"DataLength\":\"500\",\"DataType\":\"varchar\",\"Field\":\"key25\",\"FieldName\":\"子项目名称\",\"FieldValue\":\"C1户型\",\"Required\":\"否\"},{\"CRName\":\"只读\",\"ControlType\":\"Label\",\"ControlValue\":\"\",\"DataLength\":\"8\",\"DataType\":\"float\",\"Field\":\"key26\",\"FieldName\":\"价款（元）\",\"FieldValue\":\"1000\",\"Required\":\"否\"},{\"CRName\":\"只读\",\"ControlType\":\"Label\",\"ControlValue\":\"\",\"DataLength\":\"8\",\"DataType\":\"float\",\"Field\":\"key27\",\"FieldName\":\"增值税（元）\",\"FieldValue\":\"50\",\"Required\":\"否\"},{\"CRName\":\"只读\",\"ControlType\":\"Label\",\"ControlValue\":\"\",\"DataLength\":\"8\",\"DataType\":\"float\",\"Field\":\"key28\",\"FieldName\":\"金额（元）\",\"FieldValue\":\"1050\",\"Required\":\"否\"}]},{\"Item\":[{\"CRName\":\"只读\",\"ControlType\":\"Label\",\"ControlValue\":\"\",\"DataLength\":\"50\",\"DataType\":\"varchar\",\"Field\":\"key29\",\"FieldName\":\"一级类型\",\"FieldValue\":\"邮寄费\",\"Required\":\"否\"},{\"CRName\":\"只读\",\"ControlType\":\"Label\",\"ControlValue\":\"\",\"DataLength\":\"50\",\"DataType\":\"varchar\",\"Field\":\"key30\",\"FieldName\":\"二级类型\",\"FieldValue\":\"\",\"Required\":\"否\"},{\"CRName\":\"只读\",\"ControlType\":\"Label\",\"ControlValue\":\"\",\"DataLength\":\"50\",\"DataType\":\"varchar\",\"Field\":\"key31\",\"FieldName\":\"归属项目号\",\"FieldValue\":\"R2016014-0\",\"Required\":\"否\"},{\"CRName\":\"只读\",\"ControlType\":\"Label\",\"ControlValue\":\"\",\"DataLength\":\"500\",\"DataType\":\"varchar\",\"Field\":\"key32\",\"FieldName\":\"归属项目名称\",\"FieldValue\":\"珠海横琴.灏怡地产.财富中心公寓及loft部分精装修项目室内设计\",\"Required\":\"否\"},{\"CRName\":\"只读\",\"ControlType\":\"Label\",\"ControlValue\":\"\",\"DataLength\":\"50\",\"DataType\":\"varchar\",\"Field\":\"key33\",\"FieldName\":\"子项目编号\",\"FieldValue\":\"R2016014-0-F\",\"Required\":\"否\"},{\"CRName\":\"只读\",\"ControlType\":\"Label\",\"ControlValue\":\"\",\"DataLength\":\"500\",\"DataType\":\"varchar\",\"Field\":\"34\",\"FieldName\":\"子项目名称\",\"FieldValue\":\"LOFT户型3\",\"Required\":\"否\"},{\"CRName\":\"只读\",\"ControlType\":\"Label\",\"ControlValue\":\"\",\"DataLength\":\"8\",\"DataType\":\"float\",\"Field\":\"key35\",\"FieldName\":\"价款（元）\",\"FieldValue\":\"2000\",\"Required\":\"否\"},{\"CRName\":\"只读\",\"ControlType\":\"Label\",\"ControlValue\":\"\",\"DataLength\":\"8\",\"DataType\":\"float\",\"Field\":\"key36\",\"FieldName\":\"增值税（元）\",\"FieldValue\":\"20.6\",\"Required\":\"否\"},{\"CRName\":\"只读\",\"ControlType\":\"Label\",\"ControlValue\":\"\",\"DataLength\":\"8\",\"DataType\":\"float\",\"Field\":\"key37\",\"FieldName\":\"金额（元）\",\"FieldValue\":\"2020.6\",\"Required\":\"否\"}]}]}";
		Gson gson=new Gson();
		detailEntity=gson.fromJson(str,PWorkDetailEntity.class);
		return detailEntity;
	}
}
