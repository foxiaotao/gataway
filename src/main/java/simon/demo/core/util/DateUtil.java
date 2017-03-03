
package simon.demo.core.util;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;

//import org.joda.time.DateTime;
//import org.joda.time.DateTimeZone;
//import org.joda.time.Days;
//import org.joda.time.Duration;
//import org.joda.time.Hours;
//import org.joda.time.LocalDate;
//import org.joda.time.LocalTime;
//import org.joda.time.Minutes;
//import org.joda.time.Period;
//import org.joda.time.PeriodType;
//import org.joda.time.format.DateTimeFormat;


public class DateUtil {
	public static final Long DATE_DAY_LONG = 1l;
	//一天时间得毫秒数
	public static final Long DATE_DAY_MILLISECOND = 24*60*60*1000*DATE_DAY_LONG;
	private static String defaultDatePattern = "yyyy-MM-dd HH:mm:ss";
	private static String defaultDatePattern1 = "yyyy-MM-dd";
	private static String defaultDatePattern2 = "yyyyMMdd";
	private static String defaultDatePattern3 = "yyyy年MM月dd日";

	
	/**
	 * 
	 * @param startTime yyyy-MM-dd HH:mm
	 * @param endTime yyyy-MM-dd HH:mm
	 * @return
	 */
	public static String calcBetweenTwoTimes(String startTime,String endTime){
		String returnStr = "";
		try{
			
		
		SimpleDateFormat dfs = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		   java.util.Date begin=dfs.parse(startTime);
		   java.util.Date end = dfs.parse(endTime);
		   long between=(end.getTime()-begin.getTime())/1000/60;//除以1000是为了转换成秒

		   long day1=between/(24*60);
		   long hour1=between%(24*60)/60;
		   long minute1=between%60;
		   if(day1==0){
			   if(hour1 ==0){
				   returnStr = minute1+"分";
			   }else{
				   returnStr =  hour1 + "小时" + minute1+"分";
			   }
		   }else{
			   returnStr = day1+"天" + hour1 + "小时" + minute1+"分";
		   }
		   
		}catch(Exception e){
			e.printStackTrace();
		}
		return returnStr;
	}
	/**
	 * 使用预设格式将字符串转为Date
	 */
	public static Date parseDate(String strDate)  {
		Date date = null;
		try {
			date =  strDate == null ? null : parseDate(strDate,defaultDatePattern);//yyyy-MM-dd HH:mm:ss
		} catch (ParseException e) {
			
		}
		return date;
	}
	/**
	 * date 格式 ：2014-10-12 12:20:10
	 * 使用预设格式将字符串转为Date
	 * return String 12-12 13:01
	 */
	public static String parseDateTOmmddhhmm(String strDate)  {
		Date date = null;
		try {
			date =  strDate == null ? null : parseDate(strDate,defaultDatePattern);
		} catch (ParseException e) {
			
		}
		return format(date,"MM-dd HH:mm");
	}
	

	/**
	 * date 格式 ：2014-10-12 12:20:10
	 * 使用预设格式将字符串转为Date
	 * return String 1212 12:20
	 */
	public static String parseDateTOmmddhhmm1(String strDate)  {
		Date date = null;
		try {
			date =  strDate == null ? null : parseDate(strDate,defaultDatePattern);
		} catch (ParseException e) {
			
		}
		return format(date,"MMdd HH:mm");
	}
	
