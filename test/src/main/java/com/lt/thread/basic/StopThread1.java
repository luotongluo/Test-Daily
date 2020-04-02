package com.lt.thread.basic;

/**
 * @author Lt
 * @description ThreadTest1
 * 终止线程：一般来说，线程执行完毕就会结束，无须自动关闭。但是对于一些while true的线程如果想关闭呢？Thread类里有stop()方法，但是已经不建议使用了。因为它是强制停止线程，无论线程处于什么状态，很容易出现线程正在处理一半数据被停止的情况，这样非常容易造成数据不一致问题。所以慎用stop()(最好不用)，通过下边这种方式来停止哪些无限循环的线程：
 * @date 2020/3/13 10:21
 */
public class StopThread1 extends Thread {
    volatile boolean stopme = false;

    /**
     * 停止方法
     */
    public void stopMe() {
        stopme = true;
    }

    @Override
    public void run() {
        while (true) {
            if (stopme) {
                System.out.println("Exit by stop me!");
                break;
            }
            System.out.println(System.currentTimeMillis());
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();

            }
        }
    }
}
