package com.lyn.utils;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;

import java.security.Timestamp;
import java.sql.Time;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @author jannal 2015-12-05
 */
public class DateUtils {

	
	  private static final String YYYY_MM_DD = "yyyy-MM-dd";
	  
	  private static final 	String YYYY_MM_DD_HH_MM_SS_FORMAT = "yyyy-MM-dd HH:mm:ss";

      private static final  String YYYY_MM_DD_HH_MM_SS_SSS_FORMAT = "yyyy-MM-dd HH:mm:ss.SSS";


	/**
     * 得到当前的yyyy-MM-dd
     * @return
     * add by jannal
     */
    public static final String getCurrentDayDefalutFormatt() {
    	return formatDateToString(new Date(),YYYY_MM_DD);
    }
    
    /**
     * 将日期转化为指定的格式
     * @param date
     * @param format
     * @return
     */
    public static final String formatDateToString(Date date,String format){
        if(date==null){
            return null;
        }
    	SimpleDateFormat sdf = new SimpleDateFormat(format);
    	return sdf.format(date);
    }
    

	 /**
    * 得到当前时间
    * @return yyyy-MM-dd HH:mm:ss 格式
    */
   public static final String getCurrentDayTime24Formatt() {
       return DateTransformString(YYYY_MM_DD_HH_MM_SS_FORMAT);
   }

   /**
  * 得到当前时间
  * @return yyyy-MM-dd HH:mm:ss 格式
  */
 public static final String getCurrentDayTime24SSSFormatt() {
     return DateTransformString(YYYY_MM_DD_HH_MM_SS_SSS_FORMAT);
 }
 
   /**
    * 得到当前时间：指定日期格式的字符串
    * @param format
    * @return
    */
	public static String DateTransformString(String format) {
		return formatDateToString(new Date(), format);
	}
	

    /***
     * 得到前一天的当前时刻
     * @return
     * add by jannal
     */
    public static final String getYesterDay(String format) {
        Calendar calendar = Calendar.getInstance();// 此时打印它获取的是系统当前时间
        calendar.add(Calendar.DATE, -1); // 得到前一天
        String yestedayDate = new SimpleDateFormat(format).format(calendar.getTime());
        return yestedayDate;
    }
    
    
    
    /**
     * 得到前一天的当前时刻 yyyy-MM-dd
     * @return
     * add by jannal
     */
    public static final String getYesterDayDefalutFormatt(){
        return getYesterDay(YYYY_MM_DD);
    }
    
    
    /**
     * 得到前一天的当前时刻 yyyy-MM-dd HH:mm:ss
     * @return
     * add by jannal
     */
    public static final String getYesterDayTime24Formatt(){
    	return getYesterDay(YYYY_MM_DD_HH_MM_SS_FORMAT);
    }
    
    /**
     *  根据指定格式解析时间
     * @param dateString
     * @param fmtString
     * @return
     */
    public static final Date parse(String dateString, String fmtString) {
        if(StringUtils.isEmpty(dateString)){
            return null;
        }
        Date date = null;
        DateFormat format = new SimpleDateFormat(fmtString);
        try {
			date = format.parse(dateString);
		} catch (ParseException e) {
			throw new RuntimeException(e.getMessage(),e);
		}
        return date;
    }
    
    private static String[] parsePatterns = {
		"yyyy-MM-dd", "yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd HH:mm", "yyyy-MM", 
		"yyyy/MM/dd", "yyyy/MM/dd HH:mm:ss", "yyyy/MM/dd HH:mm", "yyyy/MM",
		"yyyy.MM.dd", "yyyy.MM.dd HH:mm:ss", "yyyy.MM.dd HH:mm", "yyyy.MM"};

	/**
	 * 得到当前日期字符串 格式（yyyy-MM-dd）
	 */
	public static String getDate() {
		return getDate("yyyy-MM-dd");
	}
	
	/**
	 * 得到当前日期字符串 格式（yyyy-MM-dd） pattern可以为："yyyy-MM-dd" "HH:mm:ss" "E"
	 */
	public static String getDate(String pattern) {
		return DateFormatUtils.format(new Date(), pattern);
	}
	