	/**
	 * date 格式 ：2014-10-12
	 * 使用预设格式将字符串转为Date
	 * return String 2014年10月12日
	 */
	public static String parseDateLocalChanese(String strDate)  {
		Date date = null;
		try {
			date =  strDate == null ? null : parseDate(strDate,defaultDatePattern1);
		} catch (ParseException e) {
			
		}
		return format(date,"yyyy年MM月dd日");
	}
	/**
	 * date 格式 ：2014-10-12 12:20:10
	 * 使用预设格式将字符串转为Date
	 * return String 2014-10-12
	 */
	public static String parseDateTOyyyymmdd(String strDate)  {
		Date date = null;
		try {
			date =  strDate == null ? null : parseDate(strDate,defaultDatePattern);//defaultDatePattern:yyyy-MM-dd HH:mm:ss
		} catch (ParseException e) {
			
		}
		return format(date,"yyyy-MM-dd");
	}
	/**
	 * date 格式 ：2014-10-12
	 * 使用预设格式将字符串转为Date
	 * return String 20141012
	 */
	public static String parseStringDateTOyyyymmdd(String strDate)  {
		Date date = null;
		try {
			date =  strDate == null ? null : parseDate(strDate,defaultDatePattern1);//defaultDatePattern1:yyyy-MM-dd
		} catch (ParseException e) {
			
		}
		return format(date,defaultDatePattern2);//defaultDatePattern2:  20141012
	}
	/**
	 * date 格式 ：20141012
	 * 使用预设格式将字符串转为Date
	 * return String 2014-10-12
	 */
	public static String parseStringDateTOYYYYMMDD(String strDate)  {
		Date date = null;
		try {
			date =  strDate == null ? null : parseDate(strDate,defaultDatePattern2);//defaultDatePattern2:20141012
		} catch (ParseException e) {
			
		}
		return format(date,defaultDatePattern1);//defaultDatePattern1:  yyyy-MM-dd
	}
	/**
	 * yyyy-MM-dd
	 * 使用预设格式将字符串转为Date
	 */
	public static Date parse(String strDate)  {
		Date date = null;
		try {
			date =  strDate == null ? null : parseDate(strDate,defaultDatePattern1);
		} catch (ParseException e) {
			
		}
		return date;
	}
	/**
	 * 使用预设格式将字符串转为Date
	 */
	public static String getStringFromDate(Date date , String strDate)  {
		SimpleDateFormat dfs = new SimpleDateFormat(strDate);
		return dfs.format(date);
	}
	/**
	 * 使用参数Format将字符串转为Date
	 */
	public static Date parseDate(String strDate, String pattern)
			throws ParseException {
		return strDate == null ? null : new SimpleDateFormat(
				pattern).parse(strDate);
	}
	
	/**
	 * 试用参数Format格式化Calendar成字符串
	 * 
	 * @param cal
	 * @param pattern
	 * @return
	 */
	public static String format(Calendar cal, String pattern) {
		return cal == null ? "" : new SimpleDateFormat(pattern).format(cal
				.getTime());
	}
	/**
	 * 使用参数Format格式化Date成字符串
	 */
	public static String format(Date date, String pattern) {
		return date == null ? "" : new SimpleDateFormat(pattern).format(date);
	}
	/**
	 * 按照给定的Long型数据,获得指定格式的时间字符串
	 * @param time
	 * @param format
	 * @return
	 */
	public static String getDateTime(Long time,String format){
		SimpleDateFormat ft = new SimpleDateFormat(format);
		return ft.format(time);
	}
	
	
	public static Long getNowStamp(){
		return (new Date().getTime())/1000;
	}
	
	
	
	
	
	
	
