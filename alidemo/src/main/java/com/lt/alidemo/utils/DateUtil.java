package com.lt.alidemo.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * @Author: LT
 * @Date: 2020/2/27 16:34
 * @Description:
 * @Version 1.0
 */
public class DateUtil {
    private static SimpleDateFormat dateformater;
    public static final String formatDefaultTimestamp = "yyyy-MM-dd HH:mm:ss";
    public static final String format_yyyyMMddHHmm = "yyyyMMddHHmm";
    public static final String format_yyyyMMdd = "yyyy-MM-dd";
    public static final String format_yyyyMM = "yyyy-MM";

    public DateUtil() {
    }

    public static boolean isDate(String strDate) {
        Pattern pattern = Pattern.compile("^((\\d{2}(([02468][048])|([13579][26]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])))))|(\\d{2}(([02468][1235679])|([13579][01345789]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|(1[0-9])|(2[0-8]))))))(\\s(((0?[0-9])|([1-2][0-3]))\\:([0-5]?[0-9])((\\s)|(\\:([0-5]?[0-9])))))?$");
        Matcher m = pattern.matcher(strDate);
        return m.matches();
    }

    public static String getStringOfFirstDayInMonth() {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
        String temp = sdf.format(date);
        String firstDayInMoth = "";
        firstDayInMoth = temp + "-01";
        return firstDayInMoth;
    }

    public static Date getDateOfFirstDayInMonth() {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
        String temp = sdf.format(date);
        String firstDayInMoth = "";
        firstDayInMoth = temp + "-01";
        return parseDate(firstDayInMoth);
    }

    public static Date parseDate(String strDate) {
        Date date = null;

        try {
            date = getDateFormater().parse(strDate);
        } catch (Exception var3) {
        }

        return date;
    }

    private static DateFormat getDateFormater() {
        if (dateformater == null) {
            dateformater = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        }

        return dateformater;
    }

    public static Date parseDate(String date, String formatType) {
        SimpleDateFormat f = new SimpleDateFormat(formatType);

        Date innerDate;
        try {
            innerDate = f.parse(date);
        } catch (ParseException var5) {
            innerDate = new Date();
            var5.printStackTrace();
        }

        return innerDate;
    }

    public static String getCurrentFormatDate(String formatType) {
        if (StringUtils.isEmpty(formatType)) {
            formatType = "yyyy-MM-dd HH:mm:ss";
        }

        Locale locale = Locale.SIMPLIFIED_CHINESE;
        SimpleDateFormat dateStyle = new SimpleDateFormat(formatType, locale);
        return dateStyle.format(new Date());
    }

    public static String formatDateTime(Date dt, String formatType) {
        String newDate = "";
        if (dt != null) {
            Locale locale = Locale.CHINESE;
            SimpleDateFormat dateStyle = new SimpleDateFormat(formatType, locale);
            newDate = dateStyle.format(dt);
        }

        return newDate;
    }

    public static Date format(String strDate, String aFormat) {
        SimpleDateFormat formatter = new SimpleDateFormat(aFormat);
        ParsePosition pos = new ParsePosition(0);
        return formatter.parse(strDate, pos);
    }

    public static int getWeek(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.set(13, 0);
        cal.setTime(date);
        return cal.get(7) - 1 == 0 ? 7 : cal.get(7) - 1;
    }

    public static int getMonth(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.set(13, 0);
        cal.setTime(date);
        return cal.get(2) + 1;
    }

    public static Date getYesterday(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(5, -1);
        return cal.getTime();
    }

    public static String getDate(long time, String format) {
        Date d = new Date();
        d.setTime(time);
        DateFormat df = new SimpleDateFormat(format);
        return df.format(d);
    }

    public static Date getDate(long time) {
        Date d = new Date();
        d.setTime(time);
        return d;
    }

    public static long getTimeStampDate(String timeStamp, String format) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String sd = sdf.format(new Date(Long.parseLong(timeStamp + "000")));
        SimpleDateFormat df = new SimpleDateFormat(format);
        Date date = null;

        try {
            date = df.parse(sd);
        } catch (ParseException var8) {
            var8.printStackTrace();
        }

