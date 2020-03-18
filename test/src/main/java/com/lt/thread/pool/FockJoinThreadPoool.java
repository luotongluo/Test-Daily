package com.lt.thread.pool;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

/**
 * @author Administrator
 * @description FockJoinThreadPoool
 * Fork/Join：大家都知道hadoop中的Map-Reduce分开处理，合并结果；当今流行的分布式，
 * 将用户的请求分散处理等等。分而治之是非常有用实用的。JDK帮我们提供了ForkJoinPool线程池，
 * 供我们做这些处理，有两个子类供我们使用，Recursive有返回值，RecursiveAction无返回值
 * ————————————————
 * 版权声明：本文为CSDN博主「~小龙~」的原创文章，遵循 CC 4.0 BY-SA 版权协议，转载请附上原文出处链接及本声明。
 * 原文链接：https://blog.csdn.net/liujiahan629629/article/details/84454908
 * @date 2020/3/18 17:56
 */
public class FockJoinThreadPoool extends RecursiveTask<Long> {
    private static final int THRESHOLD = 10000;
    private long start;
    private long end;

    public FockJoinThreadPoool(long start, long end) {
        this.start = start;
        this.end = end;
    }

    @Override
    protected Long compute() {
        long sum = 0;
        boolean canCompute = (end - start) < THRESHOLD;
        if (canCompute) {
            for (long i = start; i < end; i++) {
                sum += i;
            }
        } else {
            //分成100份进行处理
            long step = (start + end) / 50;
            ArrayList<FockJoinThreadPoool> subTasks = new ArrayList<FockJoinThreadPoool>();
            long pos = start;
            for (int i = 0; i < 50; i++) {
                long lastOne = pos + step;
                if (lastOne > end) {
                    lastOne = end;
                }
                FockJoinThreadPoool subTask = new FockJoinThreadPoool(pos, lastOne);
                pos += step;
                subTasks.add(subTask);
                subTask.fork();
            }
            for (FockJoinThreadPoool t : subTasks) {
                sum += t.join();
            }
        }

        return sum;
    }

    public static void main(String[] args) {
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        FockJoinThreadPoool task = new FockJoinThreadPoool(0, 20000);
        ForkJoinTask<Long> result = forkJoinPool.submit(task);

        try {
            long res = result.get();
            System.out.println("结果" + res);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}
