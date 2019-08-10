package com.lt.thread;

import com.google.common.util.concurrent.ThreadFactoryBuilder;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by tong.luo on 2019/8/11 0:35
 * 测试多线程中的futuretash
 */
public class MutpartThread {

    public static void main(String[] args) {
        long start = System.currentTimeMillis();

        ThreadFactory threadFactory = new ThreadFactoryBuilder().setNameFormat("demo-pool-%d").build();

        //common thread pool
        ThreadPoolExecutor poolExecutor = new ThreadPoolExecutor(5, Integer.MAX_VALUE, 0L,
                TimeUnit.MINUTES,
                new LinkedBlockingQueue<Runnable>(1024),
                threadFactory,
                new ThreadPoolExecutor.AbortPolicy());

        int maxVal = 10000000;
//        int judgenum = 5;
        int threadNum = 10;
        for (int a = 0; a < maxVal; a++) {
            Integer singleNum = getSingleNum(a, threadNum);
//            for (int b = 0; b < judgenum; b++) {
//                if (singleNum % threadNum == b) {
//                    doassableMutliThread(poolExecutor, a);
//                }
//            }
            if (singleNum % threadNum == 0) {
                doassableMutliThread(poolExecutor, a);
            } else if (singleNum % threadNum == 1) {
                doassableMutliThread(poolExecutor, a);
            } else if (singleNum % threadNum == 2) {
                doassableMutliThread(poolExecutor, a);
            } else if (singleNum % threadNum == 3) {
                doassableMutliThread(poolExecutor, a);
            } else if (singleNum % threadNum == 4) {
                doassableMutliThread(poolExecutor, a);
            } else if (singleNum % threadNum == 5) {
                doassableMutliThread(poolExecutor, a);
            } else if (singleNum % threadNum == 6) {
                doassableMutliThread(poolExecutor, a);
            } else if (singleNum % threadNum == 7) {
                doassableMutliThread(poolExecutor, a);
            } else if (singleNum % threadNum == 8) {
                doassableMutliThread(poolExecutor, a);
            } else if (singleNum % threadNum == 9) {
                doassableMutliThread(poolExecutor, a);
            }

        }

        poolExecutor.shutdown();
        System.out.println(Thread.currentThread().getName() +
                "\tid :" + Thread.currentThread().getId() +
                "\t status:" + Thread.currentThread().getState());
        System.out.println("cost:" + (System.currentTimeMillis() - start));
    }

    /**
     * 多线程中的操作的方法
     *
     * @param poolExecutor
     * @param a
     */
    private static void doassableMutliThread(ThreadPoolExecutor poolExecutor, int a) {
        int finalA2 = a;
        poolExecutor.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(600);
                } catch (InterruptedException e) {
                    System.out.println("errror" + e);
                }
                System.out.println("name:" + Thread.currentThread().getName() +
                        "\t id:" + Thread.currentThread().getId() +
                        "\tstatus:" + Thread.currentThread().getState() +
                        "\tnum:" + finalA2
                );
            }
        });
    }

    /**
     * 获取个位数字上面得知
     *
     * @param n
     * @param judgenum
     * @return
     */
    public static Integer getSingleNum(int n, int judgenum) {
        int num = n % 10;
//        System.out.println("n: " + n + "\t judgenum" + judgenum
//                + "\t% 之后为:" + num);
        if (num < judgenum) {
            return num;
        } else {
            getSingleNum(num, judgenum);
        }
        return null;
    }

}
