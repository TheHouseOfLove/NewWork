package com.abl.RWD.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.abl.RWD.R;
import com.abl.RWD.activity.base.BaseNormalActivity;
import com.abl.RWD.common.Common;
import com.abl.RWD.component.CommonHeaderView;
import com.abl.RWD.component.ListOpenWordView;
import com.abl.RWD.component.ListTopItemView;
import com.abl.RWD.entity.PContractItemEntity;
import com.abl.RWD.listener.IBtnClickListener;
import com.abl.RWD.listener.IWordOpenListener;
import com.abl.RWD.util.FileUtil;
import com.abl.RWD.util.IntentUtils;
import com.abl.RWD.util.MyLog;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;

/**
 * Created by yas on 2016/10/13.
 */
public class SearchContractDetailActivity extends BaseNormalActivity {
    private CommonHeaderView mHeader;
    private ListTopItemView mTopView;
    private LinearLayout mFileView;
    private PContractItemEntity mEntity;
    private ArrayList<String> keyList=new ArrayList<String>();
    private ArrayList<String> valList=new ArrayList<String>();
    private File file;
    private String filename;
    private String savePath= Environment.getExternalStorageDirectory().getAbsolutePath();
    private static final int what=1;
    private Handler mHandler=new Handler(){
        public void handleMessage(Message msg) {
            hideLoadingDialog();
            switch (msg.what) {
                case what:
                    String fileName=(String) msg.obj;
                    String[] strs=fileName.split("\\.");
                    String str=strs[strs.length-1];
                    str=str.toLowerCase();
                    if(!new File(savePath+"/"+fileName).exists()){
                        showToast("文件下载失败！");
                    }else {
                        if("doc".equals(str)||"docx".equals(str)){
                            Intent intent= FileUtil.getWordFileIntent(savePath+"/"+fileName);
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
        setContentView(R.layout.activity_contract_detail);
        initExtras();
        initLayout();
    }

    @Override
    protected void handleRsp(Object obj, boolean isSucc, int errorCode, int seqNo, int src) {

    }

    private void initLayout() {
        mHeader= (CommonHeaderView) this.findViewById(R.id.header_contractDetail);
        mHeader.updateType(CommonHeaderView.TYPE_ONLY_LEFT_IMAGE);
        mHeader.setTitle("合同详情");
        mHeader.setHeaderClickListener(new IBtnClickListener() {
            @Override
            public void btnLeftClick() {
                finish();
            }
        });
        mTopView= (ListTopItemView) this.findViewById(R.id.topView);
        mFileView= (LinearLayout) this.findViewById(R.id.fileView);
        //合同名称，合同编号，合同金额，签订部门，签订日期，已收款，未收款
        keyList.add("合同名称");
        keyList.add("合同编号");
        keyList.add("合同金额");
        keyList.add("签订部门");
        keyList.add("签订日期");
        keyList.add("客户名称");
        keyList.add("已收款");
        keyList.add("未收款");
        if (mEntity!=null){
                valList.add(mEntity.HTMingCheng);
                valList.add(mEntity.HTBianHao);
                valList.add(mEntity.HTJinE);
                valList.add(mEntity.HTQianDingBuMen);
                valList.add(mEntity.HTQianDingRiQi.split(" ")[0]);
                 valList.add(mEntity.HTKuHuMingCheng.replace(",","/n/n"));
                valList.add(mEntity.LeiJiShouKuan);
                valList.add(mEntity.WeiShouKuan);
                if(mEntity.FuJian!=null&&mEntity.FuJian.size()>0){
                    for(int i=0;i<mEntity.FuJian.size();i++){
                        ListOpenWordView openView=new ListOpenWordView(this);
                        openView.setData(mEntity.FuJian.get(i));
                        openView.setOpenListener(mListener);
                        if (i!=mEntity.FuJian.size()-1){
                            openView.hideLine();
                        }
                        if(i==0){
                            openView.showTitle();
                        }
                        mFileView.addView(openView);
                    }
                }else{
                    ListOpenWordView openView=new ListOpenWordView(this);
                    openView.setEmptyData();
                    mFileView.addView(openView);
                }
        }else{
            ListOpenWordView openView=new ListOpenWordView(this);
            openView.setEmptyData();
            mFileView.addView(openView);
        }
        mTopView.setData(keyList,valList);
    }

    private void initExtras() {
        mEntity= (PContractItemEntity) getIntent().getSerializableExtra(IntentUtils.KEY_ENTITY);
    }
    private IWordOpenListener mListener=new IWordOpenListener() {

        @Override
        public void openListener(final String fileName, String path) {
            filename=fileName;
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
                    try {
                        str= URLEncoder.encode("合同文件", "utf-8");
                        name= URLEncoder.encode(fileName, "utf-8");
                    } catch (UnsupportedEncodingException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                    String url="http://60.208.75.182:9080/file/"+name;
                    MyLog.debug(TAG, "[IWordOpenListener]  url:" + url);
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
}
