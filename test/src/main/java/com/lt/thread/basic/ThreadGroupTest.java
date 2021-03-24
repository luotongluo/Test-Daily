package com.lt.thread.basic;

/**
 * @author Administrator
 * @description ThreadGroupTest
 * @date 2020/3/13 10:54
 */
public class ThreadGroupTest implements Runnable {
    @Override
    public void run() {
        String groupName = Thread.currentThread().getThreadGroup().getName() + "--"
                + Thread.currentThread().getName();
        while (true) {
            System.out.println("i am " + groupName);
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        ThreadGroup testGroup = new ThreadGroup("testGroup");
        Thread t1 = new Thread(testGroup, new ThreadGroupTest(), "T1");
        Thread t2 = new Thread(testGroup, new ThreadGroupTest(), "T2");
        t1.start();
        t2.start();
        System.out.println(testGroup.activeCount());
        testGroup.list();
    }
}
