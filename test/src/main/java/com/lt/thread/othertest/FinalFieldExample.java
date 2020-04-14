package com.lt.thread.othertest;

/**
 * @author luotong
 * @description FinalFieldExample
 * @date 2020/4/13 17:17
 */
public class FinalFieldExample {
    final int x;
    int y;
    static FinalFieldExample f;
    public FinalFieldExample() {
        x = 3;
        y = 4;
    }

    static void writer() {
        f = new FinalFieldExample();
    }

    static void reader() {
        if (f != null) {
            int i = f.x;
            int j = f.y;
            System.out.println(i);
            System.out.println(j);
        }
    }

    public static void main(String[] args) throws Exception{
        reader();
        Object obj = new Object();
        obj.wait();
        obj.notify();
    }
}
