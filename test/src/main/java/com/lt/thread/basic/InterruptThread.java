package com.lt.thread.basic;

/**
 * @author Administrator
 * @description InterruptThread
 * 是一种线程协作机制。是告诉目标线程一个中断通知，至于接到通知后如何处理，则有线程自己决定处理。
 * @date 2020/3/13 10:28
 */
public class InterruptThread {
    /**
     * public void interrupt() 中断线程
     * public static boolean interrupted() 判断是否被中断，并清除当前中断标识
     * public boolean isInterrupted() 判断是否被中断
     */
    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread() {
            @Override
            public void run() {
                while (true) {
                    //判断如果有中断标识，则直接跳出
                    if (Thread.currentThread().isInterrupted()) {
                        System.out.println("exit by interrupted");
                        break;
                    }

                    System.out.println(System.currentTimeMillis());
                    Thread.yield();
                }
            }
        };

        t1.start();
        Thread.sleep(5000);
        //打上中断标识
        t1.interrupt();
    }
}
