package com.lt.thread.cooperation;

import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author Administrator
 * @description CountDownLatchTest
 * 主线等待一组子线程执行完毕再进行业务的处理
 * @date 2020/3/17 9:30
 */
public class CountDownLatchTest implements Runnable {
    static final CountDownLatch COUNT_DOWN_LATCH = new CountDownLatch(10);
    static final CountDownLatchTest COUNT_DOWN_LATCH_TEST = new CountDownLatchTest();

    @Override
    public void run() {
        try {
            Thread.sleep(new Random().nextInt(10) * 100);
            System.out.println("check complete" + Thread.currentThread().getName());
        } catch (Exception e) {
            COUNT_DOWN_LATCH.countDown();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(10);

        for (int a = 0; a < 10; a++) {
            executorService.execute(COUNT_DOWN_LATCH_TEST);
        }
        COUNT_DOWN_LATCH.await();
        System.out.println("all complete");
        executorService.shutdown();
    }
}