	/**
	 * 得到日期字符串 默认格式（yyyy-MM-dd） pattern可以为："yyyy-MM-dd" "HH:mm:ss" "E"
	 */
	public static String formatDate(Date date, Object... pattern) {
		String formatDate = null;
		if (pattern != null && pattern.length > 0) {
			formatDate = DateFormatUtils.format(date, pattern[0].toString());
		} else {
			formatDate = DateFormatUtils.format(date, "yyyy-MM-dd");
		}
		return formatDate;
	}
	
	
	/**
	 * 得到日期字符串 默认格式（yyyy-MM-dd） pattern可以为："yyyy-MM-dd" "HH:mm:ss" "E"
	 */
	public static String formatDate(Timestamp date, Object... pattern) {
		String formatDate = null;
		if (pattern != null && pattern.length > 0) {
			formatDate = DateFormatUtils.format(date.getTimestamp(), pattern[0].toString());
		} else {
			formatDate = DateFormatUtils.format(date.getTimestamp(), "yyyy-MM-dd");
		}
		return formatDate;
	}
	
	/**
	 * 得到日期时间字符串，转换格式（yyyy-MM-dd HH:mm:ss）
	 */
	public static String formatDateTime(Date date) {
		return formatDate(date, "yyyy-MM-dd HH:mm:ss");
	}
	
	/**
	 * 得到日期时间字符串，转换格式（yyyy-MM-dd HH:mm:ss）
	 */
	public static String formatDateTimeYYMMDD(Date date) {
		return formatDate(date, "yyyyMMdd");
	}

	/**
	 * 得到当前时间字符串 格式（HH:mm:ss）
	 */
	public static String getTime() {
		return formatDate(new Date(), "HH:mm:ss");
	}

	/**
	 * 得到当前日期和时间字符串 格式（yyyy-MM-dd HH:mm:ss）
	 */
	public static String getDateTime() {
		return formatDate(new Date(), "yyyy-MM-dd HH:mm:ss");
	}

	/**
	 * 得到当前年份字符串 格式（yyyy）
	 */
	public static String getYear() {
		return formatDate(new Date(), "yyyy");
	}

	/**
	 * 得到当前月份字符串 格式（MM）
	 */
	public static String getMonth() {
		return formatDate(new Date(), "MM");
	}

	/**
	 * 得到当天字符串 格式（dd）
	 */
	public static String getDay() {
		return formatDate(new Date(), "dd");
	}

	/**
	 * 得到当前星期字符串 格式（E）星期几
	 */
	public static String getWeek() {
		return formatDate(new Date(), "E");
	}
	


	/**
	 * 获取过去的天数
	 * @param date
	 * @return
	 */
	public static long pastDays(Date date) {
		long t = new Date().getTime()-date.getTime();
		return t/(24*60*60*1000);
	}
	
	
	

	/**
	 * 获取过去的小时
	 * @param date
	 * @return
	 */
	public static long pastHour(Date date) {
		long t = new Date().getTime()-date.getTime();
		return t/(60*60*1000);
	}
	
	/**
	 * 获取过去的分钟
	 * @param date
	 * @return
	 */
	public static long pastMinutes(Date date) {
		long t = new Date().getTime()-date.getTime();
		return t/(60*1000);
	}
	
	/**
	 * 转换为时间（天,时:分:秒.毫秒）
	 * @param timeMillis
	 * @return
	 */
    public static String formatDateTime(long timeMillis){
		long day = timeMillis/(24*60*60*1000);
		long hour = (timeMillis/(60*60*1000)-day*24);
		long min = ((timeMillis/(60*1000))-day*24*60-hour*60);
		long s = (timeMillis/1000-day*24*60*60-hour*60*60-min*60);
		long sss = (timeMillis-day*24*60*60*1000-hour*60*60*1000-min*60*1000-s*1000);
		return (day>0?day+",":"")+hour+":"+min+":"+s+"."+sss;
    }
	
