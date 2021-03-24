package com.lt.thread.pool;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author tong.luo
 * @description MulitiPrit
 * @date 2020/12/6 22:53
 */
public class MulitiPrit {
    /**
     * 使用公平锁，防止一个线程连续获取锁的情况
     */
    private Lock lock = new ReentrantLock(true);
    // 计数
    private int COUNT = 0;
    // 循环次数
    private int LOOP_NUM = 3;
    // 计数取模
    private int MOD = 3;

    private void printChar(int threadIdentify) {
        for (int i = 0; i < LOOP_NUM; i++) {
            lock.lock();
            try {
                /**
                 * threadIdentify: 0 表示线程 a, 1 表示线程 b, 2 表示线程 c
                 */
                if (COUNT % MOD == threadIdentify) {
                    System.out.print(Thread.currentThread().getName());
                    COUNT++;
                }
            } finally {
                lock.unlock();
            }
        }
    }

    public static void main(String[] args) {
        MulitiPrit lockDemo = new MulitiPrit();
        Thread a = new Thread(() -> lockDemo.printChar(0), "A");
        Thread b = new Thread(() -> lockDemo.printChar(1), "B");
        Thread c = new Thread(() -> lockDemo.printChar(2), "C");

        a.start();
        b.start();
        c.start();
    }
}
