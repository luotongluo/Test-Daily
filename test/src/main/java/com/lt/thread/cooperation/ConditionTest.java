package com.lt.thread.cooperation;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author Administrator
 * @description ConditionTest
 * Condition条件：记得上篇博客中我们wait()、notify()等待和通知，Condition也可以实现类似的功能，
 * 配合锁进行使用。提供的方法await()、awaitUninterruptibly()、awaitNanos(long nanosTimeout)、
 * await(long time,TimeUnit unit)、signal()、signalAll()等，也很容易理解。这里提一下：
 * 生产者消费者模型中，如果库房慢了，则生产者await；如果库房没了，则消费者await；生产者生成一个则signal；
 * 消费者消费一个则signal。是不是非常实用。好看个简单例子：
 * ————————————————
 * 版权声明：本文为CSDN博主「~小龙~」的原创文章，遵循 CC 4.0 BY-SA 版权协议，转载请附上原文出处链接及本声明。
 * 原文链接：https://blog.csdn.net/liujiahan629629/article/details/84312204
 * @date 2020/3/13 15:24
 */
public class ConditionTest implements Runnable {
    public static ReentrantLock reentrantLock = new ReentrantLock();
    public static Condition condition = reentrantLock.newCondition();

    @Override
    public void run() {
        try {
            reentrantLock.lock();
            //导致当前线程等到发信号或 interrupted 。
            condition.await();
            System.out.println("Thread is going on " + System.currentTimeMillis());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            reentrantLock.unlock();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        long start = System.currentTimeMillis();
        System.out.println(start);
        ConditionTest test = new ConditionTest();
        Thread thread = new Thread(test);
        thread.start();
        Thread.sleep(2000);
        reentrantLock.lock();
        condition.signal();
        reentrantLock.unlock();
        System.out.println("cost:" + (System.currentTimeMillis() - start));
    }
}
