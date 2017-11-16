package com.abl.RWD.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {

	
	public static final int TYPE_LAST=1;
	public static final int TYPE_NEXT=2;
	public static String getDate(){
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");//设置日期格式
		String date=df.format(new Date());// new Date()为获取当前系统时间
		return date;
	}
    /*** 
     * 日期月份减一个月 
     *  
     * @param datetime 
     *            日期(2014-11) 
     * @return 2014-10 
     */  
    public static String changeMonth(String datetime, int mType) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        try {  
            date = sdf.parse(datetime);  
        } catch (ParseException e) {
            e.printStackTrace();  
        }  
        Calendar cl = Calendar.getInstance();
        cl.setTime(date);  
        if(mType==TYPE_LAST){
        	cl.add(Calendar.MONTH, -1);
        }else if(mType==TYPE_NEXT){
        	cl.add(Calendar.MONTH, 1);
        }
        date = cl.getTime();  
        return sdf.format(date);  
    }  
    /*** 
     * 日期年份减一个月 
     *  
     * @param datetime 
     *            日期(2014-11) 
     * @return 2014-10 
     */  
    public static String changeYear(String datetime, int mType) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        try {  
            date = sdf.parse(datetime);  
        } catch (ParseException e) {
            e.printStackTrace();  
        }  
        Calendar cl = Calendar.getInstance();
        cl.setTime(date);  
        if(mType==TYPE_LAST){
        	cl.add(Calendar.YEAR, -1);
        }else if(mType==TYPE_NEXT){
        	cl.add(Calendar.YEAR, 1);
        }
        date = cl.getTime();  
        return sdf.format(date);  
    }  
    /*** 
     * 日期减一天、加一天 
     *  
     * @param option 
     *            传入类型 pro：日期减一天，next：日期加一天 
     * @param _date 
     *            2014-11-24 
     * @return 减一天：2014-11-23或(加一天：2014-11-25) 
     */  
    public static String checkOption(String _date, int mType) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar cl = Calendar.getInstance();
        Date date = null;
  
        try {  
            date = (Date) sdf.parse(_date);
        } catch (ParseException e) {
            e.printStackTrace();  
        }  
        cl.setTime(date);  
        if (mType==TYPE_LAST) {  
            // 时间减一天  
            cl.add(Calendar.DAY_OF_MONTH, -1);
  
        } else if (mType==TYPE_NEXT) {  
            // 时间加一天  
            cl.add(Calendar.DAY_OF_YEAR, 1);
        } else {  
            // do nothing  
        }  
        date = cl.getTime();  
        return sdf.format(date);  
    }  
	public static String DateToStr(Date date) {
        if (date!=null) {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            String str = format.format(date);
            return str;
        }
        return "";
	}

	public static String getCurrentTime(long date) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		String str = format.format(new Date(date));
		return str;
	}

	public static Date StrToDate(String str) {
        str=str.replaceAll("-","/");
		SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date date = null;
		try {
			date = format.parse(str);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}
}