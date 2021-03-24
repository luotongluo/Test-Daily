package com.lt.thread.cooperation;

import java.util.concurrent.locks.LockSupport;

/**
 * @author Administrator
 * @description LockSupportTest
 * LockSupport：上篇讲了挂起（suspend）和继续执行（resume），其中都是JDK不建议使用的，
 * 也说到它的不好处。而LockSupport的静态方法park()可以阻塞当前线程，unpark()继续执行，
 * 利用的就是信号量的原理，它为每个线程准备了一个许可证，如果许可证可用，则park()立即返回，
 * 并消费许可，如果不可用则进行阻塞；而unpark()则使这个许可变为可用。许可为唯一的
 * ————————————————
 * 版权声明：本文为CSDN博主「~小龙~」的原创文章，遵循 CC 4.0 BY-SA 版权协议，转载请附上原文出处链接及本声明。
 * 原文链接：https://blog.csdn.net/liujiahan629629/article/details/84312204
 * @date 2020/3/17 14:21
 */
public class LockSupportTest {
    public static Object obj = new Object();
    static ChangeObjectThread thread = new ChangeObjectThread("t1");
    static ChangeObjectThread thread1 = new ChangeObjectThread("t2");

    public static void main(String[] args) throws InterruptedException {
        thread.start();
        Thread.sleep(1000);
        thread1.start();
        //为给定的线程提供许可证（如果尚未提供）。
        LockSupport.unpark(thread);
        LockSupport.unpark(thread1);
        thread.join();
        thread1.join();
    }

    public static class ChangeObjectThread extends Thread {
        public ChangeObjectThread(String name) {
            super.setName(name);
        }

        @Override
        public void run() {
            synchronized (obj) {
                System.out.println("in" + getName());
                //禁止当前线程进行线程调度，除非许可证可用。
                LockSupport.park();
            }
        }
    }
}

