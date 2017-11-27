package com.abl.RWD.listener;


import com.abl.RWD.component.DetailItemView;

/**
 * Created by yas on 2017/11/22.
 */

public interface IDetailItemClickListener {
   /**
    * 流程详情单选item点击回调
    * @param itemView   点击的item对应的控件
    * @param options    供选择的选项值
    */
   void itemRadioClick(DetailItemView itemView, String options);

   /**
    * 流程详情多选item点击回调
    * @param itemView
    * @param options
    */
   void itemCheckBoxClick(DetailItemView itemView, String options);
   /**
    * 流程详情时间选择item点击回调
    * @param itemView
    */
   void itemCalendarClick(DetailItemView itemView);
}
