package com.lt.alidemo.utils;

import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

public class DateUtils {

    public static void main(String[] args) {
        String stringToday = DateUtils.dateToString(new Date());
        System.out.println(stringToday);
    }

    /**
     * 得到现在时间
     *
     * @return 字符串 yyyyMMdd HHmmss
     */
    public static String getStringToday() {
        Date currentTime = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd HHmmss");
        String dateString = formatter.format(currentTime);
        return dateString;
    }

    /**
     * 指定的date类型时间转换为指定格式的字符串
     *
     * @param date yyyy-MM-dd HH:mm:ss 24小时 hh12小时时间
     * @return
     */
    public static String dateToString(Date date) {
        String str = "yyyy-MM-dd HH:mm:ss";
        SimpleDateFormat format = new SimpleDateFormat(str);
        String dateFormat = format.format(date);
        return dateFormat;
    }

    /**
     * 日期修改的类 例如对分钟进行修改，减去或者加上多少分钟
     *
     * @param date
     * @param data
     * @return
     */
    public static String dateToStringMod(Date date, int data) {
        String str = "yyyy-MM-dd HH:mm:ss";
        SimpleDateFormat format = new SimpleDateFormat(str);
        /*
         * date = new Date(); data = (long) -6;
         */
        String dateFormat = format.format(date.getTime() + (long) data * 1000 * 60);
        return dateFormat;
    }

    /**
     * 日期修改的类 例如对小时进行修改，减去或者加上多少分钟
     *
     * @param date
     * @param data
     * @return
     */
    public static String dateToStringModHour(Date date, int data) {
        String str = "yyyy-MM-dd HH:mm:ss";
        SimpleDateFormat format = new SimpleDateFormat(str);
        /*
         * date = new Date(); data = (long) -6;
         */
        String dateFormat = format.format(date.getTime() + (long) data * 1000 * 60 * 60);
        return dateFormat;
    }

    /**
     * 把指定的日期格式的字符串转换成Date类型
     *
     * @param string
     * @return
     */
    public static Date stringToDate(String string) {
        String str = "yyyy-MM-dd HH:mm:ss";
        SimpleDateFormat format = new SimpleDateFormat(str);
        Date date = new Date();
        try {
            date = format.parse(string);
        } catch (Exception e) {
            e.getStackTrace();
        }
        return date;
    }

    /**
     * 获取当前时间的Date类型的日期  yyyy-MM-dd
     *
     * @return
     */
    public static Date getNowDateToDay() {
        Date currentTime = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String dateString = formatter.format(currentTime);
        Date parse = null;
        try {
            parse = formatter.parse(dateString);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return parse;
    }

    /**
     * 将短时间格式时间转换为字符串 yyyy-MM-dd
     *
     * @param dateDate
     * @return
     */
    public static String dateToStr(Date dateDate) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String dateString = formatter.format(dateDate);
        return dateString;
    }

    /**
     * 将短时间格式时间转换为字符串 yyyy年MM月dd日
     *
     * @param dateDate
     * @return
     */
    public static String dateToStrForPop(Date dateDate) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy年MM月dd日");
        String dateString = formatter.format(dateDate);
        return dateString;
    }

    /**
     * 将短时间格式字符串转换为时间 yyyy-MM-dd
     *
     * @param strDate
     * @return
     */
    public static Date strToDate(String strDate) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        ParsePosition pos = new ParsePosition(0);
        Date strtodate = formatter.parse(strDate, pos);
        return strtodate;
    }

    /**
     * 得到现在时间
     *
     * @return
     */
    public static Date getNow() {
        Date currentTime = new Date();
        return currentTime;
    }

    /**
     * 生成格式为yyyymmddhhmmss+k位随机数
     *
     * @param k 表示是取几位随机数，可以自己定
     */
    public static synchronized String getNo(int k) {
        return getUserDate("yyyyMMddhhmmss") + getRandom(k);
    }


    /**
     * 根据用户传入的时间表示格式，返回当前时间的格式 如果是yyyyMMdd，注意字母y不能大写。
     *
     * @param sformat yyyyMMddhhmmss
     * @return
     */
    public static String getUserDate(String sformat) {
        Date currentTime = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat(sformat);
        String dateString = formatter.format(currentTime);
        return dateString;
    }

    /**
     * 返回一个随机数
     *
     * @param i
     * @return
     */
    public synchronized static String getRandom(int i) {
        Random jjj = new Random();
        // int suiJiShu = jjj.nextInt(9);
        if (i == 0)
            return "";
        String jj = "";
        for (int k = 0; k < i; k++) {
            jj = jj + jjj.nextInt(9);
        }
        return jj;
    }

    /**
     * 通过时间秒毫秒数判断两个时间的间隔
     *
     * @param date1
     * @param date2
     * @return
     */
    public static int differentDaysByMillisecond(Date date1, Date date2) {
        int days = (int) ((date2.getTime() - date1.getTime()) / (1000 * 3600 * 24));
        return days;
    }

    /**
     * 日期修改的类 例如对分钟进行修改，减去或者加上多少天
     *
     * @param date
     * @param data
     * @return
     */
    public static String dateToStringModDate(Date date, int data) {
        String str = "yyyyMMdd";
        SimpleDateFormat format = new SimpleDateFormat(str);
        String dateFormat = format.format(date.getTime() + (long) data * 1000 * 60 * 60 * 24);
        return dateFormat;
    }

    /**
     * 得到现在时间
     *
     * @return 字符串 yyyyMMdd
     */
    public static String dateToStringModDate(Date date) {
        String str = "yyyyMMdd";
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.YEAR, -1);
        SimpleDateFormat format = new SimpleDateFormat(str);
        String dateFormat = format.format(calendar.getTime());
        return dateFormat;
    }

}