	/**
	 * 获取两个日期之间的天数
	 * 
	 * @param before
	 * @param after
	 * @return
	 */
	public static double getDistanceOfTwoDate(Date before, Date after) {
		long beforeTime = before.getTime();
		long afterTime = after.getTime();
		return (afterTime - beforeTime) / (1000 * 60 * 60 * 24);
	}
	
	/**
	 * 得到毫秒字符串
	 */
	public static String formatDateMill(Date date) {
		return formatDate(date, "yyyyMMddHHmmssSSS");
	}
	/*** 
     * 日期偏移方法
     * @param option 传入类型 preDay：日期减一天，nextDay：日期加一天 
     * 		  preMonth：日期减一天，nextMonth：日期加一天 
     * 		  preYear：日期减一天，nextYear：日期加一天 
     * @param date  
     * @param mode   如 ：yyyy-MM-dd HH:mm:ss
     * @param period 整数
     * @param unit D M Y
     * @return 
     * 
     * period,String unit
     */  
    public static String offsetDay(Integer period,String unit, Date date,String mode) {
    	if(period==null||unit==null){
    		return "";
    	}
    	Calendar cl = Calendar.getInstance();  
        SimpleDateFormat sdf;
		try {
			sdf = new SimpleDateFormat(mode);
			cl.setTime(date);  
		} catch (Exception e) {
			return "";
		}  
        if("D".equals(unit)) {  
            cl.add(Calendar.DATE, period.intValue());  
        }else if("M".equals(unit)) {  
        	cl.add(Calendar.MONTH, period.intValue());  
        }else if("Y".equals(unit)) {  
        	cl.add(Calendar.YEAR, period.intValue());  
        }else{
        	return "";
        }    
        date = cl.getTime();  
        return sdf.format(date);  
    }  
    
    /**
     * 获取今天之后的某天日期
     * @param day   今天之后几天
     * @return
     */
    public static String getAddDayDate(int day){
    	SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar c = Calendar.getInstance();
        System.out.println("当前日期：               "+sf.format(c.getTime()));
        c.add(Calendar.DAY_OF_MONTH, day);
        System.out.println("增加一天后日期 ：  "+sf.format(c.getTime()));
        return sf.format(c.getTime());
    }
    
    /** 
     * @Description::(date日期的基础上增加day天). <br/> 
     * @author jingmiao
     * @param date
     * @param day
     * @return
     */ 
    public static Date addDay(Date date,int day){
    	return addDateTime(date, day, null, null, null, null);
    }
    
    /** 
     * @Description::(date日期的基础上增加hour小时). <br/> 
     * @author jingmiao
     * @param date
     * @param hour
     * @return
     */ 
    public static Date addHour(Date date,int hour){
    	return addDateTime(date, null, hour, null, null, null);
    }
    
    /** 
     * @Description::(date日期基础上增加时间). <br/> 
     * @author jingmiao
     * @param date
     * @param day
     * @param hour
     * @param minute
     * @param second
     * @param millisecond
     * @return
     */ 
    private static Date addDateTime(Date date,Integer day,Integer hour,Integer minute,Integer second,Integer millisecond){
    	Calendar calendar = Calendar.getInstance();
		if(date!=null){
			calendar.setTime(date);
		}
		if(day!=null){
			calendar.add(Calendar.DAY_OF_MONTH, day);
		}
		if(hour!=null){
			calendar.add(Calendar.HOUR_OF_DAY, hour);
		}
		if(minute!=null){
			calendar.add(Calendar.MINUTE, minute);
		}
		if(second!=null){
			calendar.add(Calendar.SECOND, second);
		}
		if(millisecond!=null){
			calendar.add(Calendar.MILLISECOND, millisecond);
		}
		return calendar.getTime();
    }
    
    /** 
     * @Description::(设置到某一天的某个整点 例如15:00:00). <br/> 
     * @author jingmiao
     * @param date
     * @param hour
     * @return
     */ 
    public static Date setDateHour(Date date,Integer hour){
    	return setDateTime(date, null, hour, 0, 0, 0);
    }
    
