package com.abl.RWD.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.abl.RWD.R;
import com.abl.RWD.activity.base.BaseNormalActivity;
import com.abl.RWD.adapter.AdapterDetailInfo;
import com.abl.RWD.common.Common;
import com.abl.RWD.component.BanLiItemView;
import com.abl.RWD.component.CommonHeaderView;
import com.abl.RWD.component.DetailBottomView;
import com.abl.RWD.component.DetailItemView;
import com.abl.RWD.component.OpenFileItemView;
import com.abl.RWD.component.fullrecyclerview.FullyLinearLayoutManager;
import com.abl.RWD.entity.PAttInfoItemEntity;
import com.abl.RWD.entity.PAttInfoSubItemEntity;
import com.abl.RWD.entity.PWorkDetailEntity;
import com.abl.RWD.entity.PWorkItemEntity;
import com.abl.RWD.entity.PYWInfoItemEntity;
import com.abl.RWD.entity.PYWInfoSubItemEntity;
import com.abl.RWD.http.ProtocalManager;
import com.abl.RWD.http.rsp.RspBanLiYiJianEntity;
import com.abl.RWD.http.rsp.RspReturnFlowBusinessEntity;
import com.abl.RWD.http.rsp.RspSaveDataEntity;
import com.abl.RWD.http.rsp.RspSubmitFlowBusinessEntity;
import com.abl.RWD.http.rsp.RspWorkDetailEntity;
import com.abl.RWD.listener.IBtnClickListener;
import com.abl.RWD.listener.IDetailBottomClickListener;
import com.abl.RWD.listener.IDetailItemClickListener;
import com.abl.RWD.listener.IWordOpenListener;
import com.abl.RWD.util.FileUtil;
import com.abl.RWD.util.IntentUtils;
import com.abl.RWD.util.ParseUtil;
import com.abl.common.util.SoftHideKeyBoardUtil;
import com.google.gson.Gson;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;

import static com.abl.RWD.R.id.mRecyclerView;

/**
 * Created by yas on 2017/11/13.
 * 事务详情页
 */

