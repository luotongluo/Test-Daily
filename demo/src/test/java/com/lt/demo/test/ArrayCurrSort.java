package com.lt.demo.test;

import org.junit.Test;

import java.util.Arrays;

/**
 * @Author: LT
 * @Date: 2019/4/15 11:16
 * @Description:比如数组的并行排序
 * @Version 1.0
 */
public class ArrayCurrSort {
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
