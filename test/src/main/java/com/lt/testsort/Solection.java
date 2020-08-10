package com.lt.testsort;

import java.util.Arrays;
import java.util.List;

/**
 * @author tong.luo
 * @description Solection
 * @date 2020/7/3 16:59
 */
public class Solection implements SortSolt {
    public static void main(String[] args) {
        Integer[] aa = {4,3,2,1,5,0,8,7};
        Solection solection = new Solection();
        solection.sort(aa);
        System.out.println(Arrays.toString(aa));
    }
    /**
     * 对数组中的元素进行排序
     *
     * @param aa
     * @return
     */
    @Override
    public Object sort(Comparable[] aa) {
        //选择排序
        for (int i = 0 ; i < aa.length ; i++) {
            //选择排序将 最小的元素放在首位，最大的元素放在末尾
            Integer minIndex = i;
            for( int j = i;j < aa.length;j++){
                //比较的是 第二个元素后面的值
                boolean greater = greater(aa[minIndex], aa[j]);
                if(greater){
                    minIndex = j;
                }
            }
            exchage(aa,i,minIndex);

        }
        return aa;
    }

    /**
     * 比较a元素是否大于b元素
     *
     * @param a
     * @param b
     * @return
     */
    @Override
    public boolean greater(Comparable a, Comparable b) {
        return a.compareTo(b) > 0;
    }

    /**
     * 数组中的 元素交换位置
     *
     * @param a
     * @param c
     * @param b
     * @return
     */
    @Override
    public Integer exchage(Comparable[] a, int c, int b) {
        Comparable temp;
        temp = a[c];
        a[c] = a[b];
        a[b] = temp;
        return null;
    }
}
