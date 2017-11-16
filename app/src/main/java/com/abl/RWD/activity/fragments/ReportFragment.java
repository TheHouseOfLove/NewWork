package com.abl.RWD.activity.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.abl.RWD.R;
import com.abl.RWD.activity.base.BaseFragment;
import com.abl.RWD.adapter.AdapterReport;
import com.abl.RWD.component.ChartView;
import com.abl.RWD.component.HeaderSelectView;
import com.abl.RWD.component.ReportSubView;
import com.abl.RWD.entity.VThirdItemEntity;
import com.abl.RWD.file.SharePreLoginUtil;
import com.abl.RWD.http.ProtocalManager;
import com.abl.RWD.http.rsp.RspMonthlyContractEntity;
import com.abl.RWD.http.rsp.RspMonthlyPaymentEntity;
import com.abl.RWD.http.rsp.RspQuarterlyContractEntity;
import com.abl.RWD.http.rsp.RspQuarterlyPaymentEntity;
import com.abl.RWD.listener.IReportSubClickListener;
import com.abl.RWD.listener.ITabChangeListener;
import com.abl.RWD.msglist.ListViewEmptyView;
import com.abl.RWD.util.DateUtil;
import com.abl.RWD.util.ParseUtil;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;

import static com.abl.RWD.R.id.mMsgPage;

/**
 * Created by yas on 2017/11/6.
 */

public class ReportFragment extends BaseFragment {
    private final int TYPE_HETONG=1;
    private final int TYPE_SHOUKUAN=2;
    private int mType=TYPE_HETONG;
    private HeaderSelectView mHeader;
    private XRecyclerView mRecyclerView;
    private AdapterReport mAdapter;
    private ReportSubView mSubView;
    private ChartView mChartView;
    private String year;
    private String LeaderRole;
    private String IsBMLeader;
    private String BMID;
    @Override
    public int getLayoutRes() {
        return R.layout.fragment_report;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        year= DateUtil.getDate();
        LeaderRole=SharePreLoginUtil.loadLoginInfo().LeaderRole;
        IsBMLeader=SharePreLoginUtil.loadLoginInfo().IsBMLeader;
        BMID= SharePreLoginUtil.loadLoginInfo().BMID;
    }

    @Override
    public void initViews(View rootView) {
        mHeader=rootView.findViewById(R.id.header_report);
        mHeader.addTabChangeListener(mTabChangeListener);
        mHeader.setTabTitle("合同", "收款");
        mRecyclerView=rootView.findViewById(R.id.mRecyclerView);
        mRecyclerView.setLoadingMoreEnabled(false);
        mRecyclerView.setPullRefreshEnabled(false);
        mSubView=rootView.findViewById(R.id.subView);
        mSubView.setClickListener(mReportSubClickListener);
        mChartView=new ChartView(getActivity());
        mRecyclerView.addHeaderView(mChartView);
        ProtocalManager.getInstance().getQuarterlyContract(year,getCallBack());
        ProtocalManager.getInstance().getMonthlyContract(year,getCallBack());
        showLoading("正在获取数据。。。");
    }

