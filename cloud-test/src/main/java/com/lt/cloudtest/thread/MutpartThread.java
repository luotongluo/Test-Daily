package com.lt.cloudtest.thread;

import com.lt.cloudtest.utils.FileUtils;
import com.lt.cloudtest.utils.ThreadUtil;

import java.util.concurrent.ThreadPoolExecutor;

/**
 * Created by tong.luo on 2019/8/11 0:35
 * 测试多线程中的futuretash
 */
public class MutpartThread {

    public static void main(String[] args) {
        long start = System.currentTimeMillis();

        ThreadUtil threadUtil = new ThreadUtil();
        ThreadPoolExecutor poolExecutor = threadUtil.init();

        int maxVal = 1000000;
//        int judgenum = 5;
        int threadNum = 5;
        StringBuffer buffer = new StringBuffer();
        for (int a = 0; a < maxVal; a++) {
            Integer singleNum = getSingleNum(a, threadNum);
//            for (int b = 0; b < judgenum; b++) {
//                if (singleNum % threadNum == b) {
//                    doassableMutliThread(poolExecutor, a);
//                }
//            }
            if (singleNum % threadNum == 0) {
                doassableMutliThread(poolExecutor, a, buffer);
            } else if (singleNum % threadNum == 1) {
                doassableMutliThread(poolExecutor, a, buffer);
            } else if (singleNum % threadNum == 2) {
                doassableMutliThread(poolExecutor, a, buffer);
            } else if (singleNum % threadNum == 3) {
                doassableMutliThread(poolExecutor, a, buffer);
            } else if (singleNum % threadNum == 4) {
                doassableMutliThread(poolExecutor, a, buffer);
            } else if (singleNum % threadNum == 5) {
                doassableMutliThread(poolExecutor, a, buffer);
            } else if (singleNum % threadNum == 6) {
                doassableMutliThread(poolExecutor, a, buffer);
            } else if (singleNum % threadNum == 7) {
                doassableMutliThread(poolExecutor, a, buffer);
            } else if (singleNum % threadNum == 8) {
                doassableMutliThread(poolExecutor, a, buffer);
            } else if (singleNum % threadNum == 9) {
                doassableMutliThread(poolExecutor, a, buffer);
            }

        }

        threadUtil.destory();
        FileUtils.writeFile("d:/", "test.txt", buffer.toString());

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
     * @param buffer
     */
    private static void doassableMutliThread(ThreadPoolExecutor poolExecutor, int a, StringBuffer buffer) {
        int finalA2 = a;
        poolExecutor.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(600);
                } catch (InterruptedException e) {
                    System.out.println("errror" + e);
                }
                buffer.append("name:" + Thread.currentThread().getName() +
                        "\t id:" + Thread.currentThread().getId() +
                        "\tstatus:" + Thread.currentThread().getState() +
                        "\tnum:" + finalA2 + "\n");
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
        if (num < 10) {
            return num;
        } else {
            getSingleNum(num, judgenum);
        }
        return null;
    }

}
