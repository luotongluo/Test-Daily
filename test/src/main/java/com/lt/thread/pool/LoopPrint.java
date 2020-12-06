package com.lt.thread.pool;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author tong.luo
 * @description LoopPrint
 * T1线程输出都是A，T2线程输出的都是B，T3线程输出的都是C要求三个线程启动后输出顺序：ABCABCABC
 * @date 2020/12/6 22:29
 */
public class LoopPrint {
    private int number = 1;
    private Lock lock = new ReentrantLock();
    Condition conA = lock.newCondition();
    Condition conB = lock.newCondition();
    Condition conC = lock.newCondition();

    public void loapAA(int loop) {
        lock.lock();
        try {
            //判断
            while (number != 1) {
                conA.await();
            }
            //干活
//            for (int i = 1; i <= 5; i++) {
                System.out.print(Thread.currentThread().getName() + "\t" + "AA" + "\t当前是第：" + loop + "轮\n");
//            }
            //线程的唤醒+改标记
            number = 2;
            conB.signal();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }

    }

    public void loapBB(int loop) {
        lock.lock();
        try {
            //判断
            while (number != 1) {
                conB.await();
            }
            //干活
//            for (int i = 1; i <= 5; i++) {
                System.out.print(Thread.currentThread().getName() + "\t" + "BB" + "\t当前是第：" + loop + "轮\n");
//            }
            //线程的唤醒+改标记
            number = 2;
            conC.signal();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void loapCC(int loop) {
        lock.lock();
        try {
            //判断
            while (number != 1) {
                conC.await();
            }
            //干活
//            for (int i = 1; i <= 5; i++) {
                System.out.print(Thread.currentThread().getName() + "\t" + "CC" + "\t当前是第：" + loop + "轮\n");
//            }
            //线程的唤醒+改标记
            number = 2;
            conA.signal();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        final LoopPrint t = new LoopPrint();
        int loop = 1;
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 1; i <= loop; i++) {
                    t.loapAA(i);
                }
            }
        }, "AA").start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 1; i <= loop; i++) {
                    t.loapBB(i);
                }
            }
        }, "BB").start();
//
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 1; i <= loop; i++) {
                    t.loapCC(i);
                }
            }
        }, "CC").start();
    }
}
