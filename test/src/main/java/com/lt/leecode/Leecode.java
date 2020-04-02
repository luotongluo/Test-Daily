package com.lt.leecode;

/**
 * @Author: LT
 * @Date: 2019/10/14 12:18
 * @Description:
 * @Version 1.0
 */
public class Leecode {
    public int rob(int[] nums) {
        int length = nums.length;
        if (length == 1) {
            return nums[0];
        } else if (length == 2) {
            return nums[0] > nums[1] ? nums[0] : nums[1];
        } else if (length == 0) {
            return 0;
        } else {
//            1,35,7,9
            for (int iter = 0; iter < length; iter++) {
                //相邻的两个不能同时存在
//                if(){
//
//                }

            }
        }
        return 0;
    }
}
