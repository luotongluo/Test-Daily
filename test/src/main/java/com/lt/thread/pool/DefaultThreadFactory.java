package com.lt.thread.pool;

import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author Administrator
 * @description DefaultThreadFactory
 * @date 2020/3/17 17:27
 */
public class DefaultThreadFactory implements ThreadFactory {
    /*
    一个int可能原子更新的值。 有关原子变量属性的描述，请参阅java.util.concurrent.atomic包规范。
    一个AtomicInteger用于诸如原子增量计数器的应用程序中，不能用作Integer的替代品 。
    但是，这个类确实扩展了Number以允许通过处理基于数字类的工具和实用程序的统一访问
     */
    private final AtomicInteger atomicInteger = new AtomicInteger(1);
    private final AtomicInteger threadNum = new AtomicInteger(1);
    private final ThreadGroup threadGroup;
    private final String namePrefix;

    public DefaultThreadFactory() {
        //SecurityManager类包含许多方法，名称以check
        //果允许访问请求， checkPermission安静地返回。 如果被拒绝，则会抛出一个SecurityException 。
        SecurityManager manager = System.getSecurityManager();
        threadGroup = (manager != null) ? manager.getThreadGroup() :
                Thread.currentThread().getThreadGroup();
        namePrefix = "pool-" +
                threadNum.getAndIncrement() +
                "-thread-";
    }

    @Override
    public Thread newThread(Runnable r) {
        Thread thread = new Thread(threadGroup, r, namePrefix + threadNum.getAndIncrement(), 0);
        //测试这个线程是否是守护线程。
        if (thread.isDaemon()) {
            thread.setDaemon(false);
        }
        //返回此线程的优先级。
        if (thread.getPriority() != Thread.NORM_PRIORITY) {
            //分配给线程的默认优先级。
            thread.setPriority(Thread.NORM_PRIORITY);
        }
        return thread;
    }
}

/**
 * 自定义类
 */
class ThreadFactoryDemo {
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
        FixThreadPoolDemo.MyTask myTask = new FixThreadPoolDemo.MyTask();

        ThreadPoolExecutor executor = new ThreadPoolExecutor(5, 10, 0L, TimeUnit.SECONDS,
                new SynchronousQueue<Runnable>(), new ThreadFactory() {
            @Override
            public Thread newThread(Runnable r) {
                Thread t = new Thread(r);
                t.setDaemon(true);
                System.out.println("create thread" + t.getName());
                return t;
            }
        });

        for (int i = 0; i < 10; i++) {
            executor.execute(myTask);
        }
    }
}
