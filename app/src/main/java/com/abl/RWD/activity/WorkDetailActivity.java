package com.abl.RWD.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.abl.RWD.R;
import com.abl.RWD.activity.base.BaseNormalActivity;
import com.abl.RWD.activity.dialog.MDateSelectorDialog;
import com.abl.RWD.activity.dialog.SingleWheelSelectorDialog;
import com.abl.RWD.common.Common;
import com.abl.RWD.component.BanLiItemView;
import com.abl.RWD.component.CommonHeaderView;
import com.abl.RWD.component.DetailBottomView;
import com.abl.RWD.component.DetailItemView;
import com.abl.RWD.component.OpenFileItemView;
import com.abl.RWD.entity.PAttInfoItemEntity;
import com.abl.RWD.entity.PAttInfoSubItemEntity;
import com.abl.RWD.entity.PWorkDetailEntity;
import com.abl.RWD.entity.PWorkItemEntity;
import com.abl.RWD.entity.PYWInfoItemEntity;
import com.abl.RWD.entity.PYWInfoSubItemEntity;
import com.abl.RWD.entity.VDateEntity;
import com.abl.RWD.entity.VDetailSelectorItemEntity;
import com.abl.RWD.http.ProtocalManager;
import com.abl.RWD.http.rsp.RspBanLiYiJianEntity;
import com.abl.RWD.http.rsp.RspReturnFlowBusinessEntity;
import com.abl.RWD.http.rsp.RspSaveDataEntity;
import com.abl.RWD.http.rsp.RspSubmitFlowBusinessEntity;
import com.abl.RWD.http.rsp.RspWorkDetailEntity;
import com.abl.RWD.listener.IBtnClickListener;
import com.abl.RWD.listener.IDatePickerListener;
import com.abl.RWD.listener.IDetailBottomClickListener;
import com.abl.RWD.listener.IDetailItemClickListener;
import com.abl.RWD.listener.IWheelViewSelectedListener;
import com.abl.RWD.listener.IWordOpenListener;
import com.abl.RWD.util.FileUtil;
import com.abl.RWD.util.IntentUtils;
import com.abl.RWD.util.MyLog;
import com.abl.RWD.util.ParseUtil;
import com.abl.common.util.SoftHideKeyBoardUtil;
import com.google.gson.Gson;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;

/**
 * Created by yas on 2017/11/13.
 * 事务详情页
 */