public class WorkDetailActivity extends BaseNormalActivity{
    private static final int REQ_SUBMIT_TYPE=1;  //提交方式选择（同意、退回）
    private static final int REQ_ACCEPTER=2;     //接收人
    private CommonHeaderView mHeader;
    private LinearLayout layoutItems;
    private LinearLayout layoutFiles;
    private LinearLayout layoutAdvice;  //办理意见
    private DetailBottomView mBottomView;
    private int mType;                  //已办待办类型
    private PWorkItemEntity entity;
    private String savePath= Environment.getExternalStorageDirectory().getAbsolutePath();
    private File file;
    private String filename;
    private static final int what=1;
    private String noTag;
    private PWorkDetailEntity mDetailEntity;
    private String mBLUserName;
    private String mBLUserID;
    private Handler mHandler=new Handler(){
        public void handleMessage(Message msg) {
            hideLoadingDialog();
            switch (msg.what) {
                case what:
                    String fileName=(String) msg.obj;
                    WorkDetailActivity.this.filename=fileName;
                    String[] strs=fileName.split("\\.");
                    String str=strs[strs.length-1];
                    str=str.toLowerCase();
                    if(!new File(savePath+"/"+fileName).exists()){
                        showToast("文件下载失败！");
                    }else {
                        if("doc".equals(str)||"docx".equals(str)){
                            Intent intent= FileUtil.getWordFileIntent(savePath + "/" + fileName);
                            startActivity(intent);
                        }else if("xls".equals(str)||"xlsx".equals(str)){
                            Intent intent=FileUtil.getExcelFileIntent(savePath+"/"+fileName);
                            startActivity(intent);
                        }else if("pdf".equals(str)){
                            Intent intent=FileUtil.getPdfFileIntent(savePath+"/"+fileName);
                            startActivity(intent);
                        }
                    }
                    break;
                default:
                    showToast("文件下载失败！");
                    break;
            }

        };
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transaction_detail);
        SoftHideKeyBoardUtil.assistActivity(this);
        initExtras();
        initLayout();
        ProtocalManager.getInstance().getWorkDetail(entity,getCallBack());
        ProtocalManager.getInstance().reqBanLiYiJian(entity.SLID,getCallBack());
        showLoading();
    }
    private void initExtras(){
        Intent intent=getIntent();
        mType=intent.getIntExtra(IntentUtils.KEY_TYPE, 0);
        entity=(PWorkItemEntity) intent.getSerializableExtra(IntentUtils.KEY_ENTITY);
//        entity=new PWorkItemEntity();
//        entity.FormName="测试";
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    private void initLayout() {
        mHeader= (CommonHeaderView) this.findViewById(R.id.header_detail);
        mHeader.updateType(CommonHeaderView.TYPE_ONLY_LEFT_IMAGE);
        mHeader.setTitle(entity.FormName);
        mHeader.setHeaderClickListener(new IBtnClickListener() {
            @Override
            public void btnLeftClick() {
                finish();
            }
        });
        layoutItems= (LinearLayout) this.findViewById(R.id.layout_items);
        layoutFiles= (LinearLayout) this.findViewById(R.id.layout_files);
        mBottomView= (DetailBottomView) this.findViewById(R.id.bottomView);
        mBottomView.setClickListener(mBottomClickListener);
        layoutAdvice= (LinearLayout) this.findViewById(R.id.layout_advice);
        if(this.mType== Common.TYPE_DAIBAN){
            mBottomView.setVisibility(View.VISIBLE);
        }else if (mType==Common.TYPE_YIBAN){
            mBottomView.setVisibility(View.GONE);
        }

//        mDetailEntity=getTestData();
//        addItems(mDetailEntity.YWInfo);
//        addDFiles(mDetailEntity.AttInfo);
    }
    private PWorkDetailEntity getTestData(){
        PWorkDetailEntity detailEntity=null;
        String str="{\"AttInfo\":[{\"Item\":[{\"AttName\":\"装修附件1.docx\",\"FJPath\":\"zhuangxiufujian1\",\"FieldName\":\"技术附件\"},{\"AttName\":\"软装附件2.docx\",\"FJPath\":\"ruanzhuangfujian2\",\"FieldName\":\"技术附件\"}]},{\"Item\":[{\"AttName\":\"大观园装修合同.jpg\",\"FJPath\":\"daguanyuan\",\"FieldName\":\"合同附件\"}]}],\"CirculationInfo\":[],\"ReferInfo\":[{\"nodeID\":\"e72e034f-4eed-45c9-b1df-1f68ee6faa0c\",\"nodeName\":\"部门负责人\",\"nodeTJType\":\"checkbox\",\"nodeTag\":\"3\",\"userBLType\":\"checkbox\",\"userchange\":\"否\",\"usersList\":\"A601CF4B-0451-4DA3-AE94-F58D3EFEA1DB|黄丹\"},{\"nodeID\":\"e72e034f-4eed-45c9-b1df-1f68ee6faa0c\",\"nodeName\":\"部门负责人\",\"nodeTJType\":\"checkbox\",\"nodeTag\":\"3\",\"userBLType\":\"checkbox\",\"userchange\":\"否\",\"usersList\":\"A601CF4B-0451-4DA3-AE94-F58D3EFEA1DB|黄丹,A601CF4B-0451-4DA3-AE94-F58D3EFEA1DB|黄丹\"}],\"ReturnInfo\":[{\"nodeID\":\"1e7a9990-434a-4e68-9ded-1320c2e40e86\",\"nodeName\":\"经办人\",\"nodeTag\":\"2\",\"usersList\":\"A601CF4B-0451-4DA3-AE94-F58D3EFEA1DB|黄丹\"},{\"nodeID\":\"1e7a9990-434a-4e68-9ded-1320c2e40e86\",\"nodeName\":\"经理\",\"nodeTag\":\"2\",\"usersList\":\"A601CF4B-0451-4DA3-AE94-F58D3EFEA1DB|ABL\"}],\"YWInfo\":[{\"Item\":[{\"CRName\":\"只读\",\"ControlType\":\"AutoCode\",\"ControlValue\":\"\",\"DataLength\":\"50\",\"DataType\":\"varchar\",\"Field\":\"key1\",\"FieldName\":\"单据编号\",\"FieldValue\":\"BXD17110001\",\"Required\":\"否\"},{\"CRName\":\"只读\",\"ControlType\":\"SelectDepartment\",\"ControlValue\":\"\",\"DataLength\":\"50\",\"DataType\":\"varchar\",\"Field\":\"key2\",\"FieldName\":\"费用所属部门\",\"FieldValue\":\"市场营销\",\"Required\":\"是\"},{\"CRName\":\"只读\",\"ControlType\":\"SelectDepartment\",\"ControlValue\":\"\",\"DataLength\":\"50\",\"DataType\":\"varchar\",\"Field\":\"key3\",\"FieldName\":\"费用所属公司\",\"FieldValue\":\"深圳分公司\",\"Required\":\"否\"},{\"CRName\":\"只读\",\"ControlType\":\"TextBox\",\"ControlValue\":\"\",\"DataLength\":\"4\",\"DataType\":\"int\",\"Field\":\"key4\",\"FieldName\":\"凭证张数\",\"FieldValue\":\"5\",\"Required\":\"是\"},{\"CRName\":\"只读\",\"ControlType\":\"Label\",\"ControlValue\":\"\",\"DataLength\":\"50\",\"DataType\":\"varchar\",\"Field\":\"key5\",\"FieldName\":\"报销类型\",\"FieldValue\":\"直接费用\",\"Required\":\"否\"},{\"CRName\":\"只读\",\"ControlType\":\"SelectUser\",\"ControlValue\":\"\",\"DataLength\":\"50\",\"DataType\":\"varchar\",\"Field\":\"key6\",\"FieldName\":\"报销人\",\"FieldValue\":\"黄丹\",\"Required\":\"是\"},{\"CRName\":\"只读\",\"ControlType\":\"SelectDepartment\",\"ControlValue\":\"\",\"DataLength\":\"50\",\"DataType\":\"varchar\",\"Field\":\"key7\",\"FieldName\":\"报销人部门\",\"FieldValue\":\"市场营销\",\"Required\":\"否\"},{\"CRName\":\"只读\",\"ControlType\":\"TextBox\",\"ControlValue\":\"\",\"DataLength\":\"50\",\"DataType\":\"varchar\",\"Field\":\"key8\",\"FieldName\":\"代报销人\",\"FieldValue\":\"黄丹\",\"Required\":\"是\"},{\"CRName\":\"\",\"ControlType\":\"HiddenInput\",\"ControlValue\":\"\",\"DataLength\":\"8\",\"DataType\":\"float\",\"Field\":\"key9\",\"FieldName\":\"报销金额\",\"FieldValue\":\"3070.6\",\"Required\":\"否\"},{\"CRName\":\"只读\",\"ControlType\":\"TextBox\",\"ControlValue\":\"\",\"DataLength\":\"100\",\"DataType\":\"varchar\",\"Field\":\"key10\",\"FieldName\":\"大写金额\",\"FieldValue\":\"叁仟零柒拾元陆角\",\"Required\":\"否\"},{\"CRName\":\"只读\",\"ControlType\":\"TextBox\",\"ControlValue\":\"\",\"DataLength\":\"8\",\"DataType\":\"float\",\"Field\":\"key11\",\"FieldName\":\"本次冲抵金额\",\"FieldValue\":\"0\",\"Required\":\"是\"},{\"CRName\":\"只读\",\"ControlType\":\"TextBox\",\"ControlValue\":\"\",\"DataLength\":\"8\",\"DataType\":\"float\",\"Field\":\"key12\",\"FieldName\":\"补领/退回\",\"FieldValue\":\"3070.6\",\"Required\":\"否\"},{\"CRName\":\"修改\",\"ControlType\":\"TextBox_1\",\"ControlValue\":\"\",\"DataLength\":\"8\",\"DataType\":\"float\",\"Field\":\"key13\",\"FieldName\":\"未还借款总金额\",\"FieldValue\":\"0\",\"Required\":\"否\"},{\"CRName\":\"修改\",\"ControlType\":\"TextBox_1\",\"ControlValue\":\"\",\"DataLength\":\"8\",\"DataType\":\"float\",\"Field\":\"key14\",\"FieldName\":\"未冲抵金额\",\"FieldValue\":\"0\",\"Required\":\"否\"},{\"CRName\":\"修改\",\"ControlType\":\"CalendarV2\",\"ControlValue\":\"\",\"DataLength\":\"50\",\"DataType\":\"varchar\",\"Field\":\"key15\",\"FieldName\":\"报销日期\",\"FieldValue\":\"2015-02-05\",\"Required\":\"否\"},{\"CRName\":\"修改\",\"ControlType\":\"TextBox_2\",\"ControlValue\":\"\",\"DataLength\":\"50\",\"DataType\":\"varchar\",\"Field\":\"key16\",\"FieldName\":\"备注\",\"FieldValue\":\"郑州鲁能花园一号院项目社区综合楼、样板间及住宅公区精装项目；珠海横琴、灏怡地产，财富中心公寓及loft部分精装修项目室内设计。\",\"Required\":\"否\"},{\"CRName\":\"只读\",\"ControlType\":\"TextBox_2\",\"ControlValue\":\"\",\"DataLength\":\"5000\",\"DataType\":\"varchar\",\"Field\":\"key17\",\"FieldName\":\"事项说明\",\"FieldValue\":\"郑州鲁能花园一号院项目社区综合楼、样板间及住宅公区精装项目，项目管理费1050元；珠海横琴、灏怡地产，财富中心公寓及loft部分精装修项目室内设计，邮寄费2020.6元。\",\"Required\":\"否\"},{\"CRName\":\"修改\",\"ControlType\":\"RadioButtonList\",\"ControlValue\":\"银行转账|yinhangzhuanzhang,现金支付|xianjinzhifu,汇票支付|huipiaozhifu\",\"DataLength\":\"50\",\"DataType\":\"varchar\",\"Field\":\"key18\",\"FieldName\":\"付款方式\",\"FieldValue\":\"\",\"Required\":\"是\"},{\"CRName\":\"修改\",\"ControlType\":\"CheckBoxList\",\"ControlValue\":\"取值1|quzhi1,取值2|quzhi2,取值3|quzhi3,取值4|quzhi4,取值5|quzhi5,取值6|quzhi6,取值7|quzhi7,取值8|quzhi8,取值9|quzhi9\",\"DataLength\":\"50\",\"DataType\":\"varchar\",\"Field\":\"key18\",\"FieldName\":\"多选框\",\"FieldValue\":\"quzhi1,quzhi2\",\"Required\":\"是\"},{\"CRName\":\"只读\",\"ControlType\":\"DropDownList\",\"ControlValue\":\"账户1|账户2|账户3|账户4\",\"DataLength\":\"200\",\"DataType\":\"varchar\",\"Field\":\"key19\",\"FieldName\":\"银行账户\",\"FieldValue\":\"\",\"Required\":\"否\"}]},{\"Item\":[{\"CRName\":\"只读\",\"ControlType\":\"Label\",\"ControlValue\":\"\",\"DataLength\":\"50\",\"DataType\":\"varchar\",\"Field\":\"key20\",\"FieldName\":\"一级类型\",\"FieldValue\":\"项目管理费\",\"Required\":\"否\"},{\"CRName\":\"只读\",\"ControlType\":\"Label\",\"ControlValue\":\"\",\"DataLength\":\"50\",\"DataType\":\"varchar\",\"Field\":\"key21\",\"FieldName\":\"二级类型\",\"FieldValue\":\"\",\"Required\":\"否\"},{\"CRName\":\"只读\",\"ControlType\":\"Label\",\"ControlValue\":\"\",\"DataLength\":\"50\",\"DataType\":\"varchar\",\"Field\":\"key22\",\"FieldName\":\"归属项目号\",\"FieldValue\":\"R2016015-0\",\"Required\":\"否\"},{\"CRName\":\"只读\",\"ControlType\":\"Label\",\"ControlValue\":\"\",\"DataLength\":\"500\",\"DataType\":\"varchar\",\"Field\":\"key23\",\"FieldName\":\"归属项目名称\",\"FieldValue\":\"郑州鲁能花园一号院项目社区综合楼、样板间及住宅公区精装项目\",\"Required\":\"否\"},{\"CRName\":\"只读\",\"ControlType\":\"Label\",\"ControlValue\":\"\",\"DataLength\":\"50\",\"DataType\":\"varchar\",\"Field\":\"key24\",\"FieldName\":\"子项目编号\",\"FieldValue\":\"R2016015-0-C\",\"Required\":\"否\"},{\"CRName\":\"只读\",\"ControlType\":\"Label\",\"ControlValue\":\"\",\"DataLength\":\"500\",\"DataType\":\"varchar\",\"Field\":\"key25\",\"FieldName\":\"子项目名称\",\"FieldValue\":\"C1户型\",\"Required\":\"否\"},{\"CRName\":\"只读\",\"ControlType\":\"Label\",\"ControlValue\":\"\",\"DataLength\":\"8\",\"DataType\":\"float\",\"Field\":\"key26\",\"FieldName\":\"价款（元）\",\"FieldValue\":\"1000\",\"Required\":\"否\"},{\"CRName\":\"只读\",\"ControlType\":\"Label\",\"ControlValue\":\"\",\"DataLength\":\"8\",\"DataType\":\"float\",\"Field\":\"key27\",\"FieldName\":\"增值税（元）\",\"FieldValue\":\"50\",\"Required\":\"否\"},{\"CRName\":\"只读\",\"ControlType\":\"Label\",\"ControlValue\":\"\",\"DataLength\":\"8\",\"DataType\":\"float\",\"Field\":\"key28\",\"FieldName\":\"金额（元）\",\"FieldValue\":\"1050\",\"Required\":\"否\"}]},{\"Item\":[{\"CRName\":\"只读\",\"ControlType\":\"Label\",\"ControlValue\":\"\",\"DataLength\":\"50\",\"DataType\":\"varchar\",\"Field\":\"key29\",\"FieldName\":\"一级类型\",\"FieldValue\":\"邮寄费\",\"Required\":\"否\"},{\"CRName\":\"只读\",\"ControlType\":\"Label\",\"ControlValue\":\"\",\"DataLength\":\"50\",\"DataType\":\"varchar\",\"Field\":\"key30\",\"FieldName\":\"二级类型\",\"FieldValue\":\"\",\"Required\":\"否\"},{\"CRName\":\"只读\",\"ControlType\":\"Label\",\"ControlValue\":\"\",\"DataLength\":\"50\",\"DataType\":\"varchar\",\"Field\":\"key31\",\"FieldName\":\"归属项目号\",\"FieldValue\":\"R2016014-0\",\"Required\":\"否\"},{\"CRName\":\"只读\",\"ControlType\":\"Label\",\"ControlValue\":\"\",\"DataLength\":\"500\",\"DataType\":\"varchar\",\"Field\":\"key32\",\"FieldName\":\"归属项目名称\",\"FieldValue\":\"珠海横琴.灏怡地产.财富中心公寓及loft部分精装修项目室内设计\",\"Required\":\"否\"},{\"CRName\":\"只读\",\"ControlType\":\"Label\",\"ControlValue\":\"\",\"DataLength\":\"50\",\"DataType\":\"varchar\",\"Field\":\"key33\",\"FieldName\":\"子项目编号\",\"FieldValue\":\"R2016014-0-F\",\"Required\":\"否\"},{\"CRName\":\"只读\",\"ControlType\":\"Label\",\"ControlValue\":\"\",\"DataLength\":\"500\",\"DataType\":\"varchar\",\"Field\":\"34\",\"FieldName\":\"子项目名称\",\"FieldValue\":\"LOFT户型3\",\"Required\":\"否\"},{\"CRName\":\"只读\",\"ControlType\":\"Label\",\"ControlValue\":\"\",\"DataLength\":\"8\",\"DataType\":\"float\",\"Field\":\"key35\",\"FieldName\":\"价款（元）\",\"FieldValue\":\"2000\",\"Required\":\"否\"},{\"CRName\":\"只读\",\"ControlType\":\"Label\",\"ControlValue\":\"\",\"DataLength\":\"8\",\"DataType\":\"float\",\"Field\":\"key36\",\"FieldName\":\"增值税（元）\",\"FieldValue\":\"20.6\",\"Required\":\"否\"},{\"CRName\":\"只读\",\"ControlType\":\"Label\",\"ControlValue\":\"\",\"DataLength\":\"8\",\"DataType\":\"float\",\"Field\":\"key37\",\"FieldName\":\"金额（元）\",\"FieldValue\":\"2020.6\",\"Required\":\"否\"}]}]}";
        Gson gson=new Gson();
        detailEntity=gson.fromJson(str,PWorkDetailEntity.class);
        return detailEntity;
    }
    private IDetailBottomClickListener mBottomClickListener=new IDetailBottomClickListener() {
        @Override
        public void typeClickListener() {
            //TODO 提交类型选择
            IntentUtils.starSubmitTypeSelectActivity(WorkDetailActivity.this,REQ_SUBMIT_TYPE,noTag);
        }

        @Override
        public void nextClickListener() {
            //TODO 接收人选择
            String str=mBottomView.getType();
            if ("同意".equals(str)) {
                IntentUtils.starNextAccepterActivity(WorkDetailActivity.this, mDetailEntity, 1, REQ_ACCEPTER);
            }else if (TextUtils.isEmpty(str)){
                showToast("请先选择提交方式！");
            }else{
                IntentUtils.starNextAccepterActivity(WorkDetailActivity.this, mDetailEntity, 2, REQ_ACCEPTER);
            }
        }

        @Override
        public void btnClickListener() {
            //TODO 提交按钮点击
            String str=mBottomView.getType();
            if ("同意".equals(str)) {
                saveChangeData();
            }else{
                returnFlowBusiness();
            }
        }

        @Override
        public void noNextListener() {
            showToast("请选择下一环节");
        }
    };

    @Override
    protected void handleRsp(Object obj, boolean isSucc, int errorCode, int seqNo, int src) {
        hideLoadingDialog();
        if (obj instanceof RspWorkDetailEntity){
            RspWorkDetailEntity rsp= (RspWorkDetailEntity) obj;
            if (rsp!=null&&isSucc){
                mDetailEntity=rsp.mEntity;
                addItems(rsp.mEntity.YWInfo);
                addDFiles(rsp.mEntity.AttInfo);
                if (rsp.mEntity.ReturnInfo!=null&&rsp.mEntity.ReturnInfo.size()>0) {
                    noTag = rsp.mEntity.ReturnInfo.get(0).nodeTag;
                }
            }
        }else if (obj instanceof RspBanLiYiJianEntity){
            RspBanLiYiJianEntity rsp= (RspBanLiYiJianEntity) obj;
            if (rsp!=null&&isSucc&&rsp.mEntity!=null&&rsp.mEntity.OpinionInfo!=null
                    &&rsp.mEntity.OpinionInfo.size()>0){
                for (int i=0;i<rsp.mEntity.OpinionInfo.size();i++){
                    BanLiItemView itemView=new BanLiItemView(WorkDetailActivity.this);
                    itemView.setMsg(rsp.mEntity.OpinionInfo.get(i));
                    layoutAdvice.addView(itemView);
                }
            }else{
                layoutAdvice.setVisibility(View.GONE);
            }
        }else if (obj instanceof RspSaveDataEntity){
            RspSaveDataEntity rsp= (RspSaveDataEntity) obj;
            if (rsp!=null&&isSucc&&rsp.mEntity!=null&&"Success".equals(rsp.mEntity.SaveData)){
                submitFlowBusiness();
            }else{
                //TODO 保存失败处理
                showToast("保存数据失败");
            }
        }else if (obj instanceof RspSubmitFlowBusinessEntity){
            RspSubmitFlowBusinessEntity rsp= (RspSubmitFlowBusinessEntity) obj;
            if(rsp!=null&&rsp.isSucc){
                showToast("请求成功！");
                finish();
            }else{
                showToast("请求失败！");
            }
        }else if (obj instanceof RspReturnFlowBusinessEntity){
            RspReturnFlowBusinessEntity rsp= (RspReturnFlowBusinessEntity) obj;
            if(rsp!=null&&rsp.isSucc){
                showToast("请求成功！");
                finish();
            }else{
                showToast("请求失败！");
            }
        }
    }

    /**
     * 修改数据
     */
    private void saveChangeData(){
        ProtocalManager.getInstance().saveData(getChangeParams(),entity.FormPKField,entity.FormDB,getCallBack());
        showLoading();
    }
    /**
     * 退回流程
     */
    private void returnFlowBusiness(){
        String opinion=mBottomView.getOpinion();
        String BLUserID=mDetailEntity.ReturnInfo.get(0).nodeID+"$$"+mBLUserID;
        try {
            opinion = URLEncoder.encode(opinion, "GBK");
        } catch (UnsupportedEncodingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        ProtocalManager.getInstance().returnFlowBusiness(entity, BLUserID, opinion,getCallBack());
        showLoading();
    }
    /**
     * 提交流程
     */
    private void submitFlowBusiness(){
        String opinion=mBottomView.getOpinion();
        String BLUserID=mDetailEntity.ReferInfo.get(0).nodeID+"$$"+mBLUserID;
        try {
            opinion = URLEncoder.encode(opinion, "GBK");
        } catch (UnsupportedEncodingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        ProtocalManager.getInstance().submitFlowBusiness(entity, BLUserID, opinion,getCallBack());
        showLoading();
    }

    /**
     * 获取修改的参数
     * @return
     */
    private String getChangeParams(){
        return "";
    }

    /**
     * 添加item
     */
    private void addItems(ArrayList<PYWInfoSubItemEntity> YWInfo){
        ArrayList<PYWInfoItemEntity> mList=ParseUtil.getDetailItemList(YWInfo);
        if (mList!=null&&mList.size()>0){
            layoutItems.removeAllViews();
            for (int i=0;i<mList.size();i++){
                DetailItemView itemView=new DetailItemView(WorkDetailActivity.this,mItemClickListener);
                itemView.setMsg(mList.get(i));
                layoutItems.addView(itemView);
            }
        }
    }
    /**
     * 添加附件view
     * @param files
     */
    private void addDFiles(ArrayList<PAttInfoSubItemEntity> files){
        if (files!=null&&files.size()>0){
            layoutFiles.setVisibility(View.VISIBLE);
            ArrayList<PAttInfoItemEntity>fileList =ParseUtil.getDetailAttItemList(files);
            for(int i=0;i<fileList.size();i++){
                OpenFileItemView openView=new OpenFileItemView(this);
                openView.setData(fileList.get(i));
                openView.setOpenListener(mListener);
                layoutFiles.addView(openView);
            }
        }else {
            layoutFiles.setVisibility(View.GONE);
        }
    }

    /**
     * item点击选择监听
     */
    private IDetailItemClickListener mItemClickListener=new IDetailItemClickListener() {
        @Override
        public void itemClick(TextView textView, String options) {
            //TODO 选择弹窗弹出
        }
    };
    private IWordOpenListener mListener=new IWordOpenListener() {

        @Override
        public void openListener(final String fileName,final String path) {
            // TODO Auto-generated method stub
            try {
                file=new File(savePath,fileName);
                if(!file.exists()){
                    file.getParentFile().mkdirs();
                    file.createNewFile();
                }
            } catch (Exception e){
                e.printStackTrace();
            }
            showLoading("文件正在下载中，请稍后。。。");
            new Thread() {
                public void run() {
                    String str="";
                    String name="";
                    String filePath="";
                    try {
                        str= URLEncoder.encode("合同文件", "utf-8");
                        name=URLEncoder.encode(fileName, "utf-8");
                        filePath=path.replaceAll("\\\\", "/");
                        filePath=URLEncoder.encode(filePath, "utf-8");
                    } catch (UnsupportedEncodingException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                    String url="http://192.168.0.102/file/"+filePath;
                    boolean is= FileUtil.saveFileFromURL(url, file);
                    Message message = new Message();
                    if(is){
                        message.what=what;
                        message.obj = fileName;
                    }
                    mHandler.sendMessage(message);
                };
            }.start();
        }
    };

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode!= Activity.RESULT_OK){
            return;
        }
        if (requestCode==REQ_SUBMIT_TYPE){
            String TJtype=data.getStringExtra("type");
            mBottomView.setType(TJtype);
        }else if (requestCode==REQ_ACCEPTER){
             mBLUserName=data.getStringExtra("name");
             mBLUserID=data.getStringExtra("id");
            mBottomView.setname(mBLUserName);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(new File(savePath+"/"+filename).exists()){
            boolean is=FileUtil.deleteFile(savePath+"/"+filename);
        }
    }
}
