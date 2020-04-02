package com.lt.thread.pool;

import com.alibaba.fastjson.JSON;

import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;

/**
 * @author Administrator
 * @description ExceptionThreadPoolDemo
 * 线程池中的堆栈信息
 * @date 2020/3/18 17:32
 */
public class ExceptionThreadPoolDemo {
    public static void main(String[] args) throws InterruptedException {
        TraceThreadPoolExcuor executor = new TraceThreadPoolExcuor(8, 20, 0L,
                TimeUnit.SECONDS, new ArrayBlockingQueue(10));

        for (int i = 0; i < 10; i++) {
            executor.execute(new myTask(100, i));
        }
        //尝试停止所有主动执行的任务，停止等待任务的处理，并返回正在等待执行的任务列表。
        List<Runnable> runnables = executor.shutdownNow();
        System.out.println(JSON.toJSONString(runnables));
        //如果所有任务在关闭后完成，则返回true。
        // 请注意， isTerminated从不true ，除非shutdown或shutdownNow被称为第一。
        boolean terminated = executor.isTerminated();
        System.out.println("isTerminated" + terminated);
        if(terminated){
            /*
            shutdown()方法将允许先前提交的任务在终止之前执行，而shutdownNow()
            方法可以防止等待任务启动并尝试停止当前正在执行的任务。 一旦终止，执行者没有任务正在执行，
            没有任务正在等待执行，并且不能提交新的任务。 应关闭未使用的ExecutorService以允许资源的回收。
             */
            executor.shutdown();
        }

    }

    public static class myTask implements Runnable {
        int a, b;

        public myTask(int a, int b) {
            this.a = a;
            this.b = b;
        }

        @Override
        public void run() {
//            if (b == 0) {
//                System.out.println("a : " + a + " b:" + b);
//
//            } else {
                double re = a / b;
                System.out.println("res : " + re);
//            }
        }
    }
}
