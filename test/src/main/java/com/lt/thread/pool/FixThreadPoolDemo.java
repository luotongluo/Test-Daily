package com.lt.thread.pool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @author Administrator
 * @description FixThreadPoolDemo
 * @date 2020/3/17 16:26
 */
public class FixThreadPoolDemo {
    public static class MyTask implements Runnable {

        @Override
        public void run() {
            System.out.println(System.currentTimeMillis() + "Thread Name:" + Thread.currentThread().getName());
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        MyTask myTask = new MyTask();
        int size = 5;
        ExecutorService executorService = Executors.newFixedThreadPool(size);
        for (int i = 0; i < 10; i++) {
            executorService.submit(myTask);
        }
        Thread.interrupted();
    }
}

class ScheduledThreadPoolDemo {
    public static void main(String[] args) {
        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(5);
        //创建并执行在给定的初始延迟之后，随后以给定的时间段首先启用的周期性动作; 那就是执行将在initialDelay之后开始，
        // 然后initialDelay+period ，然后是initialDelay + 2 * period等等
        scheduledExecutorService.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000);
                    System.out.println("当前时间：" + System.currentTimeMillis()/1000);
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {

                }
            }
        }, 0, 2, TimeUnit.SECONDS);
    }
}
