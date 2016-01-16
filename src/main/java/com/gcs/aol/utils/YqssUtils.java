package com.gcs.aol.utils;

import java.util.Calendar;
import java.util.Date;

import com.gcs.utils.DateUtils;


public class YqssUtils {

	public static final int LEAP_YEAR_DAYS = 366;
	public static final int NO_LEAP_YEAR_DAYS = 365;

	public static final String DEFAULT_FORMAT = "yyyy-MM-dd HH:mm:ss";
	
	
	/**
	 * 
     * @Title: borrowResidueDate
     * @Description: 计算借款还款时间
     * @param @return    参数
     * @return String    返回类型
     * @throws
	 */
	public static String borrowResidueDate(int days) {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DAY_OF_YEAR, days);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		return DateUtils.toString(calendar.getTime(), DEFAULT_FORMAT);
	}
	
	public static String nextResidueDay(Date residueDate) {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.MONTH, 1);
		return DateUtils.toString(calendar.getTime(), DEFAULT_FORMAT);
	}
	
	public static void main(String[] args) {
//		System.out.println(residueDay("2014-10-15 00:00:00"));
//		System.out.println(nextResidueDay("2016-01-15 00:00:00"));
//		System.out.println(firstResidueDay());
		System.out.println(borrowResidueDate(16));
//		System.out.println(countRate(0.02, 15, 3000));
//		System.out.println(numberFormat(100000.123456798));
//		System.out.println(countRate0(10,1000));
//		System.out.println(nextResidueDay("2016-1-30 15:09:00"));
//		System.out.println(generateSn());
		
//		System.out.println(countRate0("2016-01-15 00:00:00", 1, 6000));
//		System.out.println(isThisMonth(1451290808295L));
//		System.out.println(isThisMonth(1450233933482L, "2016-1-16 00:00:00", 1));
		
	}
}
