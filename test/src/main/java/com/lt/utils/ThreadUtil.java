package com.lt.utils;

import com.google.common.util.concurrent.ThreadFactoryBuilder;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by tong.luo on 2019/8/11 10:58
 */
public class ThreadUtil {
    private int minPoolSize = 5;
    private int maxPoolSize = Integer.MAX_VALUE;
    ThreadPoolExecutor poolExecutor = null;

    public ThreadUtil() {
    }

    public ThreadPoolExecutor init() {
        ThreadFactory threadFactory = new ThreadFactoryBuilder().setNameFormat("demo-pool-%d").build();
        //common thread pool
        poolExecutor = new ThreadPoolExecutor(5, Integer.MAX_VALUE, 0L,
                TimeUnit.MINUTES,
                new LinkedBlockingQueue<Runnable>(1024),
                threadFactory,
                new ThreadPoolExecutor.AbortPolicy());
        return poolExecutor;
    }

    /**
     * 关闭线程池
     */
    public void destory() {
        if (poolExecutor != null) {
            poolExecutor.shutdown();
        }

    }
}