    /**
     * 在原有时间上加减月份后得出时间
     * @param date
     * @param Month
     * @return
     */
    public static Date setDateMonth(Date date,Integer Month){
    	Calendar calendar = Calendar.getInstance();
		if(date!=null){
			calendar.setTime(date);
		}
		calendar.add(Calendar.MONTH, Month);  
		return calendar.getTime();
		}
    
    /** 
     * @Description::(在date日期基础上设定一个日期). <br/> 
     * @author jingmiao
     * @param date
     * @param day
     * @param hour
     * @param minute
     * @param second
     * @param millisecond
     * @return
     */ 
    private static Date setDateTime(Date date,Integer day,Integer hour,Integer minute,Integer second,Integer millisecond){
    	Calendar calendar = Calendar.getInstance();
		if(date!=null){
			calendar.setTime(date);
		}
		if(day!=null){
			calendar.set(Calendar.DAY_OF_MONTH, day);
		}
		if(hour!=null){
			calendar.set(Calendar.HOUR_OF_DAY, hour);
		}
		if(minute!=null){
			calendar.set(Calendar.MINUTE, minute);
		}
		if(second!=null){
			calendar.set(Calendar.SECOND, second);
		}
		if(millisecond!=null){
			calendar.set(Calendar.MILLISECOND, millisecond);
		}
		return calendar.getTime();
    }
    
    /** 
     * @Description::(roll方法在date日期基础上减少day天). <br/> 
     * @author jingmiao
     * @param date
     * @param day
     * @return
     */ 
    public static Date rollDate(Date date,Integer day){
    	return rollDateTime(date, day, null, null, null, null);
    }
    
    /** 
     * @Description::(roll方法在date日期基础上减少时间). <br/> 
     * @author jingmiao
     * @param date
     * @param day
     * @param hour
     * @param minute
     * @param second
     * @param millisecond
     * @return
     */ 
    private static Date rollDateTime(Date date,Integer day,Integer hour,Integer minute,Integer second,Integer millisecond){
    	Calendar calendar = Calendar.getInstance();
		if(date!=null){
			calendar.setTime(date);
		}
		if(day!=null){
			calendar.roll(Calendar.DAY_OF_MONTH, day);
		}
		if(hour!=null){
			calendar.roll(Calendar.HOUR_OF_DAY, hour);
		}
		if(minute!=null){
			calendar.roll(Calendar.MINUTE, minute);
		}
		if(second!=null){
			calendar.roll(Calendar.SECOND, second);
		}
		if(millisecond!=null){
			calendar.roll(Calendar.MILLISECOND, millisecond);
		}
		return calendar.getTime();
    }
    
    
    /**
     * 
     * Description: 返回两个时分秒时间hh:mm:ss的大小，如果返回1，说明time1大于time2，返回0，说明两个时间相同，返回-1，说明time1小于time2
     * @param time1
     * @param time2
     * @return
     * @return int
     * @throws
     * @Author 耿伟
     * Create Date: 2015年9月6日 下午6:53:40
     */
    public static int compareHMS(String time1,String time2) {
		return Time.valueOf(time1).compareTo(Time.valueOf(time2));
	}

	/**
	 * 返回当天23:59:59
	 * 
	 * @param date
	 * @return
	 */
	public static Date getDayLast(Date date) {
		if (date == null) {
			return null;
		}
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);

		calendar.set(Calendar.HOUR_OF_DAY, 23);
		calendar.set(Calendar.MINUTE, 59);
		calendar.set(Calendar.SECOND, 59);
		calendar.set(Calendar.MILLISECOND, 0);
		return calendar.getTime();
	}
	
	/**
	 * 返回第二天00:00:00
	 * 
	 * @param date
	 * @return
	 */
	public static Date getNextDay(Date date) {
		if (date == null) {
			return null;
		}
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DATE, 1);

		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		return calendar.getTime();
	}
}
