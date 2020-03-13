package com.lt.thread;

/**
 * @author Administrator
 * @description WaitNotifyThread
 * @date 2020/3/13 10:34
 */
public class WaitNotifyThread {
    final static Object obj = new Object();

    public static class T1 extends Thread {
        @Override
        public void run() {
            synchronized (obj) {
                System.out.println(System.currentTimeMillis() + "\tt1 is start");

                try {
                    System.out.println(System.currentTimeMillis() + "\tt1 is wait");
                    obj.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(System.currentTimeMillis() + "\tt1 is end");
            }
        }
    }

    public static class T2 extends Thread {
        @Override
        public void run() {
            synchronized (obj) {
                System.out.println(System.currentTimeMillis() + "\tt2 is start");


                System.out.println(System.currentTimeMillis() + "\tt2 is notify start");
                obj.notify();
                System.out.println(System.currentTimeMillis() + "\tt2 is notify end");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(System.currentTimeMillis() + "\tt2 is end");

            }
        }
    }

    /**
     * 等待（wait）和通知（notify），也是为了支持线程之间的协作。方法都是在Object上定义的，
     * 例如线程A调用了obj.wiat()方法，那么线程A就会停止执行，等到其它线程调用obj.notify()方法为止
     *
     * @param args
     * @throws InterruptedException
     */
    public static void main(String[] args) throws InterruptedException {
        T1 t1 = new T1();
        T2 t2 = new T2();
        t1.start();
//        Thread.sleep(200);
        t2.start();
    }
}
