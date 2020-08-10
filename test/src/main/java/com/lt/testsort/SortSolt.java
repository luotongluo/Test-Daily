package com.lt.testsort;

/**
 * @author tong.luo
 * @description Sort
 * @date 2020/7/3 17:00
 */
public interface SortSolt {
    /**
     * 对数组中的元素进行排序
     * @param aa
     * @return
     */
    public  Object sort(Comparable[] aa);

    /**
     * 比较a元素是否大于b元素 大于则返回的是true
     * @param a
     * @param b
     * @return
     */
    public boolean greater(Comparable a,Comparable b);

    /**
     * 数组中的 元素交换位置
     * @param a
     * @param c
     * @param b
     * @return
     */
    public Integer exchage(Comparable[] a ,int c ,int b);
}
