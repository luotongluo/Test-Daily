package com.lt.thread.cooperation;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author Administrator
 * @description ReentrantLockTest
 * 1,ReentrantLock例子
 * @date 2020/3/13 14:12
 */
public class ReentrantLockTest implements Runnable {
    public static ReentrantLock reentrantLock = new ReentrantLock();

    public static int i = 0;

    @Override
    public void run() {
        for (int j = 0; j < 100; j++) {
            reentrantLock.lock();
            try {
                i++;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ReentrantLockTest lockTest = new ReentrantLockTest();
        Thread thread = new Thread(lockTest);
        Thread thread1 = new Thread(lockTest);

        thread.start();
        thread1.start();
        thread.join();
        thread1.join();
        System.out.println(i);
    }
}

//2，lock1.lockInterruptibly()中断后可放弃
class InterruptLock implements Runnable {
    public static ReentrantLock lock2 = new ReentrantLock();
    public static ReentrantLock lock1 = new ReentrantLock();

    int lock;

    public InterruptLock(int lock) {
        this.lock = lock;
    }

    @Override
    public void run() {
        try {
            if (lock == 1) {
                //获取锁定，除非当前线程是 interrupted 。
                lock1.lockInterruptibly();
                Thread.sleep(500);
                lock2.lockInterruptibly();
            } else {
                lock2.lockInterruptibly();
                Thread.sleep(500);
                lock1.lockInterruptibly();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //判断该线程是否是锁定的状态
            if (lock1.isHeldByCurrentThread()) {
                lock1.unlock();
            }
            if (lock2.isHeldByCurrentThread()) {
                lock2.unlock();
            }
            System.out.println(Thread.currentThread().getName() + "线程退出");
        }

    }

    public static void main(String[] args) throws InterruptedException {
        InterruptLock interruptLock = new InterruptLock(1);
        InterruptLock interruptLock1 = new InterruptLock(2);
        Thread thread = new Thread(interruptLock);
        Thread thread1 = new Thread(interruptLock1);

        thread.start();
        thread1.start();

        Thread.sleep(1000);
        thread1.interrupt();
    }
}

//3，申请等待时间例子：如果直接使用tryLock()如果拿不到则直接返回，不会等待
class TimeLock implements Runnable {
    public static ReentrantLock lock = new ReentrantLock();

    @Override
    public void run() {
        try {
            if (lock.tryLock(2, TimeUnit.SECONDS)) {
                System.out.println(Thread.currentThread().getName() + "get lock success!");
                Thread.sleep(6000);
            } else {
                System.out.println(Thread.currentThread().getName() + "get lock failed!");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            if (lock.isHeldByCurrentThread()) {
                lock.unlock();
            }
        }
    }

    public static void main(String[] args) {
        TimeLock timeLock = new TimeLock();
        Thread thread = new Thread(timeLock);
        Thread thread1 = new Thread(timeLock);
        thread.start();
        thread1.start();
    }
}

//4,公平锁，先到先得
class FileLock implements Runnable {
    //根据给定的公平政策创建一个 ReentrantLock的实例
    public static ReentrantLock lock = new ReentrantLock(true);

    @Override
    public void run() {
        while (true) {
            try {
                lock.lock();
                System.out.println(Thread.currentThread().getName() + " get lock");
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (lock.isHeldByCurrentThread()) {
                    lock.unlock();
                }
            }
        }
    }

    public static void main(String[] args) {
        FileLock fairlock = new FileLock();
        Thread thread1 = new Thread(fairlock, "thread1");
        Thread thread2 = new Thread(fairlock, "thread2");
        thread1.start();
        thread2.start();
    }
}
