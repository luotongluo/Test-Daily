package com.lt.demo.test;

import org.junit.Test;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.TemporalAdjusters;
import java.util.Arrays;

/**
 * @Author: LT
 * @Date: 2019/4/15 11:13
 * @Description:
 * @Version 1.0
 */
public class TimeTest {
    @Test
    public void today(){
        LocalDate today = LocalDate.now();
        System.out.println(today);
    }

    @Test
    public void parseString(){
        // 严格按照ISO yyyy-MM-dd验证，02写成2都不行，当然也有一个重载方法允许自己定义格式
        LocalDate date = LocalDate.parse("2016-02-05");
        System.out.println(date);
    }

    @Test
    public void calculate(){
        LocalDate today = LocalDate.now();
        LocalDate firstDayOfThisMonth = today.with(TemporalAdjusters.firstDayOfMonth());
        System.out.println(firstDayOfThisMonth);

        // 取本月第2天：
        LocalDate secondDayOfThisMonth = today.withDayOfMonth(2);
        System.out.println(secondDayOfThisMonth);

        // 取本月最后一天，再也不用计算是28，29，30还是31：
        LocalDate lastDayOfThisMonth = today.with(TemporalAdjusters.lastDayOfMonth());
        System.out.println(lastDayOfThisMonth);

        // 取下一天：
        LocalDate nextDay = lastDayOfThisMonth.plusDays(1);
        System.out.println(nextDay);

        // 取2015年1月第一个周一，这个计算用Calendar要死掉很多脑细胞：
        LocalDate firstMondayOf2015 = LocalDate.parse("2015-01-01").with(TemporalAdjusters.firstInMonth(DayOfWeek.MONDAY));
        System.out.println(firstMondayOf2015);
    }

    @Test
    public void getTime(){
        LocalTime now = LocalTime.now();
        System.out.println(now);
    }

    @Test
    public void getTimeWithoutMillis(){
        LocalTime now = LocalTime.now().withNano(0);
        System.out.println(now);
    }

    @Test
    public void parseTime(){
        LocalTime zero = LocalTime.of(0, 0, 0); // 00:00:00
        System.out.println(zero);

        LocalTime mid = LocalTime.parse("12:00:00"); // 12:00:00
        System.out.println(mid);
    }

//    java.lang and java.util Packages
//    比如数组的并行排序
    public class UtilDemo {

        int[] data = {4,12,1,3,5,7,9};

        @Test
        public void parallelSort(){
            Arrays.parallelSort(data);
            System.out.println(Arrays.toString(data));
        }

        @Test
        public void testCollectPrallel() {
            //[4, 16, 17, 20, 25, 32, 41]
            Arrays.parallelPrefix(data, Integer::sum);
            System.out.println(Arrays.toString(data));
        }
    }

}
