package com.lt.thread.basic;

import java.util.Vector;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Author: LT
 * @Date: 2019/11/5 18:11
 * @Description:
 * @Version 1.0
 */
public class VoliteTest {
    public int aaa = 0;
    Lock lock = new ReentrantLock();

    public void increase() {
        aaa++;
    }

    public void increaseLock() {
        try {
            lock.lock();
            aaa++;
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) throws Exception {
        final VoliteTest test = new VoliteTest();
        Vector<Thread> threads = new Vector<Thread>();
        for (int i = 0; i < 10; i++) {
            Thread thread = new Thread() {
                public void run() {
                    for (int j = 0; j < 1000; j++)
                        test.increase();
                }

            };
            threads.add(thread);
            thread.start();
        }
        System.out.println("========started=======");
        //保证前面的线程都执行完
        for (Thread t : threads) {
            /*
            线程的join方法是将线程的并行变为串行
             */
            System.out.println(t.getName() + ":" + t.getId() + ":" + t.getState());
            t.setName("wer");
            t.join();
            System.out.println(t.getName() + ":" + t.getId() + ":" + t.getState());
        }
        System.out.println(test.aaa);
    }
}