    @Override
    protected void handleRsp(Object obj, boolean isSucc, int errorCode, int seqNo, int src) {
        hideLoading();
        if (obj instanceof RspQuarterlyContractEntity){
            RspQuarterlyContractEntity rsp= (RspQuarterlyContractEntity) obj;
            if(rsp!=null&&rsp.isSucc){
                ArrayList<VThirdItemEntity> mList=new ArrayList<>();
                if("False".equals(LeaderRole)){
                    if("True".equals(IsBMLeader)){
                        mList= ParseUtil.getThirdList(rsp.mEntity.JiDuHeTongInfo,BMID);
                    }
                }else{
                    mList=ParseUtil.getThirdList(rsp.mEntity.JiDuHeTongInfo);
                }
                if (mList!=null&&mList.size()>1) {
                    VThirdItemEntity item = mList.get(mList.size() - 1);
                    mChartView.setData(item, year);
                    if (mAdapter == null) {
                        mAdapter = new AdapterReport(getActivity(),mList);
                        mRecyclerView.setAdapter(mAdapter);
                    } else {
                        mAdapter.reSetList(mList);
                    }
                }else{
                    //TODO 空view设置
//                    mMsgPage.setEmpty(ListViewEmptyView.TYPE_ENROLL);
                }
            }else{
                showToast("网络异常");
            }
        }else if (obj instanceof RspMonthlyContractEntity){
            RspMonthlyContractEntity rsp= (RspMonthlyContractEntity) obj;
            if (rsp!=null&&rsp.mEntity!=null){
                mChartView.setData(rsp.mEntity.YueDuHeTongInfo,ChartView.TYPE_HETONG,year);
            }
        }else if (obj instanceof RspQuarterlyPaymentEntity){
            RspQuarterlyPaymentEntity rsp1=(RspQuarterlyPaymentEntity) obj;
            if(rsp1!=null&&rsp1.isSucc){
                ArrayList<VThirdItemEntity> mList=new ArrayList<VThirdItemEntity>();
                if("False".equals(LeaderRole)){
                    if("True".equals(IsBMLeader)){
                        mList=ParseUtil.getThirdList1(rsp1.mEntity.JiDuShouKuanInfo, BMID);
                    }
                }else{
                    mList=ParseUtil.getThirdList1(rsp1.mEntity.JiDuShouKuanInfo);
                }
                if (mList!=null&&mList.size()>1) {
                    VThirdItemEntity item = mList.get(mList.size() - 1);
                    mChartView.setData(item, year);
                    if (mAdapter == null) {
                        mAdapter = new AdapterReport(getActivity(),mList);
                        mRecyclerView.setAdapter(mAdapter);
                    } else {
                        mAdapter.reSetList(mList);
                    }
                }else {
                    //TODO 空view设置
                }
            }else{
                showToast("网络异常");
            }
        }else if (obj instanceof RspMonthlyPaymentEntity){
            RspMonthlyPaymentEntity rsp3= (RspMonthlyPaymentEntity) obj;
            if (rsp3!=null&&rsp3.mEntity!=null){
                mChartView.setData(rsp3.mEntity.YueDuShouKuanInfo,ChartView.TYPE_SHOUKUAN,year);
            }
        }
    }

    /**
     * 合同、收款tab切换监听
     */
    private ITabChangeListener mTabChangeListener=new ITabChangeListener() {
        @Override
        public void leftTabClick() {
            ProtocalManager.getInstance().getQuarterlyContract(year,getCallBack());
            ProtocalManager.getInstance().getMonthlyContract(year,getCallBack());
            showLoading("正在获取数据。。。");
            mType=TYPE_HETONG;
            mChartView.setType(mType);
        }

        @Override
        public void rightTabClick() {
            ProtocalManager.getInstance().getQuarterlyPayment(year,getCallBack());
            ProtocalManager.getInstance().getMonthlyPayment(year,getCallBack());
            showLoading("正在获取数据。。。");
            mType=TYPE_SHOUKUAN;
            mChartView.setType(mType);
        }
    };
    /**
     * 年份切换回调
     */
    private IReportSubClickListener mReportSubClickListener=new IReportSubClickListener() {

        @Override
        public void thistYearClickListener() {
            // TODO Auto-generated method stub
            year=DateUtil.getDate();
            if(mType==TYPE_HETONG){
                ProtocalManager.getInstance().getQuarterlyContract(year,getCallBack());
                ProtocalManager.getInstance().getMonthlyContract(year,getCallBack());
            }else if(mType==TYPE_SHOUKUAN){
                ProtocalManager.getInstance().getQuarterlyPayment(year,getCallBack());
                ProtocalManager.getInstance().getMonthlyPayment(year,getCallBack());
            }
            showLoading("正在获取数据。。。");
        }

        @Override
        public void nextYearClickListener() {
            // TODO Auto-generated method stub
            year=DateUtil.changeYear(year, DateUtil.TYPE_NEXT);
            if(mType==TYPE_HETONG){
                ProtocalManager.getInstance().getQuarterlyContract(year,getCallBack());
                ProtocalManager.getInstance().getMonthlyContract(year,getCallBack());
            }else if(mType==TYPE_SHOUKUAN){
                ProtocalManager.getInstance().getQuarterlyPayment(year,getCallBack());
                ProtocalManager.getInstance().getMonthlyPayment(year,getCallBack());
            }
            showLoading("正在获取数据。。。");
        }

        @Override
        public void lastYearClickListener() {
            // TODO Auto-generated method stub
            year=DateUtil.changeYear(year, DateUtil.TYPE_LAST);
            if(mType==TYPE_HETONG){
                ProtocalManager.getInstance().getQuarterlyContract(year,getCallBack());
                ProtocalManager.getInstance().getMonthlyContract(year,getCallBack());
            }else if(mType==TYPE_SHOUKUAN){
                ProtocalManager.getInstance().getQuarterlyPayment(year,getCallBack());
                ProtocalManager.getInstance().getMonthlyPayment(year,getCallBack());
            }
            showLoading("正在获取数据。。。");
        }
    };


}
