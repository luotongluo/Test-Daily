package com.lt;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

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
//        List<String> strings = Arrays.asList( ss.split(","));
        List<String> strings = null;
        CopyOnWriteArrayList copyOnWriteArrayList = new CopyOnWriteArrayList(strings);
        boolean empty = copyOnWriteArrayList.isEmpty();
        System.out.println("111");
    }
}
