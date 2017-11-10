package com.dchz.newwork.component;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.dchz.newwork.R;
import com.dchz.newwork.common.Common;

import java.util.List;

public class ListTopItemView extends LinearLayout {
	private TextView text_item1_key;
	private TextView text_item1_value;
	private TextView text_item2_key;
	private TextView text_item2_value;
	private TextView text_item3_key;
	private TextView text_item3_value;
	private TextView text_item4_key;
	private TextView text_item4_value;
	private TextView text_item5_key;
	private TextView text_item5_value;
	private TextView text_item6_key;
	private TextView text_item6_value;
	private TextView text_item7_key;
	private TextView text_item7_value;
	private TextView text_item8_key;
	private TextView text_item8_value;
	private TextView text_item9_key;
	private TextView text_item9_value;
	private TextView text_item10_key;
	private TextView text_item10_value;
	private TextView text_item11_key;
	private TextView text_item11_value;
	private TextView text_item12_key;
	private TextView text_item12_value;
	private TextView text_item13_key;
	private TextView text_item13_value;
	private TextView text_item14_key;
	private TextView text_item14_value;
	private TextView text_item15_key;
	private TextView text_item15_value;
	private TextView[] keys;
	private TextView[] values;

	private View line01;
	private View line02;
	private View line03;
	private View line04;
	private View line05;
	private View line06;
	private View line07;
	private View line08;
	private View line09;
	private View line10;
	private View line11;
	private View line12;
	private View line13;
	private View line14;
	private View line15;
	private View[] lines;

	private RelativeLayout item_top;

	public ListTopItemView(Context context) {
		super(context);
		init();
	}

	public ListTopItemView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();
	}

	public ListTopItemView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		init();
	}

	private void init() {
		// TODO Auto-generated method stub
		LayoutInflater li = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		li.inflate(R.layout.list_top_item, this, true);
		this.text_item1_key=(TextView) this.findViewById(R.id.text_item1_key);
		this.text_item1_value=(TextView) this.findViewById(R.id.text_item1_value);
		this.text_item2_key=(TextView) this.findViewById(R.id.text_item2_key);
		this.text_item2_value=(TextView) this.findViewById(R.id.text_item2_value);
		this.text_item3_key=(TextView) this.findViewById(R.id.text_item3_key);
		this.text_item3_value=(TextView) this.findViewById(R.id.text_item3_value);
		this.text_item4_key=(TextView) this.findViewById(R.id.text_item4_key);
		this.text_item4_value=(TextView) this.findViewById(R.id.text_item4_value);
		this.text_item5_key=(TextView) this.findViewById(R.id.text_item5_key);
		this.text_item5_value=(TextView) this.findViewById(R.id.text_item5_value);
		this.text_item6_key=(TextView) this.findViewById(R.id.text_item6_key);
		this.text_item6_value=(TextView) this.findViewById(R.id.text_item6_value);
		this.text_item7_key=(TextView) this.findViewById(R.id.text_item7_key);
		this.text_item7_value=(TextView) this.findViewById(R.id.text_item7_value);
		this.text_item8_key=(TextView) this.findViewById(R.id.text_item8_key);
		this.text_item8_value=(TextView) this.findViewById(R.id.text_item8_value);
		this.text_item9_key=(TextView) this.findViewById(R.id.text_item9_key);
		this.text_item9_value=(TextView) this.findViewById(R.id.text_item9_value);
		this.text_item10_key=(TextView) this.findViewById(R.id.text_item10_key);
		this.text_item10_value=(TextView) this.findViewById(R.id.text_item10_value);

		this.text_item11_key=(TextView) this.findViewById(R.id.text_item11_key);
		this.text_item11_value=(TextView) this.findViewById(R.id.text_item11_value);
		this.text_item12_key=(TextView) this.findViewById(R.id.text_item12_key);
		this.text_item12_value=(TextView) this.findViewById(R.id.text_item12_value);
		this.text_item13_key=(TextView) this.findViewById(R.id.text_item13_key);
		this.text_item13_value=(TextView) this.findViewById(R.id.text_item13_value);
		this.text_item14_key=(TextView) this.findViewById(R.id.text_item14_key);
		this.text_item14_value=(TextView) this.findViewById(R.id.text_item14_value);
		this.text_item15_key=(TextView) this.findViewById(R.id.text_item15_key);
		this.text_item15_value=(TextView) this.findViewById(R.id.text_item15_value);

		
		item_top=(RelativeLayout) this.findViewById(R.id.item_top);
		
		keys=new TextView[]{text_item1_key,text_item2_key,text_item3_key,text_item4_key,
				text_item5_key,text_item6_key,text_item7_key,text_item8_key,text_item9_key,text_item10_key,
				text_item11_key,text_item12_key,text_item13_key,text_item14_key,text_item15_key};
		values=new TextView[]{text_item1_value,text_item2_value,text_item3_value,text_item4_value,
				text_item5_value,text_item6_value,text_item7_value,text_item8_value,text_item9_value,text_item10_value,
				text_item11_value,text_item12_value,text_item13_value,text_item14_value,text_item15_value};

		this.line01=this.findViewById(R.id.line01);
		this.line02=this.findViewById(R.id.line02);
		this.line03=this.findViewById(R.id.line03);
		this.line04=this.findViewById(R.id.line04);
		this.line05=this.findViewById(R.id.line05);
		this.line06=this.findViewById(R.id.line06);
		this.line07=this.findViewById(R.id.line07);
		this.line08=this.findViewById(R.id.line08);
		this.line09=this.findViewById(R.id.line09);
		this.line10=this.findViewById(R.id.line10);
		this.line11=this.findViewById(R.id.line11);
		this.line12=this.findViewById(R.id.line12);
		this.line13=this.findViewById(R.id.line13);
		this.line14=this.findViewById(R.id.line14);
		this.line15=this.findViewById(R.id.line15);
		line01.setVisibility(View.GONE);
		line02.setVisibility(View.GONE);
		line03.setVisibility(View.GONE);
		line04.setVisibility(View.GONE);
		line05.setVisibility(View.GONE);
		line06.setVisibility(View.GONE);
		line07.setVisibility(View.GONE);
		line08.setVisibility(View.GONE);
		line09.setVisibility(View.GONE);
		line10.setVisibility(View.GONE);
		line11.setVisibility(View.GONE);
		line12.setVisibility(View.GONE);
		line13.setVisibility(View.GONE);
		line14.setVisibility(View.GONE);
		line15.setVisibility(View.GONE);
		lines=new View[]{line01,line02,line03,line04,line05,line06,line07,line08,line09,line10,line11,line12,line13,line14,line15};
	}
	
