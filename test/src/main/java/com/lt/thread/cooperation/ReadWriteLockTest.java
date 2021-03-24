package com.lt.thread.cooperation;

import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author Administrator
 * @description ReadWriteLockTest
 * @date 2020/3/16 16:49
 */
public class ReadWriteLockTest {
    private static Lock lock = new ReentrantLock();
    private static ReentrantReadWriteLock reentrantReadWriteLock = new ReentrantReadWriteLock();
    private static Lock readLock = reentrantReadWriteLock.readLock();
    private static Lock writeLock = reentrantReadWriteLock.writeLock();
    private int value;

    public Object headleRead(Lock lock) throws InterruptedException {
        try {
            lock.lock();
            Thread.sleep(100);
            return value;
        } finally {
            lock.unlock();
        }

    }

    public void headleWite(Lock lock, int index) throws InterruptedException {
        try {
            lock.lock();
            Thread.sleep(100);
            value = index;
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        final ReadWriteLockTest readWriteLockTest = new ReadWriteLockTest();
        Runnable runnableRead = new Runnable() {

            @Override
            public void run() {
                try {
                    Object headleRead = readWriteLockTest.headleRead(readLock);
                    System.out.println("read :value " + headleRead);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
        Runnable runnableWrite = new Runnable() {

            @Override
            public void run() {
                try {
                    int nextInt = new Random().nextInt();
                    System.out.println("nextint:" + nextInt);
                    readWriteLockTest.headleWite(readLock, nextInt);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };


        for (int i = 0; i < 2; i++) {
            new Thread(runnableWrite).start();
        }
        for (int i = 0; i < 18; i++) {
            new Thread(runnableRead).start();
        }
    }
}