        long s = date.getTime() / 1000L;
        return s;
    }

    public static String getDateTimeStamp(String timeStamp) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String sd = sdf.format(new Date(Long.parseLong(timeStamp + "000")));
        return sd;
    }

    public static String getTimeMinuteAdd(Date date, int x) {
        long new_d = date.getTime() + (long)(x * 60 * 1000);
        return getDate(new_d, "yyyy-MM-dd HH:mm:ss");
    }

    public static Date addDate(int Interval) {
        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        c.add(5, Interval);
        Date date = c.getTime();
        return date;
    }

    public static Date addYear(Date date, int year) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(1, year);
        return c.getTime();
    }

    public static Boolean isInTheTime(Date time, Date starttime, Date endtime) {
        if (time != null && starttime != null && endtime != null) {
            return time.compareTo(starttime) >= 0 && time.compareTo(endtime) <= 0 ? true : false;
        } else {
            return false;
        }
    }

    public static Date getNowStartTime() {
        Calendar todayStart = Calendar.getInstance();
        todayStart.set(11, 0);
        todayStart.set(12, 0);
        todayStart.set(13, 0);
        todayStart.set(14, 0);
        return todayStart.getTime();
    }

    public static Date getNowEndTime() {
        Calendar todayEnd = Calendar.getInstance();
        todayEnd.set(11, 23);
        todayEnd.set(12, 59);
        todayEnd.set(13, 59);
        todayEnd.set(14, 999);
        return todayEnd.getTime();
    }

    public static String getNowTime() {
        Calendar Cld = Calendar.getInstance();
        int YY = Cld.get(1);
        int MM = Cld.get(2) + 1;
        int DD = Cld.get(5);
        int HH = Cld.get(11);
        int mm = Cld.get(12);
        int SS = Cld.get(13);
        int MI = Cld.get(14);
        String valueOf = String.valueOf(YY);
        String MMString = String.valueOf(MM);
        String DDString = String.valueOf(DD);
        String HHString = String.valueOf(HH);
        String mmString = String.valueOf(mm);
        String SSString = String.valueOf(SS);
        String MIString = String.valueOf(MI);
        return valueOf + MMString + DDString + HHString + mmString + SSString + MIString;
    }

    public static Date getEndOfDay(String date) throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date parseDate = simpleDateFormat.parse(date);
        LocalDateTime localDateTime = LocalDateTime.ofInstant(Instant.ofEpochMilli(parseDate.getTime()), ZoneId.systemDefault());
        LocalDateTime endOfDay = localDateTime.with(LocalTime.MAX);
        return Date.from(endOfDay.atZone(ZoneId.systemDefault()).toInstant());
    }

    public static Date getEndTimeOfMonth(String yearMonth) {
        try {
            SimpleDateFormat yearMonthFormat = new SimpleDateFormat("yyyy-MM");
            String firstDate = formatDateTime(yearMonthFormat.parse(yearMonth), "yyyy-MM-dd HH:mm:ss");
            DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            LocalDate localDate = LocalDate.parse(firstDate, dateTimeFormatter);
            String dateFormat = getStartOrEndDayOfMonth(localDate, false);
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date parseDate = simpleDateFormat.parse(dateFormat);
            LocalDateTime localDateTime = LocalDateTime.ofInstant(Instant.ofEpochMilli(parseDate.getTime()), ZoneId.systemDefault());
            LocalDateTime endOfDay = localDateTime.with(LocalTime.MAX);
            return Date.from(endOfDay.atZone(ZoneId.systemDefault()).toInstant());
        } catch (Exception var10) {
            var10.printStackTrace();
            return null;
        }
    }

    public static Date getStartTimeOfMonth(String yearMonth) {
        try {
            SimpleDateFormat yearMonthFormat = new SimpleDateFormat("yyyy-MM");
            String firstDate = formatDateTime(yearMonthFormat.parse(yearMonth), "yyyy-MM-dd HH:mm:ss");
            DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            LocalDate localDate = LocalDate.parse(firstDate, dateTimeFormatter);
            String dateFormat = getStartOrEndDayOfMonth(localDate, true);
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date parseDate = simpleDateFormat.parse(dateFormat);
            LocalDateTime localDateTime = LocalDateTime.ofInstant(Instant.ofEpochMilli(parseDate.getTime()), ZoneId.systemDefault());
            LocalDateTime endOfDay = localDateTime.with(LocalTime.MIN);
            return Date.from(endOfDay.atZone(ZoneId.systemDefault()).toInstant());
        } catch (Exception var10) {
            var10.printStackTrace();
            return null;
        }
    }

    public static Date getStartOfDay(String date) throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date parseDate = simpleDateFormat.parse(date);
        LocalDateTime localDateTime = LocalDateTime.ofInstant(Instant.ofEpochMilli(parseDate.getTime()), ZoneId.systemDefault());
        LocalDateTime startOfDay = localDateTime.with(LocalTime.MIN);
        return Date.from(startOfDay.atZone(ZoneId.systemDefault()).toInstant());
    }

    public static int subYearDate(Date startTime, Date endTime) {
        if (startTime != null && endTime != null) {
            Calendar start = Calendar.getInstance();
            Calendar end = Calendar.getInstance();
            start.setTime(startTime);
            end.setTime(endTime);
            int year = 0;
            if (end.get(1) > start.get(1)) {
                 year = end.get(1) - start.get(1);
                if (end.get(2) + 1 >= start.get(2) + 1) {
                    return end.get(5) >= start.get(5) ? year : year - 1;
                } else {
                    return year - 1;
                }
            } else {
                return year;
            }
        } else {
            return 0;
        }
    }

    public static Long dateTime2TimeStamp(String dateStr, String format) throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
        Date date = simpleDateFormat.parse(dateStr);
        return date.getTime();
    }

    public static Integer getDifMonth(Date startDate, Date endDate) {
        Calendar start = Calendar.getInstance();
        Calendar end = Calendar.getInstance();
        start.setTime(startDate);
        end.setTime(endDate);
        int result = end.get(2) - start.get(2) + 1;
        int month = (end.get(1) - start.get(1)) * 12;
        return Math.abs(month + result);
    }

    public static Integer getDifMonth(String startDateStr, String endDateStr) {
        Date startDate = parseDate(startDateStr);
        Date endDate = parseDate(endDateStr);
        return getDifMonth(startDate, endDate);
    }

    public static List<String> getAllMonthBetween(String minDateStr, String maxDateStr) {
        Date startDate = parseDate(minDateStr);
        Date endDate = parseDate(maxDateStr);
        return getAllMonthBetween(startDate, endDate);
    }

    public static List<String> getAllMonthBetween(Date minDate, Date maxDate) {
        List<String> result = new ArrayList();
        Calendar min = Calendar.getInstance();
        Calendar max = Calendar.getInstance();
        min.setTime(minDate);
        min.set(min.get(1), min.get(2), 1);
        max.setTime(maxDate);
        max.set(max.get(1), max.get(2), 2);
        Calendar curr = min;

        while(curr.before(max)) {
            result.add(formatDateTime(curr.getTime(), "yyyy-MM"));
            curr.add(2, 1);
        }

        return result;
    }

    public static String getStartOrEndDayOfQuarter(LocalDate today, Boolean isFirst) {
        LocalDate resDate = LocalDate.now();
        if (today == null) {
            today = resDate;
        }

        Month month = today.getMonth();
        Month firstMonthOfQuarter = month.firstMonthOfQuarter();
        Month endMonthOfQuarter = Month.of(firstMonthOfQuarter.getValue() + 2);
        if (isFirst) {
            resDate = LocalDate.of(today.getYear(), firstMonthOfQuarter, 1);
        } else {
            resDate = LocalDate.of(today.getYear(), endMonthOfQuarter, endMonthOfQuarter.length(today.isLeapYear()));
        }

        return resDate.toString();
    }

    public static String getStartOrEndDayOfWeek(LocalDate today, Boolean isFirst) {
        LocalDate resDate = LocalDate.now();
        if (today == null) {
            today = resDate;
        }

        DayOfWeek week = today.getDayOfWeek();
        int value = week.getValue();
        if (isFirst) {
            resDate = today.minusDays((long)(value - 1));
        } else {
            resDate = today.plusDays((long)(7 - value));
        }

        return resDate.toString();
    }

    public static String getStartOrEndDayOfMonth(LocalDate today, Boolean isFirst) {
        LocalDate resDate = LocalDate.now();
        if (today == null) {
            today = resDate;
        }

        Month month = today.getMonth();
        int length = month.length(today.isLeapYear());
        if (isFirst) {
            resDate = LocalDate.of(today.getYear(), month, 1);
        } else {
            resDate = LocalDate.of(today.getYear(), month, length);
        }

        return resDate.toString();
    }

    public static String getStartOrEndDayOfYear(LocalDate today, Boolean isFirst) {
        LocalDate resDate = LocalDate.now();
        if (today == null) {
            today = resDate;
        }

        if (isFirst) {
            resDate = LocalDate.of(today.getYear(), Month.JANUARY, 1);
        } else {
            resDate = LocalDate.of(today.getYear(), Month.DECEMBER, Month.DECEMBER.length(today.isLeapYear()));
        }

        return resDate.toString();
    }

    public static int getDateDiffQuarter(Date startDate, Date endDate) {
        String startDateStr = formatDateTime(startDate, "yyyy-MM");
        String endDateStr = formatDateTime(endDate, "yyyy-MM");
        return getDateDiffQuarter(startDateStr, endDateStr);
    }

    public static int getDateDiffQuarter(String startDate, String endDate) {
//        boolean diffQuarter = false;
        String[] startArr = startDate.split("-");
        String[] endArr = endDate.split("-");
        String startYear = startArr[0];
        String startMonth = startArr[1];
        String endYear = endArr[0];
        String endMonth = endArr[1];
        int syi = Integer.parseInt(startYear);
        int smi = Integer.parseInt(startMonth);
        int eyi = Integer.parseInt(endYear);
        int emi = Integer.parseInt(endMonth);
        int diffQuarter = ((eyi - syi) * 12 + (emi - 1)) / 3 - (smi - 1) / 3 + 1;
        return diffQuarter;
    }

    public static int getDateQuarter(Date startDate) {
        String date = formatDateTime(startDate, "yyyy-MM");
//        int quarter = true;
        String[] startArr = date.split("-");
        String startMonth = startArr[1];
        int smi = Integer.parseInt(startMonth);
        int quarter = (smi - 1) / 3 + 1;
        return quarter;
    }

    public static int getDateQuarter(String startDate) {
//        int quarter = true;
        String[] startArr = startDate.split("-");
        String startMonth = startArr[1];
        int smi = Integer.parseInt(startMonth);
        int quarter = (smi - 1) / 3 + 1;
        return quarter;
    }

    public static <T> List<List<T>> splitToPieces(Collection<T> data, int eachPieceSize) {
        if (CollectionUtils.isEmpty(data)) {
            return new ArrayList(0);
        } else if (eachPieceSize <= 0) {
            throw new IllegalArgumentException("参数错误");
        } else {
            List<List<T>> result = new ArrayList();

            for(int index = 0; index < data.size(); index += eachPieceSize) {
                result.add(data.stream().skip((long)index).limit((long)eachPieceSize).collect(Collectors.toList()));
            }

            return result;
        }
    }

    public static Map<Integer, List<String>> getMonthSpilt(List<String> yearMonths) {
        Map<Integer, List<String>> monthMap = new HashMap();
        yearMonths = (List)yearMonths.stream().sorted().collect(Collectors.toList());
        List<List<String>> lists = splitToPieces(yearMonths, 3);
        int i = 1;

        for(Iterator var4 = lists.iterator(); var4.hasNext(); ++i) {
            List<String> months = (List)var4.next();
            List<String> list = (List)monthMap.get(i);
            if (CollectionUtils.isEmpty(list)) {
                monthMap.put(i, months);
            } else {
                months.addAll(list);
                monthMap.put(i, months);
            }
        }

        return monthMap;
    }

    public static Date getBeforeOrAfterDate(Date date, int days) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(5, days);
        date = calendar.getTime();
        return date;
    }

    public static String getBeforeOrAfterDate(String time, int days) {
        Date date = format(time, "yyyy-MM-dd HH:mm:ss");
        date = getBeforeOrAfterDate(date, days);
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return format.format(date);
    }

    public static String getNdaydDate(int days, String format) {
        long nDays = System.currentTimeMillis() + 1L * (long)days * 24L * 60L * 60L * 1000L;
        Date date = new Date(nDays);
        return formatDateTime(date, format);
    }

    public static void main(String[] args) throws ParseException {
        Date d1 = parseDate("2019-06-01 00:00:00");
        Date d2 = parseDate("2020-09-11 23:00:00");
        System.out.println(formatDateTime(addYear(d2, 1), "yyyy-MM-dd HH:mm:ss"));
        System.out.println(formatDateTime(addYear(d2, -1), "yyyy-MM-dd HH:mm:ss"));
        System.out.println(formatDateTime(addYear(d2, 0), "yyyy-MM-dd HH:mm:ss"));
        System.out.println(subYearDate(d2, d1));
        System.out.println(System.currentTimeMillis());
        System.out.println(formatDateTime(new Date(), "yyyy-MM"));
        System.out.println("difMonth:" + getDifMonth(d1, d2));
        System.out.println(JSON.toJSONString(getAllMonthBetween("2019-01-12 11:30:23", "2019-11-12 11:30:24")));
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate localDate = LocalDate.parse("2019-02-01", dateTimeFormatter);
        SimpleDateFormat yearMonth4 = new SimpleDateFormat("yyyy-MM");
        LocalDateTime time3 = LocalDateTime.ofInstant(Instant.ofEpochMilli(yearMonth4.parse("2019-02").getTime()), ZoneId.systemDefault());
        LocalDateTime day = time3.with(LocalTime.MAX);
        System.out.println("=====" + Date.from(day.atZone(ZoneId.systemDefault()).toInstant()));
        System.out.println(getStartOrEndDayOfMonth(localDate, true));
        System.out.println("====================");
        String dateFormat = getStartOrEndDayOfMonth(localDate, false);
        System.out.println(dateFormat);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date parseDate = simpleDateFormat.parse(dateFormat);
        LocalDateTime localDateTime = LocalDateTime.ofInstant(Instant.ofEpochMilli(parseDate.getTime()), ZoneId.systemDefault());
        LocalDateTime endOfDay = localDateTime.with(LocalTime.MAX);
        System.out.println(Date.from(endOfDay.atZone(ZoneId.systemDefault()).toInstant()));
        System.out.println("=====================");
        System.out.println(getStartOrEndDayOfMonth(localDate, false));
        List<String> months = Arrays.asList("2019-03", "2019-02", "2019-04", "2019-05", "2019-06", "2019-07");
        months = (List)months.stream().sorted().collect(Collectors.toList());
        Iterator var14 = months.iterator();

        while(var14.hasNext()) {
            String yearMonth = (String)var14.next();
            String[] yearMonthArr = yearMonth.split("-");
            Integer month = Integer.parseInt(yearMonthArr[1]);
            Integer quarter = (month - 1) / 3 + 1;
            System.out.println("时间" + yearMonth + ",季度：" + quarter);
        }

        String time = String.valueOf(15552000000L);
        BigInteger bigInteger = BigInteger.valueOf(15552000000L);
        System.out.println(bigInteger.toString());
        System.out.println(time);
        System.out.println(BigDecimal.valueOf(10.203D));
        System.out.println(new BigDecimal(10.233D));
        ZoneId zoneId = ZoneId.systemDefault();
        ZonedDateTime zdt = localDate.atStartOfDay(zoneId);
        Date date = Date.from(zdt.toInstant());
        System.out.println(LocalDate.now());
        System.out.println(date);
        List<Integer> list = new ArrayList();
        list.add(123);
        list.add(124);
        System.out.println(JSONArray.toJSONString(list));
        String data = "{\"wid\":\"" + JSONArray.toJSONString(list) + "\",\"pid\":0,\"refid\":0,\"sku\":";
        System.out.println(data);
        List<String> sksu = new ArrayList();
        sksu.add("T12006535A");
        System.out.println(JSONObject.toJSONString(sksu));
        String json = "{\"code\":1,\"218\":{\"sku\":{\"T12006801A\":0,\"X330000501\":134}},\"338\":{\"sku\":{\"X330000501\":0,\"T12006801A\":0}},\"421\":{\"sku\":{\"X330000501\":66,\"T12006801A\":0}},\"137\":{\"sku\":{\"X330000501\":33,\"T12006801A\":0}},\"42\":{\"sku\":{\"X330000501\":1148,\"T12006801A\":0}},\"33\":{\"sku\":{\"X330000501\":0,\"T12006801A\":0}},\"223\":{\"sku\":{\"X330000501\":0,\"T12006801A\":0}}}";
//        Header header = new Header();
//        System.out.println(Optional.ofNullable(header).isPresent());
//        System.out.println(Optional.ofNullable(header));
        System.out.println(formatDateTime(getEndOfDay("2020-01-02 00:00:00"), "yyyy-MM-dd HH:mm:ss"));
        Set hashSet = new HashSet();
        hashSet.add("dsd");
        hashSet.add("444");
        System.out.println(String.join(",", hashSet));
        List<String> years = new ArrayList();
        years.add("2020-02");
        years.add("2020-03");
        years.add("2020-04");
        years.add("2020-05");
        years.add("2020-06");
        years.add("2020-07");
        years.add("2020-08");
//        List<String> years = (List)years.stream().sorted().collect(Collectors.toList());
        List<List<String>> listList = splitToPieces(years, 3);
        Iterator var27 = listList.iterator();

        while(var27.hasNext()) {
            List<String> list1 = (List)var27.next();
            System.out.println("-----------");
            System.out.println(JSON.toJSONString(list1));
        }

        System.out.println("--------" + getStartTimeOfMonth("2019-02"));
        System.out.println("--------" + getEndTimeOfMonth("2019-02"));
        Date nowDate = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(nowDate);
        calendar.add(5, -2);
        nowDate = calendar.getTime();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateString = format.format(nowDate);
        System.out.println("结果：" + dateString);
        System.out.println(getBeforeOrAfterDate((String)"2020-02-19 00:00:00", 9));
        System.out.println(getBeforeOrAfterDate((Date)(new Date()), 9));
    }
}
