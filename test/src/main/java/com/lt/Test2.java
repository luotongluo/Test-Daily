package com.lt;

import org.springframework.util.StringUtils;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * @Author: LT
 * @Date: 2019/11/12 20:32
 * @Description:
 * @Version 1.0
 */
public class Test2 {
    public static void main(String[] args) {
        String ss = "";
        String s1 = null;
        List<String> onelist = null;
        if (!StringUtils.isEmpty(ss)) {
            String[] leve1 = ss.split(",");
            onelist = Arrays.asList(leve1);
        }

        HashMap<Integer, List<String>> cityListMap = new HashMap<>();
        cityListMap.put(1, onelist);
        cityListMap.put(2, onelist);
        System.out.println("11");
    }
}
