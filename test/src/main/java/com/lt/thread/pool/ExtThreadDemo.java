package com.lt.thread.pool;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author Administrator
 * @description ExtThreadDemo
 * 线程池的扩展：JDK已经对线程池做了非常好的编写，如果我们想扩展怎么办呢？ThreadPoolExecutor提供了三个方法供我们使用：
 * beforeExecute()每个线程执行前，afterExecute()每个线程执行后，terminated()线程池退出时。
 * @date 2020/3/18 16:03
 */
public class ExtThreadDemo {
    public static class MyTask implements Runnable {

        @Override
        public void run() {
            System.out.println(System.currentTimeMillis() + " Thread Name: " + Thread.currentThread().getName());

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ExtThreadDemo.MyTask myTask = new ExtThreadDemo.MyTask();
        ThreadPoolExecutor executor = new ThreadPoolExecutor(5, 10, 0L,
                TimeUnit.SECONDS, new LinkedBlockingQueue(16)) {
            @Override
            protected void beforeExecute(Thread t, Runnable r) {
                System.out.println("准备执行线程： " + r.toString() + "===" + t.getName());
            }

            @Override
            protected void afterExecute(Runnable r, Throwable t) {
                System.out.println("执行完成线程：" + r.toString());
            }

            @Override
            protected void terminated() {
                System.out.println("线程池退出");
            }
        };

        for (int i = 0; i < 100; i++) {
            executor.execute(myTask);
        }
        Thread.sleep(3000);
        executor.shutdown();
    }
}
