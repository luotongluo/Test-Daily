package com.lt.commontest;

import java.util.concurrent.CountDownLatch;

/**
 * @Author: LT
 * @Date: 2020/1/5 16:16
 * @Description:
 * @Version 1.0
 */
public class ThreadTest {
    public static void main(String[] args) throws InterruptedException {
        Foo2 foo = new Foo2();
        foo.first(() -> {
            System.out.println(1 + Thread.currentThread().getName());
        });
        foo.second(() -> {
            System.out.println(2 + Thread.currentThread().getName());
        });
        foo.third(() -> {
            System.out.println(3 + Thread.currentThread().getName());
        });
    }
}

/**
 * 使用volatile 关键 字
 */
class Foo2 {

    public Foo2() {

    }

    volatile int count = 1;

    public void first(Runnable printFirst) throws InterruptedException {
        printFirst.run();
        count++;
    }

    public void second(Runnable printSecond) throws InterruptedException {
        while (count != 2) ;
        printSecond.run();
        count++;
    }

    public void third(Runnable printThird) throws InterruptedException {
        while (count != 3) ;
        printThird.run();
    }
}

/**
 * 使用lock锁关键 字
 */
class Foo1 {

    private boolean firstFinished;
    private boolean secondFinished;
    private Object lock = new Object();

    public Foo1() {
        System.out.println("Foo1");
    }

    public void first(Runnable printFirst) throws InterruptedException {

        synchronized (lock) {
            // printFirst.run() outputs "first". Do not change or remove this line.
            printFirst.run();
            System.out.println("first");
            firstFinished = true;
            lock.notifyAll();
        }
    }

    public void second(Runnable printSecond) throws InterruptedException {

        synchronized (lock) {
            while (!firstFinished) {
                lock.wait();
            }

            // printSecond.run() outputs "second". Do not change or remove this line.
            printSecond.run();
            System.out.println("second");
            secondFinished = true;
            lock.notifyAll();
        }
    }

    public void third(Runnable printThird) throws InterruptedException {

        synchronized (lock) {
            while (!secondFinished) {
                lock.wait();
            }

            // printThird.run() outputs "third". Do not change or remove this line.
            printThird.run();
            System.out.println("third");
        }
    }
}

/**
 * 使用CountDownLatch
 */
class Foo {
    private CountDownLatch c2;
    private CountDownLatch c3;

    public Foo() {
        c2 = new CountDownLatch(1);
        c3 = new CountDownLatch(1);
    }

    public void first(Runnable printFirst) throws InterruptedException {

        // printFirst.run() outputs "first". Do not change or remove this line.
        printFirst.run();
        System.out.println("first");
        c2.countDown();
    }

    public void second(Runnable printSecond) throws InterruptedException {
        c2.await();
        // printSecond.run() outputs "second". Do not change or remove this line.
        printSecond.run();
        System.out.println("second");
        c3.countDown();
    }

    public void third(Runnable printThird) throws InterruptedException {
        c3.await();
        // printThird.run() outputs "third". Do not change or remove this line.
        printThird.run();
        System.out.println("third");
    }
}