//	public void showBottom(){
//		item_top.setVisibility(View.VISIBLE);
//	}
	public void setData(String type, List<String> strs){
		if(strs!=null&&strs.size()>0){
			for(int i=0;i<strs.size();i++){
				keys[i].setVisibility(View.VISIBLE);
				values[i].setVisibility(View.VISIBLE);
				lines[i].setVisibility(View.VISIBLE);
				values[i].setText(strs.get(i));
			}
		}
		setKey(type);
	}
	public void setData(List<String> key, List<String> value){
		if(key!=null&&key.size()>0){
			for(int i=0;i<key.size();i++){
				keys[i].setVisibility(View.VISIBLE);
				values[i].setVisibility(View.VISIBLE);
				keys[i].setText(key.get(i));
				lines[i].setVisibility(View.VISIBLE);
				values[i].setText(value.get(i));
			}
		}
	}
	private void setKey(String type){
		if(Common.FAPIAO.equals(type)){
			text_item1_key.setText("发票金额");
			text_item2_key.setText("发票类型");
			text_item3_key.setText("合同编号");
			text_item4_key.setText("开票公司");
			text_item5_key.setText("合同名称");
		}else if(Common.FEIYONG.equals(type)){
			text_item1_key.setText("一级类型");
			text_item2_key.setText("二级类型");
			text_item3_key.setText("费用日期");
			text_item4_key.setText("归属项目号");
			text_item5_key.setText("归属项目名称");
			text_item6_key.setText("项目归属部门");
			text_item7_key.setText("金额（元）");
			text_item8_key.setText("费用描述");
		}else if(Common.FENBAO.equals(type)){
			text_item1_key.setText("分包方名称");
			text_item2_key.setText("单位地址");
			text_item3_key.setText("业绩");
			text_item4_key.setText("企业法人");
			text_item5_key.setText("是否长期合作的对象");
			text_item6_key.setText("联系人");
			text_item7_key.setText("联系电话");
		}else if(Common.JIEKUAN.equals(type)){
			text_item1_key.setText("借款类别");
			text_item2_key.setText("用途");
			text_item3_key.setText("项目编号");
			text_item4_key.setText("项目名称");
			text_item5_key.setText("借款事由");
			text_item6_key.setVisibility(View.GONE);
		}else if(Common.QINGJIA.equals(type)){
			text_item1_key.setText("本次请假");
			text_item2_key.setText("请假时间");
			text_item3_key.setText("当前项目状况");
		}else if(Common.TOUBIAO.equals(type)){
			text_item1_key.setText("保证金类型");
			text_item2_key.setText("担保金额（元）");
			text_item3_key.setText("支付方式");
			text_item4_key.setText("项目名称");
			text_item5_key.setText("申请内容");
		}else if(Common.YINGFUHT.equals(type)){
			text_item1_key.setText("分包合同编号");
			text_item2_key.setText("分包合同名称");
			text_item3_key.setText("分包方");
			text_item4_key.setText("结算方式");
			text_item5_key.setText("分包内容");
		}else if(Common.YINGSHOUHT.equals(type)){
			text_item1_key.setText("合同编号");
			text_item2_key.setText("合同名称");
			text_item3_key.setText("客户名称");
			text_item4_key.setText("主专业");
			text_item5_key.setText("工程投资");
			text_item6_key.setText("结算方式");
			text_item7_key.setText("设计内容");
		}else if(Common.GAIZHANG.equals(type)){
			text_item1_key.setText("项目名称");
			text_item2_key.setText("盖章用途");
			text_item3_key.setText("盖章签约单位");
			text_item4_key.setText("盖章文件");
			text_item5_key.setText("盖章合同编号");
			text_item6_key.setText("盖章份数");
			text_item7_key.setText("盖章是否归档");
		}else if(Common.FAREN.equals(type)){
			text_item1_key.setText("项目名称");
			text_item2_key.setText("项目地点");
			text_item3_key.setText("项目招标人");
			text_item4_key.setText("授权投标责任");
			text_item5_key.setText("项目投标估算");
			text_item6_key.setText("项目专业类别");
			text_item7_key.setText("项目时间要求");
		}else if(Common.XIANGMUXIADA.equals(type)){
			text_item1_key.setText("项目名称");
			text_item2_key.setText("阶段名称");
			text_item3_key.setText("下达日期");
			text_item4_key.setText("完成日期");
			text_item5_key.setText("承担部门");
		}else if(Common.GONGCHENG.equals(type)){
			text_item1_key.setText("分包合同名称");
			text_item2_key.setText("分包方名称");
			text_item3_key.setText("分包合同编号");
			text_item4_key.setText("支付金额");
		}else if (Common.GONGZHANGJIECHU.equals(type)){
			text_item1_key.setText("事由");
			text_item2_key.setText("备注");
		}else if (Common.GUDINGZICHAN.equals(type)){
			text_item1_key.setText("固定资产名称");
			text_item2_key.setText("规格型号");
			text_item3_key.setText("数量");
			text_item4_key.setText("金额（元）");
			text_item5_key.setText("说明");
		}else if (Common.DIZHIYIHAOPIN.equals(type)){
			text_item1_key.setText("名称");
			text_item2_key.setText("规格");
			text_item3_key.setText("数量");
			text_item4_key.setText("金额（元）");
			text_item5_key.setText("供应商");
		}else if (Common.TOUBIAOFEIYONG.equals(type)){
			text_item1_key.setText("工程名称");
			text_item2_key.setText("工程编码");
			text_item3_key.setText("工程进度");
			text_item4_key.setText("建设单位名称");
			text_item5_key.setText("合同金额（元）");
			text_item6_key.setText("已收款额（元）");
			text_item7_key.setText("前期已付款（元）");
			text_item8_key.setText("本次付款（元)");
			text_item9_key.setText("累计付款（元）");
			text_item10_key.setText("备注");

		}else if (Common.CHENGBAOFEI.equals(type)){
			text_item1_key.setText("项目编号");
			text_item2_key.setText("项目名称");
			text_item3_key.setText("收入金额");
			text_item4_key.setText("抵减金额");
			text_item5_key.setText("可提取收入");
			text_item6_key.setText("财务凭证号");
			text_item7_key.setText("提取比例（%）");
			text_item8_key.setText("本次净交");
			text_item9_key.setText("本次应提");
			text_item10_key.setText("前研经费");
			text_item11_key.setText("固定资产");
			text_item12_key.setText("审图费");
			text_item13_key.setText("培训费");
			text_item14_key.setText("税费");
			text_item15_key.setText("本次实提");

		}
		else {
			//错误处理
		}
	}
}
