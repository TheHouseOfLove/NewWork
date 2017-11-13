package com.abl.RWD.component;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.abl.RWD.R;

/**
 * Created by yas on 2016/10/18.
 */
public class MyPieChart extends RelativeLayout {
    private ArcChartView mChartView;
    private TextView txtProportion;
    private TextView txtNumber;
    public MyPieChart(Context context) {
        super(context);
        init();
    }

    public MyPieChart(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        LayoutInflater li= (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        li.inflate(R.layout.my_picchart_view,this,true);
        mChartView= (ArcChartView) this.findViewById(R.id.charView);
        txtProportion= (TextView) this.findViewById(R.id.txt_proportion);
        txtNumber= (TextView) this.findViewById(R.id.txt_number);
    }
    public void setData(double total,double real){
        mChartView.setData(total, real);
        if (total==0){
            if (real==0){
                txtProportion.setText("0.00%");
            }else{
                txtProportion.setText("100%");
            }
        }else{
            double d=((real/total)*100);
            java.text.DecimalFormat df = new java.text.DecimalFormat("0.00");
            String str=df.format(d)+"%";
            txtProportion.setText(str);
        }
        java.text.DecimalFormat df2 = new java.text.DecimalFormat("0");
        txtNumber.setText("已完成 "+"\n"+df2.format(real)+" 万元");
    }
}
