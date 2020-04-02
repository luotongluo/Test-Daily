package com.lt.expetiontest;

/**
 * @Author: LT
 * @Date: 2019/11/21 17:17
 * @Description:
 * @Version 1.0
 */
public class ExceptionTest {
    public static void main(String[] args) {

        try {
            testException1(5);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println(e.getStackTrace());
            System.out.println(e.getCause());
            System.out.println(e.getLocalizedMessage());
            System.out.println(e.getClass());
        }
    }

    private static void testException1(int i)  {
        if ( i ==5)
            throw new  RuntimeException("123");
    }
}
