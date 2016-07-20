package com.fm.utill;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import android.content.Context;

/**
 * @author fangming
 */
public class DateUtils {
	/**
	 * 转换 Date to String
	 *
	 * @param Date
	 *            d
	 * @return String format yyyy-MM-dd HH:mm:ss ,
	 */
	public static String toDateAndTimeFormat(Date d) {
		String myDate = "";
		if (d != null) {
			SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			myDate = f.format(d);
		}
		return myDate;
	}

	public static Date strToDateMin(String str) {
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = null;
		try {
			date = format.parse(str);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		return date;
	}

	/**
	 * 转换String类型时间，返回long�?
	 *
	 * @param time
	 * @param dateFormat
	 * @return
	 */
	public static long toDateAndTime(String time, String dateFormat) {
		SimpleDateFormat format = new SimpleDateFormat(dateFormat);
		Date d = null;
		try {
			d = format.parse(time);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		long l = d.getTime();
		return l;
	}

	/**
	 * 将字符串转化成data
	 */
	public static Date toDate_(String dateFormat) {
		SimpleDateFormat myFmt = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		try {
			Date newDate = (Date) myFmt.parse(dateFormat);
			return newDate;
		} catch (ParseException e1) {
			e1.printStackTrace();
			return null;
		}
	}

	/**
	 * 转换 Date to String
	 *
	 * @param Date
	 *            d
	 * @return String format yyyy-MM-dd HH:mm,
	 */
	public static String toDateAndTimeFormatNoSecend(Date d) {
		String myDate = "";
		if (d != null) {
			SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd HH:mm");
			myDate = f.format(d);
		}
		return myDate;
	}

	/**
	 * yyyy-MM-dd HH:mm:ss 格式转换�? yyyy-MM-dd HH:mm
	 *
	 * @param date
	 * @return
	 */
	public static String toDateStringToString(String date) {
		String _dateStr = null;

		Date _date = strToDate(date);// date格式：yyyy-MM-dd HH:mm:ss
		SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		_dateStr = f.format(_date);

		return _dateStr;
	}

	/**
	 * 转换 Date to String
	 *
	 * @param Date
	 *            d
	 * @return String format MM-dd HH:mm,
	 */
	public static String toDateAndTimeFormatNoYearNoSecend(Date d) {
		String myDate = "";
		if (d != null) {
			SimpleDateFormat f = new SimpleDateFormat("MM-dd HH:mm");
			myDate = f.format(d);
		}
		return myDate;
	}

	/**
	 * 转换 Date to String
	 *
	 * @param Date
	 *            d
	 * @return String format yyyy-MM-dd,
	 */
	public static String toDateFormat(Date d) {
		if (null == d)
			return "";
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		String strdate = format.format(d);
		return strdate;
	}

	/**
	 * 转换 String to Date
	 *
	 * @param String
	 *            dateFormat, yyyyMMdd
	 * @return Date
	 */
	public static String getDateforint() {
		Calendar c = Calendar.getInstance();// 可以对每个时间域单独修改

		Date nowdate = c.getTime();

		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		String s = sdf.format(nowdate);

		return s;
	}

	/**
	 * 转换 Date to String
	 *
	 * @param Date
	 *            d
	 * @return String format HH:mm,
	 */
	public static String toTimeFormat(Date d) {
		SimpleDateFormat format = new SimpleDateFormat("HH:mm");
		String strdate = format.format(d);
		return strdate;
	}

	/**
	 * 转换 String to Date
	 *
	 * @param String
	 *            dateFormat, yyyy-MM-dd
	 * @return Date
	 */
	public static Date toDate(String dateFormat) {
		SimpleDateFormat myFmt = new SimpleDateFormat("yyyy-MM-dd");
		try {
			Date newDate = (Date) myFmt.parse(dateFormat);
			return newDate;
		} catch (ParseException e1) {
			e1.printStackTrace();
			return null;
		}
	}

	/**
	 * 字符转日�?
	 *
	 * @param str
	 * @return
	 */
	public static Date strToDate(String str) {
		if (null == str) {
			return null;
		}
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = null;
		try {
			date = format.parse(str);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		return date;
	}

	/**
	 * 转换 String to Date
	 *
	 * @param String
	 *            dateFormat, yyyy-MM-dd HH:mm:ss
	 * @return Date
	 */
	public static Date toDateAndTime(String dateFormat) {
		StringBuffer sb = new StringBuffer();
		if (dateFormat.split(":").length < 3) {
			dateFormat = sb.append(dateFormat + ":00").toString();
		}
		SimpleDateFormat myFmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			Date newDate = (Date) myFmt.parse(dateFormat);
			return newDate;
		} catch (ParseException e1) {
			e1.printStackTrace();
			return null;
		}
	}

	/**
	 * 小于10前面补零
	 *
	 * @param c
	 * @return String
	 */
	public static String pad(int c) {
		if (c >= 10)
			return String.valueOf(c);
		else
			return "0" + String.valueOf(c);
	}

	/**
	 * 获取当前时间
	 *
	 * @return yyyy-MM-dd HH:mm:ss
	 */

	public static String getNowDate() {

		Calendar c = Calendar.getInstance();// 可以对每个时间域单独修改

		Date nowdate = c.getTime();

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String s = sdf.format(nowdate);

		return s;

	}

	/**
	 * 获取当前时间
	 *
	 * @return yyyy-MM-dd HH:mm:ss
	 */

	public static String getNowDay() {

		Calendar c = Calendar.getInstance();// 可以对每个时间域单独修改

		Date nowdate = c.getTime();

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String s = sdf.format(nowdate);

		return s;

	}

	/**
	 * 返回 2014-9-4 �? 2014-10-13
	 *
	 * @param data
	 * @return
	 */
	public static String proDay(String data) {
		StringBuilder s = new StringBuilder();
		if (null == data) {
			return "";
		}
		String[] sp = data.split("-");
		if (null == sp) {
			return "";
		}
		if (sp.length > 1 && sp[1].startsWith("0")) {
			sp[1] = sp[1].replaceFirst("0", "");
		}

		if (sp.length > 2 && sp[2].startsWith("0")) {
			sp[2] = sp[2].replaceFirst("0", "");
		}
		try {

			s.append(sp[0]).append("-").append(sp[1]).append("-").append(sp[2]);
		} catch (Exception e) {
			// TODO: handle exception
		}

		return s.toString();
	}

	/**
	 * 返回 2014-9-4 �? 2014-10-13 11:11
	 *
	 * @param data
	 * @return
	 */
	public static String proDays(String data) {
		StringBuilder s = new StringBuilder();
		if (null == data || "".equals(data)) {
			return "";
		}

		String[] sps = data.split(" ");

		String[] sp = sps[0].split("-");
		if (null == sp) {
			return "";
		}
		if (sp.length > 1 && sp[1].length() == 1) {
			sp[1] = "0" + sp[1];
		}

		if (sp.length > 2 && sp[2].length() == 1) {
			sp[2] = "0" + sp[2];
		}
		try {

			s.append(sp[0]).append("-").append(sp[1]).append("-").append(sp[2]);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		// "" 11:11
		String[] ssp = sps[1].split(":");
		if (null == ssp) {
			return "";
		}
		if (ssp.length > 1 && ssp[0].length() == 1) {
			ssp[0] = "0" + ssp[0];
		}
		if (ssp.length >= 2 && ssp[1].length() == 1) {
			ssp[1] = "0" + ssp[1];
		}
		try {

			s.append(" ").append(ssp[0]).append(":").append(ssp[1]);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		return s.toString();
	}

	public static String getNowDate(String dateFormat) {

		Calendar c = Calendar.getInstance();// 可以对每个时间域单独修改

		Date nowdate = c.getTime();

		SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
		String s = sdf.format(nowdate);

		return s;

	}

	/**
	 * 截取时间
	 *
	 * @return yyyy-MM-dd HH:mm
	 */

	public static String Sub(String str) {
		if (null != str && !"".equals(str)) {

			return str.split(":").length > 1 ? str.split(":")[0] + ":"
					+ str.split(":")[1] : str;
		} else {
			return "";
		}

	}

	/**
	 * 截取时间
	 *
	 * @return yyyy-MM-dd
	 */

	public static String SubToDay(String str) {
		if (null != str && !"".equals(str)) {

			return str.split(" ").length > 1 ? str.split(" ")[0] : str;
		} else {
			return str;
		}

	}

	/**
	 * 截取时分�?
	 *
	 * @return HH:MM:SS
	 */

	public static String SubToHHMMSS(String str) {
		if (null != str && !"".equals(str)) {

			return str.split(" ").length > 1 ? str.split(" ")[1] : str;
		} else {
			return str;
		}

	}

	/**
	 * 截取时间
	 *
	 * @return yyyy年MM月dd�? HH时mm�?
	 */

	public static String SubChinese(String str) {
		if (null != str && !"".equals(str)) {

			String H = str.split(" ").length > 1 ? str.split(" ")[1] : str;
			String time = H.split(":").length > 1 ? H.split(":")[0] + "�?"
					+ H.split(":")[1] + "�?" : H;
			if (H.length() > 10) {
				return str;
			}
			return SubToDayChinese(str) + time;
		} else {
			return "";
		}

	}

	/**
	 * 截取时间
	 *
	 * @return yyyy年MM月dd�? HH�?
	 */

	public static String SubToH(String str) {
		if (null != str && !"".equals(str)) {

			String H = str.split(" ").length > 1 ? str.split(" ")[1] : str;
			String time = H.split(":").length > 1 ? H.split(":")[0] + "�?" : H;

			return SubToDayChinese(str) + time;
		} else {
			return "";
		}

	}

	/**
	 * 截取时间
	 *
	 * @return yyyy年MM月dd�?
	 *
	 */

	public static String SubToDayChinese(String str) {
		if (null != str && !"".equals(str)) {

			String strtime = str.split(" ").length > 1 ? str.split(" ")[0]
					: str;

			return strtime.split("-").length > 1 ? strtime.split("-")[0] + "�?"
					+ strtime.split("-")[1] + "�?" + strtime.split("-")[2] + "�?"
					: strtime;
		} else {
			return str;
		}

	}

	/**
	 * 截取时间
	 *
	 * @return yyyy年MM月dd�? 11/11/11 00:00:00
	 */

	public static String SubToDayChinese2(String str) {
		if (null != str && !"".equals(str)) {

			String strtime = str.split(" ").length > 1 ? str.split(" ")[0]
					: str;

			return strtime.split("/").length > 1 ? strtime.split("/")[0] + "�?"
					+ strtime.split("/")[1] + "�?" + strtime.split("/")[2] + "�?"
					: strtime;
		} else {
			return str;
		}

	}

	/**
	 * 截取�?
	 *
	 * @return yyyy
	 */

	public static String SubYear(String str) {
		if (null != str && !"".equals(str)) {

			return str.split("-").length > 1 ? str.split("-")[0] : str;
		} else {
			return str;
		}

	}

	/**
	 * 生成指定天数之后的日�?
	 *
	 * @param date
	 *            当前日期
	 * @param dayAgo
	 *            long 多少天，+之后�?-之前
	 * @return
	 */
	public static Date toDayAfter(Date date, long dayAgo) {
		return new Date(date.getTime() + dayAgo * 24 * 60 * 60 * 1000);
	}

	/**
	 * 生成指定天数之前的日�?
	 *
	 * @param date
	 *            当前日期
	 * @param dayAgo
	 *            long 多少天，+之后�?-之前
	 * @return
	 */
	public static Date toDayBefore(Date date, long dayAgo) {
		return new Date(date.getTime() - dayAgo * 24 * 60 * 60 * 1000);
	}

	/**
	 * 将毫�? 转成 "yyyy-MM-dd HH:mm:ss"
	 *
	 * @param longTime
	 * @return
	 */
	public static String toDateString(long longTime) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		Date date = new Date(longTime);

		String time = format.format(date);

		return time;
	}

	/**
	 * 将毫�? 转成 "yyyy-MM-dd HH:mm:ss"
	 *
	 * @param longTime
	 * @return
	 */
	public static String toDateString(long longTime, String dateFormat) {
		SimpleDateFormat format = new SimpleDateFormat(dateFormat);

		Date date = new Date(longTime);

		String time = format.format(date);

		return time;
	}

	/**
	 * �?"yyyy-MM-dd HH:mm:ss"转换�?"yyyy年MM月dd�?"
	 *
	 * @param time
	 * @return
	 */
	public static String strToStr(String time) {
		if (time != null && !"".equals(time)) {
			SimpleDateFormat format = new SimpleDateFormat("yyyy年MM月dd�?");
			String t1 = SubToDay(time);
			Date t2 = toDate(t1);
			return format.format(t2);
		}
		return time;
	}

}