public class WorkDetailActivity extends BaseNormalActivity{
    private static final int REQ_SUBMIT_TYPE=1;  //提交方式选择（同意、退回）
    private static final int REQ_ACCEPTER=2;     //接收人
    private static final int REQ_CHECKBOX=3;     //多选页面
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
    private SingleWheelSelectorDialog mDialog;
    private MDateSelectorDialog mDateDialog;
    private Handler mHandler=new Handler(){
        public void handleMessage(Message msg) {
            hideLoadingDialog();
            switch (msg.what) {
                case what:
                    String fileName=(String) msg.obj;
                    WorkDetailActivity.this.filename=fileName;
//                    String[] strs=fileName.split("\\.");
//                    String str=strs[strs.length-1];
//                    str=str.toLowerCase();
                    File file=new File(savePath+"/"+fileName);
                    if(!file.exists()){
                        showToast("文件下载失败！");
                    }else {
//                        if("doc".equals(str)||"docx".equals(str)){
//                            Intent intent= FileUtil.getWordFileIntent(savePath + "/" + fileName);
//                            startActivity(intent);
//                        }else if("xls".equals(str)||"xlsx".equals(str)){
//                            Intent intent=FileUtil.getExcelFileIntent(savePath+"/"+fileName);
//                            startActivity(intent);
//                        }else if("pdf".equals(str)){
//                            Intent intent=FileUtil.getPdfFileIntent(savePath+"/"+fileName);
//                            startActivity(intent);
//                        }
                        try {
                            Intent intent= FileUtil.openFile(file);
                            startActivity(intent);
                        }catch (Exception e){
                            showToast("为安装支持该文件格式的应用！");
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
            if ("退回".equals(str)) {
                IntentUtils.starNextAccepterActivity(WorkDetailActivity.this, mDetailEntity, 2, REQ_ACCEPTER);
            }else if ("同意".equals(str)){
                IntentUtils.starNextAccepterActivity(WorkDetailActivity.this, mDetailEntity, 1, REQ_ACCEPTER);
            }else{
                showToast("请先选择提交方式！");
            }
        }

        @Override
        public void btnClickListener() {
            //TODO 提交按钮点击
//            String str=mBottomView.getType();
//            if ("同意".equals(str)) {
                saveChangeData();
//            }else{
//                returnFlowBusiness();
//            }
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
                String str=mBottomView.getType();
                if ("同意".equals(str)) {
                    submitFlowBusiness();
                }else{
                    returnFlowBusiness();
                }
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
     * 选择窗弹出
     * @param itemView
     * @param options
     */
    private void showDateDialog(final DetailItemView itemView){
        hideDateDialog();
        mDateDialog= new MDateSelectorDialog(this, R.style.MyDialogBg);
        mDateDialog.setIDatePickerListener(new IDatePickerListener() {
            @Override
            public void onPickerClick(VDateEntity vEntity) {
                itemView.seDate(vEntity);
            }
        });
        mDateDialog.show();
    }
    private void hideDateDialog(){
        if (mDateDialog!=null){
            mDateDialog.dismiss();
            mDateDialog=null;
        }
    }
    /**
     * 选择窗弹出
     * @param itemView
     * @param options
     */
    private void showDialog(final DetailItemView itemView, String options){
        hideDialog();
        mDialog= new SingleWheelSelectorDialog(this, R.style.MyDialogBg);
        mDialog.setSelectListener(new IWheelViewSelectedListener() {
            @Override
            public void select(VDetailSelectorItemEntity itemEntity) {
                itemView.setParam(itemEntity);
            }
        });
        mDialog.show();
        mDialog.setData(options);
    }
    private void hideDialog(){
        if (mDialog!=null){
            mDialog.dismiss();
            mDialog=null;
        }
    }
    /**
     * 修改数据
     */
    private void saveChangeData(){
        String str=getChangeParams();
        MyLog.debug(TAG,"[saveChangeData] str:"+str);
        ProtocalManager.getInstance().saveData(str,entity.FormPKField,entity.FormDB,getCallBack());
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
        int count=layoutItems.getChildCount();
       StringBuilder builder=new StringBuilder();
        builder.append( "\""+entity.FormPKField+"\":\"" + entity.YWID +"\"");
        for (int i=0;i<count;i++){
            View view=layoutItems.getChildAt(i);
            if (view instanceof DetailItemView){
                DetailItemView itemView= (DetailItemView) view;
                String str=itemView.getParam();
                if ("empty".equals(str)){
                    showToast("有必填项未填写！");
                    return "";
                }
                if (!TextUtils.isEmpty(str)){
//                    if (builder.length()==0){
//                        builder.append(str);
//                    }else{
                        builder.append(","+str);
//                    }
                }
            }
        }
        if (!TextUtils.isEmpty(builder.toString())) {
            String str="{\"" + entity.FormTable + "\":[{" + builder + "}]}";
            return str;
        }
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
                DetailItemView itemView=new DetailItemView(WorkDetailActivity.this,mItemClickListener,mType);
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

    private DetailItemView checkBoxItemView;
    /**
     * item点击选择监听
     */
    private IDetailItemClickListener mItemClickListener=new IDetailItemClickListener() {

        @Override
        public void itemRadioClick(DetailItemView itemView, String options) {
            showDialog(itemView,options);
        }

        @Override
        public void itemCheckBoxClick(DetailItemView itemView, String options) {
            //TODO 多选页面
            checkBoxItemView=itemView;
            IntentUtils.startDetailCheckBoxActivity(WorkDetailActivity.this,options,REQ_CHECKBOX);
        }

        @Override
        public void itemCalendarClick(DetailItemView itemView) {
            //TODO 时间选择
            showDateDialog(itemView);
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
//                    String url="http://192.168.0.102/file/"+filePath;
                    String url="http://218.17.223.19:88/file/"+filePath;
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
        }else if (requestCode==REQ_CHECKBOX){
            ArrayList<VDetailSelectorItemEntity> mList= (ArrayList<VDetailSelectorItemEntity>) data.getSerializableExtra(IntentUtils.KEY_ENTITY);
            checkBoxItemView.setParam(mList);
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
