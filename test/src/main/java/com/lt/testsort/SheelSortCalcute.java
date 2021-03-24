package com.lt.testsort;

import com.lt.sort;
import org.springframework.util.CollectionUtils;

import java.util.Arrays;

/**
 * @author tong.luo
 * @description SheelSortCalcute
 * @date 2020/7/4 21:59
 */
public class SheelSortCalcute implements SortSolt {
    public static void main(String[] args) {
        Integer[] aa = {6, 5, 4, 3, 2, 2, 1, 0};
        SortSolt sortSolt = new SheelSortCalcute();
        sortSolt.sort(aa);
        System.out.println(Arrays.toString(aa));
    }

    /**
     * 对数组中的元素进行排序
     * xier排序
     *
     * @param aa
     * @return
     */
    @Override
    public Object sort(Comparable[] aa) {
        /**
         * xier排序中是对数组中的元素进行分组的插入算法
         * 如果该索引处的值大于前面的值的时候不用排序
         * 如果小于前面的值 则需要交换两处的值
         */
        int h = 1;
        while (h < aa.length / 2) {
            //得到最大的h的值  分组的h值
            h = 2 * h + 1;
        }
        while (h >= 1) {
            for (int a = h; a < aa.length; a++) {
//            for (int j = aa.length; j > a; j -= h) {
                for (int j = a; j >= h; j -= h) {
//                while (greater(aa[j],aa[a])){
//                    exchage(aa,j,a);
//                }
                    if (greater(aa[j - h], aa[j])) {
                        exchage(aa, j - h, a);
                    } else {
                        break;
                    }
                }
            }
            h = h / 2;
        }

        return aa;
    }

    /**
     * 比较a元素是否大于b元素 大于则返回的是true
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
