package com.ek.mobileapp.utils;

import static android.text.format.DateUtils.MINUTE_IN_MILLIS;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.StringTokenizer;

import android.text.format.DateUtils;

public class TimeTool {
    private static SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private static SimpleDateFormat formatDate = new SimpleDateFormat("yyyy-MM-dd");

    private static Calendar calendar = Calendar.getInstance();

    public static int getYear() {
        return calendar.get(Calendar.YEAR);
    }

    public static int getMonth() {
        return calendar.get(Calendar.MONTH);
    }

    public static int getDay() {
        return calendar.get(Calendar.DAY_OF_MONTH);
    }

    public static int getHour() {
        return calendar.get(Calendar.HOUR_OF_DAY);
    }

    public static int getMinute() {
        return calendar.get(Calendar.MINUTE);
    }

    public static String getweekStartDate() {
        Calendar c = Calendar.getInstance();
        c.setTime(TimeTool.getCurrentTime());
        int weekDay = c.get(Calendar.DAY_OF_WEEK) == 1 ? 8 : c.get(Calendar.DAY_OF_WEEK);
        c.add(Calendar.DATE, Calendar.MONDAY - weekDay);
        Date start = c.getTime();
        return String.format("%1$tY-%1$tm-%1$td", start);

    }

    public static String getweekEndDate() {
        Calendar c = Calendar.getInstance();
        c.setTime(TimeTool.getCurrentTime());
        int weekDay = c.get(Calendar.DAY_OF_WEEK) == 1 ? 8 : c.get(Calendar.DAY_OF_WEEK);
        c.add(Calendar.DATE, Calendar.MONDAY - weekDay);
        c.add(Calendar.DATE, 6);
        Date end = c.getTime();
        return String.format("%1$tY-%1$tm-%1$td", end);

    }

    /*
     * 周一是0 ，周二是1，。。。周六是5，周日是6
     */
    public static String getDayofWeek(int week) {
        if (week == 6) {
            return getweekEndDate();
        } else {
            Calendar c = Calendar.getInstance();
            c.setTime(TimeTool.getCurrentTime());
            int weekDay = c.get(Calendar.DAY_OF_WEEK) == 1 ? 8 : c.get(Calendar.DAY_OF_WEEK);
            c.add(Calendar.DATE, week + 2 - weekDay);
            Date day = c.getTime();
            return String.format("%1$tY-%1$tm-%1$td", day);
        }
    }

    /*
     * 获取今天是周几
     */
    public static int getWeekIndexbyDay() {
        int weekIndex = calendar.get(Calendar.DAY_OF_WEEK);
        if (weekIndex == 0) {
            return 6;
        } else {
            return weekIndex - 2;
        }
    }

    public static String getDateFormatedFromDataPicker(int y, int m, int d) {
        String month;
        String day;
        if (m < 9)
            month = "0" + (m + 1);
        else
            month = (m + 1) + "";
        if (d < 10)
            day = "0" + d;
        else
            day = d + "";
        return y + "-" + month + "-" + day;
    }

    public static String getTimeFormatedFromTimePicker(int h, int m) {
        String hour;
        String minute;
        if (h < 10) {
            hour = "0" + h;
        } else {
            hour = h + "";
        }
        if (m < 10) {
            minute = "0" + m;
        } else {
            minute = m + "";
        }
        return hour + ":" + minute;
    }

    public static Date getCurrentTime() {
        return new Date(System.currentTimeMillis());
    }

    public static String getTimeFormated(Date d) {
        return format.format(d);
    }

    public static String getDateFormated(Date d) {
        return formatDate.format(d);
    }

    public static String getCycleDateFormated(Date d) {
        int day = d.getDate() + 1;
        d.setDate(day);
        return formatDate.format(d);
    }

    /**
     * 返回两个日期之间的天数（相同天数，返回值为0）
     *
     * @param startDate
     *            开始日期
     * @param endDate
     *            结束日期
     * @return 天数
     */
    public static long getDayTotal(String startDate, String endDate) {

        Calendar startCal, endCal;
        int lyear, lmonth, lday;

        lyear = Integer.parseInt(getDateYear(startDate));
        lmonth = Integer.parseInt(getDateMonth(startDate));
        lday = Integer.parseInt(getDateDay(startDate));
        startCal = new GregorianCalendar(lyear, lmonth - 1, lday);

        lyear = Integer.parseInt(getDateYear(endDate));
        lmonth = Integer.parseInt(getDateMonth(endDate));
        lday = Integer.parseInt(getDateDay(endDate));
        endCal = new GregorianCalendar(lyear, lmonth - 1, lday);

        return differenceInDays(endCal, startCal);
    }

