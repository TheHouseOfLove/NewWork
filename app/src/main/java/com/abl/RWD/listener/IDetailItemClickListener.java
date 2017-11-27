package com.abl.RWD.listener;


import com.abl.RWD.component.DetailItemView;

/**
 * Created by yas on 2017/11/22.
 */

public interface IDetailItemClickListener {
   /**
    * 流程详情点击选择回调
    * @param itemView   点击的item对应的控件
    * @param options    供选择的选项值
    */
   void itemClick(DetailItemView itemView, String options);
}
