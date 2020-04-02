package com.lt.utils.utils.tools;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class RandomUtil {
    public RandomUtil() {
    }

    public static String getMessgeId() {
        StringBuilder result = new StringBuilder();
        result.append(getCurrTime()).append(assembleRandom());
        return result.toString();
    }

    public static String assembleRandom() {
        StringBuilder str = new StringBuilder();
        Random random = new Random();

        for(int i = 0; i < 12; ++i) {
            str.append(random.nextInt(10));
        }

        return str.toString();
    }

    public static String assembleRandom(int n) {
        StringBuilder str = new StringBuilder();
        Random random = new Random();

        for(int i = 0; i < n; ++i) {
            str.append(random.nextInt(10));
        }

        return str.toString();
    }

    public static String getCurrTime() {
        Date date = new Date();
        DateFormat format = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        String time = format.format(date);
        return time;
    }

    public static void main(String[] args) {
    }

    public static String autoGenericCode(Long code, int num) {
        String codeStr = String.valueOf(code);
        if (codeStr.length() > num) {
            codeStr = codeStr.substring(codeStr.length() - num - 1, codeStr.length() - 1);
        } else {
            codeStr = String.format("%0" + num + "d", code);
        }

        return codeStr;
    }
}