    /**
     * 返回指定日期的年份值
     */
    public static String getDateYear(String date) {
        String year = "";

        String dateSplit = getDateSplit();
        if (dateSplit.equals("."))
            dateSplit = "\\.";

        StringTokenizer filter = new StringTokenizer(date, dateSplit);
        year = filter.nextToken();

        return year;
    }

    /**
     * 返回指定日期的月份值
     */
    public static String getDateMonth(String date) {
        String month = "";

        String dateSplit = getDateSplit();
        if (dateSplit.equals("."))
            dateSplit = "\\.";

        StringTokenizer filter = new StringTokenizer(date, dateSplit);
        month = filter.nextToken();
        month = filter.nextToken();

        return month;
    }

    /**
     * 返回指定日期的日期值
     */
    public static String getDateDay(String date) {
        String day = "";

        String dateSplit = getDateSplit();
        if (dateSplit.equals("."))
            dateSplit = "\\.";

        StringTokenizer filter = new StringTokenizer(date, dateSplit);
        day = filter.nextToken();
        day = filter.nextToken();
        day = filter.nextToken();

        return day;
    }

    /**
     * 返回当前系统采用的日期分隔符
     */
    public static String getDateSplit() {
        return "-";
    }

    public static long differenceInDays(Calendar endDate, Calendar startDate) {
        int MILLIS_IN_DAY = 1000 * 60 * 60 * 24;
        long endInstant = endDate.getTimeInMillis();
        int presumedDays = (int) ((endInstant - startDate.getTimeInMillis()) / MILLIS_IN_DAY);
        Calendar cursor = (Calendar) startDate.clone();
        cursor.add(Calendar.DAY_OF_YEAR, presumedDays);
        long instant = cursor.getTimeInMillis();
        if (instant == endInstant)
            return presumedDays;
        final int step = instant < endInstant ? 1 : -1;
        do {
            cursor.add(Calendar.DAY_OF_MONTH, step);
            presumedDays += step;
        } while (cursor.getTimeInMillis() != endInstant);
        return presumedDays;
    }

    /**
     * 为字符型日期增加天数
     */
    public static String addDays(String dateStr, int days) {
        Date date = getStrDateToDate(dateStr);
        date = addDays(date, days);

        SimpleDateFormat formatter;
        formatter = new SimpleDateFormat("yyyy" + getDateSplit() + "MM" + getDateSplit() + "dd");
        String dateString = formatter.format(date);

        return dateString.trim();
    }

    /**
     * 在指定的日期上增减天数
     *
     * @param date
     * @param days
     * @return
     */
    public static Date addDays(Date date, int days) {
        Calendar cal = new GregorianCalendar();
        cal.setTime(date);
        cal.add(Calendar.DATE, days);
        return cal.getTime();
    }

    /**
     * 转换字符日期值到日期符，字符日期值的日期分隔符为jsite-config.xml定义
     */
    public static Date getStrDateToDate(String dateStr) {
        String dateSplit = getDateSplit();
        if (dateSplit.equals("."))
            dateSplit = "\\.";
        StringTokenizer filter = new StringTokenizer(dateStr, dateSplit);
        int year = Integer.parseInt(filter.nextToken());
        int mon = Integer.parseInt(filter.nextToken()) - 1;
        int day = Integer.parseInt(filter.nextToken());

        Calendar cal = new GregorianCalendar(year, mon, day);
        return cal.getTime();
    }

    /**
     * Get relative time for date
     *
     * @param date
     * @return relative time
     */
    public static CharSequence getRelativeTime(final Date date) {
        long now = System.currentTimeMillis();
        if (Math.abs(now - date.getTime()) > 60000)
            return DateUtils.getRelativeTimeSpanString(date.getTime(), now,
                    MINUTE_IN_MILLIS);
        else
            return "just now";
    }

    public static String nowDateString(String format) {
        SimpleDateFormat df = new SimpleDateFormat(format);
        return df.format(new Date());
    }
}
