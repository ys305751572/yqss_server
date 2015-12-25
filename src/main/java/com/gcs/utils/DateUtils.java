/**
 * 
 */
package com.gcs.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtils {
	public static String a = "";
	
	private static final Logger logger = LoggerFactory.getLogger(DateUtils.class);

	final static String DEFAULT_FORMAT = "yyyy-MM-dd";
	
	public static final String format = "yyyy-MM-dd HH:mm:ss";
	public static void main(String[] aa){
		String t = "http://www.uhoem.com/product/detail/130.jhtml";
		t = t.substring(t.lastIndexOf("/")+1);
		t = t.substring(0,t.lastIndexOf("."));
		System.err.println(t);
		//System.err.println(test1(10));
		//System.err.println(1111);

	}

	public static void test(Integer i){
		if(i>1){
			a=","+i+a;
			i--;
			test(i);
		}
	}

	public static String test1(Integer i){
		test(i);
		return a;
	}

	// 传入时间毫秒，返回友好时间格式
	public static String time(long time) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日");
		try {
			Long now = new Date().getTime();
			Long mss = now - time;

			long days = mss / (1000 * 60 * 60 * 24);
			long hours = (mss % (1000 * 60 * 60 * 24)) / (1000 * 60 * 60);
			long minutes = (mss % (1000 * 60 * 60)) / (1000 * 60);
			long seconds = (mss % (1000 * 60)) / 1000;
			if(days>0){
				if(days == 1){
					return "昨天";
				}else {
					Date date = new Date();
					date.setTime(time);
					return sdf.format(date);
				}
			}
			if(hours>0){
				return hours+"小时前";
			}
			if(minutes>0){
				return minutes+"分钟前";
			}
			return "1分钟内";
		}catch (Exception e){
			e.printStackTrace();
			return sdf.format(new Date());
		}
	}

	// 传入时间字符串和format格式，返回友好时间格式
	public static String stringToFriendlyDate(String tempTime,String ... patterns){
		if(org.apache.commons.lang3.StringUtils.isEmpty(tempTime)){
			return "";
		}
		String pattern = "yyyy-MM-dd HH:mm:ss";
		if(patterns.length > 0){
			pattern = patterns[0];
		}
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		// 时间格式化
		Long templong = 0L;
		try {
			templong = sdf.parse(tempTime).getTime();
			return time(templong);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}

	public static String formatDuring(long mss) {
		long days = mss / (1000 * 60 * 60 * 24);
		long hours = (mss % (1000 * 60 * 60 * 24)) / (1000 * 60 * 60);
		long minutes = (mss % (1000 * 60 * 60)) / (1000 * 60);
		long seconds = (mss % (1000 * 60)) / 1000;
		return days + " days " + hours + " hours " + minutes + " minutes "
				+ seconds + " seconds ";
	}

	public static String getCurrentTime() {
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		return sdf.format(new Date());
	}
	
	/**
	 * 根据给定的格式化参数，将字符串转换为日期
	 * 
	 * @param dateString
	 * @param dateFormat
	 * @return java.util.Date
	 */
	public static java.util.Date parse(String dateString, String dateFormat) {
		if (logger.isDebugEnabled()) {
			logger.debug(">>>parse(String dateString, String dateFormat)");
		}
		if ("".equals(dateString.trim()) || dateString == null) {
			return null;
		}
		DateFormat sdf = new SimpleDateFormat(dateFormat);
		Date date = null;
		try {
			date = sdf.parse(dateString);

		} catch (Exception e) {
			e.printStackTrace();
		}

		if (logger.isDebugEnabled()) {
			logger.debug("<<<parse(return java.util.Date)");
		}
		return date;
	}

	/**
	 * 根据给定的格式化参数，将字符串转换为日期
	 * 
	 * @param dateString
	 * @param dateFormat
	 * @return java.util.Date
	 */
	public static String parse(long datalong, String dateFormat) {
		if (logger.isDebugEnabled()) {
			logger.debug(">>>parse(String dateString, String dateFormat)");
		}
		DateFormat sdf = new SimpleDateFormat(dateFormat);
		String time = null;
		try {
			time = sdf.format(new Date(datalong));
		} catch (Exception e) {
			e.printStackTrace();
		}

		if (logger.isDebugEnabled()) {
			logger.debug("<<<parse(return java.util.Date)");
		}
		return time;
	}
	
	/**
	 * 
	 * 默认将字符串转换为日期，格式(yyyy-MM-dd)
	 * 
	 * @param dateString
	 * @return
	 */
	public static java.util.Date parse(String dateString) {
		return parse(dateString, DEFAULT_FORMAT);
	}

	/**
	 * 根据给定的格式化参数，将日期转换为字符串
	 * 
	 * @param date
	 * @param dateFormat
	 * @return String
	 */
	public static String toString(java.util.Date date, String dateFormat) {
		if (logger.isDebugEnabled()) {
			logger.debug(">>> toString(java.util.Date date, String dateFormat)");
		}
		if ("".equals(date) || date == null) {
			return "bug: date is null";
		}
		DateFormat sdf = new SimpleDateFormat(dateFormat);
		String str = sdf.format(date);

		if (logger.isDebugEnabled()) {
			logger.debug("<<< toString return String");
		}
		return str;
	}
	
	/**
	 * 
     * @Title: stringToLong
     * @Description: TODO(这里用一句话描述这个方法的作用)
     * @param @param datetime
     * @param @param dateFormat
     * @param @return    参数
     * @return long    返回类型
     * @throws
	 */
	public static long stringToLong(String datetime,String dateFormat) {
		Date date = parse(datetime, dateFormat);
		return date.getTime();
	}
	
	/**
	 * 默认将日期转换为字符串，格式(yyyy-MM-dd)
	 * 
	 * @param date
	 * @return String
	 */
	public static String toString(java.util.Date date) {
		return toString(date, DEFAULT_FORMAT);
	}

	/**
	 * 将日期转换为长整型?
	 * 
	 * @param date
	 * @return long
	 */
	public static long toLong(java.util.Date date) {
		if (logger.isDebugEnabled()) {
			logger.debug(">>>toLong(java.util.Date date)");
		}
		if (date == null) {
			return 0;
		}
		long d = date.getTime();

		if (logger.isDebugEnabled()) {
			logger.debug("<<<toLong return long");
		}
		return d;
	}

	/**
	 * 将长整型转换为日期对象
	 * 
	 * @param time
	 * @return date
	 */
	public static java.util.Date toDate(long time) {
		if (logger.isDebugEnabled()) {
			logger.debug(">>>toDate (long time)");
		}
		if ("".equals(time)) {
			return new Date();
		}
		Date date = new Date(time);

		if (logger.isDebugEnabled()) {
			logger.debug("<<<toDate return date");
		}
		return date;
	}

	/**
	 * 获得系统当前时间
	 * 
	 * @return java.util.Date
	 */
	public static String currentStringDate() {
		if (logger.isDebugEnabled()) {
			logger.debug(">>>currentDate()");
		}
		Date date = new Date();

		if (logger.isDebugEnabled()) {
			logger.debug("<<<currentDate() return date");
		}
		return toString(date);
	}

	/**
	 * 获得系统当前时间(按用户自己格式)
	 * 
	 * @return java.util.Date
	 */
	public static String currentYourDate(String formate) {
		if (logger.isDebugEnabled()) {
			logger.debug(">>>currentDate()");
		}
		Date date = new Date();

		if (logger.isDebugEnabled()) {
			logger.debug("<<<currentDate() return date");
		}
		return toString(date, formate);
	}

	/**
	 * 获得系统当前时间
	 * 
	 * @return java.util.Date
	 */
	public static java.util.Date currentDate() {
		if (logger.isDebugEnabled()) {
			logger.debug(">>>currentDate()");
		}
		Date date = new Date();

		if (logger.isDebugEnabled()) {
			logger.debug("<<<currentDate() return date");
		}
		return date;
	}

	/**
	 * 根据日历的规则，为给定的日历字段添加或减去指定的时间�?
	 * 
	 * @param field
	 *            指定的日历字段
	 * @param date
	 *            需要操作的日期对象
	 * @param value
	 *            更改的时间值
	 * @return java.util.Date
	 */
	public static Date add(int field, Date date, int value) {
		if (logger.isDebugEnabled()) {
			logger.debug(">>>add(int field,Date date,int value)");
		}
		Calendar ca = Calendar.getInstance();
		ca.setTime(date);
		ca.add(field, value);
		Date newDate = ca.getTime();

		if (logger.isDebugEnabled()) {
			logger.debug("<<<add() return date");
		}
		return newDate;
	}
	
	/**
	 * 根据日历的规则，为给定的日历字段添加或减去指定的时间�?
	 * 
	 * @param field
	 *            指定的日历字段
	 * @param date
	 *            需要操作的日期对象
	 * @param value
	 *            更改的时间值
	 * @return java.util.Date
	 */
	public static Date add(int field, String date, int value) {
		if (logger.isDebugEnabled()) {
			logger.debug(">>>add(int field,Date date,int value)");
		}
		Calendar ca = Calendar.getInstance();
		ca.setTime(parse(date));
		ca.add(field, value);
		Date newDate = ca.getTime();

		if (logger.isDebugEnabled()) {
			logger.debug("<<<add() return date");
		}
		return newDate;
	}
	
	/**
	 * 返回给定日历字段的值
	 * 
	 * @param field
	 *            指定的日历字段
	 * @param date
	 *            给定的日期对象
	 * @return java.util.Date
	 */
	public static int get(int field, Date date) {
		if (logger.isDebugEnabled()) {
			logger.debug(">>>get(int field, Date date");
		}
		Calendar ca = Calendar.getInstance();
		ca.setTime(date);
		int value = ca.get(field);

		if (logger.isDebugEnabled()) {
			logger.debug("<<<get() return date");
		}
		return value;
	}

	/**
	 * 返回前N个月的日期值
	 * 
	 * @param month
	 * @return
	 */
	public static Date getLastMonth(String month) {
		Calendar ca = Calendar.getInstance();
		int m = 0;
		try {
			m = Integer.parseInt(month);
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		ca.add(Calendar.MONTH, -m);
		return ca.getTime();
	}
	
}