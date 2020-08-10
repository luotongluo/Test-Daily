package com.lt.other;

import com.alibaba.fastjson.JSON;

import java.util.ArrayList;
import java.util.List;

/**
 * @author tong.luo
 * @description Calcute
 * @date 2020/8/7 21:35
 */
public class Calcute {
    public static void main(String[] args) {
//        b*(y-x^b)
        long start = System.currentTimeMillis();
        double b = -50;
        double incr = 1;
        double endVal = 50;
        int loop = 10;
//        List<Integer> listA = getListNums(1, 10);
//        List<Integer> listB = getListNums(11, 20);
        List<Double> listB = geRantListNums(loop);
        List<Double> listA = geRantListNums(loop);
        System.out.println("lista :: valX"+ JSON.toJSON(listA)+
                "::::"+"listB :: valY"+ JSON.toJSON(listB));
        for (double a = b; a < endVal; a += incr) {
//            int size = listA.size();
            double sum = 0D;
            for (int i = 0; i < loop; i++) {
                Double valY = listB.get(i);
                Double valX = listA.get(i);
                double pow = Math.pow(valX.doubleValue(), Double.valueOf(a));
//                System.out.println("pow:" + pow + " valX:" + valX + " 次方值:" + a);
                double val = a * (valY - pow);
                sum = val + sum;
            }
            System.out.println(sum + " b:" + a);
        }
        System.out.println("耗时：" + (System.currentTimeMillis() - start));
    }

    /**
     * 求出数据中的数组的值
     *
     * @param integer1
     * @param integer2
     * @return
     */
    public static List<Integer> getListNums(Integer integer1, Integer integer2) {
        ArrayList<Integer> retArr = new ArrayList<>(16);
        if (integer1 != null && integer2 != null && integer2.compareTo(integer1) > 0) {
            for (int a = integer1; a <= integer2; a++) {
                retArr.add(a);
            }
        }
        return retArr;
    }

    public static List<Double> geRantListNums(int loop) {
        ArrayList<Double> retArr = new ArrayList<>(16);
        for (int i = 0; i < loop; i++) {
            double random = Math.random();
            retArr.add(random);
        }
        return retArr;
    }


}
