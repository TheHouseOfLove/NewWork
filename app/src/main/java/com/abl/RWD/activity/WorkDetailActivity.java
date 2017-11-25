package com.abl.RWD.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ScrollView;

import com.abl.RWD.R;
import com.abl.RWD.activity.base.BaseNormalActivity;
import com.abl.RWD.adapter.AdapterDetailInfo;
import com.abl.RWD.common.Common;
import com.abl.RWD.component.BanLiItemView;
import com.abl.RWD.component.CommonHeaderView;
import com.abl.RWD.component.DetailBottomView;
import com.abl.RWD.component.OpenFileItemView;
import com.abl.RWD.component.fullrecyclerview.FullyLinearLayoutManager;
import com.abl.RWD.entity.PAttInfoItemEntity;
import com.abl.RWD.entity.PAttInfoSubItemEntity;
import com.abl.RWD.entity.PWorkItemEntity;
import com.abl.RWD.entity.PYWInfoItemEntity;
import com.abl.RWD.http.ProtocalManager;
import com.abl.RWD.http.rsp.RspBanLiYiJianEntity;
import com.abl.RWD.http.rsp.RspWorkDetailEntity;
import com.abl.RWD.listener.IBtnClickListener;
import com.abl.RWD.listener.IDetailBottomClickListener;
import com.abl.RWD.listener.IWordOpenListener;
import com.abl.RWD.util.FileUtil;
import com.abl.RWD.util.IntentUtils;
import com.abl.RWD.util.ParseUtil;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;

/**
 * Created by yas on 2017/11/13.
 * 事务详情页
 */

public class WorkDetailActivity extends BaseNormalActivity{
    private CommonHeaderView mHeader;
    private RecyclerView mRecyclerView;
    private LinearLayout layoutFiles;
    private LinearLayout layoutAdvice;  //办理意见
    private DetailBottomView mBottomView;
    private AdapterDetailInfo mAdapter;
    private int mType;
    private PWorkItemEntity entity;
    private String savePath= Environment.getExternalStorageDirectory().getAbsolutePath();
    private File file;
    private String filename;
    private static final int what=1;
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
        initExtras();
        initLayout();
        ProtocalManager.getInstance().getWorkDetail(entity,getCallBack());
        showLoading();
        ProtocalManager.getInstance().reqBanLiYiJian(entity.SLID,getCallBack());
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
        mRecyclerView= (RecyclerView) this.findViewById(R.id.recycler_items);
        FullyLinearLayoutManager manager=new FullyLinearLayoutManager(this);
        mRecyclerView.setLayoutManager(manager);
        layoutFiles= (LinearLayout) this.findViewById(R.id.layout_files);
        mBottomView= (DetailBottomView) this.findViewById(R.id.bottomView);
        mBottomView.setClickListener(mBottomClickListener);
        layoutAdvice= (LinearLayout) this.findViewById(R.id.layout_advice);
        if(this.mType== Common.TYPE_DAIBAN){
            mBottomView.setVisibility(View.VISIBLE);
        }else if (mType==Common.TYPE_YIBAN){
            mBottomView.setVisibility(View.GONE);
        }
        mRecyclerView.setFocusable(false);
    }
    private IDetailBottomClickListener mBottomClickListener=new IDetailBottomClickListener() {
        @Override
        public void typeClickListener() {
            //TODO 提交类型选择
            IntentUtils.starSubmitTypeSelectActivity(WorkDetailActivity.this,100,"");
        }

        @Override
        public void nextClickListener() {
            //TODO 接收人选择
            IntentUtils.starNextAccepterActivity(WorkDetailActivity.this,null,1,200);
        }

        @Override
        public void btnClickListener() {
            //TODO 提交按钮点击
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
                if (mAdapter==null) {
                    mAdapter = new AdapterDetailInfo(this, ParseUtil.getDetailItemList(rsp.mEntity.YWInfo));
                    mRecyclerView.setAdapter(mAdapter);
                }else{
                    mAdapter.reSetList(ParseUtil.getDetailItemList(rsp.mEntity.YWInfo));
                }
                addDFiles(rsp.mEntity.AttInfo);
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
        }
    }
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
    protected void onDestroy() {
        super.onDestroy();
        if(new File(savePath+"/"+filename).exists()){
            boolean is=FileUtil.deleteFile(savePath+"/"+filename);
        }
    }
}
