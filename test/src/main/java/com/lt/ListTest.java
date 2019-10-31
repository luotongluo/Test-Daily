package com.lt;

import java.util.Arrays;
import java.util.List;

/**
 * @Author: LT
 * @Date: 2019/10/22 21:51
 * @Description:
 * @Version 1.0
 */
public class ListTest {
    public static void main(String[] args) {
        List li = null;
//        boolean empty = li.isEmpty();
//        System.out.println(empty);
        String ss = "11,22,33";
        List<String> strings = Arrays.asList( ss.split(","));
        System.out.println("111");
    }
}
