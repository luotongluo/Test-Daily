package com.lt.thread.cooperation;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * @author Administrator
 * @description SemaphoreTest
 * Semaphore 信号量：这个也挺容易理解的。无论是synchronized还是lock都是一次只能一个线程获取资源，
 * 而信号量可以多个同时访问。其中方法有：acquire()、acquireUninterruptibly()、tryAcquire()、
 * tryAcquire(long timeout,TimeUnit unit)、release()等
 * ————————————————
 * 版权声明：本文为CSDN博主「~小龙~」的原创文章，遵循 CC 4.0 BY-SA 版权协议，转载请附上原文出处链接及本声明。
 * 原文链接：https://blog.csdn.net/liujiahan629629/article/details/84312204
 * @date 2020/3/16 10:16
 */
public class SemaphoreTest implements Runnable {
    final Semaphore semaphore = new Semaphore(5);

    @Override
    public void run() {
        try {
            //从该信号量获取许可证，阻止直到可用，或线程为 interrupted 。
            semaphore.acquire();
            Thread.sleep(2000);
            System.out.println(Thread.currentThread().getName() + "done");
            //释放许可证，将其返回到信号量。
            semaphore.release();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {

        }
    }

    public static void main(String[] args) {
        ExecutorService es = Executors.newFixedThreadPool(10);

        final SemaphoreTest semaphoreTest = new SemaphoreTest();
        for (int a = 0; a < 20; a++) {
            es.execute(semaphoreTest);
        }
    }
}
