package com.lt;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: LT
 * @Date: 2019/11/12 20:32
 * @Description:
 * @Version 1.0
 */
public class Test2 {
    public static void main(String[] args) {
        List<String> negativeShopList =new ArrayList<>();
        negativeShopList.add("571");
        String ss= "571";
        String s1= "1";
        boolean contains =negativeShopList.contains(ss);
        boolean contains2 =negativeShopList.contains(1);
        System.out.println(contains);
        System.out.println(contains2);
    }
}
