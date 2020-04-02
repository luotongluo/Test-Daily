package com.lt.thread;

import com.alibaba.fastjson.JSON;
import org.springframework.util.CollectionUtils;

import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author Administrator
 * @description ThreadLocalTest
 * @date 2020/3/20 14:17
 */
public class ThreadLocalTest {
    private static String famtter = "yyyy-MM-dd HH:mm:ss";
    //线程不安全 加锁控制
    private static final SimpleDateFormat SIMPLE_DATE_FORMAT = new SimpleDateFormat(famtter);
    //使用threadLocal容器
    private static ThreadLocal<SimpleDateFormat> threadLocal = new ThreadLocal<SimpleDateFormat>();

    public static class ParseDate implements Runnable {
        int n = 0;

        public ParseDate(int n) {
            this.n = n;
        }

        @Override
        public void run() {
            try {
                ////线程不安全
//                Date t = SIMPLE_DATE_FORMAT.parse(famtter);

                if (threadLocal.get() == null) {
                    threadLocal.set(new SimpleDateFormat(famtter));
                }
                Date t = threadLocal.get().parse("2018-12-09 12:29:" + n % 60);
//                Object o = threadLocal.get();
                System.out.println(n + ":" + t);

            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }

    public static void main(String[] args) throws InterruptedException {
        int e = 0;
        ExecutorService es = Executors.newFixedThreadPool(10);

        for (int i = 0; i < 1000; i++) {
            es.execute(new ParseDate(i));
        }
        List<Runnable> runnables = es.shutdownNow();
        System.out.println(JSON.toJSONString(runnables));
        es.shutdown();
        while (true) {

            System.out.println("i:" + e);
            e = ++e;
            List<Runnable> runnables1 = es.shutdownNow();
            System.out.println(JSON.toJSONString(runnables1));
            if (CollectionUtils.isEmpty(runnables)) {
                es.shutdown();
            }
            Thread.sleep(100);
        }

    }
}
