package com.abl.RWD.component;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * Created by aaa
 * Date  15-4-8
 */
public class ArcChartView extends View {

    private Paint arcPaint;
    //弧线的尺寸
    private RectF arcRect;
    private double total;
    private double real;
    public ArcChartView(Context context) {
        this(context, null);
    }

    public ArcChartView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public ArcChartView(Context context, AttributeSet attrs, int defStyle) {

        super(context, attrs, defStyle);
    }

    /**
     * 通用的初始化方法
     *
     * @param context
     * @param attrs
     */
    private void init(Context context, AttributeSet attrs) {
        arcPaint = new Paint();
        arcPaint.setColor(Color.BLACK);
        //设置不填充
        // arcPaint.setStyle(Paint.Style.STROKE);
        //反锯齿
        arcPaint.setAntiAlias(true);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //清除内容
        canvas.drawColor(Color.WHITE);
        //画弧线
        //第一个参数，弧线尺寸范围
        //第二个参数，其实角度,第三个参数，弧线的角度
        //第四个参数，是否连接中心点,true，形成饼图的效果
        //第五个参数，paint
//        canvas.drawArc(arcRect,45,90,true,arcPaint);

        //画绿色半圆
//        arcPaint.setColor(Color.GREEN);
//        canvas.drawArc(arcRect, 0, 180, true, arcPaint);
//        //oe6ab8
//        //画蓝色上半部分的圆
//        arcPaint.setColor(Color.parseColor("#FF0E6AB8"));
//        canvas.drawArc(arcRect, 180, 180, true, arcPaint);
        int width=getWidth();
        int height=getHeight();
        int padingLeft=getPaddingLeft();
        int padingTop=getPaddingTop();
        arcRect = new RectF(padingLeft, padingTop, width, height);
        if (real>=total){
            if (real==0){
                arcPaint.setColor(Color.parseColor("#FBC300"));
                canvas.drawArc(arcRect, 0, 360, true, arcPaint);
            }else {
                arcPaint.setColor(Color.parseColor("#ADDAFA"));
                canvas.drawArc(arcRect, 0, 360, true, arcPaint);
            }
        }else{
            int start= (int) ((real*360)/total);
            Log.d("ddddd", "[onDraw]  start:" + start + "   real:" + real + "  total:" + total);

            arcPaint.setColor(Color.parseColor("#FBC300"));
            canvas.drawArc(arcRect, 0, 360 - start, true, arcPaint);

            arcPaint.setColor(Color.parseColor("#ADDAFA"));
            canvas.drawArc(arcRect, 360 - start, start, true, arcPaint);
        }
    }
    public void setData(double total,double real){
        this.total=total;
        this.real=real;
        postInvalidate();
    }
}
