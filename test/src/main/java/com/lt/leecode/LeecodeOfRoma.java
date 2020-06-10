package com.lt.leecode;

import org.apache.commons.lang.StringUtils;

import java.util.HashMap;

/**
 * @author Administrator
 * @description LeecodeOfRoma
 * @date 2020/5/23 15:38
 */
public class LeecodeOfRoma {
    public static void main(String[] args) {
        String val = "MCMXCIV";
        int romanToInt = new LeecodeOfRoma().romanToInt1(val);
        System.out.println(romanToInt);
    }
    public int romanToInt(String s) {
        if (s == null || s.equals("")) {
            return 0;
        }
        HashMap<String, Integer> hashMap = new HashMap<>(16);
        hashMap.put("I", 1);
        hashMap.put("V", 5);
        hashMap.put("X", 10);
        hashMap.put("L", 50);
        hashMap.put("C", 100);
        hashMap.put("D", 500);
        hashMap.put("M", 1000);
        /*
        需要判断传入的值在最大值的左边还是右边
        如果最大值在左边 则是每个值相加
        如果最大值是在右边的话 则是最大值减去每个值
         */
        char[] chars = s.toCharArray();
        int length = s.length();
        Integer maxval = 0;
        Integer endVal = 0;
            endVal  = hashMap.get(String.valueOf(chars[0]));
        for (int a = 0; a < length; a++) {
            Integer integer = hashMap.get(String.valueOf(chars[a]));
            if(endVal > integer){
                maxval =   - maxval  +  integer;
            }else {
                maxval = maxval +  integer;
            }
        }
        return maxval;
    }

    public int romanToInt1(String s) {
        int sum = 0;
        int preNum = getValue(s.charAt(0));
        for(int i = 1;i < s.length(); i ++) {
            int num = getValue(s.charAt(i));
            if(preNum < num) {
                sum -= preNum;
            } else {
                sum += preNum;
            }
            preNum = num;
        }
        sum += preNum;
        return sum;
    }

    private int getValue(char ch) {
        switch(ch) {
            case 'I': return 1;
            case 'V': return 5;
            case 'X': return 10;
            case 'L': return 50;
            case 'C': return 100;
            case 'D': return 500;
            case 'M': return 1000;
            default: return 0;
        }
    }

}
