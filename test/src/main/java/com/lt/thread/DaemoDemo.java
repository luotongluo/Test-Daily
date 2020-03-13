package com.lt.thread;

/**
 * @author Administrator
 * @description DaemoDemo
 * 守护线程（Daemon）:是一种特殊的线程，听名字猜想这种线程是系统的守护者。例如jvm的垃圾回收线程等。
 * 当一个Java应用中只有守护线程时，也就会自然退出
 * @date 2020/3/13 11:11
 */
public class DaemoDemo {
    public static class T1 extends Thread{
        @Override
        public void run() {
            while (true){
                System.out.println("i am alive ");
                try {
                    Thread.sleep(500);
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new T1();
        t1.setDaemon(true);
        t1.start();

        Thread.sleep(2000);
    }
}
