package com.lt.thread.cooperation;

import java.util.concurrent.CyclicBarrier;

/**
 * @author Administrator
 * @description CyclicBarrierTest
 * CyclicBarrier循环栅栏：增强版CountDownLatch，但是是可以循环使用。这里看个经典例子：司令下达命令，10个士兵一起去完成一项任务：
 * 10个士兵首先集合报道，然后去执行任务，执行完了再汇报司令，司令宣布完成任务。相当于计数了两次，循环使用了
 * @date 2020/3/17 13:33
 */
public class CyclicBarrierTest {
    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        final int n = 10;
        Thread[] threads = new Thread[10];
        boolean flag = false;
        CyclicBarrier cyclicBarrier = new CyclicBarrier(n, new BarrerRun(flag, n));
        System.out.println("jihe ");
        for (int i = 0; i < n; i++) {
            System.out.println(i + "solider come ");
            threads[i] = new Thread(new Solider("solider" + n, cyclicBarrier));
            threads[i].start();
        }
        System.out.println("cost:" + (System.currentTimeMillis() - start));
    }
}

class Solider implements Runnable {
    private String solider;
    private final CyclicBarrier cyclicBarrier;

    public Solider(String solider, CyclicBarrier cyclicBarrier) {
        this.solider = solider;
        this.cyclicBarrier = cyclicBarrier;
    }

    @Override
    public void run() {
        try {
            //等待所有 parties已经在这个障碍上调用了 await 。
            cyclicBarrier.await();
            doWork();
            cyclicBarrier.await();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {

        }
    }

    private void doWork() {
        try {
            Thread.sleep(100);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {

        }
        System.out.println(solider + "do work");
    }
}

class BarrerRun implements Runnable {
    boolean flag;
    int n;

    public BarrerRun(boolean flag, int n) {
        this.flag = flag;
        this.n = n;
    }

    @Override
    public void run() {
        if (flag) {
            System.out.println("soldier" + n + "个，do work");
        } else {
            System.out.println("soldier" + n + "个，集合完毕");
            flag = true;
        }
    }
}

