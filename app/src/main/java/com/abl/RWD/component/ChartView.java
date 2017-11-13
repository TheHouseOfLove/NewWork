package com.abl.RWD.component;

import android.content.Context;
import android.graphics.Color;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.abl.RWD.R;
import com.abl.RWD.entity.PYueDuHeTongItemEntiity;
import com.abl.RWD.entity.VThirdItemEntity;
import com.abl.RWD.util.MyLog;
import com.abl.RWD.util.ParseUtil;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;

import java.util.ArrayList;

/**
 * Created by yas on 2016/8/23.
 */
public class ChartView extends LinearLayout {
    private MyPieChart mPicChart; //饼图
    private LineChart mChart;
    private TextView txtTitle;
    private TextView txtInfo;
    private int mType;
    private final int FLAG_SET_DATA=0x100;
    public static final int TYPE_HETONG=1;
    public static final int TYPE_SHOUKUAN=2;
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case FLAG_SET_DATA:
                    // 加载数据
                    String[] strArr= (String[]) msg.obj;
                    if (strArr!=null&&strArr.length>0){
                        LineData data = getLineData(strArr);
                        mChart.setData(data);
                        // 刷新图表
                        mChart.invalidate();
                    }
                    break;
                default:
                    break;
            }
        }

    };
    public ChartView(Context context) {
        super(context);
        init();
    }

    public ChartView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public ChartView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    private void init() {
        LayoutInflater li= (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        li.inflate(R.layout.chart_view, this, true);
        initPieChart();
        initLineChart();

        txtTitle= (TextView) this.findViewById(R.id.text_title);
        txtInfo= (TextView) this.findViewById(R.id.text_info);
    }

    private void initLineChart() {
        mChart= (LineChart) this.findViewById(R.id.mLineChart1);
        /**
         * ====================1.初始化-自由配置===========================
         */
        // 是否在折线图上添加边框
        mChart.setDrawGridBackground(false);
        mChart.setDrawBorders(true);
        mChart.setDrawMarkerViews(false);
        // 设置右下角描述
        mChart.setDescription("");
        //设置透明度
        mChart.setAlpha(0.8f);
        //设置网格底下的那条线的颜色
        mChart.setBorderColor(Color.rgb(173, 218, 250));
        //设置高亮显示
//        mChart.setHighlightEnabled(true);
        //设置是否可以触摸，如为false，则不能拖动，缩放等
        mChart.setTouchEnabled(false);
        //设置是否可以拖拽
        mChart.setDragEnabled(false);
        //设置是否可以缩放
        mChart.setScaleEnabled(false);
        //设置是否能扩大扩小
        mChart.setPinchZoom(false);
        /**
         * ====================2.布局点添加数据-自由布局===========================
         */
        // 折线图的点，点击战士的布局和数据
//        MyMarkView mv = new MyMarkView(this);
//        mChart.setMarkerView(mv);
        /**
         * ====================3.x，y动画效果和刷新图表等===========================
         */
        //从X轴进入的动画
        mChart.animateX(4000);
        mChart.animateY(3000);   //从Y轴进入的动画
        mChart.animateXY(3000, 3000);    //从XY轴一起进入的动画
        //设置最小的缩放
        mChart.setScaleMinima(0.5f, 1f);
        Legend l = mChart.getLegend();
        l.setForm(Legend.LegendForm.LINE);  //设置图最下面显示的类型
        l.setTextSize(15);
        l.setTextColor(Color.parseColor("#555555"));
        l.setFormSize(150f);

        // 设置Y轴右边不显示数字
        mChart.getAxisRight().setEnabled(false);

        XAxis xAxis = mChart.getXAxis();
        // 设置X轴的数据显示在报表的下方
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setDrawLabels(true);
        xAxis.setAvoidFirstLastClipping(true);
        xAxis.setSpaceBetweenLabels(3);
//        xAxis.resetLabelsToSkip();
//        xAxis.setDrawAxisLine(true);
        // 设置不从X轴发出纵向直线
//        xAxis.setDrawGridLines(false);
    }

    private void initPieChart() {
        mPicChart= (MyPieChart) this.findViewById(R.id.mPieChart);
    }

    public void setData(VThirdItemEntity item, String year){
        int type=item.type;
        mType=type;
        String[] strs=year.split("-");
//		topView.setInfo(strs[0]+"合同信息查询");
        if (type==VThirdItemEntity.TYPE_HeTong){
            String yusuan=item.mEntity.NianDuHeTongYuSuan;
            String shiji=item.mEntity.NianDuHeTongShiJi;
            mPicChart.setData(Double.valueOf(yusuan), Double.valueOf(shiji));
            mPicChart.invalidate();
            txtTitle.setText(strs[0]+"年合同总体情况");
            txtInfo.setText(strs[0]+"年各部门完成情况");
        }else if (type==VThirdItemEntity.TYPE_SHOUKUAN){
            String yusuan=item.shouKuanEntity.NianDuShouKuanYuSuan;
            String shiji=item.shouKuanEntity.NianDuShouKuanShiJi;
            mPicChart.setData(Double.valueOf(yusuan), Double.valueOf(shiji));
            mPicChart.invalidate();
            txtTitle.setText(strs[0]+"年收款总体情况");
            txtInfo.setText(strs[0]+"年各部门完成情况");
        }
    }
    public void setData(final ArrayList<PYueDuHeTongItemEntiity> srcList, final int type, String year){
        MyLog.debug("dd","[setData] .............");
        if (srcList!=null&&srcList.size()>0) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    String[] strArr= ParseUtil.getYueduTeHongList(srcList,type);
                    Message msg =new Message();
                    msg.obj = strArr;//可以是基本类型，可以是对象，可以是List、map等；
                    msg.what=FLAG_SET_DATA;
                    mHandler.sendMessage(msg);
                }
            }).start();
        }
    }
    public void setType(int type){
        mType=type;
    }
    private LineData getLineData(String[] yList) {
        String[] xx = {"1", "2", "3", "4", "5", "6", "7", "8", "9","10","11","12"};
//        String[] yy = {"20", "80", "10", "60", "30", "70", "55", "22", "40"};
        String[] yy= yList;
        ArrayList<String> xVals = new ArrayList<String>();
        for (int i = 0; i < xx.length; i++) {
            xVals.add(xx[i]);
        }

        ArrayList<Entry> yVals = new ArrayList<Entry>();
        for (int i = 0; i < yy.length; i++) {
            yVals.add(new Entry(Float.parseFloat(yy[i]), i));
            MyLog.debug("dd","[getLineData]  i:"+yy[i]);
        }
        String str="每月收款";
        if (mType==VThirdItemEntity.TYPE_HeTong){
            str="每月合同";
        }else if (mType==VThirdItemEntity.TYPE_SHOUKUAN){
            str="每月收款";
        }
        LineDataSet set1 = new LineDataSet(yVals, str);
        set1.setDrawCubic(true);  //设置曲线为圆滑的线
        set1.setCubicIntensity(0.2f);
        set1.setDrawFilled(false);  //设置包括的范围区域填充颜色
        set1.setDrawCircles(false);  //设置有圆点
        set1.setLineWidth(2f);    //设置线的宽度
        set1.setCircleSize(5f);   //设置小圆的大小
        set1.setHighLightColor(Color.rgb(173, 218, 250));
        set1.setColor(Color.rgb(173, 218, 250));    //设置曲线的颜色
        set1.setValueTextSize(0);
        return new LineData(xVals, set1);
    }
}
