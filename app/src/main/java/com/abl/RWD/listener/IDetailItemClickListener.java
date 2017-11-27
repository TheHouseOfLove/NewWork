package com.abl.RWD.listener;

import android.widget.TextView;

/**
 * Created by yas on 2017/11/22.
 */

public interface IDetailItemClickListener {
   /**
    * 流程详情点击选择回调
    * @param textView   点击的item对应的textView控件
    * @param options    供选择的选项值
    */
   void itemClick(TextView textView,String options);
}
