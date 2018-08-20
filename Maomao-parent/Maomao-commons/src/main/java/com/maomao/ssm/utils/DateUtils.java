package com.maomao.ssm.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtils {
	private final static SimpleDateFormat sdfYear = new SimpleDateFormat("yyyy");

	private final static SimpleDateFormat sdfDay = new SimpleDateFormat("yyyy-MM-dd");

	private final static SimpleDateFormat sdfDays = new SimpleDateFormat("yyyyMMdd");

	private final static SimpleDateFormat sdfTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	/**
	 * 获取YYYY格式
	 * 
	 * @return
	 */
	public static String getYear() {
		return sdfYear.format(new Date());
	}

	/**
	 * 获取YYYY-MM-DD格式
	 * 
	 * @return
	 */
	public static String getDay() {
		return sdfDay.format(new Date());
	}

	/**
	 * 获取YYYYMMDD格式
	 * 
	 * @return
	 */
	public static String getDays() {
		return sdfDays.format(new Date());
	}

	/**
	 * 获取YYYY-MM-DD HH:mm:ss格式
	 * 
	 * @return
	 */
	public static String getTime() {
		return sdfTime.format(new Date());
	}

	/**
	 * @Title: compareDate
	 * @Description: TODO(日期比较，如果s>=e 返回true 否则返回false)
	 * @param s
	 * @param e
	 * @return boolean
	 * @throws @author
	 * 
	 */
	public static boolean compareDate(String s, String e) {
		if (fomatDate(s) == null || fomatDate(e) == null) {
			return false;
		}
		return fomatDate(s).getTime() >= fomatDate(e).getTime();
	}

	public static boolean compareDate(Date s, Date e) {
		return s.getTime() >= e.getTime();
	}

	/**
	 * 格式化日期
	 * 
	 * @return
	 */
	public static Date fomatDate(String date) {
		DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
		try {
			return fmt.parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 校验日期是否合法
	 * 
	 * @return
	 */
	public static boolean isValidDate(String s) {
		DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
		try {
			fmt.parse(s);
			return true;
		} catch (Exception e) {
			// 如果throw java.text.ParseException或者NullPointerException，就说明格式不对
			return false;
		}
	}

	public static int getDiffYear(String startTime, String endTime) {
		DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
		try {
			long aa = 0;
			int years = (int) (((fmt.parse(endTime).getTime() - fmt.parse(startTime).getTime()) / (1000 * 60 * 60 * 24))
					/ 365);
			return years;
		} catch (Exception e) {
			// 如果throw java.text.ParseException或者NullPointerException，就说明格式不对
			return 0;
		}
	}

	/**
	 * <li>功能描述：时间相减得到天数
	 * 
	 * @param beginDateStr
	 * @param endDateStr
	 * @return long
	 * @author Administrator
	 */
	public static long getDaySub(String beginDateStr, String endDateStr) {
		long day = 0;
		java.text.SimpleDateFormat format = new java.text.SimpleDateFormat("yyyy-MM-dd");
		java.util.Date beginDate = null;
		java.util.Date endDate = null;

		try {
			beginDate = format.parse(beginDateStr);
			endDate = format.parse(endDateStr);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		day = (endDate.getTime() - beginDate.getTime()) / (24 * 60 * 60 * 1000);
		// System.out.println("相隔的天数="+day);

		return day;
	}

	/**
	 * <li>功能描述：时间相减得到年数
	 * 
	 * @param beginDateStr
	 * @param endDateStr
	 * @return long
	 * @author Administrator
	 */
	public static int getYearSub(String beginDateStr, String endDateStr) {
		java.text.SimpleDateFormat format = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		java.util.Date beginDate = null;
		java.util.Date endDate = null;
		int yearSub = 0;
		try {
			beginDate = format.parse(beginDateStr);
			endDate = format.parse(endDateStr);
			Calendar c1 = Calendar.getInstance();
			Calendar c2 = Calendar.getInstance();
			c1.setTime(beginDate);
			c2.setTime(endDate);
			int year1 = c1.get(Calendar.YEAR);
			int year2 = c2.get(Calendar.YEAR);
			yearSub = year2 - year1;
			if (c1.get(Calendar.MONTH) == c2.get(Calendar.MONTH)
					&& c1.get(Calendar.DAY_OF_MONTH) == c2.get(Calendar.DAY_OF_MONTH)) {
				yearSub += 1;
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}

		return yearSub;
	}

	/**
	 * <li>功能描述：时间相减得到年数
	 * 
	 * @param beginDateStr
	 * @param endDateStr
	 * @return long
	 * @author Administrator
	 */
	public static int getYearSub(Date beginDate, Date endDate) {
		Calendar c1 = Calendar.getInstance();
		Calendar c2 = Calendar.getInstance();
		c1.setTime(beginDate);
		c2.setTime(endDate);
		int year1 = c1.get(Calendar.YEAR);
		int year2 = c2.get(Calendar.YEAR);
		int yearSub = year2 - year1;
		if (c1.get(Calendar.MONTH) == c2.get(Calendar.MONTH)
				&& c1.get(Calendar.DAY_OF_MONTH) == c2.get(Calendar.DAY_OF_MONTH)) {
			yearSub += 1;
		}
		return yearSub;
	}

	/**
	 * 得到n天之后的日期
	 * 
	 * @param days
	 * @return
	 */
	public static String getAfterDayDateString(String days) {
		int daysInt = Integer.parseInt(days);
		return getAfterDayDateString(daysInt);
	}

	/**
	 * 得到n天之后的日期
	 * 
	 * @param days
	 * @return
	 */
	public static String getAfterDayDateString(int days) {
		Calendar canlendar = Calendar.getInstance(); // java.util包
		canlendar.add(Calendar.DATE, days); // 日期减 如果不够减会将月变动
		Date date = canlendar.getTime();

		SimpleDateFormat sdfd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String dateStr = sdfd.format(date);

		return dateStr;
	}

	/**
	 * 得到n天之后的日期
	 * 
	 * @param days
	 * @return
	 */
	public static Date getAfterDayDate(String days) {
		int daysInt = Integer.parseInt(days);
		return getAfterDayDate(daysInt);
	}

	/**
	 * 得到n天之后的日期
	 * 
	 * @param days
	 * @return
	 */
	public static Date getAfterDayDate(int days) {
		Calendar canlendar = Calendar.getInstance(); // java.util包
		canlendar.add(Calendar.DATE, days); // 日期减 如果不够减会将月变动
		Date date = canlendar.getTime();

		return date;
	}

	/**
	 * 得到n天之前的日期
	 * 
	 * @param days
	 * @return
	 */
	public static String getBeforeDayDateString(String days) {
		int daysInt = Integer.parseInt(days);
		return getBeforeDayDateString(daysInt);
	}

	/**
	 * 得到n天之前的日期
	 * 
	 * @param days
	 * @return
	 */
	public static String getBeforeDayDateString(int days) {

		Calendar canlendar = Calendar.getInstance(); // java.util包
		canlendar.add(Calendar.DATE, days); // 日期减 如果不够减会将月变动
		Date date = canlendar.getTime();

		SimpleDateFormat sdfd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String dateStr = sdfd.format(date);

		return dateStr;
	}

	/**
	 * 得到n天之前的日期
	 * 
	 * @param days
	 * @return
	 */
	public static Date getBeforeDayDate(String days) {
		int daysInt = Integer.parseInt(days);
		return getBeforeDayDate(daysInt);
	}

	/**
	 * 得到n天之前的日期
	 * 
	 * @param days
	 * @return
	 */
	public static Date getBeforeDayDate(int days) {

		Calendar canlendar = Calendar.getInstance(); // java.util包
		canlendar.add(Calendar.DATE, -days); // 日期减 如果不够减会将月变动
		Date date = canlendar.getTime();

		return date;
	}

	/**
	 * 得到n分钟之前的日期
	 * 
	 * @param days
	 * @return
	 */
	public static Date getBeforeMinDate(int mins) {

		Calendar canlendar = Calendar.getInstance(); // java.util包
		canlendar.add(Calendar.MINUTE, -mins);
		Date date = canlendar.getTime();

		return date;
	}

	/**
	 * 得到指定时间n分钟之前的日期
	 * 
	 * @param days
	 * @return
	 */
	public static Date getBeforeMinDate(Date date, int mins) {
		Calendar canlendar = Calendar.getInstance(); // java.util包
		canlendar.setTime(date);
		canlendar.add(Calendar.MINUTE, -mins);
		return canlendar.getTime();
	}

	/**
	 * 得到指定时间n分钟之前的日期
	 * 
	 * @param days
	 * @return
	 */
	public static Date getAfterMinDate(Date date, int mins) {
		Calendar canlendar = Calendar.getInstance(); // java.util包
		canlendar.setTime(date);
		canlendar.add(Calendar.MINUTE, mins);
		return canlendar.getTime();
	}

	/**
	 * 得到n天之后是周几
	 * 
	 * @param days
	 * @return
	 */
	public static String getAfterDayWeek(String days) {
		int daysInt = Integer.parseInt(days);

		Calendar canlendar = Calendar.getInstance(); // java.util包
		canlendar.add(Calendar.DATE, daysInt); // 日期减 如果不够减会将月变动
		Date date = canlendar.getTime();

		SimpleDateFormat sdf = new SimpleDateFormat("E");
		String dateStr = sdf.format(date);

		return dateStr;
	}

	/**
	 * 得到指定日期n天之后
	 * 
	 * @param date
	 * @param amount
	 * @return
	 */
	public static Date addDays(Date date, Integer amount) {
		final Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.add(Calendar.DAY_OF_MONTH, amount);
		return c.getTime();
	}

	/**
	 * 得到昨天0点0分0秒
	 * 
	 * @return
	 */
	public static Date getYesterdayFirstSecond() {
		Calendar canlendar = Calendar.getInstance(); // java.util包
		canlendar.add(Calendar.DATE, -1); // 日期减 如果不够减会将月变动
		canlendar.set(Calendar.HOUR_OF_DAY, 0);
		canlendar.set(Calendar.MINUTE, 0);
		canlendar.set(Calendar.SECOND, 0);
		canlendar.set(Calendar.MILLISECOND, 0);
		return canlendar.getTime();
	}

	/**
	 * 得到昨天23点59分59秒
	 * 
	 * @return
	 */
	public static Date getYesterdayLastSecond() {
		Calendar canlendar = Calendar.getInstance(); // java.util包
		canlendar.add(Calendar.DATE, -1); // 日期减 如果不够减会将月变动
		canlendar.set(Calendar.HOUR_OF_DAY, 23);
		canlendar.set(Calendar.MINUTE, 59);
		canlendar.set(Calendar.SECOND, 59);
		canlendar.set(Calendar.MILLISECOND, 999);
		return canlendar.getTime();
	}

	/**
	 * 得到0点0分0秒
	 * 
	 * @return
	 */
	public static Date getFirstSecond(Date date) {
		Calendar canlendar = Calendar.getInstance(); // java.util包
		canlendar.setTime(date);
		canlendar.set(Calendar.HOUR_OF_DAY, 0);
		canlendar.set(Calendar.MINUTE, 0);
		canlendar.set(Calendar.SECOND, 0);
		canlendar.set(Calendar.MILLISECOND, 0);
		return canlendar.getTime();
	}

	/**
	 * 得到23点59分59秒
	 * 
	 * @return
	 */
	public static Date getLastSecond(Date date) {
		Calendar canlendar = Calendar.getInstance(); // java.util包
		canlendar.setTime(date);
		canlendar.set(Calendar.HOUR_OF_DAY, 23);
		canlendar.set(Calendar.MINUTE, 59);
		canlendar.set(Calendar.SECOND, 59);
		canlendar.set(Calendar.MILLISECOND, 999);
		return canlendar.getTime();
	}

	public static void main(String[] args) {
		System.out.println(getDays());
		System.out.println(getAfterDayWeek("3"));
		System.out.println(getYearSub(new Date(91, 6, 12), new Date()));
		System.out.println(getFirstSecond(new Date()));
		System.out.println(getLastSecond(new Date()));
	}

}
