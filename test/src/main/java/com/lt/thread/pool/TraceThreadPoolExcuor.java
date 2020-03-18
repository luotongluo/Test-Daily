package com.lt.thread.pool;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author Administrator
 * @description TraceThreadPoolExcuor
 * @date 2020/3/18 17:49
 */
public class TraceThreadPoolExcuor extends ThreadPoolExecutor {
    public TraceThreadPoolExcuor(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue) {
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue);
    }

    public TraceThreadPoolExcuor(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue, ThreadFactory threadFactory) {
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, threadFactory);
    }

    public TraceThreadPoolExcuor(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue, RejectedExecutionHandler handler) {
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, handler);
    }

    public TraceThreadPoolExcuor(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue, ThreadFactory threadFactory, RejectedExecutionHandler handler) {
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, threadFactory, handler);
    }

    @Override
    public void execute(Runnable command) {
        super.execute(wrap(command, ljhTrace(), Thread.currentThread().getName()));
    }

    private Runnable wrap(final Runnable command, final Exception ljhTrace, String name) {
        return new Runnable() {
            @Override
            public void run() {
                try {
                    command.run();
                } catch (Exception e) {
                    ljhTrace.printStackTrace();
                    e.printStackTrace();
                }
            }
        };
    }

    private Exception ljhTrace() {
        return new Exception("ljhTrace Expection");
    }
}