	/**
	 * 获取当前时间的前/后的一段时间
	 * @param day 前几天时间 (day等于0则取当前时间),day为正数则时间向前推，day为负数时间向后推
	 * @param strDate 输入的日期，格式yyyy-yy-dd
	 * @return 返回格式如：2012-10-10
	 */
	public static String getDateByDay(String strDate,int day){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date data = new Date();
		String startTime = "";
		try {
			data = strDate == null ? null : sdf.parse(strDate);
			long time = data.getTime();
			//如果传入的参数为0 则取当前时间
			if(day<1 && day >-1){
				
			}else{
				long timeOld10 = DATE_DAY_MILLISECOND*day;
				time = time - timeOld10;
			}
		    startTime = sdf.format(time);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		return startTime;
	}
	
	/**
	 * 获取yyyy-MM-dd HH:mm:ss格式的时间
	 * @param timestamp
	 * @return
	 */
	public static String getStringTimestamp(Long timestamp){
		Timestamp ts = new Timestamp(timestamp);  
        String tsStr = "";  
        DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
        try {  
            //方法一  
            tsStr = sdf.format(ts);  
  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
        return tsStr;
	}
	
	
	/**
	 * 获取yyyy-MM-dd格式的时间
	 * @param timestamp
	 * @return
	 */
	public static String getStringTimestampFormat(Long timestamp){
		Timestamp ts = new Timestamp(timestamp);  
        String tsStr = "";  
        DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");  
        try {  
            //方法一  
            tsStr = sdf.format(ts);  
  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
        return tsStr;
	}
	
	/**
	 * 
	 * @param date 格式yyyyMMdd
	 * @return 格式yyyy-MM-dd
	 */
	public static String formateDate(String date){
		String returntime = "";
		try {
			returntime = format(parseDate(date,defaultDatePattern2),defaultDatePattern1);
		} catch (ParseException e) {
			
		}
		return returntime;
	}
	
	/**
	 * 
	 * @param date 格式yyyyMMdd
	 * @return 格式yyyy年MM月dd日
	 */
	public static String formateDate3(String date){
		String returntime = "";
		try {
			returntime = format(parseDate(date,defaultDatePattern2),defaultDatePattern3);
		} catch (ParseException e) {
			
		}
		return returntime;
	}
	
	
	
	/**
	 * 给定开始日期和天数，生成日期队列
	 * @param startDate 格式yyyy-mm-dd
	 * @param dayCount 天数
	 * @return
	 */
	public static List<String> getDateListWithDayCount(String startDate,int dayCount){
		 List<String> days = new ArrayList<String>();
		 for(int i = 0;i<dayCount;i++){
			 days.add(getDateByDay(startDate,-i));
		 }
		// System.out.println("" + days);
		 return days;
	}
	
	/**
	 * 将yyyyMMdd格式时间转为yyyy-MM-dd格式
	 * @param day 格式为yyyyMMdd
	 * @return 格式为yyyy-MM-dd
	 */
	public static String getFormateDay(String day){
		Date date = null;
		try {
			date = parseDate(day,defaultDatePattern2);
		} catch (ParseException e) {
			
			//e.printStackTrace();
		}
		return format(date,defaultDatePattern1);
	}
	
	/**
	 * 将yyyyMMdd格式时间转为yyyy-MM-dd格式
	 * @param day 格式为yyyyMMdd
	 * @return 格式为yyyy-MM-dd
	 */
	public static String getFormateDaytest(String day){
		Date date = null;
		try {
			date = parseDate(day,defaultDatePattern2);
		} catch (ParseException e) {
			
			//e.printStackTrace();
		}
		return format(date,defaultDatePattern1);
	}
	
	
	/**
	 * 将yyyy-mm-dd格式转换成yyyyMMdd格式
	 * @param day  yyyy-mm-dd
	 * @return yyyyMMdd
	 */
	public static String getFormateDayShort(String day){
		Date date = null;
		try {
			date = parseDate(day,defaultDatePattern1);
		} catch (ParseException e) {
			
			//e.printStackTrace();
		}
		return format(date,defaultDatePattern2);
	}
	
	/**
	 * 时间格式转换
	 * @param day  传入的日期
	 * @param formateType formate格式字符串
	 * @return yyyyMMdd
	 */
	public static String getFormateDayTime(String day,String formateType){
		Date date = null;
		try {
			date = parseDate(day,formateType);
		} catch (ParseException e) {
			
			//e.printStackTrace();
		}
		return format(date,defaultDatePattern2);
	}
	
	/**
	 * 将日期从指定日期增加一天
	 * @param date
	 * @return
	 */
	public static Date addDateOneDay(Date date) {  
        if (null == date) {  
            return date;  
        }  
        Calendar c = Calendar.getInstance();  
        c.setTime(date);   //设置当前日期  
        c.add(Calendar.DATE, 1); //日期加1天  
        date = c.getTime();  
        return date;  
    } 
	
	/**
	 * 将日期从指定日期增加N天
	 * @param date
	 * @return
	 */
	public static Date addDateOneDay(Date date, int num) {  
        if (null == date) {  
            return date;  
        }  
        Calendar c = Calendar.getInstance();  
        c.setTime(date);   //设置当前日期  
        c.add(Calendar.DATE, num); //日期加1天  
        date = c.getTime();  
        return date;  
    } 
	
	/**
	 * date 2014-01-01
	 * 将字符串从指定日期增加一天,返回字符串
	 * @param date
	 * @return
	 */
	public static String addDateOneDay(String date) {  
		SimpleDateFormat dfs = new SimpleDateFormat("yyyy-MM-dd");
		if (null == date) {  
			return date;  
		}  
		return dfs.format(addDateOneDay(parse(date)));
	} 
	/**
	 * date 2014-01-01
	 * 将字符串从指定日期减少一天,返回字符串
	 * @param date
	 * @return
	 */
	public static String mulDateOneDay(String date) {  
		SimpleDateFormat dfs = new SimpleDateFormat("yyyy-MM-dd");
		if (null == date) {  
			return date;  
		}  
		return dfs.format(mulDateOneDay(parse(date)));
	} 
	
	/**
	 * 将日期从指定日期减小一天
	 * @param date
	 * @return
	 */
	public static Date mulDateOneDay(Date date) {  
		if (null == date) {  
			return date;  
		}  
		Calendar c = Calendar.getInstance();  
		c.setTime(date);   //设置当前日期  
		c.add(Calendar.DATE, -1); //日期加1天  
		date = c.getTime();  
		return date;  
	} 
	
	/**
	 * For yyyyMMdd
	 * 比较startDate是否在endDate前
	 * 相等时，视为true
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	public static boolean isBefore(String startDate, String endDate) {
		if (startDate.equals(endDate)) {
			return true;
		}
		return parse(startDate).before(parse(endDate));
	}
	/**
	 * For yyyyMMddHHmmss
	 * 比较startDate是否在endDate前
	 * 相等时，视为true
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	public static boolean isBeforeForyyyyMMddHHmmss(String startDate, String endDate) {
		if (startDate.equals(endDate)) {
			return true;
		}
		return parseDate(startDate).before(parseDate(endDate));//yyyy-MM-dd HH:mm:ss
	}
	
	
	/**
	 * 
	 * @param startDateStr 	yyyy-MM-dd
	 * @param endDateStr	yyyy-MM-dd
	 * @param spaceDay		间隔天数， 整数向开始日期前推算，负数向开始日期后推算
	 * @return list<yyyy-MM-dd>
	 * @throws ParseException 
	 */
	public static List<String> getDateStrBetweenStartToEnd(String startDateStr, String endDateStr, int spaceDay) throws ParseException {
	  	List<String> retList = new ArrayList<String>();
	  	Date startDate = DateUtil.parseDate(startDateStr, "yyyy-MM-dd");
	  	Date endDate = DateUtil.parseDate(endDateStr, "yyyy-MM-dd");
	  	
	  	while(startDate.before(endDate)) {
	  		retList.add(DateUtil.format(startDate, "yyyy-MM-dd"));
			startDate = DateUtil.parseDate(DateUtil.getDateByDay(DateUtil.format(startDate, "yyyy-MM-dd"), spaceDay), "yyyy-MM-dd");
		}
	  	if (endDateStr.equals(DateUtil.format(startDate, "yyyy-MM-dd"))) {
	  		retList.add(endDateStr);
	  	}
	  	
	  	return retList;
	}
	
	public static String getWeekOfDate(Date date, int pattern) {
		String[] weekDays = {"星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"};
		String[] weekDays1 = {"7", "1", "2", "3", "4", "5", "6"};
        
		Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
        if (w < 0)
            w = 0;
        
		switch(pattern) {
		case 0 :  
			return weekDays[w];			
		case 1 :  
			return weekDays1[w];		
		default :
			return weekDays[w];		
        }
		
		
	   
	}

	


	
	  
	
	}
		
		
	



